package com.github.t1.bulmajava.columns;

import com.github.t1.bulmajava.basic.AbstractElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Columns extends AbstractElement<Columns> {
    public static Columns columns() {return new Columns();}


    private Columns() {super("div", "columns");}


    public Columns multiline() {return classes("is-multiline");}
}
