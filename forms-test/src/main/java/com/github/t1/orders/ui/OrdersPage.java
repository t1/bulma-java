package com.github.t1.orders.ui;

import com.github.t1.htmljava.Renderable;
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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static com.github.t1.bulmajava.components.Card.card;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.htmljava.HtmlBasics.p;
import static com.github.t1.htmljava.HtmlBasics.small;
import static jakarta.ws.rs.core.MediaType.TEXT_HTML;
import static java.time.format.FormatStyle.SHORT;

@Provider
@Produces(TEXT_HTML)
@RequiredArgsConstructor
public class OrdersPage implements MessageBodyWriter<List<Order>> {
    private final Page page;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return genericType instanceof ParameterizedType p &&
               p.getActualTypeArguments()[0].equals(Order.class);
    }

    @Override
    public void writeTo(List<Order> orders, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) {
        page.title("Orders")
                .content(orders.stream().map(this::map))
                .render(entityStream);
    }

    private Renderable map(Order order) {
        return card()
                .header(
                        p("Order " + order.getId()),
                        button().ariaLabel("order details").icon("angle-right")
                                .onclick("window.location.href='/orders/" + order.getId() + "'"))
                .content(
                        p().content(page.formatted(SHORT, order.getOrderDate())),
                        p().content(small(order.getCustomer().getName())));
    }
}
