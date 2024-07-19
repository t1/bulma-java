package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.elements.Icon;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Anchor;
import com.github.t1.htmljava.Attributes;
import com.github.t1.htmljava.Basic;
import com.github.t1.htmljava.Classes;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.State.ACTIVE;
import static com.github.t1.htmljava.Attribute.StringAttribute.stringAttribute;
import static com.github.t1.htmljava.Basic.li;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Breadcrumb extends AbstractElement<Breadcrumb> {
    public static Breadcrumb breadcrumb() {return new Breadcrumb();}

    private Breadcrumb() {
        super("nav", Attributes.of(
                        Classes.of("breadcrumb"),
                        stringAttribute("aria-label", "breadcrumbs")),
                Basic.ul());
    }

    public Breadcrumb content(Renderable renderable) {
        ul().content(item(renderable));
        return this;
    }

    private Element ul() {return contentAs(Element.class);}

    private static AbstractElement<?> item(Renderable renderable) {
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
