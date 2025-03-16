package com.github.t1.bulmajava.basic;

public enum FontWeight implements Modifier {
    LIGHT,
    NORMAL,
    MEDIUM,
    SEMIBOLD,
    BOLD;

    @Override public String prefix() {return "has";}

    @Override public String key() {return "text-weight-" + Modifier.super.key();}
}
