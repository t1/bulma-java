package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.*;
import com.github.t1.bulmajava.elements.Icon;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Basic.span;
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
                theContent(id, buttonTitle, icon));
    }

    private static Renderable theContent(String id, String buttonTitle, String icon) {
        return ConcatenatedRenderable.concat(div().classes("dropdown-trigger").contains(
                        button(span(buttonTitle))
                                .attr("aria-haspopup", "true")
                                .attr("aria-controls", id)
                                .icon(icon).isIcon(SMALL).icon(Icon::ariaHidden)),
                div().classes("dropdown-menu").id(id).attr("role", "menu").contains(
                        div().classes("dropdown-content")));
    }

    @Override
    public Dropdown contains(Renderable renderable) {return contains(new Renderable[]{renderable});}

    @Override
    public Dropdown contains(Renderable... renderable) {
        var triggerAndMenu = concatContent();
        var menu = (Element) triggerAndMenu.renderables().get(1);
        var content = (Element) menu.content();
        var newItems = Stream.of(renderable).map(Dropdown::item);
        var newContent = content.contains(newItems);
        var newMenu = menu.content(newContent);
        var newTriggerAndMenu = triggerAndMenu.replace(menu, newMenu);
        return content(newTriggerAndMenu);
    }

    private static Renderable item(Renderable renderable) {
        if (renderable instanceof Anchor a) return a.classes("dropdown-item");
        if (renderable instanceof Element e)
            return e.hasName("hr") ? e.classes("dropdown-divider") : e.classes("dropdown-item");
        return renderable;
    }
}
