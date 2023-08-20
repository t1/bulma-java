package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.*;
import com.github.t1.bulmajava.elements.Icon;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Basic.span;
import static com.github.t1.bulmajava.basic.Renderable.ConcatenatedRenderable.concat;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.FILE;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class FileInput extends AbstractElement<FileInput> {
    public static FileInput fileInput(String title) {return new FileInput(title);}

    private FileInput(String title) {
        super("div", Attributes.of(Classes.of("file")),
                Basic.element("label").classes("file-label").contains(
                        input(FILE).notClasses("input").classes("file-input").name("resume"),
                        span().classes("file-cta").contains(
                                span(title).classes("file-label"))));
    }

    public FileInput icon(String iconName) {
        return label(label -> {
            var cta = (Element) label.find(renderable -> renderable instanceof AbstractElement<?> e && e.hasClass("file-cta")).orElseThrow();
            var icon = Icon.icon(iconName).notClasses("icon").classes("file-icon");
            return (ConcatenatedRenderable) label.replace(cta,
                    cta.content(concat(icon, cta.content())));
        });
    }

    private FileInput label(Function<ConcatenatedRenderable, ConcatenatedRenderable> function) {
        var div = contentElement();
        var label = div.concatContent();
        return content(div.content(function.apply(label)));
    }

    public FileInput fileName(String fileName) {
        return classes("has-name").label(label -> label.contains(span(fileName).classes("file-name")));
    }

    public FileInput isBoxed() {return classes("is-boxed");}
}
