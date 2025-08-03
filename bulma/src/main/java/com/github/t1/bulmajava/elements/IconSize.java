package com.github.t1.bulmajava.elements;

import com.github.t1.htmljava.ClassModifier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE) @Getter @Accessors(fluent = true)
public enum IconSize implements ClassModifier {
    XXS("2xs"), XS("xs"), SM("sm"),
    N(null) {
        @Override public String className() {return null;}
    },
    LG("lg"), XL("xl"), XXL("2xl");

    private final String key;


    @Override public String className() {return "fa-" + key;}
}
