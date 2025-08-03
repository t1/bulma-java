package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.Alignment;
import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.bulmajava.basic.IsModifier;
import com.github.t1.bulmajava.elements.Icon;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Anchor;
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
import static com.github.t1.bulmajava.elements.Button.BUTTON;
import static com.github.t1.htmljava.HtmlBasics.div;
import static com.github.t1.htmljava.HtmlBasics.element;
import static com.github.t1.htmljava.HtmlBasics.p;

/**
 * The Bulma docs is not explicit about the <code>field-label</code> and <code>field-body</code> classes,
 * but they seem to be required when the field itself is {@link #horizontal()}
 */
@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Field extends BulmaElement<Field> {
    public static final IsModifier EXPANDED = () -> "expanded";
    private static final IsModifier GROUPED = () -> "grouped";
    private static final IsModifier GROUPED_MULTILINE = () -> "grouped-multiline";
    private static final IsModifier HORIZONTAL = () -> "horizontal";

    public static Element fieldset() {return element("fieldset");}

    public static Field field() {return new Field();}


    public static Field group() {return field().is(GROUPED);}

    public static Field multilineGroup() {return group().is(GROUPED_MULTILINE);}


    public Field() {super("div", "field");}


    public Field grouped() {return is(GROUPED);}

    public Field groupedRight() {return grouped().classes("is-grouped-right");}

    public Field groupedCentered() {return grouped().classes("is-grouped-centered");}

    public Field groupedMultiline() {return grouped().classes("is-grouped-multiline");}

    public Field horizontal() {return super.content(div().classes("field-label")).is(HORIZONTAL);}

    public Field label(String name, Modifier... modifiers) {
        var label = element("label").classes("label").content(name);
        if (hasModifier(HORIZONTAL)) findElement("field-label").orElseThrow() // created in #horizontal()
                .is(modifiers).content(label);
        else super.content(label.is(modifiers));
        return this;
    }

    public Field help(String text, Modifier... modifiers) {return help(p(text), modifiers);}

    public Field help(AbstractElement<?> content, Modifier... modifiers) {return super.content(content.classes("help").is(modifiers));}

    /** Use {@link #control(AbstractElement, Modifier...)} instead! */
    @Deprecated @Override public Field content(Renderable content) {return super.content(content);}

    /** Use {@link #controls(AbstractElement[])} instead! */
    @Deprecated @Override public Field content(Renderable... content) {return super.content(content);}

    /** Use {@link #controls(Stream, Modifier...)} instead! */
    @Deprecated @Override public Field content(Stream<? extends Renderable> content) {return super.content(content);}

    /** Use {@link #control(AbstractElement, Modifier...)} instead! */
    // TODO this method doesn't make sense here, as field content cannot be a simple string.
    //  this it true for may other types of abstract elements, so we should probably remove it from AbstractElement
    //  and add it to only those element types that can.
    @Deprecated @Override public Field content(String content) {return super.content(content);}

    // TODO maybe we can use the normal content methods and use a map function to wrap it in a control?
    //  The LOADING and SIZE modifiers would be handled with methods that hide the logic for applying the classes
    //  to the the control and/or element.
    public Field controls(AbstractElement<?>... content) {return controls(Stream.of(content));}

    public Field controls(Stream<AbstractElement<?>> content, Modifier... modifiers) {
        content.forEach(c -> control(c, modifiers));
        return this;
    }

    public Field control(AbstractElement<?> content, Modifier... modifiers) {
        if (content instanceof Anchor) content.is(BUTTON);
        if (this.hasModifier(GROUPED)) return super.content(control().is(modifiers).content(content));
        return control(control -> control.is(modifiers).content(content));
    }

    private Field control(Function<AbstractElement<?>, AbstractElement<?>> function) {
        var control = getOrCreate(e -> e.hasClass("control") || e.hasClass("field-body"), this::body);
        function.apply(control);
        return this;
    }

    private Element body() {return hasModifier(HORIZONTAL) ? div().classes("field-body") : control();}

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
     * <p>
     * TODO We'd need some sort of meta-data mechanism for that
     */
    public Field addonLeft(AbstractElement<?> content, Modifier... modifiers) {
        if (content instanceof Anchor) content.is(BUTTON);
        return firstContent(control().is(modifiers).content(content)).classes("has-addons");
    }

    public Field addonRight(AbstractElement<?> content, Modifier... modifiers) {
        if (content instanceof Anchor) content.is(BUTTON);
        return super.content(control().is(modifiers).content(content)).classes("has-addons");
    }
}
