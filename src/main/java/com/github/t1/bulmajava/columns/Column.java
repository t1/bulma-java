package com.github.t1.bulmajava.columns;

import com.github.t1.bulmajava.basic.AbstractElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Column extends AbstractElement<Column> {
    public static Column column() {return new Column();}

    public static Column column(ColumnSize size) {return column().is(size);}

    public static Column column(int size) {return column().classes("is-" + size);}

    public static Column column(ColumnSize mobileSize,
                                ColumnSize tabletSize,
                                ColumnSize desktopSize,
                                ColumnSize widescreenSize,
                                ColumnSize fullhdSize) {
        return column().classes(
                mobileSize.className() + "-mobile",
                tabletSize.className() + "-tablet",
                desktopSize.className() + "-desktop",
                widescreenSize.className() + "-widescreen",
                fullhdSize.className() + "-fullhd");
    }


    private Column() {super("div", "column");}


    public Column offset(ColumnSize columnSize) {return classes("is-offset-" + columnSize.key());}

    public Column offset(int offset) {return classes("is-offset-" + offset);}

    public Column narrow() {return classes("is-narrow");}
}
