package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Basic;
import com.github.t1.bulmajava.basic.Element;
import com.github.t1.bulmajava.basic.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Table extends AbstractElement<Table> {
    public static Element row(Renderable... cols) {return tr().map(Table::td).content(cols);}

    /** First colum is a header column <code>th</code>, the rest are regular <code>td</code> */
    public static Element rowH(String hCol, Renderable... cols) {return rowH(string(hCol), cols);}

    /** First colum is a header column <code>th</code>, the rest are regular <code>td</code> */
    public static Element rowH(Renderable hCol, Renderable... cols) {
        return tr()
                .content(th(hCol))
                .map(Table::td)
                .content(cols);
    }

    public static Element tableContainer() {
        return div().classes("table-container");
    }

    public static Element tbody(Renderable... cols) {return Basic.element("tbody").content(cols);}

    public static Element tbody(Stream<Renderable> cols) {return Basic.element("tbody").content(cols);}

    public static Element td() {return Basic.element("td");}

    public static Element td(String content) {return td().content(content);}

    public static Element td(Renderable... content) {return td().content(content);}

    public static Element th() {return Basic.element("th");}

    public static Element th(String content) {return th().content(content);}

    public static Element th(Renderable... content) {return th().content(content);}

    public static Element tr() {return Basic.element("tr");}

    public static Table table() {return new Table();}


    private Table() {super("table", "table");}


    public Table head(String... content) {return head(Stream.of(content).map(RenderableString::string));}

    public Table head(Renderable... content) {return head(Stream.of(content));}

    public Table head(Stream<Renderable> content) {
        return content(Basic.element("thead").content(
                tr().map(c -> th().content(c)).content(content)));
    }

    public Table body(Renderable... content) {return content(tbody(content));}

    public Table body(Stream<Renderable> content) {return content(tbody(content));}

    public Table foot(Renderable... content) {
        return content(Basic.element("tfoot").content(
                tr().map(e -> th().content(e)).content(content)));
    }

    /** Use {@link #head(Renderable...)}, {@link #body(Renderable...)}, or {@link #foot(Renderable...)} instead! */
    @Deprecated
    @Override public Table content(String content) {return super.content(content);}

    /** Use {@link #head(Renderable...)}, {@link #body(Renderable...)}, or {@link #foot(Renderable...)} instead! */
    @Deprecated
    @Override public Table content(Stream<? extends Renderable> content) {return super.content(content);}

    /** Use {@link #head(Renderable...)}, {@link #body(Renderable...)}, or {@link #foot(Renderable...)} instead! */
    @Deprecated
    @Override public Table content(Renderable... content) {return super.content(content);}

    /** Use {@link #head(Renderable...)}, {@link #body(Renderable...)}, or {@link #foot(Renderable...)} instead! */
    @Deprecated
    @Override public Table content(Renderable content) {return super.content(content);}
}
