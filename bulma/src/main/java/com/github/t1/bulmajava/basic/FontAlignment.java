package com.github.t1.bulmajava.basic;

/// Text alignment. Overrides `key()` to produce `has-text-centered`, `has-text-left`, etc.
public enum FontAlignment implements HasModifier {
    CENTERED,
    JUSTIFIED,
    LEFT,
    RIGHT;

    @Override public String key() {return "text-" + HasModifier.super.key();}
}
