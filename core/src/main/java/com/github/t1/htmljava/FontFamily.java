package com.github.t1.htmljava;

public enum FontFamily implements Modifier {
    SANS_SERIF,
    MONOSPACE,
    PRIMARY,
    SECONDARY,
    CODE;

    @Override public String key() {return "family-" + Modifier.super.key();}
}
