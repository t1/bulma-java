package com.github.t1.bulmajava.basic;

public enum FontAlignment implements Modifier {
    CENTERED,
    JUSTIFIED,
    LEFT,
    RIGHT;

    @Override public String prefix() {return "has";}

    @Override public String key() {return "text-" + Modifier.super.key();}
}
