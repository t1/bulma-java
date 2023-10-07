package com.github.t1.bulmajava.basic;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Attribute.NoValueAttribute.noValueAttribute;
import static com.github.t1.bulmajava.basic.Attribute.StringAttribute.stringAttribute;
import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Renderable.ConcatenatedRenderable.concat;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;

@Accessors(fluent = true, chain = true) @SuperBuilder(toBuilder = true)
public class AbstractElement<SELF extends AbstractElement<?>> implements Renderable {

    private boolean close;
    @Getter private boolean rendersOnSeparateLines;
    @Getter @NonNull private String name;
    @Getter private Attributes attributes;
    private Renderable content;
    // TODO get rid of the mapFunction mechanism
    @NonNull private Function<Renderable, Renderable> mapFunction;

    protected AbstractElement(@NonNull String name, String... classes) {this(name, Attributes.of(Classes.of(classes)));}

    protected AbstractElement(@NonNull String name, Attributes attributes) {this(name, attributes, null);}

    protected AbstractElement(@NonNull String name, Attributes attributes, Renderable content) {this(name, attributes, content, Function.identity());}

    protected AbstractElement(@NonNull String name, Attributes attributes, Renderable content, Function<Renderable, Renderable> mapFunction) {
        this.close = true;
        this.rendersOnSeparateLines = true;
        this.name = name;
        this.attributes = attributes;
        this.content = content;
        this.mapFunction = mapFunction;
    }

    @Override public String toString() {return render();}

    public boolean hasName(String name) {return this.name.equals(name);}

    public boolean hasAttribute(String name, String value) {return attributes.hasAttribute(name, value);}

    public boolean has(Modifier modifier) {return hasClass(modifier.className());}

    @Override public boolean hasClass(String name) {
        return Optional.ofNullable(getClasses()).map(classes -> classes.hasClass(name)).orElse(false);
    }

    public Classes getClasses() {
        return attributes == null ? null : attributes.find(Classes.class).orElse(null);
    }

    public Renderable content() {return content;}

    public Stream<Renderable> contentStream() {
        return (content == null) ? Stream.of()
                : (contentIsA(ConcatenatedRenderable.class)
                ? contentAs(ConcatenatedRenderable.class).renderables().stream()
                : Stream.of(content));
    }

    public <T extends Renderable> T contentAs(Class<T> type) {return type.cast(content);}

    public <T extends Renderable> boolean contentIsA(Class<T> type) {return type.isAssignableFrom(content.getClass());}


    @SuppressWarnings("unchecked")
    protected SELF self() {return (SELF) this;}


    public SELF close(boolean close) {
        this.close = close;
        return self();
    }

    public SELF rendersOnSeparateLines(boolean rendersOnSeparateLines) {
        this.rendersOnSeparateLines = rendersOnSeparateLines;
        return self();
    }

    public SELF id(String id) {return attr("id", id);}

    public SELF classes(String... classes) {return classes(Classes.of(classes));}

    private SELF classes(Classes classes) {return (classes == null || classes.empty()) ? self() : attr(classes);}

    public SELF notClasses(String... classes) {return notClasses(Classes.of(classes));}

    public SELF notClasses(Classes removing) {
        if (attributes == null) return self();
        attributes.find(Classes.class).ifPresent(existing -> {
            existing.minus(removing);
            if (existing.empty()) attributes.remove(existing);
        });
        return self();
    }

    public SELF is(Modifier... modifiers) {
        return classes(Stream.of(modifiers).map(Modifier::className).toArray(String[]::new));
    }

    public SELF is(int size) {return classes("is-" + size);}

    public SELF isPulledLeft() {return classes("is-pulled-left");}

    public SELF isPulledRight() {return classes("is-pulled-right");}

    public SELF style(String style) {return attr("style", style);}

    public SELF ariaHidden(boolean hidden) {return ariaHidden(Boolean.toString(hidden));}

    public SELF ariaHidden(String hidden) {return attr("aria-hidden", hidden);}

