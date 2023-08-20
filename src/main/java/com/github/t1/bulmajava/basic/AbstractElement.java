package com.github.t1.bulmajava.basic;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Attribute.StringAttribute.stringAttribute;
import static com.github.t1.bulmajava.basic.Attribute.noValueAttribute;
import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Renderable.ConcatenatedRenderable.concat;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;

@Accessors(fluent = true) @SuperBuilder(toBuilder = true)
public class AbstractElement<SELF extends AbstractElement<?>> implements Renderable {

    private final boolean close;
    @Getter private final boolean rendersOnSeparateLines;
    @NonNull private final String name;
    private final Attributes attributes;
    @Getter private final Renderable content;
    // TODO get rid of the mapFunction mechanism
    @NonNull private final Function<Renderable, Renderable> mapFunction;

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

    public boolean hasClass(String name) {
        return attributes != null && attributes.find(Classes.class).map(classes -> classes.hasClass(name)).orElse(false);
    }

    public Element contentElement() {return (Element) content;}

    public ConcatenatedRenderable concatContent() {return (ConcatenatedRenderable) content;}


    @SuppressWarnings("unchecked")
    protected SELF self() {return (SELF) this;}


    public SELF close(boolean close) {return with(e -> e.close(close));}

    public SELF rendersOnSeparateLines(boolean b) {return with(e -> e.rendersOnSeparateLines(b));}

    public SELF attributes(Attributes attributes) {return with(e -> e.attributes(attributes));}

    public SELF content(Renderable content) {return with(e -> e.content(content));}

    public SELF mapFunction(Function<Renderable, Renderable> mapFunction) {return with(e -> e.mapFunction(mapFunction));}

    private SELF with(Consumer<AbstractElementBuilder<?, ?, ?>> build) {
        var builder = self().toBuilder();
        build.accept(builder);
        //noinspection unchecked
        return (SELF) builder.build();
    }

    public SELF classes(String... classes) {return classes(Classes.of(classes));}

    private SELF classes(Classes classes) {return (classes == null || classes.empty()) ? self() : attr(classes);}

    public SELF is(Modifier... modifiers) {
        return classes(Stream.of(modifiers).map(Modifier::className).toArray(String[]::new));
    }

    public SELF ariaHidden() {
        return ((attributes != null) && attributes.hasAttribute("aria-hidden", "true")) ? self() : attr("aria-hidden", "true");
    }

    public SELF ariaLabel(String label) {return attr("aria-label", label);}

    public SELF attr(String name, String value) {return attr(stringAttribute(name, value));}

    public SELF attr(Attribute attribute) {
        return attributes(attributes == null ? Attributes.of(attribute) : attributes.and(attribute));
    }

    public SELF disabled() {return attr(noValueAttribute("disabled"));}

    public SELF size(int size) {return classes("is-" + size);}

    public SELF contains(String content) {return contains(string(content));}

    public SELF contains(Stream<Renderable> content) {return contains(content.toArray(Renderable[]::new));}

    @SuppressWarnings("unchecked")
    public SELF contains(Renderable... content) {
        var out = self();
        for (var renderable : content)
            if (renderable != null)
                out = (SELF) out.contains(renderable);
        return out;
    }

    public SELF contains(Renderable content) {
        var mapped = mapFunction.apply(content);
        return content((this.content == null) ? mapped : concat(this.content, mapped));
    }

    public SELF id(String id) {return attr("id", id);}

    public SELF map(Function<Renderable, Renderable> function) {return mapFunction(function);}

    public <ELEMENT extends AbstractElement<?>> SELF replace(ELEMENT existing, Function<ELEMENT, ELEMENT> function) {
        return replace((Function<SELF, ELEMENT>) x -> existing, function);
    }

    public <ELEMENT extends AbstractElement<?>> SELF replace(Function<SELF, ELEMENT> existing, Function<ELEMENT, ELEMENT> function) {
        var existingElement = existing.apply(self());
        return replace(existingElement, function.apply(existingElement));
    }

    public SELF replace(Renderable existing, Renderable replacement) {
        return content(content.equals(existing) ? replacement : content.replace(existing, replacement));
    }

    public SELF style(String style) {return attr("style", style);}

    public SELF notClasses(String... classes) {return notClasses(Classes.of(classes));}

    public SELF notClasses(Classes classes) {
        if (attributes == null) return self();
        return attributes.find(Classes.class)
                .map(existing -> attributes(attributes.replace(existing, existing.minus(classes))))
                .orElse(self());
    }

    public SELF dataValue(String value) {return attr("data-value", value);}


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

    public Optional<AbstractElement<?>> findContent(String className) {return findContent(e -> e.hasClass(className));}

    public Optional<AbstractElement<?>> findContent(Predicate<AbstractElement<?>> predicate) {
        return (content() == null) ? Optional.empty() :
                content().find(renderable -> renderable instanceof AbstractElement<?> e && predicate.test(e))
                        .map(obj -> (AbstractElement<?>) obj);
    }

    public SELF element(String className, Function<AbstractElement<?>, AbstractElement<?>> function) {
        return element(e -> e.hasClass(className), function, () -> div().classes(className));
    }

    public SELF element(
            Predicate<AbstractElement<?>> predicate,
            Function<AbstractElement<?>, AbstractElement<?>> function,
            Supplier<AbstractElement<?>> generator) {
        var element = findContent(predicate);
        return element.isPresent() ? replace(element.get(), function) : contains(function.apply(generator.get()));
    }

    public SELF hasText(Modifier modifier) {return classes("has-text-" + modifier.key());}

    public SELF hasBackground(Modifier modifier) {return classes("has-background-" + modifier.key());}
}
