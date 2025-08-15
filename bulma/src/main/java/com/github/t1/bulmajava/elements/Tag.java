package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.htmljava.Anchor;
import com.github.t1.htmljava.Element;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.htmljava.HtmlBasics.div;
import static com.github.t1.htmljava.Renderable.RenderableString.string;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Tag extends BulmaElement<Tag> {
    public static Tag tag(String content) {return tag().content(string(content));}

    public static Tag tag() {return new Tag();}

    public static Anchor tagA() {return a().classes("tag");}

    public static Element tags() {return div().classes("tags");}

    public static Element tagsAddon() {return tags().has(ADDONS);}

    private Tag() {super("span", "tag");}
}
