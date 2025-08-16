package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Form extends BulmaElement<Form> {
    public static Form form() {return new Form();}

    private boolean horizontal;

    private Form() {super("form");}

    public Form action(String action) {return attr("action", action);}

    public Form get() {return method("get");}

    public Form get(String action) {return get().action(action);}

    public Form post() {return method("post");}

    public Form post(String action) {return post().action(action);}

    public Form dialog() {return method("dialog");}

    public Form dialog(String action) {return dialog().action(action);}

    public Form method(String method) {return attr("method", method);}

    public Form multipart() {return enctype("multipart/form-data");}

    /// this is the default
    public Form urlencoded() {return enctype("application/x-www-form-urlencoded");}

    public Form plain() {return enctype("text/plain");}

    public Form enctype(String enctype) {return attr("enctype", enctype);}

    /// Mark all fields added to this form as {@link Field#horizontal horizontal}.
    public Form horizontal() {return this;}

    @Override public Form content(Renderable content, int index) {
        if (content instanceof Field field) field.horizontal();
        return super.content(content, index);
    }
}
