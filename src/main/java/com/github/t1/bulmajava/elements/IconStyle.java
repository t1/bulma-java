package com.github.t1.bulmajava.elements;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE) @Getter @Accessors(fluent = true)
public enum IconStyle {
    SOLID("fas"), REGULAR("far"), LIGHT("fal"), BOLD("fab"), DUOTONE("fad");

    private final String className;
}
