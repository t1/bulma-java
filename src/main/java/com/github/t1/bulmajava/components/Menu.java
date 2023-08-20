package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Basic;
import com.github.t1.bulmajava.basic.Renderable;

import static com.github.t1.bulmajava.basic.Basic.element;

public class Menu {
    public static AbstractElement<?> menu() {return element("aside").classes("menu");}

    public static AbstractElement<?> menuLabel(String text) {return Basic.p().classes("menu-label").contains(text);}

    public static AbstractElement<?> menuList() {return Basic.ul().classes("menu-list").map(Menu::item);}

    private static Renderable item(Renderable renderable) {
        return (renderable instanceof AbstractElement<?> e && e.hasName("li")) ? renderable : Basic.li().contains(renderable);
    }
}
