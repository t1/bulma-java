package com.github.t1.htmljava;

import java.util.Set;

public class Styles extends CombinableAttribute {
    public static final String KEY = "style";

    public static Styles of(String style) {return new Styles(Set.of(style));}

    private Styles(Set<String> values) {super(values);}

    @Override protected Styles create(Set<String> values) {return new Styles(values);}

    @Override public String key() {return KEY;}

    @Override public Styles mergeWith(Attribute incoming) {return (Styles) super.mergeWith(incoming);}
}
