package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Basic.ul;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Tabs extends AbstractElement<Tabs> {
    public static Tabs tabs() {return new Tabs("div").contains(ul());}

    public static Tabs navTabs() {return new Tabs("nav").contains(ul());}

    private Tabs(String elementName) {super(elementName, "tabs");}

    @Override public Tabs contains(Renderable... content) {
        var ul = contentElement();
        return content(ul.contains(content));
    }

    public Tabs isBoxed() {return classes("is-boxed");}

    public Tabs isToggle() {return classes("is-toggle");}

    public Tabs isRoundedToggle() {return classes("is-toggle is-toggle-rounded");}
}
