package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.RADIO;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Radio extends BulmaElement<Radio> {
    /// A radio group (a single radio button without a group doesn't make sense)
    public static Radios radios(String name) {return new Radios(name);}

    private final Input input;

    private Radio(String value, String label) {
        super("label", "radio");
        this.input = input(RADIO).notClasses("input");
        input.attr("value", value);
        content(input);
        content(label);
    }

    @EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
    public static class Radios extends BulmaElement<Radios> {
        private String name;
        private Radio lastRadio;

        private Radios(String name) {
            super("div", "radios");
            this.name = name;
        }

        @Override public Radios content(Renderable content, boolean first) {
            if (content instanceof Radio radio) radio.input.attr("name", this.name);
            return super.content(content, first);
        }

        public Radios option(String value, String label) {
            return content(lastRadio = new Radio(value, label));
        }

        @Override public Radios disabled() {
            lastRadio.input.disabled();
            lastRadio.disabled();
            return this;
        }

        public Radios checked() {
            lastRadio.input.attr("checked");
            return this;
        }
    }
}
