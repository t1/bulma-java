package com.github.t1.bulmajava.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor @Getter @Accessors(fluent = true)
public enum FontSize {
    xxs("2xs"), xs("xs"), sm("sm"), lg("lg"), xl("xl"), xxl("2xl"),

    x1("1x"), x2("2x"), x3("3x"), x4("4x"), x5("5x"),
    x6("6x"), x7("7x"), x8("8x"), x9("9x"), x10("10x");

    private final String code;
}
