package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.State;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Attributes;
import com.github.t1.htmljava.Basic;
import com.github.t1.htmljava.Classes;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Modifier;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.State.LOADING;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Select extends AbstractElement<Select> {

    private static final Function<Select, Element> CONTENT_ELEMENT = select -> select.contentAs(Element.class);

    /**
     * It generally makes sense to give the select a name; if you really don't want it, pass <code>null</code>.
     */
    public static Select select(String name) {return new Select(name);}


    private Select(String name) {
        super("div", Attributes.of(Classes.of("select")), selectElement(name));
    }

    private static Element selectElement(String name) {
        var select = Basic.element("select");
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
        var option = Basic.element("option");
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

    /** Use {@link #options(String...)} )} instead! */
    @Deprecated
    @Override public Select content(Renderable... content) {return super.content(content);}

    /** Use {@link #options(String...)} )} instead! */
    @Deprecated
    @Override public Select content(Stream<? extends Renderable> content) {return super.content(content);}

    /** Marks the last option as <code>selected</code> */
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
        for (var modifier : modifiers)
            if (modifier instanceof State && modifier != LOADING) withS(i -> i.is(modifier));
            else classes(modifier.className());
        return this;
    }

    private void withS(Function<Element, Element> function) {function.apply(contentAs(Element.class));}
}
