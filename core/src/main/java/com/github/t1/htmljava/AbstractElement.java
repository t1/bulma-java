package com.github.t1.htmljava;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.github.t1.htmljava.Attribute.StringAttribute.unsafeStringAttribute;
import static com.github.t1.htmljava.HtmlBasics.div;
import static com.github.t1.htmljava.Renderable.ConcatenatedRenderable.concat;
import static com.github.t1.htmljava.Renderable.RenderableString.string;

@Accessors(fluent = true, chain = true) @SuperBuilder(toBuilder = true)
public class AbstractElement<SELF extends AbstractElement<?>> implements Renderable {
    /// A convenience method-chain to _copy_ modifiers from one element to another.
    public static CopyModifierStep1 copy(Modifier... modifier) {return new CopyModifierStep1(modifier);}

    public record CopyModifierStep1(Modifier[] modifiers) {
        public CopyModifierStep2 from(AbstractElement<?> source) {return new CopyModifierStep2(modifiers, source);}
    }

    public record CopyModifierStep2(Modifier[] modifiers, AbstractElement<?> source) {
        public void to(AbstractElement<?> target) {
            Stream.of(modifiers).filter(source::hasModifier).forEach(target::is);
        }
    }

    /// A convenience method-chain to _move_ modifiers from one element to another.
    public static MoveModifierStep1 move(Modifier... modifier) {return new MoveModifierStep1(modifier);}

    public record MoveModifierStep1(Modifier[] modifiers) {
        public MoveModifierStep2 from(AbstractElement<?> source) {return new MoveModifierStep2(modifiers, source);}
    }

    public record MoveModifierStep2(Modifier[] modifiers, AbstractElement<?> source) {
        public void to(AbstractElement<?> target) {
            Stream.of(modifiers).filter(source::hasModifier).forEach(modifier -> {
                source.not(modifier);
                target.is(modifier);
            });
        }
    }


    private boolean close;

    @Getter private boolean rendersOnSeparateLines;

    @Getter @NonNull private String tagName;

    @Getter private Attributes attributes;

    private Renderable content;

    // TODO get rid of the mapFunction mechanism... overload #content(Renderable content, boolean first) instead
    @NonNull private Function<Renderable, Renderable> mapFunction;

    protected AbstractElement(@NonNull String tagName, String... classes) {this(tagName, Attributes.of(Classes.of(classes)));}

    protected AbstractElement(@NonNull String tagName, Attributes attributes) {this(tagName, attributes, null);}

    protected AbstractElement(@NonNull String tagName, Attributes attributes, Renderable content) {this(tagName, attributes, content, Function.identity());}

    protected AbstractElement(@NonNull String tagName, Attributes attributes, Renderable content, Function<Renderable, Renderable> mapFunction) {
        this.close = true;
        this.rendersOnSeparateLines = true;
        this.tagName = tagName;
        this.attributes = attributes;
        this.content = content;
        this.mapFunction = mapFunction;
    }

    /// Clone, but don't use Cloneable; and `copy` is already something else
    public AbstractElement<SELF> twin() {return toBuilder().attributes(attributes().twin()).build();}

    @Override public String toString() {return render();}

    public boolean hasTagName(String name) {return this.tagName.equals(name);}

    public boolean hasAttribute(String name) {return attributes.hasAttribute(name);}

    public Optional<Attribute> findAttribute(String name) {return attributes.findAttribute(name);}

    public boolean hasAttribute(String name, String value) {return attributes.hasAttribute(name, value);}

    public boolean hasModifier(Modifier modifier) {return modifier.check(this);}

