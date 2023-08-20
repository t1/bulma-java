package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.*;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Size.LARGE;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.elements.IconStyle.SOLID;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Icon extends AbstractElement<Icon> {
    public static Icon icon(String name, String... classes) {return icon(name, (FontSize) null, classes);}

    public static Icon icon(String name, IconStyle style, String... classes) {
        return icon(name, style, null, classes);
    }

    public static Icon icon(String name, FontSize fontSize, String... classes) {
        return icon(name, SOLID, fontSize, classes);
    }

    public static Icon icon(String name, IconStyle style, FontSize fontSize, String... classes) {
        return new Icon().contains(Basic.i().classes(style.className(), "fa-" + name).classes(classes)).fontSize(fontSize);
    }

    /** FontAwesome only allows stacking two icons */
    public static AbstractElement<?> iconStack(Icon... icons) {
        var stack = Basic.span().classes("fa-stack fa-lg");
        for (int i = 0; i < icons.length; i++)
            stack = stack.contains(icons[i].getI().classes("fa-stack-" + (i + 1) + "x"));
        return Basic.span().classes("icon").is(LARGE).contains(stack);
    }

    private AbstractElement<?> getI() {return (AbstractElement<?>) this.content();}

    public static AbstractElement<?> iconText() {return Basic.span().classes("icon-text");}

    public static AbstractElement<?> iconTextFlex() {return Basic.div().classes("icon-text");}


    private Icon() {super("span", "icon");}


    public Icon ariaHidden() {return withI(AbstractElement::ariaHidden);}

    @Override public Icon is(Modifier... modifiers) {
        var out = this;
        for (var modifier : modifiers)
            if (modifier instanceof IconSize) out = out.withI(i -> i.is(modifier));
            else if (modifier instanceof Size)
                out = out.notClasses(SMALL.className()).classes(modifier.className());
            else out = out.classes(modifier.className());
        return out;
    }

    private Icon fontSize(FontSize fontSize) {
        if (fontSize == null) return this;
        return withI(i -> i.classes("fa-" + fontSize.code()));
    }

    private Icon withI(Function<Element, Element> function) {return content(function.apply(contentElement()));}
}
