package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.Modifier;

/** These are only the alternative separators; i.e., the default <code>/</code> is not in here */
public enum BreadcrumbSeparator implements Modifier {
    ARROW, BULLET, DOT, SUCCEEDS;

    @Override public String className() {return "has-" + key() + "-separator";}
}
