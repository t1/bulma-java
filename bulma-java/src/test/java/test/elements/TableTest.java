package test.elements;

import com.github.t1.bulmajava.elements.TableStyle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.State.SELECTED;
import static com.github.t1.bulmajava.basic.Style.FULLWIDTH;
import static com.github.t1.bulmajava.elements.Table.row;
import static com.github.t1.bulmajava.elements.Table.rowH;
import static com.github.t1.bulmajava.elements.Table.table;
import static com.github.t1.bulmajava.elements.Table.tableContainer;
import static com.github.t1.bulmajava.elements.Table.td;
import static com.github.t1.bulmajava.elements.Table.th;
import static com.github.t1.bulmajava.elements.Table.tr;
import static com.github.t1.bulmajava.elements.TableStyle.BORDERED;
import static com.github.t1.bulmajava.elements.TableStyle.HOVERABLE;
import static com.github.t1.bulmajava.elements.TableStyle.NARROW;
import static com.github.t1.bulmajava.elements.TableStyle.STRIPED;
import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.htmljava.Basic.abbr;
import static com.github.t1.htmljava.Basic.div;
import static com.github.t1.htmljava.Basic.em;
import static com.github.t1.htmljava.Renderable.RenderableString.string;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class TableTest {
    @Test void shouldRenderTable() {
        var table = table()
                .head(abbr("Position", "Pos"), string("Team"), abbr("Played", "Pld"))
                .foot(abbr("Position", "#"), string("T"), abbr("Played", "P"))
                .body(
                        rowH("1",
                                a("Leicester City").href("https://en.wikipedia.org/wiki/Leicester_City_F.C.")
                                        .title("Leicester City F.C."),
                                string("38")),
                        rowH("2",
                                a("Arsenal").href("https://en.wikipedia.org/wiki/Arsenal_F.C.").title("Arsenal F.C."),
                                string("38"))
                                .is(SELECTED));

        then(table).rendersAs("""
                <table class="table">
                    <thead>
                        <tr>
                            <th>
                                <abbr title="Position">Pos</abbr>
                            </th>
                            <th>Team</th>
                            <th>
                                <abbr title="Played">Pld</abbr>
                            </th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>
                                <abbr title="Position">#</abbr>
                            </th>
                            <th>T</th>
                            <th>
                                <abbr title="Played">P</abbr>
                            </th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <tr>
                            <th>1</th>
                            <td>
                                <a href="https://en.wikipedia.org/wiki/Leicester_City_F.C." title="Leicester City F.C.">Leicester City</a>
                            </td>
                            <td>38</td>
                        </tr>
                        <tr class="is-selected">
                            <th>2</th>
                            <td>
                                <a href="https://en.wikipedia.org/wiki/Arsenal_F.C." title="Arsenal F.C.">Arsenal</a>
                            </td>
                            <td>38</td>
                        </tr>
                    </tbody>
                </table>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderStyledTable(TableStyle style) {
        var table = table().is(style)
                .head(string("One"), string("Two"))
                .body(
                        row(string("Three"), string("Four")),
                        row(string("Five"), string("Six")),
                        row(string("Seven"), string("Eight")),
                        row(string("Nine"), string("Ten")),
                        row(string("Eleven"), string("Twelve")));

        then(table).rendersAs(tableWithStyles(style.className()));
    }

    @Test void shouldRenderFullwidthTable() {
        var table = table().is(FULLWIDTH)
                .head(string("One"), string("Two"))
                .body(
                        row(string("Three"), string("Four")),
                        row(string("Five"), string("Six")),
                        row(string("Seven"), string("Eight")),
                        row(string("Nine"), string("Ten")),
                        row(string("Eleven"), string("Twelve")));

        then(table).rendersAs(tableWithStyles("is-fullwidth"));
    }

    @Test void shouldRenderAllStylesTable() {
        var table = table().is(BORDERED, STRIPED, NARROW, HOVERABLE, FULLWIDTH)
                .head(string("One"), string("Two"))
                .body(
                        row(string("Three"), string("Four")),
                        row(string("Five"), string("Six")),
                        row(string("Seven"), string("Eight")),
                        row(string("Nine"), string("Ten")),
                        row(string("Eleven"), string("Twelve")));

        then(table).rendersAs(tableWithStyles("is-bordered is-striped is-narrow is-hoverable is-fullwidth"));
    }

    private static String tableWithStyles(String styles) {
        return """
                <table class="table $styles">
                    <thead>
                        <tr>
                            <th>One</th>
                            <th>Two</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Three</td>
                            <td>Four</td>
                        </tr>
                        <tr>
                            <td>Five</td>
                            <td>Six</td>
                        </tr>
                        <tr>
                            <td>Seven</td>
                            <td>Eight</td>
                        </tr>
                        <tr>
                            <td>Nine</td>
                            <td>Ten</td>
                        </tr>
                        <tr>
                            <td>Eleven</td>
                            <td>Twelve</td>
                        </tr>
                    </tbody>
                </table>
                """.replace("$styles", styles);
    }

    @Test void shouldRenderScrollableTable() {
        var div = tableContainer().content(
                table().body(
                        row(string("One"), string("Two")),
                        row(string("Three"), string("Four"))));

        then(div).rendersAs("""
                <div class="table-container">
                    <table class="table">
                        <tbody>
                            <tr>
                                <td>One</td>
                                <td>Two</td>
                            </tr>
                            <tr>
                                <td>Three</td>
                                <td>Four</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                """);
    }

    @Test void shouldRenderTableWithStyledCells() {
        var table = table().body(
                row(em("One"), div().style("word-break: keep-all;").content("Two")),
                row(em("Three"), div().style("word-break: keep-all;").content("Four")));

        then(table).rendersAs("""
                <table class="table">
                    <tbody>
                        <tr>
                            <td><em>One</em></td>
                            <td>
                                <div style="word-break: keep-all;">Two</div>
                            </td>
                        </tr>
                        <tr>
                            <td><em>Three</em></td>
                            <td>
                                <div style="word-break: keep-all;">Four</div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                """);
    }

    @Test void shouldRenderTableWithTrAndTd() {
        var table = table().body(
                tr().content(td(em("One")), td(div().content("Two"))),
                tr().content(th("Three"), td("Four")));

        then(table).rendersAs("""
                <table class="table">
                    <tbody>
                        <tr>
                            <td><em>One</em></td>
                            <td>
                                <div>Two</div>
                            </td>
                        </tr>
                        <tr>
                            <th>Three</th>
                            <td>Four</td>
                        </tr>
                    </tbody>
                </table>
                """);
    }
}
