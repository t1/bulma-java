package com.github.t1.bulmajava.basic;

public enum FontFamily implements IsModifier {
    SANS_SERIF,
    MONOSPACE,
    PRIMARY,
    SECONDARY,
    CODE;

    @Override public String key() {return "family-" + IsModifier.super.key();}
}
