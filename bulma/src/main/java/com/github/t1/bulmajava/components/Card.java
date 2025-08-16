package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.bulmajava.elements.Button;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.htmljava.HtmlBasics.div;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Card extends BulmaElement<Card> {
    public static Card card() {return new Card();}

    private Card() {super("div", "card");}

    public static Element cardContent() {return div().classes("card-content");}

    @EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
    public static class CardFooter extends BulmaElement<CardFooter> {
        private static CardFooter cardFooter() {return new CardFooter();}

        private CardFooter() {super("footer", "card-footer");}

        @Override public CardFooter content(Renderable content, int index) {
            if (content instanceof AbstractElement<?> e) e.classes("card-footer-item");
            return super.content(content, index);
        }
    }

    @EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
    public static class CardHeader extends BulmaElement<CardHeader> {
        private static CardHeader cardHeader() {return new CardHeader();}

        private CardHeader() {super("header", "card-header");}

        @Override public CardHeader content(Renderable content, int index) {
            if (content instanceof Button b)
                b.notClasses("button").classes("card-header-icon").icon(icon -> icon.ariaHidden(true));
            if (content instanceof AbstractElement<?> e && e.hasTagName("p"))
                e.classes("card-header-title");
            return super.content(content, index);
        }
    }

    public static Element cardImage() {return div().classes("card-image");}

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
        getOrCreate("card-header", CardHeader::cardHeader).content(components);
        return this;
    }

    public Card footer(Renderable... components) {
        getOrCreate("card-footer", CardFooter::cardFooter).content(components);
        return this;
    }
}
