package com.github.t1.bulmajava.basic;

public enum FontWeight implements HasModifier {
    LIGHT,
    NORMAL,
    MEDIUM,
    SEMIBOLD,
    BOLD;

    @Override public String key() {return "text-weight-" + HasModifier.super.key();}
}
