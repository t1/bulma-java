package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Attributes;
import com.github.t1.bulmajava.basic.Classes;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Attribute.NoValueAttribute.noValueAttribute;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.RADIO;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Radio extends AbstractElement<Radio> {
    /**
     * It generally makes sense to give the radio a name; if you really don't want it, pass <code>null</code>.
     */
    public static Radio radio(String name) {return new Radio(name);}

    private Radio(String name) {
        super("label", Attributes.of(Classes.of("radio")),
                input(RADIO).notClasses("input").name(name));
    }


    @Override public Radio disabled() {
        getInput().disabled();
        return super.disabled();
    }

    public Radio checked() {
        getInput().attr(noValueAttribute("checked"));
        return this;
    }

    private Input getInput() {return content().find(Input.class).orElseThrow();}
}
