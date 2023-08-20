package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.AbstractElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Attribute.noValueAttribute;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Textarea extends AbstractElement<Textarea> {
    public static Textarea textarea() {return new Textarea();}


    private Textarea() {super("textarea", "textarea");}


    public Textarea placeholder(String placeholder) {return attr("placeholder", placeholder);}

    public Textarea rows(int rows) {return attr("rows", Integer.toString(rows));}

    public Textarea readonly() {return attr(noValueAttribute("readonly"));}

    public Textarea fixedSize() {return classes("has-fixed-size");}
}
