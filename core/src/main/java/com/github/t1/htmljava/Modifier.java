package com.github.t1.htmljava;

public interface Modifier {
    boolean check(AbstractElement<?> element);
    void apply(AbstractElement<?> element);
    void unapply(AbstractElement<?> element);
}
