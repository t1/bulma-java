package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Renderable;

import static com.github.t1.bulmajava.basic.Basic.*;

public class Menu {
    public static AbstractElement<?> menu() {return element("aside").classes("menu");}

    public static AbstractElement<?> menuLabel(String text) {return p().classes("menu-label").content(text);}

    public static AbstractElement<?> menuList() {return ul().classes("menu-list").map(Menu::item);}

    private static Renderable item(Renderable renderable) {
        return (renderable instanceof AbstractElement<?> e && e.hasName("li")) ? renderable : li().content(renderable);
    }
}
