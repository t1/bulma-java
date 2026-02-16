package com.github.t1.htmljava;

import java.util.Set;
import java.util.stream.Stream;

public class Classes extends CombinableAttribute {
    public static final String KEY = "class";

    public static Classes of(Stream<String> classes) {return of(classes.toArray(String[]::new));}

    public static Classes of(String... classes) {
        return (classes.length == 0) ? null :
                Classes.of(Set.of()).plus(classes); // ignore null classes
    }

    public static Classes of(Set<String> classes) {return new Classes(classes);}

    private Classes(Set<String> values) {super(values);}

    @Override protected Classes create(Set<String> values) {return new Classes(values);}

    @Override public String key() {return KEY;}

    public Classes and(Attribute classes) {return plus((Classes) classes);}

    @Override public Classes plus(CombinableAttribute other) {return (Classes) super.plus(other);}

    @Override public Classes plus(String... classes) {return (Classes) super.plus(classes);}

    @Override public Classes minus(CombinableAttribute other) {return (Classes) super.minus(other);}

    public Classes minus(Classes classes) {return (Classes) super.minus(classes);}

    @Override public Classes mergeWith(Attribute incoming) {return (Classes) super.mergeWith(incoming);}
}
