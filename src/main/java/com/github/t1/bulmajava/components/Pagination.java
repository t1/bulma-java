package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Attributes;
import com.github.t1.bulmajava.basic.Classes;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Attribute.StringAttribute.stringAttribute;
import static com.github.t1.bulmajava.basic.Basic.*;
import static com.github.t1.bulmajava.basic.Renderable.UnsafeString.unsafeString;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Pagination extends AbstractElement<Pagination> {

    public static Pagination pagination(int min, int current, int max) {return new Pagination(min, current, max);}

    private final int min;
    private final int current;
    private final int max;

    public Pagination(int min, int current, int max) {
        super("nav", Attributes.of(
                Classes.of("pagination"),
                stringAttribute("role", "navigation"),
                stringAttribute("aria-label", "pagination")));
        if (current < min) throw new IllegalArgumentException("current value " + current + " is below minimum " + min);
        if (current > max) throw new IllegalArgumentException("current value " + current + " is above maximum " + max);
        this.min = min;
        this.current = current;
        this.max = max;
    }

    public Pagination previous(String label) {
        var link = a(label).classes("pagination-previous");
        if (current <= min) link = link.classes("is-disabled");
        return content(link);
    }

    public Pagination next(String label) {
        var link = a(label).classes("pagination-next");
        if (current >= max) link = link.classes("is-disabled");
        return content(link);
    }

    public Pagination pages() {
        var list = ul().classes("pagination-list");
        if (min < current)
            list = list.content(
                    li().content(a(Integer.toString(min)).classes("pagination-link").ariaLabel("Goto page " + min)));
        if (current - min > 2)
            list = list.content(
                    li().content(span().classes("pagination-ellipsis").content(unsafeString("&hellip;"))));
        if (current - min > 1)
            list = list.content(
                    li().content(a(Integer.toString(current - 1)).classes("pagination-link").ariaLabel("Goto page " + (current - 1))));
        list = list.content(
                li().content(a(Integer.toString(current)).classes("pagination-link", "is-current")
                        .ariaLabel("Page " + current).active()));
        if (max - current > 1)
            list = list.content(
                    li().content(a(Integer.toString(current + 1)).classes("pagination-link").ariaLabel("Goto page " + (current + 1))));
        if (max - current > 2)
            list = list.content(
                    li().content(span().classes("pagination-ellipsis").content(unsafeString("&hellip;"))));
        if (current != max)
            list = list.content(
                    li().content(a(Integer.toString(max)).classes("pagination-link").ariaLabel("Goto page " + max)));
        return content(list);
    }
}