    public boolean hasClass(String name) {
        return Optional.ofNullable(getClasses())
                .map(classes -> classes.hasClass(name))
                .orElse(false);
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

    public SELF classes(Stream<String> classes) {return classes(Classes.of(classes));}

    private SELF classes(Classes classes) {return (classes == null || classes.empty()) ? self() : attr(classes);}

    public SELF notClasses(String... classes) {return notClasses(Classes.of(classes));}

    public SELF notClasses(Classes removing) {
        if (attributes != null) {
            attributes.find(Classes.class).ifPresent(existing -> {
                var removed = existing.minus(removing);
                if (removed.empty()) attributes.remove(existing);
                else attributes.replace(existing, removed);
            });
        }
        return self();
    }

    public SELF has(Modifier... modifiers) {return is(modifiers);}

    public SELF has(Stream<Modifier> modifiers) {return is(modifiers);}

    public SELF is(Modifier... modifiers) {return is(Stream.of(modifiers));}

    public SELF is(Stream<Modifier> modifiers) {
        modifiers.filter(Objects::nonNull).forEach(m -> m.apply(this));
        return self();
    }

    public SELF not(Modifier... modifiers) {return not(Stream.of(modifiers));}

    public SELF not(Stream<Modifier> modifiers) {
        modifiers.forEach(m -> m.unapply(this));
        return self();
    }

    public SELF style(String style) {return attr("style", style);}

    public SELF ariaHidden(boolean hidden) {return ariaHidden(Boolean.toString(hidden));}

    public SELF ariaHidden(String hidden) {return attr("aria-hidden", hidden);}

    public SELF ariaLabel(String label) {return attr("aria-label", label);}

    public SELF autofocus() {return attr("autofocus");}

    public SELF attr(String name) {return attr(Attribute.of(name));}

    public SELF attr(String name, String value) {return attr(Attribute.of(name, value));}

    public SELF attr(Attribute attribute) {
        if (attributes == null) Attributes.of(attribute);
        else attributes.add(attribute);
        return self();
    }

    public SELF attrs(Attribute... attributes) {return attrs(Stream.of(attributes));}

    public SELF attrs(Stream<Attribute> attributes) {
        attributes.forEach(this::attr);
        return self();
    }

    public SELF dataValue(String value) {return attr("data-value", value);}

    public SELF disabled() {return attr("disabled");}

    public SELF tabindex(int tabindex) {return attr("tabindex", Integer.toString(tabindex));}


    public SELF onclick(String action) {return on("click", action);}

    public SELF onkeyup(String key, String action) {return onkey("up", key, action);}

    public SELF onkeydown(String key, String action) {return onkey("down", key, action);}

    public SELF onkey(String eventType, String key, String action) {
        // `event` is officially deprecated, but seems to be okay to use: https://stackoverflow.com/a/58341967/3333174
        return on("key" + eventType, "if (event.key === '" + key + "') { " + action + " }");
    }

    public SELF on(String event, String action) {
        return attr(unsafeStringAttribute("on" + event, action));
    }

    public SELF with(Consumer<SELF> consumer) {
        consumer.accept(self());
        return self();
    }

    /// A function that is applied to each content element before it is added.
    /// This allows to, e.g., add classes to the content or wrap it with an additional element.
    public SELF map(Function<Renderable, Renderable> function) {
        this.mapFunction = function;
        return self();
    }

    public SELF content(String content) {return content(string(content));}

    public SELF content(Renderable... content) {return content(Arrays.stream(content));}

    public SELF content(Stream<? extends Renderable> content) {
        content.filter(Objects::nonNull).forEach(this::content);
        return self();
    }

    public SELF content(Collection<? extends Renderable> content) {return content(content.stream());}

    public SELF content(Renderable content) {return content(content, LAST);}

    /// Add the given content to the existing content at that index (too big numbers are appended).
    /// This is the central method to add content to an element; all other methods delegate to this one,
    /// so this is the perfect method to override in subclasses to change the way content is added.
    public SELF content(Renderable content, int index) {
        assert index >= 0;
        var mapped = mapFunction.apply(content);
        this.content = (this.content == null) ? mapped :
                this.content instanceof ConcatenatedRenderable concatenated ? concatenated.plus(mapped, index) :
                        (index == 0) ? concat(mapped, this.content) : concat(this.content, mapped);
        return self();
    }

    public final SELF content(String className, Function<AbstractElement<?>, AbstractElement<?>> wrapper) {
        return content(e -> e.hasClass(className), wrapper, () -> div().classes(className));
    }

    public final SELF content(
            Predicate<AbstractElement<?>> predicate,
            Function<AbstractElement<?>, AbstractElement<?>> wrapper,
            Supplier<AbstractElement<?>> generator) {
        var element = findElement(predicate);
        element.ifPresentOrElse(wrapper::apply, () -> content(wrapper.apply(generator.get())));
        return self();
    }

    public final SELF setContent(Renderable content) {
        this.content = null;
        return content(content);
    }

    public <T extends AbstractElement<?>> T getOrCreate(String className) {
        //noinspection unchecked
        return (T) getOrCreate(className, HtmlBasics::div);
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
        try (var ignored = renderOpeningTag(renderer, contentRendersOnSeparateLines())) {
            renderContent(renderer);
        }
    }

    public TagContinuation renderOpeningTag(Renderer renderer, boolean contentRendersOnSeparateLines) {
        if (rendersOnSeparateLines()) renderer.indent();
        renderer.unsafeAppend("<").safeAppend(tagName);
        if (attributes != null && !attributes.isEmpty()) {
            renderer.unsafeAppend(" ");
            attributes.render(renderer);
            if (slashOpeningTag()) renderer.unsafeAppend(" /");
        }
        renderer.unsafeAppend(">");
        return new TagContinuation(renderer, contentRendersOnSeparateLines);
    }

    /// Helper class to help with indentation when manually rendering the content of tags.
    public class TagContinuation implements AutoCloseable {
        private final Renderer renderer;
        private final boolean contentRendersOnSeparateLines;

        public TagContinuation(Renderer renderer, boolean contentRendersOnSeparateLines) {
            this.renderer = renderer;
            this.contentRendersOnSeparateLines = contentRendersOnSeparateLines;
            if (contentRendersOnSeparateLines) renderer.nl().in();
        }

        @Override public void close() {
            renderClosingTag(renderer, contentRendersOnSeparateLines);
        }
    }

    public void renderContent(Renderer renderer) {
        if (content != null) content.render(renderer);
    }

    public void renderClosingTag(Renderer renderer, boolean contentRendersOnSeparateLines) {
        if (contentRendersOnSeparateLines) renderer.out();
        if (close) {
            if (contentRendersOnSeparateLines) renderer.indent();
            renderer.unsafeAppend("</").safeAppend(tagName).unsafeAppend(">");
        }
        if (rendersOnSeparateLines) renderer.nl();
    }

    protected boolean contentRendersOnSeparateLines() {return content != null && content.rendersOnSeparateLines();}

    protected boolean slashOpeningTag() {return false;}
}
