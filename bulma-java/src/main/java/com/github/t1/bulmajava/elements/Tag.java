package com.github.t1.bulmajava.elements;

import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Anchor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.htmljava.Basic.div;
import static com.github.t1.htmljava.Renderable.RenderableString.string;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Tag extends AbstractElement<Tag> {
    public static AbstractElement<?> tag(String content) {return tag().content(string(content));}

    public static AbstractElement<?> tag() {return new Tag();}

    public static Anchor tagA() {return a().classes("tag");}

    public static AbstractElement<?> tags() {return div().classes("tags");}

    public static AbstractElement<?> tagsAddon() {return tags().classes("has-addons");}

    private Tag() {super("span", "tag");}
}
