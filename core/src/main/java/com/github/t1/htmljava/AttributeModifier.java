package com.github.t1.htmljava;

public interface AttributeModifier extends Modifier {
    @Override default boolean check(AbstractElement<?> element) {
        return element.hasAttribute(key());
    }

    @Override default void apply(AbstractElement<?> element) {
        element.attr(key(), value());
    }

    @Override default void unapply(AbstractElement<?> element) {
        element.attributes().remove(key());
    }

    String key();

    String value();
}
