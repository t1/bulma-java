package com.github.t1.bulmajava.elements;


import com.github.t1.bulmajava.basic.Color;
import com.github.t1.bulmajava.basic.FontSize;
import com.github.t1.bulmajava.basic.Size;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Modifier;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Size.LARGE;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.elements.IconStyle.SOLID;
import static com.github.t1.htmljava.Basic.div;
import static com.github.t1.htmljava.Basic.i;
import static com.github.t1.htmljava.Basic.span;

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
        return new Icon().content(i().classes(style.className(), "fa-" + name).classes(classes)).fontSize(fontSize);
    }

    /** FontAwesome only allows stacking two icons */
    public static AbstractElement<?> iconStack(Icon... icons) {
        var stack = span().classes("fa-stack fa-lg");
        for (int i = 0; i < icons.length; i++)
            stack = stack.content(icons[i].getI().classes("fa-stack-" + (i + 1) + "x"));
        return span().classes("icon").is(LARGE).content(stack);
    }

    private AbstractElement<?> getI() {return (AbstractElement<?>) content();}

    public static AbstractElement<?> iconText() {return span().classes("icon-text");}

    public static AbstractElement<?> iconTextFlex() {return div().classes("icon-text");}


    private Icon() {super("span", "icon");}


    public Icon ariaHidden(boolean hidden) {
        withI(e -> e.ariaHidden(hidden));
        return this;
    }

    @Override public Icon is(Modifier... modifiers) {
        for (var modifier : modifiers)
            if (modifier instanceof IconSize) withI(i -> i.is(modifier));
            else if (modifier instanceof Color) withI(i -> i.hasText(modifier));
            else if (modifier instanceof Size) notClasses(SMALL.className()).classes(modifier.className());
            else classes(modifier.className());
        return this;
    }

    private Icon fontSize(FontSize fontSize) {
        if (fontSize != null) withI(i -> i.classes("fa-" + fontSize.code()));
        return this;
    }

    private void withI(Function<Element, Element> function) {function.apply(contentAs(Element.class));}
}
