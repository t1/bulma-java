package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.*;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Basic.span;
import static com.github.t1.bulmajava.basic.Renderable.ConcatenatedRenderable.concat;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.elements.Button.button;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Dropdown extends AbstractElement<Dropdown> {
    public static Dropdown dropdown(String buttonTitle, String id) {
        return new Dropdown(id, buttonTitle, "angle-down");
    }

    public static Dropdown dropup(String buttonTitle, String id) {
        return new Dropdown(id, buttonTitle, "angle-up").classes("is-up");
    }

    private Dropdown(String id, String buttonTitle, String icon) {
        super("div", Attributes.of(Classes.of("dropdown")),
                concat(trigger(id, buttonTitle, icon), menu(id)));
    }

    private static Element trigger(String id, String buttonTitle, String icon) {
        return div().classes("dropdown-trigger").content(button(span(buttonTitle))
                .attr("aria-haspopup", "true")
                .attr("aria-controls", id)
                .icon(icon).isIcon(SMALL).icon(i -> i.ariaHidden(true)));
    }

    private static Element menu(String id) {
        return div().classes("dropdown-menu").id(id).attr("role", "menu")
                .content(div().classes("dropdown-content"));
    }

    @Override
    public Dropdown content(Renderable renderable) {
        menu().content(item(renderable));
        return this;
    }

    private Element menu() {
        var triggerAndMenu = contentAs(ConcatenatedRenderable.class);
        var menu = (Element) triggerAndMenu.renderables().get(1);
        return menu.contentAs(Element.class);
    }

    private static Renderable item(Renderable renderable) {
        if (renderable instanceof Anchor a) return a.classes("dropdown-item");
        if (renderable instanceof Element e)
            return e.hasName("hr") ? e.classes("dropdown-divider") : e.classes("dropdown-item");
        return renderable;
    }
}
