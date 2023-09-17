package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.*;
import com.github.t1.bulmajava.elements.Icon;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Basic.span;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.FILE;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class FileInput extends AbstractElement<FileInput> {
    public static FileInput fileInput(String title) {return new FileInput(title);}

    private FileInput(String title) {
        super("div", Attributes.of(Classes.of("file")),
                Basic.element("label").classes("file-label").content(
                        input(FILE).notClasses("input").classes("file-input").name("resume"),
                        span().classes("file-cta").content(
                                span(title).classes("file-label"))));
    }

    public FileInput icon(String iconName) {
        return label(label -> {
            var cta = (Element) label.find(renderable -> renderable instanceof AbstractElement<?> e && e.hasClass("file-cta")).orElseThrow();
            var icon = Icon.icon(iconName).notClasses("icon").classes("file-icon");
            cta.firstContent(icon);
            return label;
        });
    }

    private FileInput label(Function<ConcatenatedRenderable, ConcatenatedRenderable> function) {
        var div = contentAs(Element.class);
        var label = div.contentAs(ConcatenatedRenderable.class);
        function.apply(label);
        return this;
    }

    public FileInput fileName(String fileName) {
        return classes("has-name").label(label -> label.content(span(fileName).classes("file-name")));
    }

    public FileInput isBoxed() {return classes("is-boxed");}
}
