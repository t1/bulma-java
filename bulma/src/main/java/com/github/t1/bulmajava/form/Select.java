package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.bulmajava.basic.State;
import com.github.t1.htmljava.Attributes;
import com.github.t1.htmljava.Classes;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Modifier;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.State.LOADING;
import static com.github.t1.htmljava.HtmlBasics.element;

/// Use `option()` and `options()` to add choices, not `content()`.
/// Modifiers applied via `is()` are forwarded to the inner `<select>` element.
///
/// @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/select">Select</a>
@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Select extends BulmaElement<Select> {

    private static final Function<Select, Element> CONTENT_ELEMENT = select -> select.contentAs(Element.class);

    /// It generally makes sense to give the select a name; if you really don't want it, pass `null`.
    public static Select select(String name) {return new Select(name);}


    private Select(String name) {
        super("div", Attributes.of(Classes.of("select")), selectElement(name));
    }

    private static Element selectElement(String name) {
        var select = element("select");
        if (name != null) select = select.attr("name", name);
        return select;
    }


    public Select options(Stream<String> options) {
        options.forEach(this::option);
        return this;
    }

    public Select options(String... options) {return options(Arrays.stream(options));}

    public Select options(Collection<String> options) {return options(options.stream());}

    public Select option(String text) {return option(text, text);}

    public Select option(String value, String text) {
        var option = element("option");
        if (value != null) option = option.attr("value", value);
        contentAs(Element.class).content(option.content(text));
        return this;
    }

    /** Use {@link #option(String)} or {@link #option(String, String)} instead! */
    @Deprecated
    @Override public Select content(String content) {return super.content(content);}

    /** Use {@link #option(String)} or {@link #option(String, String)} instead! */
    @Deprecated
    @Override public Select content(Renderable content) {
        throw new UnsupportedOperationException("select elements can only contain options");
    }

    /** Use {@link #options(String...)} instead! */
    @Deprecated
    @Override public Select content(Renderable... content) {return super.content(content);}

    /// Use {@link #options(String...)} instead!
    @Deprecated
    @Override public Select content(Stream<? extends Renderable> content) {return super.content(content);}

    /// Mark the option with that value as selected
    public Select selected(String optionValue) {
        if (optionValue == null) options()
                .filter(option -> option.hasAttribute("selected"))
                .filter(option -> option.hasAttribute("value"))
                .forEach(option -> option.attributes().remove("selected"));
        else findOption(optionValue)
                .orElseThrow(() -> new IllegalArgumentException("no option with value '" + optionValue + "'")).attr("selected");
        return this;
    }

    public Optional<Element> findOption(String optionValue) {
        return options()
                .filter(option -> option.hasAttribute("value", optionValue))
                .findAny();
    }

    public Stream<Element> options() {
        var select = contentAs(Element.class);
        return select.contentIsA(ConcatenatedRenderable.class)
                ? select.contentAs(ConcatenatedRenderable.class).renderables().stream().map(Element.class::cast)
                : Stream.of(select.contentAs(Element.class));
    }

    /// Marks the last option as <code>selected</code>
    public Select selected() {
        lastOption().attr("selected");
        return this;
    }

    private Element lastOption() {
        var contentElement = contentAs(Element.class);
        return (Element) (contentElement.contentIsA(ConcatenatedRenderable.class) ?
                contentElement.contentAs(ConcatenatedRenderable.class).last() :
                contentElement.content());
    }

    public Select multiple(int size) {
        multiple();
        contentAs(Element.class).attr("size", Integer.toString(size));
        return this;
    }

    public Select multiple() {
        contentAs(Element.class).attr("multiple");
        return classes("is-multiple");
    }

    @Override public Select is(Modifier... modifiers) {
        for (var modifier : modifiers) {
            if (modifier instanceof State && modifier != LOADING)
                contentAs(Element.class).is(modifier);
            else super.is(modifier);
        }
        return this;
    }

    public Select required() {contentAs(Element.class).attr("required"); return this;}
}
