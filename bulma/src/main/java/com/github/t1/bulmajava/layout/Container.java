package com.github.t1.bulmajava.layout;

import com.github.t1.htmljava.AbstractElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Container extends AbstractElement<Container> {
    public static Container container() {return new Container();}

    public Container() {super("div", "container");}


    public Container isMaxDesktop() {return classes("is-max-desktop");}

    public Container isMaxWidescreen() {return classes("is-max-widescreen");}

    public Container isFluid() {return classes("is-fluid");}
}
