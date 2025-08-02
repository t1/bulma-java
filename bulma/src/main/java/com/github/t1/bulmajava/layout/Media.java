package com.github.t1.bulmajava.layout;

import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Media extends AbstractElement<Media> {
    public static Media media() {return new Media();}

    public Media() {super("article", "media");}

    @Override public Media content(Renderable content) {
        if (content instanceof Element e && e.hasName("div") && e.hasClass("media-content"))
            return super.content(content); // break recursion
        getOrCreate("media-content").content(content);
        return this;
    }

    public Media left(AbstractElement<?> content) {return super.content(content.classes("media-left"));}

    public Media right(AbstractElement<?> content) {return super.content(content.classes("media-right"));}
}
