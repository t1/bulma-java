package com.github.t1.bulmajava.layout;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Anchor;
import com.github.t1.bulmajava.basic.Element;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.div;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Level extends AbstractElement<Level> {
    public static Level level() {return new Level();}

    public Level() {super("nav", "level");}


    /** Use {@link #addItem(Renderable, String...)} instead! */
    @Deprecated
    @Override public Level content(String content) {return super.content(content);}

    /** Use {@link #addItem(Renderable, String...)} instead! */
    @Deprecated
    @Override public Level content(Renderable content) {return super.content(content);}

    /** Use {@link #addItem(Renderable, String...)} instead! */
    @Deprecated
    @Override public Level content(Renderable... content) {return super.content(content);}

    /** Use {@link #addItem(Renderable, String...)} instead! */
    @Deprecated
    @Override public Level content(Stream<Renderable> content) {return super.content(content);}

    public Level addItem(Renderable content, String... classNames) {
        return super.content(item(content).classes(classNames));
    }

    public Level addLeft(Renderable... content) {
        return content("level-left", left -> left.content(Stream.of(content).map(Level::item)));
    }

    public Level addLeftA(Renderable... content) {
        return content("level-left", left -> left.content(Stream.of(content).map(Level::itemA)));
    }

    private static Anchor itemA(Renderable... content) {return a().classes("level-item").content(content);}

    private static Element item(Renderable... content) {return div().classes("level-item").content(content);}

    public Level addRight(Renderable... content) {
        return content("level-right", right -> right.content(Stream.of(content).map(Level::item)));
    }
}
