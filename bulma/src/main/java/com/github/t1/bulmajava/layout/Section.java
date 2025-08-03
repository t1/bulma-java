package com.github.t1.bulmajava.layout;

import com.github.t1.bulmajava.basic.BulmaElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Section extends BulmaElement<Section> {
    public static Section section() {return new Section();}

    public Section() {super("section", "section");}
}
