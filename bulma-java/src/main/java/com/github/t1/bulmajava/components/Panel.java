package com.github.t1.bulmajava.components;

import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.stream.Stream;

import static com.github.t1.htmljava.Basic.p;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Panel extends AbstractElement<Panel> {
    public static Panel panel() {return panel("article");}

    public static Panel panel(String elementName) {return new Panel(elementName);}

    private Panel(String name) {super(name, "panel");}

    public Panel heading(String heading) {return heading(p(heading));}

    public Panel heading(AbstractElement<?> content) {return content(content.classes("panel-heading"));}

    public Panel block(AbstractElement<?> content) {return content(content.classes("panel-block"));}

    public Panel blocks(Stream<AbstractElement<?>> content) {return content(content.map(element -> element.classes("panel-block")));}

    public Panel tabs(Renderable... content) {return content(p().classes("panel-tabs").content(content));}
}
