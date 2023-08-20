package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.*;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Attribute.noValueAttribute;
import static com.github.t1.bulmajava.basic.State.LOADING;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Select extends AbstractElement<Select> {

    private static final Function<Select, Element> CONTENT_ELEMENT = Select::contentElement;

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


    public Select options(String... options) {
        var out = this;
        for (var option : options) out = out.option(option);
        return out;
    }

    public Select option(String text) {return option(text, text);}

    public Select option(String value, String text) {
        var option = Basic.element("option");
        if (value != null) option = option.attr("value", value);
        return content(contentElement().contains(option.contains(text)));
    }

    @Deprecated
    @Override public Select contains(Renderable... content) {
        throw new UnsupportedOperationException("select elements can only contain options");
    }

    /** Marks the last option as <code>selected</code> */
    public Select selected() {return replace(lastOption(), Select::selectedAttr);}

    private Element lastOption() {
        return (contentElement().content() instanceof ConcatenatedRenderable c) ? (Element) c.last() : (Element) contentElement().content();
    }

    private static AbstractElement<?> selectedAttr(AbstractElement<?> option) {return option.attr(noValueAttribute("selected"));}

    public Select multiple(int size) {
        return multiple().replace(CONTENT_ELEMENT, s -> s.attr("size", Integer.toString(size)));
    }

    public Select multiple() {
        return classes("is-multiple").replace(CONTENT_ELEMENT, s -> s.attr(noValueAttribute("multiple")));
    }

    @Override public Select is(Modifier... modifiers) {
        var out = this;
        for (var modifier : modifiers)
            if (modifier instanceof State && modifier != LOADING) out = out.withS(i -> i.is(modifier));
            else out = out.classes(modifier.className());
        return out;
    }

    private Select withS(Function<Element, Element> function) {return content(function.apply((Element) content()));}
}
