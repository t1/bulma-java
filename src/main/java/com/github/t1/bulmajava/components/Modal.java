package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Renderable;
import com.github.t1.bulmajava.elements.Button;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Basic.p;
import static com.github.t1.bulmajava.basic.Size.LARGE;
import static com.github.t1.bulmajava.elements.Button.button;

public class Modal {
    public static AbstractElement<?> modal() {
        return div().classes("modal").content(
                div().classes("modal-background"));
    }

    public static AbstractElement<?> modalCard() {return div().classes("modal-card").map(Modal::part);}

    private static Renderable part(Renderable renderable) {
        if (renderable instanceof AbstractElement<?> e) {
            if (e.hasName("header")) return e.classes("modal-card-head");
            if (e.hasName("footer")) return e.classes("modal-card-foot");
            return e.classes("modal-card-body");
        }
        return renderable;
    }

    public static AbstractElement<?> modalCardTitle(String text) {return p(text).classes("modal-card-title");}


    public static Button modalCloseButton() {
        return button().notClasses("button").classes("modal-close").is(LARGE).ariaLabel("close");
    }

    public static AbstractElement<?> modalContent() {return div().classes("modal-content");}
}
