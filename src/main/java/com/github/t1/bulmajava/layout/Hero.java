package com.github.t1.bulmajava.layout;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Attributes;
import com.github.t1.bulmajava.basic.Classes;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Hero extends AbstractElement<Hero> {
    public static Hero hero() {return new Hero();}

    public Hero() {super("section", Attributes.of(Classes.of("hero")));}

    /** Use {@link #containsBody(Renderable...)} instead! */
    @Deprecated @Override public Hero contains(Renderable content) {return super.contains(content);}

    /** Use {@link #containsBody(Renderable...)} instead! */
    @Deprecated @Override public Hero contains(Renderable... content) {return super.contains(content);}


    public Hero containsHead(Renderable content) {
        return element("hero-head", body -> body.contains(content));
    }

    public Hero containsBody(Renderable... content) {
        return element("hero-body", body -> body.contains(content));
    }

    public Hero containsFoot(Renderable content) {
        return element("hero-foot", body -> body.contains(content));
    }

    public Hero isFullheightWithNavbar() {return classes("is-fullheight-with-navbar");}
}
