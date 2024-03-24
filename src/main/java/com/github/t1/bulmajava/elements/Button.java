package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.FontSize;
import com.github.t1.bulmajava.basic.Modifier;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Basic.control;
import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static java.util.Locale.ROOT;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Button extends AbstractElement<Button> {
    public static AbstractElement<?> buttons() {return div().classes("buttons");}

    public static AbstractElement<?> fieldsAddon() {return div().classes("field", "has-addons").map(Button::inControl);}

    private static Renderable inControl(Renderable renderable) {
        return renderable.hasClass("control") ? renderable : control().content(renderable);
    }

    public static AbstractElement<?> buttonsAddon() {return buttons().classes("has-addons");}

    public static Button button(String content) {return button(string(content));}

    public static Button button(Renderable content) {return button().content(content);}

    public static Button button() {return new Button();}


    private Button() {super("button", "button");}


    public Button responsive() {return is(() -> "responsive");}

    public Button submit() {return attr("type", "submit");}

    public Button icon(String name, String... classes) {return icon(name, null, classes);}

    public Button icon(String name, FontSize fontSize, String... classes) {
        content(Icon.icon(name, fontSize, classes));
        return this;
    }

    public Button icon(Function<Icon, Icon> function) {
        var existing = content().find(Icon.class).orElseThrow(() -> new IllegalStateException("no icon found"));
        function.apply(existing);
        return this;
    }

    public Button isIcon(Modifier... modifiers) {return icon(icon -> icon.is(modifiers));}

    public Button type(ButtonType buttonType) {return attr("type", buttonType.name().toLowerCase(ROOT));}
}
