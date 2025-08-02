package com.github.t1.bulmajava.elements;

import com.github.t1.htmljava.Modifier;

import static com.github.t1.bulmajava.elements.Button.button;

public class Delete {
    public static final Modifier DELETE = () -> "delete";

    public static Button delete() {
        return button().notClasses("button").classes("delete").ariaLabel("delete");
    }

    public static Button close() {
        return button().notClasses("button").classes("delete").ariaLabel("close");
    }
}
