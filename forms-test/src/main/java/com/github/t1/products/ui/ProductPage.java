package com.github.t1.products.ui;

import com.github.t1.products.Product;
import com.github.t1.ui.Page;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import lombok.RequiredArgsConstructor;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static com.github.t1.bulmajava.elements.Image.figure;
import static com.github.t1.bulmajava.elements.Image.imageP;
import static com.github.t1.bulmajava.elements.Image.img;
import static com.github.t1.bulmajava.elements.ImageSize._128x128;
import static com.github.t1.bulmajava.elements.ImageSize._64x64;
import static com.github.t1.bulmajava.layout.Media.media;
import static com.github.t1.htmljava.HtmlBasics.p;
import static com.github.t1.htmljava.HtmlBasics.small;
import static com.github.t1.products.ui.ProductsPage.productImage;
import static jakarta.ws.rs.core.MediaType.TEXT_HTML;

@Provider
@Produces(TEXT_HTML)
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ProductPage implements MessageBodyWriter<Product> {
    private final Page page;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Product product, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) {
        page.title(product.getName())
                .content(media()
                        .left(figure().content(imageP(_64x64).content(
                                img(productImage(_128x128.key()), product.getName()))))
                        .content(
                                p().content(small(product.getPrice() + " €")),
                                p().content(small(product.getDescription()))))
                .render(entityStream);
    }
}
