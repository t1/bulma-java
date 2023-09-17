package com.github.t1.bulmajava.layout;

import com.github.t1.bulmajava.basic.AbstractElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Tile extends AbstractElement<Tile> {
    public static Tile ancestorTile() {return tile().classes("is-ancestor");}

    public static Tile parentTile() {return tile().classes("is-parent");}

    public static Tile tile() {return new Tile("div");}

    public Tile(String elementName) {super(elementName, "tile");}


    public Tile vertical() {return classes("is-vertical");}

    public Tile is(int size) {
        assert size >= 1 && size <= 12;
        return super.is(size);
    }

    public Tile child(AbstractElement<?> content) {return content(content.classes("tile", "is-child"));}

    public Tile child(Stream<AbstractElement<?>> content) {
        content.forEach(this::child);
        return this;
    }
}
