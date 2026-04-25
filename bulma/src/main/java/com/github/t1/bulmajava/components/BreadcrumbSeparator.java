package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.HasModifier;

/// These are only the alternative separators; i.e., the default `/` is not in here
public enum BreadcrumbSeparator implements HasModifier {
    ARROW, BULLET, DOT, SUCCEEDS;

    @Override public String key() {return HasModifier.super.key() + "-separator";}
}
