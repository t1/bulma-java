package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.bulmajava.elements.Icon;
import com.github.t1.htmljava.Anchor;
import com.github.t1.htmljava.Attribute;
import com.github.t1.htmljava.Attributes;
import com.github.t1.htmljava.Classes;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.HtmlBasics;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.State.ACTIVE;
import static com.github.t1.htmljava.HtmlBasics.li;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Breadcrumb extends BulmaElement<Breadcrumb> {
    public static Breadcrumb breadcrumb() {return new Breadcrumb();}

    private Breadcrumb() {
        super("nav", Attributes.of(
                        Classes.of("breadcrumb"),
                        Attribute.of("aria-label", "breadcrumbs")),
                HtmlBasics.ul());
    }

    public Breadcrumb content(Renderable renderable) {
        ul().content(item(renderable));
        return this;
    }

    private Element ul() {return contentAs(Element.class);}

    private static Element item(Renderable renderable) {
        var li = li();
        if (renderable instanceof Anchor a) {
            if (a.hasAttribute("aria-current", "page")) li = li.is(ACTIVE);
            if (a.content() instanceof ConcatenatedRenderable concat) {
                concat.find(Icon.class).ifPresent(icon -> icon.is(SMALL).ariaHidden(true));
            }
        }
        return li.content(renderable);
    }
}
