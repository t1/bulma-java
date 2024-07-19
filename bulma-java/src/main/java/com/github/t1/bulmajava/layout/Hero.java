package com.github.t1.bulmajava.layout;

import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Attributes;
import com.github.t1.htmljava.Classes;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Hero extends AbstractElement<Hero> {
    public static Hero hero() {return new Hero();}

    public Hero() {super("section", Attributes.of(Classes.of("hero")));}


    public Hero isFullheightWithNavbar() {return classes("is-fullheight-with-navbar");}


    /** Use {@link #body(Renderable...)} instead! */
    @Deprecated @Override public Hero content(String content) {return super.content(content);}

    /** Use {@link #body(Renderable...)} instead! */
    @Deprecated @Override public Hero content(Renderable content) {return super.content(content);}

    /** Use {@link #body(Renderable...)} instead! */
    @Deprecated @Override public Hero content(Renderable... content) {return super.content(content);}

    /** Use {@link #body(Renderable...)} instead! */
    @Deprecated @Override public Hero content(Stream<? extends Renderable> content) {return super.content(content);}

    public Hero head(Renderable content) {return content("hero-head", head -> head.content(content));}

    public Hero body(Renderable... content) {return content("hero-body", body -> body.content(content));}

    public Hero foot(Renderable content) {return content("hero-foot", foot -> foot.content(content));}
}
