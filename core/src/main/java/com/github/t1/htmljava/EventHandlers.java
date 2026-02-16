package com.github.t1.htmljava;

import static com.github.t1.htmljava.Attribute.StringAttribute.unsafeStringAttribute;

/// Mixin interface for HTML event handler attributes like `onclick`, `onkeyup`, etc.
public interface EventHandlers<SELF extends AbstractElement<?>> {
    SELF attr(Attribute attribute);

    default SELF onclick(String action) {return on("click", action);}

    default SELF onkeyup(String key, String action) {return onkey("up", key, action);}

    default SELF onkeydown(String key, String action) {return onkey("down", key, action);}

    /// `event` is officially deprecated, but seems to be okay to use
    /// [see SO](https://stackoverflow.com/a/58341967/3333174)
    default SELF onkey(String eventType, String key, String action) {
        return on("key" + eventType, "if (event.key === '" + key + "') { " + action + " }");
    }

    default SELF on(String event, String action) {
        return attr(unsafeStringAttribute("on" + event, action));
    }
}
