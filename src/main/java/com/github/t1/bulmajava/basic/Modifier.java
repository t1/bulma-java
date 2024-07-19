package com.github.t1.bulmajava.basic;

import static java.util.Locale.ROOT;

public interface Modifier {

    String name();

    default String key() {return name().replace('_', '-').toLowerCase(ROOT);}

    default String className() {return "is-" + key();}
}
