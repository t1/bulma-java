package com.github.t1.bulmajava.basic;

import com.github.t1.bulmajava.elements.Icon;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.function.Function;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Anchor extends AbstractElement<Anchor> {
    public static Anchor a() {return new Anchor();}

    public static Anchor a(String content) {return a().contains(content);}


    private Anchor() {super("a");}


    public Anchor href(String href) {return attr("href", href);}

    public Anchor title(String title) {return attr("title", title);}

    public Anchor button() {return classes("button");}

    public Anchor mapIcon(Function<Icon, Icon> icon) {
        if (content() instanceof ConcatenatedRenderable c) {
            var renderables = c.renderables();
            for (int i = 0; i < renderables.size(); i++) {
                if (renderables.get(i) instanceof Icon old) {
                    renderables = new ArrayList<>(renderables);
                    renderables.set(i, icon.apply(old));
                    return content(new ConcatenatedRenderable(renderables));
                }
            }
        }
        return this;
    }
}
