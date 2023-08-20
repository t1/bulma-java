package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Anchor;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Basic.li;
import static com.github.t1.bulmajava.basic.Basic.ul;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.State.ACTIVE;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Breadcrumb extends AbstractElement<Breadcrumb> {
    public static Breadcrumb breadcrumb() {return new Breadcrumb().classes("breadcrumb").ariaLabel("breadcrumbs");}

    private Breadcrumb() {
        super("nav", null, ul());
    }

    public Breadcrumb contains(Renderable... renderable) {
        return content((contentElement()).contains(Stream.of(renderable).map(Breadcrumb::item)));
    }

    private static AbstractElement<?> item(Renderable renderable) {
        var li = li();
        if (renderable instanceof Anchor a) {
            if (a.hasAttribute("aria-current", "page")) li = li.is(ACTIVE);
            renderable = a.mapIcon(icon -> icon.is(SMALL).ariaHidden());
        }
        return li.contains(renderable);
    }

    public static Anchor active(Anchor anchor) {return anchor.attr("aria-current", "page");}
}
