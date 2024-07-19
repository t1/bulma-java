package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.elements.Button;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Basic;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.htmljava.Basic.div;

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

    public Card image(AbstractElement<?> image) {
        getOrCreate("card-image", Card::cardImage).content(image);
        return this;
    }

    @Override
    public Card content(Renderable... components) {
        getOrCreate("card-content", Card::cardContent).content(components);
        return this;
    }

    public Card header(Renderable... components) {
        getOrCreate("card-header", Card::cardHeader).content(components);
        return this;
    }

    public Card footer(Renderable... components) {
        getOrCreate("card-footer", Card::cardFooter).content(components);
        return this;
    }
}
