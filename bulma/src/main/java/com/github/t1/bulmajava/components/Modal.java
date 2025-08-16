package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.ClassModifier;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Size.LARGE;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.htmljava.HtmlBasics.div;
import static com.github.t1.htmljava.HtmlBasics.p;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Modal extends BulmaElement<Modal> {
    private static final ClassModifier BACKGROUND = () -> "modal-background";
    private static final ClassModifier CARD = () -> "modal-card";
    private static final ClassModifier CLOSE = () -> "modal-close";

    public static Modal modal() {return new Modal();}

    private Modal() {
        super("div", "modal");
        content(div().is(BACKGROUND));
    }

    public static Element modalCardTitle(String text) {return p(text).classes("modal-card-title");}


    public Modal closeButton() {
        return content(button().notClasses("button").is(CLOSE, LARGE).ariaLabel("close"));
    }

    @Override public Modal content(Renderable content, int index) {
        if (!isModalContent(content))
            content = div().classes("modal-content").content(content);
        return super.content(content, index);
    }

    private static boolean isModalContent(Renderable content) {
        return content instanceof AbstractElement<?> e && (e.hasModifier(BACKGROUND) || e.hasModifier(CLOSE) || e.hasModifier(CARD));
    }

    /// If you need more control over how to add content to the card, use the modalCard() method
    public Modal card(Renderable... content) {return content(modalCard().content(content), LAST);}

    public static ModalCard modalCard() {return new ModalCard();}

    @EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
    public static class ModalCard extends BulmaElement<ModalCard> {
        private ModalCard() {super("div", CARD.className());}

        @Override public ModalCard content(Renderable content, int index) {
            if (content instanceof AbstractElement<?> e) {
                if (e.hasTagName("header")) e.classes("modal-card-head");
                else if (e.hasTagName("footer")) e.classes("modal-card-foot");
                else e.classes("modal-card-body");
            }
            return super.content(content, index);
        }
    }
}
