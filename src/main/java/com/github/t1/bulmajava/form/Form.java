package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.AbstractElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Form extends AbstractElement<Form> {
    public static Form form() {return new Form();}

    private Form() {super("form");}

    public Form action(String action) {return attr("action", action);}

    public Form get() {return method("get");}

    public Form get(String action) {return get().action(action);}

    public Form post() {return method("post");}

    public Form post(String action) {return post().action(action);}

    public Form dialog() {return method("dialog");}

    public Form dialog(String action) {return dialog().action(action);}

    public Form method(String method) {return attr("method", method);}
}
