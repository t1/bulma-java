package com.github.t1.htmljava;

import static java.util.Locale.ROOT;

/// Generates CSS class names from a prefix and the enum constant name (converted to kebab-case).
/// E.g., enum constant `PRIMARY` with prefix `"is"` yields `"is-primary"`.
/// This is how most Bulma modifiers are defined.
public interface PrefixedClassModifier extends ClassModifier {
    default String className() {return prefix() + "-" + key();}

    String prefix();

    default String key() {return name().replace('_', '-').toLowerCase(ROOT);}

    String name();
}
