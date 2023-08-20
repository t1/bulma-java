package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Attributes;
import com.github.t1.bulmajava.basic.Classes;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Attribute.StringAttribute.stringAttribute;
import static com.github.t1.bulmajava.basic.Attribute.noValueAttribute;
import static com.github.t1.bulmajava.form.InputType.RESET;
import static com.github.t1.bulmajava.form.InputType.SUBMIT;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Input extends AbstractElement<Input> {
    public static Input submit(String value) {return inputButton(SUBMIT, value);}

    public static Input reset(String value) {return inputButton(RESET, value);}

    private static Input inputButton(InputType type, String value) {
        return input(type).value(value).notClasses("input").classes("button");
    }

    public static Input input(InputType type) {return new Input(type).close(false);}

    private Input(InputType type) {super("input", Attributes.of(Classes.of("input"), stringAttribute("type", type.key())));}


    public Input value(String value) {return attr("value", value);}

    public Input placeholder(String placeholder) {return attr("placeholder", placeholder);}

    public Input name(String name) {return attr("name", name);}

    public Input readonly() {return attr(noValueAttribute("readonly"));}
}
