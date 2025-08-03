package com.github.t1.htmljava;

public interface ClassModifier extends Modifier {
    @Override default boolean check(AbstractElement<?> element) {return element.hasClass(className());}

    @Override default void apply(AbstractElement<?> element) {element.classes(className());}

    @Override default void unapply(AbstractElement<?> element) {element.notClasses(className());}

    String className();
}
