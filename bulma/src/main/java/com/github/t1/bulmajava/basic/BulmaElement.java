package com.github.t1.bulmajava.basic;

import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Attributes;
import com.github.t1.htmljava.ClassModifier;
import com.github.t1.htmljava.Renderable;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.BulmaElement.BackgroundModifier.background;
import static com.github.t1.bulmajava.basic.BulmaElement.TextModifier.text;

/// Extends {@link AbstractElement} with Bulma-specific helpers.
/// Use `hasText(COLOR)` for `has-text-*` and `hasBackground(COLOR)` for `has-background-*` classes,
/// since plain `is(PRIMARY)` would produce `is-primary`, not `has-text-primary`.
@Accessors(fluent = true, chain = true) @SuperBuilder(toBuilder = true)
public class BulmaElement<SELF extends BulmaElement<?>> extends AbstractElement<SELF> {
    public static final IsModifier RESPONSIVE = () -> "responsive";
    public static final ClassModifier CONTROL = () -> "control";
    public static final HasModifier ADDONS = () -> "addons";

    @SuppressWarnings("unused") // actually, it _is_ used by lombok
    public static BulmaElementBuilder<?, ?, ?> builder() {return new BulmaElementBuilderImpl<>();}

    // normally, Lombok generates this, but here, javadoc fails to find it... strange!
    public static abstract class BulmaElementBuilder<
            SELF extends BulmaElement<?>,
            C extends BulmaElement<SELF>,
            B extends BulmaElementBuilder<SELF, C, B>> extends AbstractElementBuilder<SELF, C, B> {}

    protected BulmaElement(AbstractElementBuilder<SELF, ?, ?> b) {super(b);}

    protected BulmaElement(@NonNull String name, String... classes) {super(name, classes);}

    protected BulmaElement(@NonNull String name, Attributes attributes) {super(name, attributes);}

    protected BulmaElement(@NonNull String name, Attributes attributes, Renderable content) {super(name, attributes, content);}

    protected BulmaElement(@NonNull String name, Attributes attributes, Renderable content, Function<Renderable, Renderable> mapFunction) {
        super(name, attributes, content, mapFunction);
    }

    public static final IsModifier PULLED_LEFT = () -> "pulled-left";

    public static final IsModifier PULLED_RIGHT = () -> "pulled-right";

    public SELF hasText(IsModifier modifier) {return has(text(modifier));}

    public SELF hasBackground(IsModifier modifier) {return has(background(modifier));}

    public record TextModifier(IsModifier modifier) implements HasModifier {
        public static TextModifier text(IsModifier modifier) {return new TextModifier(modifier);}

        @Override public String name() {return "text-" + modifier.name();}
    }

    public record BackgroundModifier(IsModifier modifier) implements HasModifier {
        public static BackgroundModifier background(IsModifier modifier) {return new BackgroundModifier(modifier);}

        @Override public String name() {return "background-" + modifier.name();}
    }
}
