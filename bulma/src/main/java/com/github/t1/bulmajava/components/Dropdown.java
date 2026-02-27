package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.bulmajava.elements.Button;
import com.github.t1.htmljava.Anchor;
import com.github.t1.htmljava.Attributes;
import com.github.t1.htmljava.Classes;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.htmljava.HtmlBasics.div;
import static com.github.t1.htmljava.HtmlBasics.span;
import static com.github.t1.htmljava.Renderable.ConcatenatedRenderable.concat;

/// Content items automatically get the `dropdown-item` class; `<hr>` elements get `dropdown-divider`.
@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Dropdown extends BulmaElement<Dropdown> {
    public static Dropdown dropdown(String id) {
        return new Dropdown(id, null, "angle-down");
    }

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
        return div().classes("dropdown-trigger").content(
                Button.button(buttonTitle == null ? null : span(buttonTitle))
                        .attr("aria-haspopup", "true")
                        .attr("aria-controls", id)
                        .icon(icon).isIcon(SMALL).icon(i -> i.ariaHidden(true)));
    }

    private static Element menu(String id) {
        return div().classes("dropdown-menu").id(id).attr("role", "menu")
                .content(div().classes("dropdown-content"));
    }

    @Override
    public Dropdown content(Renderable renderable, int index) {
        menu().content(item(renderable), index);
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
            return e.hasTagName("hr") ? e.classes("dropdown-divider") : e.classes("dropdown-item");
        return renderable;
    }

    public Button button() {
        return (Button) findElement("dropdown-trigger").orElseThrow()
                .findElement("button").orElseThrow();
    }
}
