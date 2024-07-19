package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.AbstractElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Content extends AbstractElement<Content> {
    /** The method name <code>content</code> clashes with the super method */
    public static Content content_() {return new Content();}

    public Content() {super("div", "content");}
}
