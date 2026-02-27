package com.github.t1.bulmajava.layout;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Alignment.CENTERED;
import static com.github.t1.bulmajava.basic.BulmaElement.TextModifier.text;
import static com.github.t1.htmljava.HtmlBasics.div;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
/// Content is auto-wrapped in `div.level-item`. Use `left()` and `right()` for sided content.
public class Level extends BulmaElement<Level> {
    public static Level level() {return new Level();}

    private boolean centered;
    private AbstractElement<?> lastItem;

    private Level() {super("nav", "level");}


    public Level content(Renderable content, int index) {
        if (content instanceof AbstractElement<?> element &&
            (element.hasClass("level-left") || element.hasClass("level-right"))) {
            return super.content(content, index);
        }
        lastItem = levelItem(content);
        if (centered) centered();
        return super.content(lastItem, index);
    }

    public Level left(Renderable... content) {return leftRight("left", content);}

    public Level right(Renderable... content) {return leftRight("right", content);}

    private Level leftRight(String leftOrRight, Renderable... content) {
        return content("level-" + leftOrRight,
                left -> left.content(Stream.of(content).map(Level::levelItem)));
    }

    private static AbstractElement<?> levelItem(Renderable... content) {
        return div().classes("level-item").content(content);
    }

    /// Adds the class `text-centered` to the last item of this level,
    /// or to _all_ items if there is no last item yet.
    public Level centered() {
        if (lastItem == null) this.centered = true;
        else lastItem.is(text(CENTERED));
        return this;
    }
}
