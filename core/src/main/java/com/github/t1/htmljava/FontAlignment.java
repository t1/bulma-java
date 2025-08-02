package com.github.t1.htmljava;

public enum FontAlignment implements Modifier {
    CENTERED,
    JUSTIFIED,
    LEFT,
    RIGHT;

    @Override public String prefix() {return "has";}

    @Override public String key() {return "text-" + Modifier.super.key();}
}
