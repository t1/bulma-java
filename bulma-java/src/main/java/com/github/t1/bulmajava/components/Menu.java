package com.github.t1.bulmajava.components;

import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Renderable;

import static com.github.t1.htmljava.Basic.aside;
import static com.github.t1.htmljava.Basic.li;
import static com.github.t1.htmljava.Basic.p;
import static com.github.t1.htmljava.Basic.ul;

public class Menu {
    public static AbstractElement<?> menu() {return aside().classes("menu");}

    public static AbstractElement<?> menuLabel(String text) {return p().classes("menu-label").content(text);}

    public static AbstractElement<?> menuList() {return ul().classes("menu-list").map(Menu::item);}

    private static Renderable item(Renderable renderable) {
        return (renderable instanceof AbstractElement<?> e && e.hasName("li")) ? renderable : li().content(renderable);
    }
}
