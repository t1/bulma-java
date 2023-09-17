package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Attributes;
import com.github.t1.bulmajava.basic.Classes;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Attribute.NoValueAttribute.noValueAttribute;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.CHECKBOX;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Checkbox extends AbstractElement<Checkbox> {
    public static Checkbox checkbox() {return new Checkbox();}

    private Checkbox() {
        super("label", Attributes.of(Classes.of("checkbox")),
                input(CHECKBOX).notClasses("input"));
    }


    @Override public Checkbox disabled() {
        getInput().disabled();
        return super.disabled();
    }

    public Checkbox checked() {
        getInput().attr(noValueAttribute("checked"));
        return this;
    }

    private Input getInput() {return content().find(Input.class).orElseThrow();}
}
