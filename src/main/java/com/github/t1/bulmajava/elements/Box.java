package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.AbstractElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Box extends AbstractElement<Box> {
    public static Box box() {return new Box("div");}

    public static Box articleBox() {return new Box("article");}

    public static Box box(String text) {return box().contains(text);}

    public Box(String elementName) {super(elementName, "box");}
}
