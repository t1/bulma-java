package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Element;
import com.github.t1.bulmajava.basic.Renderable;

import static com.github.t1.bulmajava.basic.Basic.aside;
import static com.github.t1.bulmajava.basic.Basic.li;
import static com.github.t1.bulmajava.basic.Basic.p;
import static com.github.t1.bulmajava.basic.Basic.ul;

public class Menu {
    public static Element menu() {return aside().classes("menu");}

    public static Element menuLabel(String text) {return p().classes("menu-label").content(text);}

    public static Element menuList() {return ul().classes("menu-list").map(Menu::item);}

    private static Renderable item(Renderable renderable) {
        return (renderable instanceof AbstractElement<?> e && e.hasName("li")) ? renderable : li().content(renderable);
    }
}
