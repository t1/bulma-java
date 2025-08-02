package com.github.t1.bulmajava.elements;

import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Anchor;
import com.github.t1.htmljava.Element;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.htmljava.Basic.div;
import static com.github.t1.htmljava.Renderable.RenderableString.string;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Tag extends AbstractElement<Tag> {
    public static Tag tag(String content) {return tag().content(string(content));}

    public static Tag tag() {return new Tag();}

    public static Anchor tagA() {return a().classes("tag");}

    public static Element tags() {return div().classes("tags");}

    public static Element tagsAddon() {return tags().classes("has-addons");}

    private Tag() {super("span", "tag");}
}
