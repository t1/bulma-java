package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.htmljava.Attributes;
import com.github.t1.htmljava.Classes;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.HtmlBasics;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Tabs extends BulmaElement<Tabs> {
    public static Tabs tabs() {return new Tabs("div");}

    public static Tabs navTabs() {return new Tabs("nav");}

    private Tabs(String elementName) {
        super(elementName, Attributes.of(Classes.of("tabs")), HtmlBasics.ul());
    }


    @Override public Tabs content(Renderable content, boolean first) {
        ul().content(content, first);
        return this;
    }

    private Element ul() {return contentAs(Element.class);}

    public Tabs isBoxed() {return classes("is-boxed");}

    public Tabs isToggle() {return classes("is-toggle");}

    public Tabs isRoundedToggle() {return classes("is-toggle is-toggle-rounded");}
}
