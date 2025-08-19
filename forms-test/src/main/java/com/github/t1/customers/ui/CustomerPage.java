package com.github.t1.customers.ui;

import com.github.t1.bulmajava.elements.Button;
import com.github.t1.bulmajava.elements.ImageSize;
import com.github.t1.bulmajava.form.Field;
import com.github.t1.bulmajava.form.Form;
import com.github.t1.customers.Customer;
import com.github.t1.htmljava.Anchor;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Renderable;
import com.github.t1.ui.Mode;
import com.github.t1.ui.Page;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.BulmaElement.PULLED_RIGHT;
import static com.github.t1.bulmajava.basic.Color.DANGER;
import static com.github.t1.bulmajava.basic.Color.LINK;
import static com.github.t1.bulmajava.basic.Color.PRIMARY;
import static com.github.t1.bulmajava.elements.Button.BUTTON;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Button.buttonsAddon;
import static com.github.t1.bulmajava.form.Field.fieldName;
import static com.github.t1.bulmajava.form.Form.form;
import static com.github.t1.bulmajava.form.Input.submit;
import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.ui.Mode.CREATE;
import static com.github.t1.ui.Mode.EDIT;
import static com.github.t1.ui.Mode.VIEW;
import static jakarta.ws.rs.core.MediaType.TEXT_HTML;
import static java.util.function.Predicate.not;

@Path("/customers")
@Provider
@Produces(TEXT_HTML)
public class CustomerPage implements MessageBodyWriter<Customer> {
    private final Page page;
    private final UriInfo uriInfo;
    private final Customer$Form form;

    @Inject
    public CustomerPage(Page page, UriInfo uriInfo, Customer$Form form) {
        this.page = page;
        this.uriInfo = uriInfo;
        this.form = form;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Customer customer, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) {
        page.title(customer.getName())
                .content(customerForm(customer))
                .render(entityStream);
    }

    @GET @Path("/create") public String create() {
        return page.title("Create Customer")
                .content(customerForm(new Customer())
                        .post().action("/customers"))
                .render();
    }

    private Form customerForm(Customer customer) {
        return form().id("customer-form").horizontal()
                // TODO .novalidate().on("submit", "if (!validateAll()) event.preventDefault();")
                .content(fields(customer))
                .content(actionButtons(customer));
    }

    private Element actionButtons(Customer customer) {return buttonsAddon().is(PULLED_RIGHT).content(actions(customer));}

    private Stream<Field> fields(Customer customer) {
        var fields = form.of(customer);
        switch (mode()) {
            case CREATE -> fields = fields.filter(not(fieldName("customerNumber")));
            case VIEW -> fields = fields.map(Field::allReadonly);
        }
        return fields;
    }

    private List<? extends Renderable> actions(Customer customer) {
        return switch (mode()) {
            case CREATE -> List.of(
                    cancel("/customers"),
                    submit("Save").is(PRIMARY));
            case VIEW -> List.of(
                    edit(customerPath(customer) + "?edit"),
                    delete(customerPath(customer)));
            case EDIT -> List.of(
                    cancel(customerPath(customer)),
                    savePut(customerPath(customer)));
        };
    }

    private Mode mode() {
        return uriInfo.getQueryParameters().get("edit") != null ? EDIT
                : uriInfo.getPath().endsWith("/create") ? CREATE
                : VIEW;
    }

    private Anchor cancel(String href) {return a("Cancel").is(BUTTON).href(href);}

    private Button savePut(String path) {return button("Save").is(PRIMARY).attr("hx-put", path);}

    private Anchor edit(String path) {return a("Edit").is(BUTTON, LINK).href(path);}

    private Button delete(String path) {return button("Delete").is(DANGER).attr("hx-delete", path);}

    private String customerPath(Customer customer) {return "/customers/" + customer.getId();}

    static String customerImage(ImageSize size) {
        return "https://bulma.io/assets/images/placeholders/" + size.key() + ".png";
    }
}
