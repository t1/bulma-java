package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.*;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Tabs extends AbstractElement<Tabs> {
    public static Tabs tabs() {return new Tabs("div");}

    public static Tabs navTabs() {return new Tabs("nav");}

    private Tabs(String elementName) {
        super(elementName, Attributes.of(Classes.of("tabs")), Basic.ul());
    }


    @Override public Tabs content(Renderable content) {
        ul().content(content);
        return this;
    }

    private Element ul() {return contentAs(Element.class);}

    public Tabs isBoxed() {return classes("is-boxed");}

    public Tabs isToggle() {return classes("is-toggle");}

    public Tabs isRoundedToggle() {return classes("is-toggle is-toggle-rounded");}
}
