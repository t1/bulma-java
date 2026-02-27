package com.github.t1.bulmajava.basic;

import com.github.t1.htmljava.AttributeModifier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/// Ordered list type. Sets the `type` attribute (e.g., `type="A"`) and optionally
/// adds a variant CSS class (e.g., `is-upper-alpha`) for Bulma styling.
@RequiredArgsConstructor @Getter @Accessors(fluent = true)
public enum ListType implements AttributeModifier {
    NUMERIC("1", null),
    UPPER_ALPHA("A", "is-upper-alpha"),
    LOWER_ALPHA("a", "is-lower-alpha"),
    UPPER_ROMAN("I", "is-upper-roman"),
    LOWER_ROMAN("i", "is-lower-roman");

    private final String code;
    private final String variant;

    @Override public String key() {return "type";}

    @Override public String value() {return code;}
}
