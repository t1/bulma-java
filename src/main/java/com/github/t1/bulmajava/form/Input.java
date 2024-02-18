package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Attributes;
import com.github.t1.bulmajava.basic.Classes;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import static com.github.t1.bulmajava.basic.Attribute.StringAttribute.stringAttribute;
import static com.github.t1.bulmajava.form.InputType.RESET;
import static com.github.t1.bulmajava.form.InputType.SUBMIT;
import static java.time.format.DateTimeFormatter.ISO_DATE;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Input extends AbstractElement<Input> {
    public static Input submit(String value) {return inputButton(SUBMIT, value);}

    public static Input reset(String value) {return inputButton(RESET, value);}

    private static Input inputButton(InputType type, String value) {
        return input(type).value(value).notClasses("input").classes("button");
    }

    public static Input input(InputType type) {return new Input(type).close(false);}

    private Input(InputType type) {super("input", Attributes.of(Classes.of("input"), stringAttribute("type", type.key())));}


    public Input value(int value) {return value(Integer.toString(value));}

    public Input value(String value) {return (value == null) ? this : attr("value", value);}

    public Input placeholder(String placeholder) {return attr("placeholder", placeholder);}

    public Input name(String name) {return attr("name", name);}


    public Input autofocus() {return attr("autofocus");}

    public Input readonly() {return attr("readonly");}

    public Input required() {return attr("required");}


    public Input minlength(int minlength) {return attr("minlength", Integer.toString(minlength));}

    public Input maxlength(int maxlength) {return attr("maxlength", Integer.toString(maxlength));}

    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/min">spec</a> */
    public Input min(LocalDate localDate) {return attr("min", localDate.format(ISO_DATE));}

    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/min">spec</a> */
    public Input min(int min) {return attr("min", Integer.toString(min));}

    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/max">spec</a> */
    public Input max(LocalDate localDate) {return attr("max", localDate.format(ISO_DATE));}

    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/max">spec</a> */
    public Input max(int max) {return attr("max", Integer.toString(max));}

    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/max">spec</a> */
    public Input step(int step) {return attr("step", Integer.toString(step));}
}
