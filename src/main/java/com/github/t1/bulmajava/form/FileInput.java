package com.github.t1.bulmajava.form;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Attributes;
import com.github.t1.bulmajava.basic.Classes;
import com.github.t1.bulmajava.basic.Element;
import com.github.t1.bulmajava.elements.Icon;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Basic.element;
import static com.github.t1.bulmajava.basic.Basic.span;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.FILE;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class FileInput extends AbstractElement<FileInput> {
    public static FileInput fileInput(String title) {return new FileInput(title);}

    private FileInput(String title) {
        super("div", Attributes.of(Classes.of("file")),
                element("label").classes("file-label").content(
                        input(FILE).notClasses("input").classes("file-input").name("resume"),
                        span().classes("file-cta").content(
                                span(title).classes("file-label"))));
    }

    public FileInput icon(String iconName) {
        return label(label -> {
            var cta = (Element) label.find(renderable -> renderable.hasClass("file-cta")).orElseThrow();
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

    public FileInput boxed() {return classes("is-boxed");}

    public FileInput accept(String... types) {
        fileInputElement().attr("accept", String.join(",", types));
        return this;
    }

    public FileInput captureUser() {
        fileInputElement().attr("capture", "user");
        return this;
    }

    public FileInput captureEnvironment() {
        fileInputElement().attr("capture", "environment");
        return this;
    }

    public FileInput multiple() {
        fileInputElement().attr("multiple");
        return this;
    }

    private AbstractElement<?> fileInputElement() {
        return getOrCreate("file-label").getOrCreate("file-input");
    }
}
