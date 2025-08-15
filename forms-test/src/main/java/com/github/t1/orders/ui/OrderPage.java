package com.github.t1.orders.ui;

import com.github.t1.orders.Order;
import com.github.t1.ui.Page;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import lombok.RequiredArgsConstructor;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static com.github.t1.htmljava.HtmlBasics.p;
import static jakarta.ws.rs.core.MediaType.TEXT_HTML;
import static java.time.format.FormatStyle.MEDIUM;

@Provider
@Produces(TEXT_HTML)
@RequiredArgsConstructor
public class OrderPage implements MessageBodyWriter<Order> {
    private final Page page;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Order order, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) {
        page.title("Order " + order.getId()).content(
                        p("Order Date:").content(page.formatted(MEDIUM, order.getOrderDate())),
                        p("Customer Email:").content(order.getCustomer().getEmail()))
                .render(entityStream);
    }
}
