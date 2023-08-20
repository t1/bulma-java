package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Attributes;
import com.github.t1.bulmajava.basic.Classes;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Attribute.noValueAttribute;
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
        return super.disabled().replace((Function<Radio, Input>) Radio::getInput, Input::disabled);
    }

    private Input getInput() {return content().find(Input.class).orElseThrow();}

    public Radio checked() {return replace((Function<Radio, Input>) Radio::getInput, input -> input.attr(noValueAttribute("checked")));}
}
