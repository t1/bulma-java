package com.github.t1.htmljava;

import static java.util.Locale.ROOT;

public interface PrefixedClassModifier extends ClassModifier {
    default String className() {return prefix() + "-" + key();}

    String prefix();

    default String key() {return name().replace('_', '-').toLowerCase(ROOT);}

    String name();
}
