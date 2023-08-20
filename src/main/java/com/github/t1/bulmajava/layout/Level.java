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


    /** Use {@link #containsItem(Renderable, String...)} instead! */
    @Deprecated
    @Override public Level contains(Renderable content) {return super.contains(content);}

    /** Use {@link #containsItem(Renderable, String...)} instead! */
    @Deprecated
    @Override public Level contains(Renderable... content) {return super.contains(content);}

    public Level containsItem(Renderable content, String... classNames) {
        return super.contains(item(content).classes(classNames));
    }

    public Level containsLeft(Renderable... content) {
        return element("level-left", left -> left.contains(Stream.of(content).map(Level::item)));
    }

    public Level containsLeftA(Renderable... content) {
        return element("level-left", left -> left.contains(Stream.of(content).map(Level::itemA)));
    }

    private static Anchor itemA(Renderable... content) {return a().classes("level-item").contains(content);}

    private static Element item(Renderable... content) {return div().classes("level-item").contains(content);}

    public Level containsRight(Renderable... content) {
        return element("level-right", right -> right.contains(Stream.of(content).map(Level::item)));
    }
}
