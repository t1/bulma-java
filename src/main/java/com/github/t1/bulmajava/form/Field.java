package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.*;
import com.github.t1.bulmajava.elements.Icon;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Alignment.*;
import static com.github.t1.bulmajava.basic.Basic.p;
import static com.github.t1.bulmajava.basic.Renderable.ConcatenatedRenderable.concat;
import static com.github.t1.bulmajava.basic.Size.SMALL;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Field extends AbstractElement<Field> {
    public static final Modifier EXPANDED = () -> "expanded";


    public static Element fieldset() {return Basic.element("fieldset");}

    public static Field field() {return new Field();}


    public Field() {super("div", "field");}


    /** Adds the <code>is-grouped</code> class and creates separate controls for the content */
    public Field grouped() {return is(() -> "grouped");}

    public Field label(String name, Modifier... modifiers) {return super.contains(Basic.label(name).is(modifiers));}

    public Field help(String text, Modifier... modifiers) {return super.contains(p(text).classes("help").is(modifiers));}

    /** Use {@link #containsControl(Renderable, Modifier...)} instead! */
    @Deprecated @Override public Field contains(Renderable content) {return containsControl(content);}

    /** Use {@link #containsControl(Renderable, Modifier...)} instead! */
    @Deprecated @Override public Field contains(Renderable... content) {return super.contains(content);}

    public Field containsControl(Renderable content, Modifier... modifiers) {
        if (hasClass("is-grouped")) return super.contains(Basic.control().is(modifiers).contains(content));
        return control(control -> control.is(modifiers).contains(content));
    }

    private Field control(Function<AbstractElement<?>, AbstractElement<?>> function) {
        var control = findContent("control");
        return control.isPresent() ? replace(control.get(), function) : super.contains(function.apply(Basic.control()));
    }

    public Field iconLeft(String iconName, Modifier... modifiers) {return icon(iconName, LEFT, modifiers);}

    public Field iconRight(String iconName, Modifier... modifiers) {return icon(iconName, RIGHT, modifiers);}

    private Field icon(String iconName, Alignment alignment, Modifier... modifiers) {
        assert alignment != CENTERED;
        return control(control -> control.classes("has-icons-" + alignment.key()).contains(
                Icon.icon(iconName).is(SMALL).is(modifiers).is(alignment)));
    }

    /**
     * Uou must call this after all the regular {@link #contains(Renderable)}, as we can't distinguish between the
     * different controls in the field.
     *
     * @implNote We'd need some sort of meta-data mechanism for that
     */
    public Field containsAddonLeft(Renderable content, Modifier... modifiers) {
        return classes("has-addons").content(concat(Basic.control().is(modifiers).contains(content), this.content()));
    }

    public Field containsAddonRight(Renderable content, Modifier... modifiers) {
        return classes("has-addons").content(concat(this.content(), Basic.control().is(modifiers).contains(content)));
    }
}
