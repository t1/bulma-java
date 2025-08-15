package com.github.t1.products.ui;

import com.github.t1.htmljava.Renderable;
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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static com.github.t1.bulmajava.basic.SizeModifier.size;
import static com.github.t1.bulmajava.components.Card.card;
import static com.github.t1.bulmajava.elements.Image.image;
import static com.github.t1.bulmajava.elements.ImageRatio._4by3;
import static com.github.t1.bulmajava.elements.ImageSize._48x48;
import static com.github.t1.bulmajava.elements.Title.subtitleP;
import static com.github.t1.bulmajava.elements.Title.titleP;
import static com.github.t1.bulmajava.form.Field.control;
import static com.github.t1.bulmajava.grid.Grid.cell;
import static com.github.t1.bulmajava.grid.Grid.grid;
import static com.github.t1.bulmajava.layout.Media.media;
import static com.github.t1.htmljava.Renderable.RenderableString.string;
import static jakarta.ws.rs.core.MediaType.TEXT_HTML;

@Provider
@Produces(TEXT_HTML)
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ProductsPage implements MessageBodyWriter<List<Product>> {
    private final Page page;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return genericType instanceof ParameterizedType p &&
               p.getActualTypeArguments()[0].equals(Product.class);
    }

    @Override
    public void writeTo(List<Product> products, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) {
        page.title("Products")
                .content(grid().content(products.stream().map(this::map)))
                .render(entityStream);
    }

    private Renderable map(Product product) {
        return cell().content(card().style("width: 260px;")
                .onclick("window.location.href='/products/" + product.getId() + "'")
                .image(image(_4by3, productImage("1280x960"), "Placeholder image"))
                .content(
                        media()
                                .left(image(_48x48, productImage("96x96"), "Small image"))
                                .content(titleP(product.getName()).is(size(4)),
                                        subtitleP(product.getPrice() + " €").is(size(6))),
                        control().content(string(product.getDescription()))));
    }

    static String productImage(String size) {
        return "https://bulma.io/assets/images/placeholders/" + size + ".png";
    }
}
