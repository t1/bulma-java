package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.BulmaElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Textarea extends BulmaElement<Textarea> {
    public static Textarea textarea() {return new Textarea();}


    private Textarea() {super("textarea", "textarea");}


    public Textarea placeholder(String placeholder) {return attr("placeholder", placeholder);}

    public Textarea rows(int rows) {return attr("rows", Integer.toString(rows));}

    public Textarea readonly() {return attr("readonly");}

    public Textarea readonly(boolean readonly) {return readonly ? readonly() : this;}

    public Textarea required() {return attr("required");}

    public Textarea required(boolean required) {return required ? required() : this;}

    public Textarea fixedSize() {return classes("has-fixed-size");}
}
