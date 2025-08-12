package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.RADIO;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Radio extends BulmaElement<Radio> {
    /// Use {@link RadioGroup#radioGroup(String)} to set the (field) `name` of all radios contained.
    public static Radio radio(String value, String label) {return new Radio(value, label);}

    private final Input input;

    private Radio(String value, String label) {
        super("label", "radio");
        this.input = input(RADIO).notClasses("input");
        input.attr("value", value);
        content(input);
        content(label);
    }

    @Override public Radio disabled() {
        input.disabled();
        return super.disabled();
    }

    public Radio checked() {
        input.attr("checked");
        return this;
    }

    @EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
    public static class RadioGroup extends BulmaElement<RadioGroup> {
        /// All radios in this group will get the same `name` attribute.
        public static RadioGroup radioGroup(String name) {return new RadioGroup(name);}

        private String name;

        private RadioGroup(String name) {
            super("div", "radios");
            this.name = name;
        }

        @Override public RadioGroup content(Renderable content, boolean first) {
            if (content instanceof Radio radio) radio.input.attr("name", this.name);
            return super.content(content, first);
        }
    }
}
