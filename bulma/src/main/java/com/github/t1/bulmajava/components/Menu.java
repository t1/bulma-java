package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.ClassModifier;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.htmljava.HtmlBasics.li;
import static com.github.t1.htmljava.HtmlBasics.p;
import static com.github.t1.htmljava.HtmlBasics.ul;

/// Content items are auto-wrapped in `<ul class="menu-list">` and `<li>`.
@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Menu extends BulmaElement<Menu> {
    private static final ClassModifier MENU_LABEL = () -> "menu-label";

    public static Menu menu() {return new Menu();}

    private Menu() {super("aside", "menu");}

    private Element lastList;

    public Menu label(String text) {lastList = null; return content(p().is(MENU_LABEL).content(text));}

    @Override public Menu content(Renderable content, int index) {
        if (content instanceof AbstractElement<?> e && MENU_LABEL.check(e)) return super.content(content, index);
        if (lastList == null) {
            lastList = ul().classes("menu-list");
            super.content(lastList, index);
        }
        lastList.content(content instanceof AbstractElement<?> e && e.hasTagName("li") ? content : li().content(content));
        return this;
    }
}
