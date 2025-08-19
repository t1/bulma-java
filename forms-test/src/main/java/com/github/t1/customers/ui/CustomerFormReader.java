package com.github.t1.customers.ui;

import com.github.t1.customers.Customer;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.Providers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED_TYPE;

@Provider
@Consumes(APPLICATION_FORM_URLENCODED)
public class CustomerFormReader implements MessageBodyReader<Customer> {
    @Context
    Providers providers;

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Customer.class.isAssignableFrom(type)
               && mediaType != null
               && mediaType.isCompatible(APPLICATION_FORM_URLENCODED_TYPE);
    }

    @Override
    public Customer readFrom(Class<Customer> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException {
        var map = providers.getMessageBodyReader(Form.class, Form.class, annotations, mediaType)
                .readFrom(Form.class, Form.class, annotations, mediaType, httpHeaders, entityStream)
                .asMap();

        return Customer.builder()
                .id(asLong(map.getFirst("id")))
                .name(map.getFirst("name"))
                .email(map.getFirst("email"))
                .supportLevel(Customer.Level.of(map.getFirst("supportLevel")))
                .build();
    }

    private static Long asLong(String idStr) {
        return (idStr == null || idStr.isBlank()) ? null : Long.valueOf(idStr);
    }
}