    public SELF ariaLabel(String label) {return attr("aria-label", label);}

    public SELF attr(String name, String value) {return attr(stringAttribute(name, value));}

    public SELF attr(Attribute attribute) {
        if (attributes == null) Attributes.of(attribute);
        else attributes.add(attribute);
        return self();
    }

    public SELF hasText(Modifier modifier) {return classes("has-text-" + modifier.key());}

    public SELF hasBackground(Modifier modifier) {return classes("has-background-" + modifier.key());}

    public SELF dataValue(String value) {return attr("data-value", value);}

    public SELF disabled() {return attr(noValueAttribute("disabled"));}


    public SELF map(Function<Renderable, Renderable> function) {
        this.mapFunction = function;
        return self();
    }

    /**
     * Insert this {@link Renderable} as the first content element.
     *
     * @see #content(Renderable)
     */
    public SELF firstContent(Renderable content) {
        var mapped = mapFunction.apply(content);
        this.content = (this.content == null) ? mapped : concat(mapped, this.content);
        return self();
    }

    public SELF content(String content) {return content(string(content));}

    public SELF content(Renderable... content) {return content(Arrays.stream(content));}

    public SELF content(Stream<? extends Renderable> content) {
        content.filter(Objects::nonNull).forEach(this::content);
        return self();
    }

    public SELF content(Renderable content) {
        var mapped = mapFunction.apply(content);
        this.content = (this.content == null) ? mapped : concat(this.content, mapped);
        return self();
    }

    public final SELF content(String className, Function<AbstractElement<?>, AbstractElement<?>> function) {
        return content(e -> e.hasClass(className), function, () -> div().classes(className));
    }

    public SELF content(
            Predicate<AbstractElement<?>> predicate,
            Function<AbstractElement<?>, AbstractElement<?>> function,
            Supplier<AbstractElement<?>> generator) {
        var element = findElement(predicate);
        element.ifPresentOrElse(function::apply, () -> content(function.apply(generator.get())));
        return self();
    }

    public <T extends AbstractElement<?>> T getOrCreate(String className) {
        //noinspection unchecked
        return (T) getOrCreate(className, Basic::div);
    }

    public <T extends AbstractElement<?>> T getOrCreate(String className, Supplier<T> generator) {
        //noinspection unchecked
        return (T) getOrCreate(e -> e.hasClass(className), () -> generator.get().classes(className));
    }

    public <T extends AbstractElement<?>> T getOrCreate(Predicate<AbstractElement<?>> predicate, Supplier<T> generator) {
        //noinspection unchecked
        return (T) findElement(predicate).orElseGet(() -> {
            var generated = generator.get();
            content(generated);
            return generated;
        });
    }


    public Optional<AbstractElement<?>> findElement(String className) {return findElement(e -> e.hasClass(className));}

    public Optional<AbstractElement<?>> findElement(Predicate<AbstractElement<?>> predicate) {
        return (content() == null) ? Optional.empty() :
                content().find(renderable -> renderable instanceof AbstractElement<?> e && predicate.test(e))
                        .map(obj -> (AbstractElement<?>) obj);
    }


    @Override public boolean equals(Object obj) {
        return obj instanceof AbstractElement<?> that && this.render().equals(that.render());
    }

    @Override public void render(Renderer renderer) {
        if (rendersOnSeparateLines) renderer.appendIndent();
        renderer.append("<").append(name);
        if (attributes != null && !attributes.isEmpty()) {
            renderer.append(" ");
            attributes.render(renderer);
        }
        renderer.append(">");
        if (content != null) {
            if (content.rendersOnSeparateLines()) renderer.nl().in();
            content.render(renderer);
            if (content.rendersOnSeparateLines()) {
                renderer.out();
                if (close) renderer.appendIndent();
            }
        }
        if (close) {
            renderer.append("</").append(name).append(">");
        }
        if (rendersOnSeparateLines) renderer.nl();
    }
}
