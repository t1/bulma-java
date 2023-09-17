package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.AbstractElement;

import static com.github.t1.bulmajava.basic.Basic.element;
import static com.github.t1.bulmajava.basic.Basic.div;

public class Message {
    public static AbstractElement<?> message(AbstractElement<?>... elements) {
        return element("article").classes("message").content(elements);
    }

    public static AbstractElement<?> messageHeader() {return div().classes("message-header");}

    public static AbstractElement<?> messageBody() {return div().classes("message-body");}
}
