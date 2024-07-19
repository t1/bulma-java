package com.github.t1.bulmajava.form;


import com.github.t1.bulmajava.basic.Alignment;
import com.github.t1.bulmajava.elements.Icon;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Basic;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Modifier;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Alignment.CENTERED;
import static com.github.t1.bulmajava.basic.Alignment.LEFT;
import static com.github.t1.bulmajava.basic.Alignment.RIGHT;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.htmljava.Basic.div;
import static com.github.t1.htmljava.Basic.p;

/**
 * The Bulma docs is not explicit about the <code>field-label</code> and <code>field-body</code> classes,
 * but they seem to be required when the field itself is {@link #horizontal()}
 */
@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Field extends AbstractElement<Field> {
    public static final Modifier EXPANDED = () -> "expanded";


    public static Element fieldset() {return Basic.element("fieldset");}

    public static Field field() {return new Field();}


    public Field() {super("div", "field");}


    /** Adds the <code>is-grouped</code> class and creates separate controls for the content */
    public Field grouped() {return is(() -> "grouped");}

    public Field groupedRight() {return grouped().classes("is-grouped-right");}

    public Field groupedCentered() {return grouped().classes("is-grouped-centered");}

    public Field groupedMultiline() {return grouped().classes("is-grouped-multiline");}

    /** Adds the <code>is-horizontal</code> class and already adds an empty <code>field-label</code> */
    public Field horizontal() {return super.content(div().classes("field-label")).classes("is-horizontal");}

    public Field label(String name, Modifier... modifiers) {
        if (this.hasClass("is-horizontal"))
            findElement("field-label").orElseThrow() // created in #horizontal()
                    .is(modifiers).content(Basic.label(name));
        else super.content(Basic.label(name).is(modifiers));
        return this;
    }

    public Field help(String text, Modifier... modifiers) {return help(p(text), modifiers);}

    public Field help(AbstractElement<?> content, Modifier... modifiers) {return super.content(content.classes("help").is(modifiers));}

    /** Use {@link #control(AbstractElement, Modifier...)} instead! */
    @Deprecated @Override public Field content(Renderable content) {return super.content(content);}

    /** Use {@link #control(AbstractElement, Modifier...)} instead! */
    @Deprecated @Override public Field content(Renderable... content) {return super.content(content);}

    /** Use {@link #control(AbstractElement, Modifier...)} instead! */
    @Deprecated @Override public Field content(Stream<? extends Renderable> content) {return super.content(content);}

    /** Use {@link #control(AbstractElement, Modifier...)} instead! */
    @Deprecated @Override public Field content(String content) {return super.content(content);}

    public Field control(Stream<AbstractElement<?>> content, Modifier... modifiers) {
        content.forEach(c -> control(c, modifiers));
        return this;
    }

    public Field control(AbstractElement<?> content, Modifier... modifiers) {
        if (this.hasClass("is-grouped")) return super.content(Basic.control().is(modifiers).content(content));
        return control(control -> control.is(modifiers).content(content));
    }

    private Field control(Function<AbstractElement<?>, AbstractElement<?>> function) {
        var control = getOrCreate(e -> e.hasClass("control") || e.hasClass("field-body"), this::body);
        function.apply(control);
        return this;
    }

    private Element body() {
        return (this.hasClass("is-horizontal")) ? div().classes("field-body") : Basic.control();
    }

    public Field iconLeft(String iconName, Modifier... modifiers) {return iconLeft(Icon.icon(iconName), modifiers);}

    public Field iconLeft(Icon icon, Modifier... modifiers) {return icon(icon, LEFT, modifiers);}

    public Field iconRight(String iconName, Modifier... modifiers) {return iconRight(Icon.icon(iconName), modifiers);}

    public Field iconRight(Icon icon, Modifier... modifiers) {return icon(icon, RIGHT, modifiers);}

    private Field icon(Icon icon, Alignment alignment, Modifier... modifiers) {
        assert alignment != CENTERED;
        return control(control -> control.classes("has-icons-" + alignment.key()).content(
                icon.is(SMALL).is(modifiers).is(alignment)));
    }

    /**
     * You must call this after all the regular {@link #content(Renderable)}, as we can't distinguish between the
     * different controls in the field.
     *
     * @implNote We'd need some sort of meta-data mechanism for that
     */
    public Field containsAddonLeft(AbstractElement<?> content, Modifier... modifiers) {
        return firstContent(Basic.control().is(modifiers).content(content)).classes("has-addons");
    }

    public Field containsAddonRight(AbstractElement<?> content, Modifier... modifiers) {
        return super.content(Basic.control().is(modifiers).content(content)).classes("has-addons");
    }
}
