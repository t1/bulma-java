package com.github.t1.customers.ui;

import com.github.t1.customers.Customer;
import com.github.t1.htmljava.Renderable;
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

import static com.github.t1.bulmajava.elements.Image.figure;
import static com.github.t1.bulmajava.elements.Image.imageP;
import static com.github.t1.bulmajava.elements.Image.img;
import static com.github.t1.bulmajava.elements.ImageSize._128x128;
import static com.github.t1.bulmajava.elements.ImageSize._64x64;
import static com.github.t1.bulmajava.layout.Media.media;
import static com.github.t1.customers.ui.CustomerPage.customerImage;
import static com.github.t1.htmljava.HtmlBasics.p;
import static com.github.t1.htmljava.HtmlBasics.small;
import static com.github.t1.htmljava.HtmlBasics.strong;
import static jakarta.ws.rs.core.MediaType.TEXT_HTML;

@Provider
@Produces(TEXT_HTML)
@RequiredArgsConstructor
public class CustomersPage implements MessageBodyWriter<List<Customer>> {
    private final Page page;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return genericType instanceof ParameterizedType p &&
               p.getActualTypeArguments()[0].equals(Customer.class);
    }

    @Override
    public void writeTo(List<Customer> customers, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) {
        page.title("Customers")
                .content(customers.stream().map(this::map))
                .render(entityStream);
    }

    private Renderable map(Customer customer) {
        return media()
                .onclick("window.location.href='/customers/" + customer.getId() + "'")
                .left(figure().content(imageP(_64x64).content(
                        img(customerImage(_128x128), customer.getName()))))
                .content(
                        p().content(strong(customer.getName())),
                        p().content(small(customer.getEmail())));
    }
}
