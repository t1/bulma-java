package com.github.t1.bulmajava.basic;

/// Font family. Overrides `key()` to produce `is-family-monospace`, `is-family-code`, etc.
public enum FontFamily implements IsModifier {
    SANS_SERIF,
    MONOSPACE,
    PRIMARY,
    SECONDARY,
    CODE;

    @Override public String key() {return "family-" + IsModifier.super.key();}
}
