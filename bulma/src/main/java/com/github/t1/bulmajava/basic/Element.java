package com.github.t1.bulmajava.basic;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Element extends AbstractElement<Element> {
    /**
     * The method name <code>element</code> clashes with the super method;
     * you can use {@link com.github.t1.bulmajava.basic.Basic#element(String)} ()}
     */
    public static Element element_(String name) {return new Element(name);}

    private Element(@NonNull String name) {super(name);}
}
