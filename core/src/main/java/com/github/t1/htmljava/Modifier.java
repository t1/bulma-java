package com.github.t1.htmljava;

import static java.util.Locale.ROOT;

public interface Modifier {

    String name();

    default String className() {return prefix() + "-" + key();}

    default String prefix() {return "is";}

    default String key() {return name().replace('_', '-').toLowerCase(ROOT);}
}
