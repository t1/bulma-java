package com.github.t1.bulmajava.basic;

/// Text weight. Overrides `key()` to produce `has-text-weight-bold`, `has-text-weight-light`, etc.
public enum FontWeight implements HasModifier {
    LIGHT,
    NORMAL,
    MEDIUM,
    SEMIBOLD,
    BOLD;

    @Override public String key() {return "text-weight-" + HasModifier.super.key();}
}
