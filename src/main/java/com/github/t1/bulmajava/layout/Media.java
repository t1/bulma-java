package com.github.t1.bulmajava.layout;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Basic.div;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Media extends AbstractElement<Media> {
    public static Media media() {return new Media();}

    public Media() {super("article", "media");}

    /** Use {@link #addContent(Renderable...)} instead! */
    @Deprecated
    @Override public Media content(String content) {return super.content(content);}

    /** Use {@link #addContent(Renderable...)} instead! */
    @Deprecated
    @Override public Media content(Renderable content) {return super.content(content);}

    /** Use {@link #addContent(Renderable...)} instead! */
    @Deprecated
    @Override public Media content(Renderable... content) {return super.content(content);}

    /** Use {@link #addContent(Renderable...)} instead! */
    @Deprecated
    @Override public Media content(Stream<Renderable> content) {return super.content(content);}

    public Media left(AbstractElement<?> content) {return super.content(content.classes("media-left"));}

    public Media addContent(String content) {return super.content(content);}

    public Media addContent(Renderable... content) {return super.content(div().classes("media-content").content(content));}

    public Media addRight(AbstractElement<?> content) {return super.content(content.classes("media-right"));}
}
