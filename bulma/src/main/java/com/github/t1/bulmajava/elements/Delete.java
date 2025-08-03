package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.IsModifier;

import static com.github.t1.bulmajava.elements.Button.button;

public class Delete {
    public static final IsModifier DELETE = () -> "delete";

    public static Button delete() {
        return button().notClasses("button").classes("delete").ariaLabel("delete");
    }

    public static Button close() {
        return button().notClasses("button").classes("delete").ariaLabel("close");
    }
}
