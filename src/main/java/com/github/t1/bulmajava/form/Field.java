package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.*;
import com.github.t1.bulmajava.elements.Icon;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Alignment.*;
import static com.github.t1.bulmajava.basic.Basic.p;
import static com.github.t1.bulmajava.basic.Size.SMALL;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Field extends AbstractElement<Field> {
    public static final Modifier EXPANDED = () -> "expanded";


    public static Element fieldset() {return Basic.element("fieldset");}

    public static Field field() {return new Field();}


    public Field() {super("div", "field");}


    /** Adds the <code>is-grouped</code> class and creates separate controls for the content */
    public Field grouped() {return is(() -> "grouped");}

    public Field label(String name, Modifier... modifiers) {return super.content(Basic.label(name).is(modifiers));}

    public Field help(String text, Modifier... modifiers) {return super.content(p(text).classes("help").is(modifiers));}

    /** Use {@link #control(Renderable, Modifier...)} instead! */
    @Deprecated @Override public Field content(Renderable content) {return super.content(content);}

    /** Use {@link #control(Renderable, Modifier...)} instead! */
    @Deprecated @Override public Field content(Renderable... content) {return super.content(content);}

    /** Use {@link #control(Renderable, Modifier...)} instead! */
    @Deprecated @Override public Field content(Stream<Renderable> content) {return super.content(content);}

    /** Use {@link #control(Renderable, Modifier...)} instead! */
    @Deprecated @Override public Field content(String content) {return super.content(content);}

    public Field control(Stream<Renderable> content, Modifier... modifiers) {
        content.forEach(c -> control(c, modifiers));
        return this;
    }

    public Field control(Renderable content, Modifier... modifiers) {
        if (hasClass("is-grouped")) return super.content(newControl(content, modifiers));
        return control(control -> control.is(modifiers).content(content));
    }

    private static Element newControl(Renderable content, Modifier... modifiers) {
        return Basic.control().is(modifiers).content(content);
    }

    private Field control(Function<AbstractElement<?>, AbstractElement<?>> function) {
        getOrCreate("control", Basic::control);
        findElement("control").ifPresentOrElse(function::apply,
                () -> super.content(function.apply(Basic.control())));
        return this;
    }

    public Field iconLeft(String iconName, Modifier... modifiers) {return icon(iconName, LEFT, modifiers);}

    public Field iconRight(String iconName, Modifier... modifiers) {return icon(iconName, RIGHT, modifiers);}

    private Field icon(String iconName, Alignment alignment, Modifier... modifiers) {
        assert alignment != CENTERED;
        return control(control -> control.classes("has-icons-" + alignment.key()).content(
                Icon.icon(iconName).is(SMALL).is(modifiers).is(alignment)));
    }

    /**
     * You must call this after all the regular {@link #content(Renderable)}, as we can't distinguish between the
     * different controls in the field.
     *
     * @implNote We'd need some sort of meta-data mechanism for that
     */
    public Field containsAddonLeft(Renderable content, Modifier... modifiers) {
        return firstContent(newControl(content, modifiers)).classes("has-addons");
    }

    public Field containsAddonRight(Renderable content, Modifier... modifiers) {
        return super.content(newControl(content, modifiers)).classes("has-addons");
    }
}
