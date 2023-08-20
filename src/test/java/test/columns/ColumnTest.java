package test.columns;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Basic;
import com.github.t1.bulmajava.columns.ColumnSize;
import com.github.t1.bulmajava.elements.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import test.RenderTestExtension;

import java.util.stream.IntStream;

import static com.github.t1.bulmajava.basic.Alignment.CENTERED;
import static com.github.t1.bulmajava.basic.Alignment.RIGHT;
import static com.github.t1.bulmajava.basic.Color.*;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.Style.LIGHT;
import static com.github.t1.bulmajava.columns.Column.column;
import static com.github.t1.bulmajava.columns.ColumnSize.*;
import static com.github.t1.bulmajava.columns.Columns.columns;
import static com.github.t1.bulmajava.columns.ScreenSize.DESKTOP;
import static com.github.t1.bulmajava.columns.ScreenSize.MOBILE;
import static com.github.t1.bulmajava.elements.Box.box;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Notification.notification;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class ColumnTest {
    @Test void shouldRenderColumns() {
        var cols = columns().contains(
                column().contains("First column"),
                column().contains("Second column"),
                column().contains("Third column"),
                column().contains("Fourth column"));

        then(cols).rendersAs("""
                <div class="columns">
                    <div class="column">First column</div>
                    <div class="column">Second column</div>
                    <div class="column">Third column</div>
                    <div class="column">Fourth column</div>
                </div>
                """);
    }

    @Test void shouldRenderFullWidthColumnSizes() {
        var div = columns().contains(
                column(FULL).contains(notificationWithCode(FULL.className())));

        then(div).rendersAs("""
                <div class="columns">
                    <div class="column is-full">
                        <div class="notification is-primary"><code class="html">is-full</code></div>
                    </div>
                </div>
                """);
    }

    @ParameterizedTest @EnumSource(mode = EXCLUDE, names = "FULL")
    void shouldRenderRationalColumnSizes(ColumnSize size) {
        var div = columns().contains(
                column(size).contains(notificationWithCode(size.className())),
                column().contains(notification().contains("Auto").is(LIGHT)),
                column().contains(notification().contains("Auto").is(LIGHT)));

        then(div).rendersAs("""
                <div class="columns">
                    <div class="column $size">
                        <div class="notification is-primary"><code class="html">$size</code></div>
                    </div>
                    <div class="column">
                        <div class="notification is-light">Auto</div>
                    </div>
                    <div class="column">
                        <div class="notification is-light">Auto</div>
                    </div>
                </div>
                """.replace("$size", size.className()));
    }

    @ParameterizedTest @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void shouldRenderNumericColumnSizes(int size) {
        var div = columns().contains(
                column(size).contains(notificationWithCode("is-" + size)));
        for (int i = size; i < 12; i++)
            div = div.contains(column().contains(notification().contains("1")));

        then(div).rendersAs("""
                                    <div class="columns">
                                        <div class="column $size">
                                            <div class="notification is-primary"><code class="html">$size</code></div>
                                        </div>
                                    """.replace("$size", "is-" + size) + """
                                        <div class="column">
                                            <div class="notification">1</div>
                                        </div>
                                    """.repeat(Math.max(0, 12 - size)) +
                            "</div>\n");
    }

    @Test void shouldRenderColumnsWithOffset() {
        var div = Basic.div().contains(
                columns().contains(
                        column(HALF).offset(ONE_QUARTER).contains(notificationWithCode("is-half is-offset-one-quarter"))),
                columns().contains(
                        column(THREE_FIFTHS).offset(ONE_FIFTH).contains(notificationWithCode("is-three-fifths is-offset-one-fifths"))),
                columns().contains(
                        column(4).offset(8).contains(notificationWithCode("is-4 is-offset-8"))),
                columns().contains(
                        column(11).offset(1).contains(notificationWithCode("is-11 is-offset-1"))));

        then(div).rendersAs("""
                <div>
                    <div class="columns">
                        <div class="column is-half is-offset-one-quarter">
                            <div class="notification is-primary"><code class="html">is-half is-offset-one-quarter</code></div>
                        </div>
                    </div>
                    <div class="columns">
                        <div class="column is-three-fifths is-offset-one-fifth">
                            <div class="notification is-primary"><code class="html">is-three-fifths is-offset-one-fifths</code></div>
                        </div>
                    </div>
                    <div class="columns">
                        <div class="column is-4 is-offset-8">
                            <div class="notification is-primary"><code class="html">is-4 is-offset-8</code></div>
                        </div>
                    </div>
                    <div class="columns">
                        <div class="column is-11 is-offset-1">
                            <div class="notification is-primary"><code class="html">is-11 is-offset-1</code></div>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderNarrowColumns() {
        var cols = columns().contains(
                column().narrow().contains(
                        box().style("width: 200px;").contains(
                                Title.titleP("Narrow column").classes("is-5"),
                                Title.subtitleP("This column is only 200px wide."))),
                column().contains(
                        box().contains(
                                Title.titleP("Flexible column").classes("is-5"),
                                Title.subtitleP("This column will take up the remaining space available."))));

        then(cols).rendersAs("""
                <div class="columns">
                    <div class="column is-narrow">
                        <div class="box" style="width: 200px;">
                            <p class="title is-5">Narrow column</p>
                            <p class="subtitle">This column is only 200px wide.</p>
                        </div>
                    </div>
                    <div class="column">
                        <div class="box">
                            <p class="title is-5">Flexible column</p>
                            <p class="subtitle">This column will take up the remaining space available.</p>
                        </div>
                    </div>
                </div>
                """);
    }

    //.is-narrow-mobile
    //.is-narrow-tablet
    //.is-narrow-touch
    //.is-narrow-desktop
    //.is-narrow-widescreen
    //.is-narrow-fullhd

    @Test void shouldRenderMobileColumns() {
        var cols = columns().is(MOBILE).contains(
                column().contains(notificationWithCode("1")),
                column().contains(notificationWithCode("2")),
                column().contains(notificationWithCode("3")),
                column().contains(notificationWithCode("4")));

        then(cols).rendersAs("""
                <div class="columns is-mobile">
                    <div class="column">
                        <div class="notification is-primary"><code class="html">1</code></div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary"><code class="html">2</code></div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary"><code class="html">3</code></div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary"><code class="html">4</code></div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderDesktopColumns() {
        var cols = columns().is(DESKTOP).contains(
                column().contains(notificationWithCode("1")),
                column().contains(notificationWithCode("2")),
                column().contains(notificationWithCode("3")),
                column().contains(notificationWithCode("4")));

        then(cols).rendersAs("""
                <div class="columns is-desktop">
                    <div class="column">
                        <div class="notification is-primary"><code class="html">1</code></div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary"><code class="html">2</code></div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary"><code class="html">3</code></div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary"><code class="html">4</code></div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderResponsiveColumns() {
        var cols = columns().is(MOBILE).contains(
                column(THREE_QUARTERS, TWO_THIRDS, HALF, ONE_THIRD, ONE_QUARTER).contains(
                        notification().is(PRIMARY).contains(
                                Basic.code("is-three-quarters-mobile"), Basic.br(),
                                Basic.code("is-two-thirds-tablet"), Basic.br(),
                                Basic.code("is-half-desktop"), Basic.br(),
                                Basic.code("is-one-third-widescreen"), Basic.br(),
                                Basic.code("is-one-quarter-fullhd"))),
                column().contains(notificationWithCode("2")),
                column().contains(notificationWithCode("3")),
                column().contains(notificationWithCode("4")));

        then(cols).rendersAs("""
                <div class="columns is-mobile">
                    <div class="column is-three-quarters-mobile is-two-thirds-tablet is-half-desktop is-one-third-widescreen is-one-quarter-fullhd">
                        <div class="notification is-primary">
                            <code>is-three-quarters-mobile</code>
                            <br>
                            <code>is-two-thirds-tablet</code>
                            <br>
                            <code>is-half-desktop</code>
                            <br>
                            <code>is-one-third-widescreen</code>
                            <br>
                            <code>is-one-quarter-fullhd</code>
                        </div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary"><code class="html">2</code></div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary"><code class="html">3</code></div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary"><code class="html">4</code></div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderNestedColumns() {
        var cols = columns().contains(
                column().contains(
                        notification().contains("First column").is(INFO),
                        columns().is(MOBILE).contains(
                                column().contains(
                                        notification().contains("First nested column").is(INFO)),
                                column().contains(
                                        notification().contains("Second nested column").is(INFO)
                                ))),
                column().contains(
                        notification().contains("Second column").is(DANGER),
                        columns().is(MOBILE).contains(
                                column(HALF).contains(
                                        notification().contains("50%").is(DANGER)
                                ),
                                column().contains(
                                        notification().contains("Auto").is(DANGER)
                                ),
                                column().contains(
                                        notification().contains("Auto").is(DANGER)
                                ))));

        then(cols).rendersAs("""
                <div class="columns">
                    <div class="column">
                        <div class="notification is-info">First column</div>
                        <div class="columns is-mobile">
                            <div class="column">
                                <div class="notification is-info">First nested column</div>
                            </div>
                            <div class="column">
                                <div class="notification is-info">Second nested column</div>
                            </div>
                        </div>
                    </div>
                    <div class="column">
                        <div class="notification is-danger">Second column</div>
                        <div class="columns is-mobile">
                            <div class="column is-half">
                                <div class="notification is-danger">50%</div>
                            </div>
                            <div class="column">
                                <div class="notification is-danger">Auto</div>
                            </div>
                            <div class="column">
                                <div class="notification is-danger">Auto</div>
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderGaplessColumns() {
        var cols = columns().classes("is-gapless").contains(
                column().contains(notification().contains("First column").is(PRIMARY)),
                column().contains(notification().contains("Second column").is(PRIMARY)),
                column().contains(notification().contains("Third column").is(PRIMARY)),
                column().contains(notification().contains("Fourth column").is(PRIMARY)));

        then(cols).rendersAs("""
                <div class="columns is-gapless">
                    <div class="column">
                        <div class="notification is-primary">First column</div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary">Second column</div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary">Third column</div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary">Fourth column</div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderVariableGapColumns() {
        var cols = Basic.div().contains(
                columns().contains(
                        column(2).contains(
                                Basic.strong("Gap:").rendersOnSeparateLines(true).contains(
                                        Basic.code("2rem").id("klmnValue"))),
                        column(5).offset(5).contains(Basic.div().is(RIGHT).contains(IntStream.range(0, 9).mapToObj(Integer::toString)
                                .map(n -> button("is-" + n).is(SMALL, WARNING).classes("bd-klmn-gap").dataValue(n))))),
                Basic.div().contains(
                        columns().classes("is-variable bd-klmn-columns is-8").contains(
                                column(3).contains(notification().is(PRIMARY).contains("Side")),
                                column(9).contains(notification().is(PRIMARY).contains("Main"))
                        ),
                        columns().classes("is-variable bd-klmn-columns is-8").contains(
                                IntStream.range(0, 3).mapToObj(n ->
                                        column(4).contains(notification().is(PRIMARY).contains("Three columns")))
                        ),
                        columns().classes("is-variable bd-klmn-columns is-8").contains(
                                IntStream.range(1, 13).mapToObj(Integer::toString)
                                        .map(n -> column().contains(notification().is(PRIMARY).contains(n))))));

        then(cols).rendersAs("""
                <div>
                    <div class="columns">
                        <div class="column is-2">
                            <strong>Gap:<code id="klmnValue">2rem</code></strong>
                        </div>
                        <div class="column is-5 is-offset-5">
                            <div class="is-right">
                                <button class="button is-small is-warning bd-klmn-gap" data-value="0">is-0</button>
                                <button class="button is-small is-warning bd-klmn-gap" data-value="1">is-1</button>
                                <button class="button is-small is-warning bd-klmn-gap" data-value="2">is-2</button>
                                <button class="button is-small is-warning bd-klmn-gap" data-value="3">is-3</button>
                                <button class="button is-small is-warning bd-klmn-gap" data-value="4">is-4</button>
                                <button class="button is-small is-warning bd-klmn-gap" data-value="5">is-5</button>
                                <button class="button is-small is-warning bd-klmn-gap" data-value="6">is-6</button>
                                <button class="button is-small is-warning bd-klmn-gap" data-value="7">is-7</button>
                                <button class="button is-small is-warning bd-klmn-gap" data-value="8">is-8</button>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="columns is-variable bd-klmn-columns is-8">
                            <div class="column is-3">
                                <div class="notification is-primary">Side</div>
                            </div>
                            <div class="column is-9">
                                <div class="notification is-primary">Main</div>
                            </div>
                        </div>
                        <div class="columns is-variable bd-klmn-columns is-8">
                            <div class="column is-4">
                                <div class="notification is-primary">Three columns</div>
                            </div>
                            <div class="column is-4">
                                <div class="notification is-primary">Three columns</div>
                            </div>
                            <div class="column is-4">
                                <div class="notification is-primary">Three columns</div>
                            </div>
                        </div>
                        <div class="columns is-variable bd-klmn-columns is-8">
                            <div class="column">
                                <div class="notification is-primary">1</div>
                            </div>
                            <div class="column">
                                <div class="notification is-primary">2</div>
                            </div>
                            <div class="column">
                                <div class="notification is-primary">3</div>
                            </div>
                            <div class="column">
                                <div class="notification is-primary">4</div>
                            </div>
                            <div class="column">
                                <div class="notification is-primary">5</div>
                            </div>
                            <div class="column">
                                <div class="notification is-primary">6</div>
                            </div>
                            <div class="column">
                                <div class="notification is-primary">7</div>
                            </div>
                            <div class="column">
                                <div class="notification is-primary">8</div>
                            </div>
                            <div class="column">
                                <div class="notification is-primary">9</div>
                            </div>
                            <div class="column">
                                <div class="notification is-primary">10</div>
                            </div>
                            <div class="column">
                                <div class="notification is-primary">11</div>
                            </div>
                            <div class="column">
                                <div class="notification is-primary">12</div>
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderBreakpointBasedColumnGaps() {
        var cols = columns().is(MOBILE).classes("is-variable", "is-1-mobile", "is-0-tablet", "is-3-desktop", "is-8-widescreen", "is-2-fullhd").contains(
                IntStream.range(1, 7).mapToObj(Integer::toString).map(n -> column().contains(notification().is(PRIMARY).contains(n))));

        then(cols).rendersAs("""
                <div class="columns is-mobile is-variable is-1-mobile is-0-tablet is-3-desktop is-8-widescreen is-2-fullhd">
                    <div class="column">
                        <div class="notification is-primary">1</div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary">2</div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary">3</div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary">4</div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary">5</div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary">6</div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderVerticallyAlignedColumns() {
        var cols = columns().classes("is-vcentered").contains(
                column(8).contains(notification().is(PRIMARY).contains("First column")),
                column().contains(notification().is(PRIMARY).contains("Second column with more content. This is so you can see the vertical alignment.")));

        then(cols).rendersAs("""
                <div class="columns is-vcentered">
                    <div class="column is-8">
                        <div class="notification is-primary">First column</div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary">Second column with more content. This is so you can see the vertical alignment.</div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderMultilineColumns() {
        var cols = columns().multiline().is(MOBILE).contains(
                column(ONE_QUARTER).contains(notificationWithCode("is-one-quarter")),
                column(ONE_QUARTER).contains(notificationWithCode("is-one-quarter")),
                column(ONE_QUARTER).contains(notificationWithCode("is-one-quarter")),
                column(ONE_QUARTER).contains(notificationWithCode("is-one-quarter")),
                column(HALF).contains(notificationWithCode("is-half")),
                column(ONE_QUARTER).contains(notificationWithCode("is-one-quarter")),
                column(ONE_QUARTER).contains(notificationWithCode("is-one-quarter")),
                column(ONE_QUARTER).contains(notificationWithCode("is-one-quarter")),
                column().contains(notification().is(PRIMARY).contains("Auto")));

        then(cols).rendersAs("""
                <div class="columns is-multiline is-mobile">
                    <div class="column is-one-quarter">
                        <div class="notification is-primary"><code class="html">is-one-quarter</code></div>
                    </div>
                    <div class="column is-one-quarter">
                        <div class="notification is-primary"><code class="html">is-one-quarter</code></div>
                    </div>
                    <div class="column is-one-quarter">
                        <div class="notification is-primary"><code class="html">is-one-quarter</code></div>
                    </div>
                    <div class="column is-one-quarter">
                        <div class="notification is-primary"><code class="html">is-one-quarter</code></div>
                    </div>
                    <div class="column is-half">
                        <div class="notification is-primary"><code class="html">is-half</code></div>
                    </div>
                    <div class="column is-one-quarter">
                        <div class="notification is-primary"><code class="html">is-one-quarter</code></div>
                    </div>
                    <div class="column is-one-quarter">
                        <div class="notification is-primary"><code class="html">is-one-quarter</code></div>
                    </div>
                    <div class="column is-one-quarter">
                        <div class="notification is-primary"><code class="html">is-one-quarter</code></div>
                    </div>
                    <div class="column">
                        <div class="notification is-primary">Auto</div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderCenteredColumns() {
        var cols = columns().is(MOBILE, CENTERED).contains(
                column(HALF).contains(notificationWithCode("is-half")));

        then(cols).rendersAs("""
                <div class="columns is-mobile is-centered">
                    <div class="column is-half">
                        <div class="notification is-primary"><code class="html">is-half</code></div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderCenteredMultilineColumns() {
        var cols = columns().multiline().is(MOBILE, CENTERED).contains(
                column().narrow().contains(notificationWithCode("is-narrow").contains(Basic.br()).contains("First Column")),
                column().narrow().contains(notificationWithCode("is-narrow").contains(Basic.br()).contains("Our Second Column")),
                column().narrow().contains(notificationWithCode("is-narrow").contains(Basic.br()).contains("Third Column")),
                column().narrow().contains(notificationWithCode("is-narrow").contains(Basic.br()).contains("The Fourth Column")),
                column().narrow().contains(notificationWithCode("is-narrow").contains(Basic.br()).contains("Fifth Column")));

        then(cols).rendersAs("""
                <div class="columns is-multiline is-mobile is-centered">
                    <div class="column is-narrow">
                        <div class="notification is-primary">
                            <code class="html">is-narrow</code>
                            <br>
                            First Column
                        </div>
                    </div>
                    <div class="column is-narrow">
                        <div class="notification is-primary">
                            <code class="html">is-narrow</code>
                            <br>
                            Our Second Column
                        </div>
                    </div>
                    <div class="column is-narrow">
                        <div class="notification is-primary">
                            <code class="html">is-narrow</code>
                            <br>
                            Third Column
                        </div>
                    </div>
                    <div class="column is-narrow">
                        <div class="notification is-primary">
                            <code class="html">is-narrow</code>
                            <br>
                            The Fourth Column
                        </div>
                    </div>
                    <div class="column is-narrow">
                        <div class="notification is-primary">
                            <code class="html">is-narrow</code>
                            <br>
                            Fifth Column
                        </div>
                    </div>
                </div>
                """);
    }

    private static AbstractElement<?> notificationWithCode(String code) {
        return notification().is(PRIMARY).contains(Basic.code(code).classes("html"));
    }
}
