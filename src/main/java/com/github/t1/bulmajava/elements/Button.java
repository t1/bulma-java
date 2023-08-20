package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.FontSize;
import com.github.t1.bulmajava.basic.Modifier;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Basic.span;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Button extends AbstractElement<Button> {
    public static AbstractElement<?> buttons() {return div().classes("buttons");}

    public static AbstractElement<?> fieldsAddon() {return div().classes("field", "has-addons");}

    public static AbstractElement<?> buttonsAddon() {return buttons().classes("has-addons");}

    public static Button button(String content) {return button(string(content));}

    public static Button button(Renderable content) {return button().contains(content);}

    public static Button button() {return new Button();}


    private Button() {super("button", "button");}


    public Button responsive() {return is(() -> "responsive");}

    public Button submit() {return attr("type", "submit");}

    public Button icon(String name, String... classes) {return icon(name, null, classes);}

    public Button icon(String name, FontSize fontSize, String... classes) {
        var result = this;
        if (result.content() instanceof RenderableString)
            result = result.content(span().content(result.content()));
        return result.contains(Icon.icon(name, fontSize, classes));
    }

    public Button icon(Function<Icon, Icon> function) {
        var existing = content().find(Icon.class).orElseThrow(() -> new IllegalStateException("no icon found"));
        var replacement = function.apply(existing);
        return content((existing == content()) ? replacement
                : content().replace(existing, replacement));
    }

    public Button isIcon(Modifier... modifiers) {return icon(icon -> icon.is(modifiers));}
}
