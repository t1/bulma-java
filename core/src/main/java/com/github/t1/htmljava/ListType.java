package com.github.t1.htmljava;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor @Getter @Accessors(fluent = true)
public enum ListType {
    NUMERIC("1", null),
    UPPER_ALPHA("A", "is-upper-alpha"),
    LOWER_ALPHA("a", "is-lower-alpha"),
    UPPER_ROMAN("I", "is-upper-roman"),
    LOWER_ROMAN("i", "is-lower-roman");

    private final String code;
    private final String variant;
}
