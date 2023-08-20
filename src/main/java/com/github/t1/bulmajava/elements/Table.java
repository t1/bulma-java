package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Basic;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Basic.div;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Table extends AbstractElement<Table> {
    public static AbstractElement<?> row(Renderable... cols) {return tr().contains(wrap(Table::td, cols));}

    /** First colum is a header column <code>th</code>, the rest are regular <code>td</code> */
    public static AbstractElement<?> rowH(Renderable hCol, Renderable... cols) {
        return tr()
                .contains(th().contains(hCol))
                .contains(wrap(Table::td, cols));
    }

    public static AbstractElement<?> tableContainer() {
        return div().classes("table-container");
    }

    public static AbstractElement<?> tbody(Renderable... cols) {return Basic.element("tbody").contains(cols);}

    public static AbstractElement<?> td() {return Basic.element("td");}

    public static AbstractElement<?> th() {return Basic.element("th");}

    public static AbstractElement<?> tr() {return Basic.element("tr");}

    public static Renderable[] wrap(Supplier<AbstractElement<?>> wrapper, Renderable... renderables) {
        return Stream.of(renderables).map((Function<Renderable, Renderable>) row -> wrapper.get().contains(row)).toArray(Renderable[]::new);
    }

    public static Table table() {return new Table();}


    private Table() {super("table", "table");}


    public Table containsHead(Renderable... content) {
        return contains(Basic.element("thead").contains(
                tr().contains(wrap(Table::th, content))));
    }

    public Table containsBody(Renderable... content) {return contains(tbody(content));}

    public Table containsFoot(Renderable... content) {
        return contains(Basic.element("tfoot").contains(
                tr().contains(wrap(Table::th, content))));
    }

    @Deprecated // use containsHead/Body/Foot instead
    @Override public Table contains(String content) {return super.contains(content);}

    @Deprecated // use containsHead/Body/Foot instead
    @Override public Table contains(Stream<Renderable> content) {return super.contains(content);}

    @Deprecated // use containsHead/Body/Foot instead
    @Override public Table contains(Renderable... content) {return super.contains(content);}

    @Deprecated // use containsHead/Body/Foot instead
    @Override public Table contains(Renderable content) {return super.contains(content);}
}
