package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.Alignment;
import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.bulmajava.basic.HasModifier;
import com.github.t1.bulmajava.basic.IsModifier;
import com.github.t1.bulmajava.basic.Size;
import com.github.t1.bulmajava.elements.Icon;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Anchor;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Modifier;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Alignment.CENTERED;
import static com.github.t1.bulmajava.basic.Alignment.LEFT;
import static com.github.t1.bulmajava.basic.Alignment.RIGHT;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.State.LOADING;
import static com.github.t1.bulmajava.elements.Button.BUTTON;
import static com.github.t1.htmljava.HtmlBasics.div;
import static com.github.t1.htmljava.HtmlBasics.element;
import static com.github.t1.htmljava.HtmlBasics.p;

/// We try to reduce the complexity of building Bulma fields:
/// * We add nested `field` elements automatically when necessary, so you don't have to.
/// * We automatically create a `control` element for you, so you don't have to.
/// * You can set the `size` of the field, and it will be applied to the `label`,
///  as well as `input` and `button` elements within.
@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Field extends BulmaElement<Field> {
    public static final IsModifier EXPANDED = () -> "expanded";
    private static final IsModifier GROUPED = () -> "grouped";
    private static final IsModifier GROUPED_MULTILINE = () -> "grouped-multiline";
    private static final IsModifier HORIZONTAL = () -> "horizontal";
    private static final HasModifier HAS_ADDONS = () -> "addons";

    public static Element fieldset() {return element("fieldset");}

    public static Field field() {return new Field();}


    public static Field group() {return field().is(GROUPED);}

    public static Field multilineGroup() {return group().is(GROUPED_MULTILINE);}


    private Size size;

    public Field() {super("div", "field");}

    @Override public Field content(Renderable content, boolean first) {
        if (content instanceof Field) {
            throw new IllegalArgumentException("There's no need to manually nest fields; we'll do that for you when necessary");
        }
        if (content instanceof AbstractElement<?> element
            && (element.hasClass("field-label") || element.hasClass("label") || element.hasClass("help"))) {
            return super.content(content, first);
        }

        AbstractElement<?> control = control().content(content);
        // all radios must go into a single control
        if (content instanceof Radio radio) {
            var existing = findElement("control");
            if (existing.isPresent()) {
                existing.get().content(radio);
                return this;
            }
        }

        if (hasModifier(HORIZONTAL)) {
            control = div().classes("field-body").content(div().classes("field").content(control));
        }
        if (content instanceof AbstractElement<?> element) {
            element.is(size);
            if (element instanceof Anchor) element.is(BUTTON);
            if (element.hasModifier(BUTTON) || element.hasClass("select") || element instanceof Input) {
                move(HAS_ADDONS).from(element).to(this); // the field
            }
            move(EXPANDED).from(element).to(control);
            if (element.hasModifier(LOADING)) {
                copy(Size.values()).from(element).to(control);
                move(LOADING).from(element).to(control);
            }
        }
        return super.content(control, first);
    }

    @Override public Field is(Modifier... modifiers) {
        // TODO maybe introduce a Modifiers class similar to Attributes and Classes
        for (int i = 0; i < modifiers.length; i++) {
            var modifier = modifiers[i];
            if (modifier instanceof Size s) {
                if (this.size != null)
                    throw new IllegalArgumentException("Field already has size " + this.size + ", cannot set to " + s);
                this.size = s;
                modifiers[i] = null; // remove size from modifiers
            }
        }
        return super.is(modifiers);
    }

    public Field grouped() {return is(GROUPED);}

    public Field groupedRight() {return grouped().classes("is-grouped-right");}

    public Field groupedCentered() {return grouped().classes("is-grouped-centered");}

    public Field groupedMultiline() {return grouped().classes("is-grouped-multiline");}

    public Field horizontal() {return super.content(div().classes("field-label")).is(HORIZONTAL);}

    public Field label(String name, Modifier... modifiers) {
        var label = element("label").classes("label").content(name);
        if (hasModifier(HORIZONTAL))
            findElement("field-label").orElseThrow() // created in #horizontal()
                    .is(modifiers).is(size).content(label);
        else super.content(label.is(modifiers).is(size));
        return this;
    }

    public Field help(String text, Modifier... modifiers) {return help(p(text), modifiers);}

    public Field help(AbstractElement<?> content, Modifier... modifiers) {return content(content.classes("help").is(modifiers));}

    private AbstractElement<?> fieldBody() {
        return findElement(e -> e.hasClass("field-body") || e.hasClass("control"))
                .orElseGet(() -> hasModifier(HORIZONTAL) ? div().classes("field-body") : control());
    }

    public Field iconLeft(String iconName, Modifier... modifiers) {return iconLeft(Icon.icon(iconName), modifiers);}

    public Field iconLeft(Icon icon, Modifier... modifiers) {return icon(icon, LEFT, modifiers);}

    public Field iconRight(String iconName, Modifier... modifiers) {return iconRight(Icon.icon(iconName), modifiers);}

    public Field iconRight(Icon icon, Modifier... modifiers) {return icon(icon, RIGHT, modifiers);}

    private Field icon(Icon icon, Alignment alignment, Modifier... modifiers) {
        assert alignment != CENTERED;
        fieldBody().classes("has-icons-" + alignment.key())
                .content(icon.is(SMALL).is(modifiers).is(alignment));
        return this;
    }

    public Field addonLeft(AbstractElement<?> content) {return firstContent(content.has(HAS_ADDONS));}

    public Field addonRight(AbstractElement<?> content) {return content(content.has(HAS_ADDONS));}
}
