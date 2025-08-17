package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.Alignment;
import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.bulmajava.basic.IsModifier;
import com.github.t1.bulmajava.basic.Size;
import com.github.t1.bulmajava.elements.Icon;
import com.github.t1.bulmajava.form.Radio.Radios;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Anchor;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Modifier;
import com.github.t1.htmljava.Renderable;
import com.github.t1.htmljava.Renderer;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static com.github.t1.bulmajava.basic.Alignment.LEFT;
import static com.github.t1.bulmajava.basic.Alignment.RIGHT;
import static com.github.t1.bulmajava.basic.Size.NORMAL;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.State.LOADING;
import static com.github.t1.bulmajava.elements.Button.BUTTON;
import static com.github.t1.htmljava.HtmlBasics.div;
import static com.github.t1.htmljava.HtmlBasics.element;
import static com.github.t1.htmljava.HtmlBasics.p;
import static com.github.t1.htmljava.Renderable.add;

/// The Bulma `field` element is a container for a label and one or more form controls with icons, addons, and a help text.
/// Most things are optional, but it requires quite some boilerplate markup to build;
/// so we provide a fluent API to make it much easier, allowing you to think of the things you want to see,
/// and this class will take care of the details.
///
/// The basic pattern is that you create a {@link #field(String)} with its label (or {@link #field()} without a label),
/// and then add your form controls (input, textarea, etc.) as {@link #content(Renderable)}.
/// After adding some content, you can decorate it with icons, addons, and/or a help text.
/// E.g., building a text field with an icon, an addon, and a help text looks like this:
/// ```java
/// field("User")
///     .content(input(TEXT).fieldName("username").required)
///     .iconLeft("user")
///     .addonRight(button("Search"))
///     .help("The username you want to search for", SUCCESS);
/// ```
///
/// You can only have one icon on each side of the input, but multiple addons.
/// The help text can be plain (with optional modifiers) or an arbitrary element, e.g., for styled text.
///
/// Sometimes, you may want to have several inputs for a single label. In that case, you can
/// add more `content` and then modify those with their icons, addons, or help. Multiple inputs can be styled as a
/// group of inputs by using {@link #grouped()} or {@link #groupedMultiline()}.
///
/// To place the labels left of the inputs instead of above, you can use {@link #horizontal()}.
@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Field extends BulmaElement<Field> {
    public static final IsModifier EXPANDED = () -> "expanded";

    // we could make GROUPED public, but GROUPED_MULTILINE also needs GROUPED, so to be consistent.
    private static final IsModifier GROUPED = () -> "grouped";
    private static final IsModifier GROUPED_MULTILINE = () -> "grouped-multiline";
    private static final IsModifier HORIZONTAL = () -> "horizontal";

    public static Predicate<Field> fieldName(String fieldName) {return field -> field.fieldControl(fieldName).isPresent();}

    // TODO maybe we could make usage of this fully automatic?
    public static Element control() {return div().is(CONTROL);}

    public static Element fieldset() {return element("fieldset");}

    @SuppressWarnings("RedundantCast") // lombok generates a second constructor taking a FieldBuilder
    public static Field field() {return new Field((String) null);}

    public static Field field(String label) {return new Field(label);}

    private final String label;
    private Size size;
    private boolean readonly;
    private final List<FieldControl> controls = new ArrayList<>();

    private FieldControl lastControl;

    public Field allReadonly() {readonly = true; return this;}

    @RequiredArgsConstructor @ToString
    private class FieldControl {
        private final AbstractElement<?> content;
        private FieldIcon leftIcon;
        private FieldIcon rightIcon;
        private final FieldAddons leftAddons = new FieldAddons();
        private final FieldAddons rightAddons = new FieldAddons();
        private FieldHelp help;

        public boolean noLabelPadding() {
            return content instanceof Radios || content instanceof Checkbox;
        }

        private boolean hasAddons() {return leftAddons.isPresent() || rightAddons.isPresent();}

        private void render(Renderer renderer) {
            var content = this.content.twin(); // we'll have to copy/move some modifiers
            var control = control().content(content);

            if (leftIcon != null) leftIcon.addTo(control, LEFT);
            if (rightIcon != null) rightIcon.addTo(control, RIGHT);

            var subField = hasModifier(HORIZONTAL)
                    ? div().classes("field").renderOpeningTag(renderer, true)
                    : null;
            // for a horizontal field with addons _and_ help, we need a sub-sub, and `has-addons` has to be copied there
            var subSubField = (subField != null && help != null && hasAddons())
                    ? div().classes("field").has(ADDONS).renderOpeningTag(renderer, true)
                    : null;

            content.is(size);
            if (readonly) {
                if (content instanceof Input input) input.readonly();
                if (content instanceof Textarea textarea) textarea.readonly();
                // all other form controls are readonly by default.
                // see https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Attributes/readonly
            }
            if (content instanceof Anchor) content.is(BUTTON);
            move(EXPANDED).from(content).to(control);
            if (content.hasModifier(LOADING)) {
                copy(Size.values()).from(content).to(control);
                move(LOADING).from(content).to(control);
            }

            leftAddons.render(renderer);

            control.render(renderer);

            rightAddons.render(renderer);

            if (subSubField != null) subSubField.close();
            if (help != null) help.build().render(renderer);
            if (subField != null) subField.close();
        }

        public Optional<AbstractElement<?>> fieldControl(String name) {
            // TODO support the other field control types
            return (content instanceof Input input && name.equals(input.fieldName())) ? Optional.of(input) : Optional.empty();
        }
    }

    private record FieldIcon(Icon icon, Modifier[] modifiers) {
        public void addTo(Element control, Alignment alignment) {
            control.classes("has-icons-" + alignment.key())
                    .content(icon.is(SMALL).is(modifiers).is(alignment));
        }
    }

    @ToString
    private static class FieldAddons {
        private List<AbstractElement<?>> addons;

        public boolean isPresent() {return addons != null && !addons.isEmpty();}

        public void add(AbstractElement<?> element) {
            if (element instanceof Anchor) element.is(BUTTON);
            if (addons == null) addons = new ArrayList<>();
            addons.add(element);
        }

        public void render(Renderer renderer) {
            if (addons != null) {
                for (var addon : addons) {
                    addon = addon.twin();
                    var control = control();
                    move(EXPANDED).from(addon).to(control);
                    control.content(addon).render(renderer);
                }
            }
        }
    }

    private record FieldHelp(AbstractElement<?> content, Modifier[] modifiers) {
        public AbstractElement<?> build() {return content.classes("help").is(modifiers);}
    }

    public Field(String label) {
        super("div", "field");
        this.label = label;
    }

    @Override public Field content(Renderable content, int index) {
        if (content instanceof Field) {
            throw new IllegalArgumentException("There's no need to manually nest fields; we'll do that for you when necessary");
        }

        lastControl = new FieldControl((AbstractElement<?>) content);
        add(lastControl).as(index).in(controls);

        return this;
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

    public Field horizontal() {return this.is(HORIZONTAL);}

    public Field grouped() {return is(GROUPED);}

    public Field groupedRight() {return grouped().classes("is-grouped-right");}

    public Field groupedCentered() {return grouped().classes("is-grouped-centered");}

    public Field groupedMultiline() {return grouped().is(GROUPED_MULTILINE);}

    public Field help(String text, Modifier... modifiers) {return help(p(text), modifiers);}

    public Field help(AbstractElement<?> content, Modifier... modifiers) {
        assert lastControl != null : "You must add a content (e.g. input) before adding help to it";
        lastControl.help = new FieldHelp(content, modifiers); return this;
    }

    public Field iconLeft(String iconName, Modifier... modifiers) {return iconLeft(Icon.icon(iconName), modifiers);}

    public Field iconLeft(Icon icon, Modifier... modifiers) {
        assert lastControl != null : "You must add a content (e.g. input) before setting an icon";
        assert lastControl.leftIcon == null : "Field already has left icon, cannot add another";
        lastControl.leftIcon = new FieldIcon(icon, modifiers);
        return this;
    }

    public Field iconRight(String iconName, Modifier... modifiers) {return iconRight(Icon.icon(iconName), modifiers);}

    public Field iconRight(Icon icon, Modifier... modifiers) {
        assert lastControl != null : "You must add a content (e.g. input) before setting an icon";
        assert lastControl.rightIcon == null : "Field already has right icon, cannot add another";
        lastControl.rightIcon = new FieldIcon(icon, modifiers);
        return this;
    }

    public Field addonLeft(AbstractElement<?> element) {
        assert lastControl != null : "You must add a content (e.g. input) before adding addons to it";
        lastControl.leftAddons.add(element);
        return this.has(ADDONS);
    }

    public Field addonRight(AbstractElement<?> element) {
        assert lastControl != null : "You must add a content (e.g. input) before adding addons to it";
        lastControl.rightAddons.add(element);
        return this.has(ADDONS);
    }


    public Optional<AbstractElement<?>> fieldControl(String name) {
        return controls.stream()
                .flatMap(fieldControl -> fieldControl.fieldControl(name).stream())
                .findFirst();
    }


    @Override public void renderContent(Renderer renderer) {
        renderLabel(renderer);
        renderControls(renderer);
    }

    private void renderLabel(Renderer renderer) {
        if (this.label != null || hasModifier(HORIZONTAL)) {
            var label = (this.label == null) ? null : element("label").classes("label").content(this.label);
            if (hasModifier(HORIZONTAL)) {
                label = div().classes("field-label").is(horizontalLabelSize()).content(label);
            } else {
                //noinspection DataFlowIssue // IntelliJ does not see that the label is never null here
                label.is(size);
            }
            renderer.nl();
            label.render(renderer);
        }
    }

    /// Horizontal labels must have a size, or they will not align; but not if they contain checkboxes or radios.
    /// Mixing checkboxes and radios with other controls in a horizontal field is not properly supported.
    private Size horizontalLabelSize() {
        return size == null && controls.stream().noneMatch(FieldControl::noLabelPadding) ? NORMAL : size;
    }

    private void renderControls(Renderer renderer) {
        // the field-body is only needed for horizontal fields, otherwise we can render directly into the field
        var fieldBody = hasModifier(HORIZONTAL)
                ? div().classes("field-body").renderOpeningTag(renderer, true)
                : null;

        controls.forEach(fieldControl -> fieldControl.render(renderer));

        if (fieldBody != null) fieldBody.close();
    }

    @Override protected boolean contentRendersOnSeparateLines() {return true;}
}
