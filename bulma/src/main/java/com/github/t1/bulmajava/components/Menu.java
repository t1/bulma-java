package com.github.t1.bulmajava.components;

import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Renderable;

import static com.github.t1.htmljava.HtmlBasics.aside;
import static com.github.t1.htmljava.HtmlBasics.li;
import static com.github.t1.htmljava.HtmlBasics.p;
import static com.github.t1.htmljava.HtmlBasics.ul;

public class Menu {
    public static Element menu() {return aside().classes("menu");}

    public static Element menuLabel(String text) {return p().classes("menu-label").content(text);}

    public static Element menuList() {return ul().classes("menu-list").map(Menu::item);}

    private static Renderable item(Renderable renderable) {
        return (renderable instanceof AbstractElement<?> e && e.hasName("li")) ? renderable : li().content(renderable);
    }
}
