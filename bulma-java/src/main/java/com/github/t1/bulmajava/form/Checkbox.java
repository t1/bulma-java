package com.github.t1.bulmajava.form;

import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Attributes;
import com.github.t1.htmljava.Classes;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.form.InputType.CHECKBOX;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Checkbox extends AbstractElement<Checkbox> {
    public static Checkbox checkbox() {return new Checkbox();}

    private Checkbox() {
        super("label", Attributes.of(Classes.of("checkbox")),
                Input.input(CHECKBOX).notClasses("input"));
    }


    public Checkbox id(String id) {
        input().id(id);
        return this;
    }

    public Checkbox name(String name) {
        input().name(name);
        return this;
    }

    @Override public Checkbox disabled() {
        input().disabled();
        return super.disabled();
    }

    public Checkbox checked() {
        input().attr("checked");
        return this;
    }

    private Input input() {return content().find(Input.class).orElseThrow();}
}
