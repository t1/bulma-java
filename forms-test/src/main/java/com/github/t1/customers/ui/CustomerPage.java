package com.github.t1.customers.ui;

import com.github.t1.bulmajava.elements.ImageSize;
import com.github.t1.customers.Customer;
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

import static jakarta.ws.rs.core.MediaType.TEXT_HTML;

@Provider
@Produces(TEXT_HTML)
@RequiredArgsConstructor
public class CustomerPage implements MessageBodyWriter<Customer> {
    private final Page page;
    private final Customer$Form form;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Customer customer, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) {
        page.title(customer.getName())
                .content(form.content(customer))
                .render(entityStream);
    }

    static String customerImage(ImageSize size) {
        return "https://bulma.io/assets/images/placeholders/" + size.key() + ".png";
    }
}
