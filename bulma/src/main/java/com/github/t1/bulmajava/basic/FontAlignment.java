package com.github.t1.bulmajava.basic;

public enum FontAlignment implements HasModifier {
    CENTERED,
    JUSTIFIED,
    LEFT,
    RIGHT;

    @Override public String key() {return "text-" + HasModifier.super.key();}
}
