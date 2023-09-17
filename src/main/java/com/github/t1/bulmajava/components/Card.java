package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Basic;
import com.github.t1.bulmajava.basic.Renderable;
import com.github.t1.bulmajava.elements.Button;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Basic.div;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Card extends AbstractElement<Card> {
    public static Card card() {return new Card();}

    private Card() {super("div", "card");}

    public static AbstractElement<?> cardContent() {return div().classes("card-content");}

    public static AbstractElement<?> cardFooter() {
        return Basic.element("footer").classes("card-footer").map(Card::footerElement);
    }

    private static Renderable footerElement(Renderable renderable) {
        if (renderable instanceof AbstractElement<?> e) return e.classes("card-footer-item");
        return renderable;
    }

    public static AbstractElement<?> cardHeader() {
        return Basic.element("header").classes("card-header").map(Card::headerElement);
    }

    private static Renderable headerElement(Renderable renderable) {
        if (renderable instanceof Button b)
            return b.notClasses("button").classes("card-header-icon").icon(icon -> icon.ariaHidden(true));
        if (renderable instanceof AbstractElement<?> e && e.hasName("p"))
            return e.classes("card-header-title");
        return renderable;
    }

    public static AbstractElement<?> cardImage() {return div().classes("card-image");}

    public static AbstractElement<?> media() {return div().classes("media");}

    public static AbstractElement<?> mediaContent() {return div().classes("media-content");}

    public static AbstractElement<?> mediaLeft() {return div().classes("media-left");}
}
