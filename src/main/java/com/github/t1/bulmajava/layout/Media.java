package com.github.t1.bulmajava.layout;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Basic.div;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Media extends AbstractElement<Media> {
    public static Media media() {return new Media();}

    public Media() {super("article", "media");}

    /** Use {@link #containsContent(Renderable...)} instead! */
    @Deprecated
    @Override public Media contains(Renderable content) {return super.contains(content);}

    /** Use {@link #containsContent(Renderable...)} instead! */
    @Deprecated
    @Override public Media contains(Renderable... content) {return super.contains(content);}

    public Media containsLeft(AbstractElement<?> content) {return super.contains(content.classes("media-left"));}

    public Media containsContent(String content) {return super.contains(content);}

    public Media containsContent(Renderable... content) {return super.contains(div().classes("media-content").contains(content));}

    public Media containsRight(AbstractElement<?> content) {return super.contains(content.classes("media-right"));}
}
