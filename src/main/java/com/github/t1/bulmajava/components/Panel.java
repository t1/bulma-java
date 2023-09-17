package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Basic.p;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Panel extends AbstractElement<Panel> {
    public static Panel panel() {return panel("article");}

    public static Panel panel(String elementName) {return new Panel(elementName);}

    private Panel(String name) {super(name, "panel");}

    public Panel addHeading(AbstractElement<?> content) {return content(content.classes("panel-heading"));}

    public Panel addBlock(AbstractElement<?> content) {return content(content.classes("panel-block"));}

    public Panel addTabs(Renderable... content) {return content(p().classes("panel-tabs").content(content));}
}
