package com.github.t1.htmljava;

/// Reusable strategy for toggling a CSS class or attribute on elements.
/// Applied via `element.is(modifier)` / `element.not(modifier)`.
/// Typically implemented as enums — see {@link PrefixedClassModifier} for the common case.
public interface Modifier {
    boolean check(AbstractElement<?> element);
    void apply(AbstractElement<?> element);
    void unapply(AbstractElement<?> element);
}
