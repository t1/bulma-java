package com.github.t1.bulmajava.columns;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Columns extends AbstractElement<Columns> {
    public static Columns columns() {return new Columns();}

    private Columns() {super("div", "columns");}


    public Columns multiline() {return classes("is-multiline");}

    public Columns column(String content) {return content(Column.column().content(content));}

    public Columns column(Renderable... content) {return content(Column.column().content(content));}

    public Columns column(ColumnSize size, Renderable... content) {return content(Column.column(size).content(content));}

    public Columns column(int size, Renderable... content) {return content(Column.column(size).content(content));}
}
