package com.github.t1.bulmajava.basic;

import java.util.stream.Stream;

public enum Size implements Modifier {
    SMALL, NORMAL, MEDIUM, LARGE;

    public static Stream<Size> sizes() {return Stream.of(values());}
}
