package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Anchor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Tag extends AbstractElement<Tag> {
    public static AbstractElement<?> tag(String content) {return tag().contains(string(content));}

    public static AbstractElement<?> tag() {return new Tag();}

    public static Anchor tagA() {return a().classes("tag");}

    public static AbstractElement<?> tags() {return div().classes("tags");}

    public static AbstractElement<?> tagsAddon() {return tags().classes("has-addons");}

    private Tag() {super("span", "tag");}
}
