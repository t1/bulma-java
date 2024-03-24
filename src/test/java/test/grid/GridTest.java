package test.grid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.grid.Grid.*;
import static java.util.stream.IntStream.range;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class GridTest {
    private static Cell roundedCell(int c) {return roundedCell(Integer.toString(c));}

    private static Cell roundedCell(String content) {
        return cell().content(content).classes("py-3", "px-4", "has-background-primary", "has-text-primary-invert", "has-radius-normal");
    }

    @Test void shouldRenderSmartGrid() {
        var button = grid().content(range(1, 25).mapToObj(n -> "Cell " + n).map(GridTest::roundedCell));

        then(button).rendersAs("""
                <div class="grid">
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 1</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 2</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 3</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 4</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 5</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 6</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 7</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 8</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 9</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 10</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 11</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 12</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 13</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 14</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 15</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 16</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 17</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 18</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 19</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 20</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 21</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 22</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 23</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 24</div>
                </div>
                """);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void shouldRenderSmartGridWithMinWidth(int n) {
        var button = grid().classes("is-col-min-" + n).content(range(1, 25).mapToObj(GridTest::roundedCell));

        then(button).rendersAs("""
                <div class="grid is-col-min-$n">
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">1</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">2</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">3</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">4</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">5</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">6</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">7</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">8</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">9</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">10</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">11</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">12</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">13</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">14</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">15</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">16</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">17</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">18</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">19</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">20</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">21</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">22</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">23</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">24</div>
                </div>
                """.replace("$n", Integer.toString(n)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldRenderSmartGridWithGap(int n) {
        var button = grid().classes("is-gap-" + n).content(range(1, 25).mapToObj(GridTest::roundedCell));

        then(button).rendersAs("""
                <div class="grid is-gap-$n">
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">1</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">2</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">3</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">4</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">5</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">6</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">7</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">8</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">9</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">10</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">11</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">12</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">13</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">14</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">15</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">16</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">17</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">18</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">19</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">20</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">21</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">22</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">23</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">24</div>
                </div>
                """.replace("$n", Integer.toString(n)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldRenderSmartGridWithColumnGap(int n) {
        var button = grid().classes("is-column-gap-" + n).content(range(1, 25).mapToObj(GridTest::roundedCell));

        then(button).rendersAs("""
                <div class="grid is-column-gap-$n">
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">1</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">2</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">3</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">4</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">5</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">6</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">7</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">8</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">9</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">10</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">11</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">12</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">13</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">14</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">15</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">16</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">17</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">18</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">19</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">20</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">21</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">22</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">23</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">24</div>
                </div>
                """.replace("$n", Integer.toString(n)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldRenderSmartGridWithRowGap(int n) {
        var button = grid().classes("is-row-gap-" + n).content(range(1, 25).mapToObj(GridTest::roundedCell));

        then(button).rendersAs("""
                <div class="grid is-row-gap-$n">
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">1</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">2</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">3</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">4</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">5</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">6</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">7</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">8</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">9</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">10</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">11</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">12</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">13</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">14</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">15</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">16</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">17</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">18</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">19</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">20</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">21</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">22</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">23</div>
                    <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">24</div>
                </div>
                """.replace("$n", Integer.toString(n)));
    }

    @Test void shouldRenderFixedGrid() {
        var button = fixedGrid().content(range(1, 13).mapToObj(n -> "Cell " + n)
                .map(GridTest::roundedCell));

        then(button).rendersAs("""
                <div class="fixed-grid">
                    <div class="grid">
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 1</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 2</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 3</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 4</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 5</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 6</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 7</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 8</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 9</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 10</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 11</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 12</div>
                    </div>
                </div>
                """);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void shouldRenderFixedGrid(int columns) {
        var button = fixedGrid(columns).content(range(1, 13).mapToObj(n -> "Cell " + n)
                .map(GridTest::roundedCell));

        then(button).rendersAs("""
                <div class="fixed-grid has-$n-cols">
                    <div class="grid">
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 1</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 2</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 3</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 4</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 5</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 6</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 7</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 8</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 9</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 10</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 11</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 12</div>
                    </div>
                </div>
                """.replace("$n", Integer.toString(columns)));
    }

    // TODO Container breakpoints

    @Test void shouldRenderAutoCountFixedGrid() {
        var button = fixedGrid().autoCount().content(range(1, 13).mapToObj(n -> "Cell " + n)
                .map(GridTest::roundedCell));

        then(button).rendersAs("""
                <div class="fixed-grid has-auto-count">
                    <div class="grid">
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 1</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 2</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 3</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 4</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 5</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 6</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 7</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 8</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 9</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 10</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 11</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 12</div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderColumnStart() {
        var button = fixedGrid(4).content(
                roundedCell("Cell 1"),
                roundedCell("Cell 2").isColStart(3),
                roundedCell("Cell 3"),
                roundedCell("Cell 4"),
                roundedCell("Cell 5"),
                roundedCell("Cell 6"));

        then(button).rendersAs("""
                <div class="fixed-grid has-4-cols">
                    <div class="grid">
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 1</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal is-col-start-3">Cell 2</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 3</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 4</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 5</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 6</div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderColumnFromEnd() {
        var button = fixedGrid(4).content(
                roundedCell("Cell 1"),
                roundedCell("Cell 2").isColFromEnd(3),
                roundedCell("Cell 3"),
                roundedCell("Cell 4"),
                roundedCell("Cell 5"),
                roundedCell("Cell 6"));

        then(button).rendersAs("""
                <div class="fixed-grid has-4-cols">
                    <div class="grid">
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 1</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal is-col-from-end-3">Cell 2</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 3</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 4</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 5</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 6</div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderColumnSpan() {
        var button = fixedGrid(4).content(
                roundedCell("Cell 1"),
                roundedCell("Cell 2").isColSpan(2),
                roundedCell("Cell 3"),
                roundedCell("Cell 4"),
                roundedCell("Cell 5"),
                roundedCell("Cell 6"));

        then(button).rendersAs("""
                <div class="fixed-grid has-4-cols">
                    <div class="grid">
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 1</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal is-col-span-2">Cell 2</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 3</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 4</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 5</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 6</div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderRowStart() {
        var button = fixedGrid(4).content(
                roundedCell("Cell 1"),
                roundedCell("Cell 2").isRowStart(3),
                roundedCell("Cell 3"),
                roundedCell("Cell 4"),
                roundedCell("Cell 5"),
                roundedCell("Cell 6"));

        then(button).rendersAs("""
                <div class="fixed-grid has-4-cols">
                    <div class="grid">
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 1</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal is-row-start-3">Cell 2</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 3</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 4</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 5</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 6</div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderRowFromEnd() {
        var button = fixedGrid(4).content(
                roundedCell("Cell 1"),
                roundedCell("Cell 2").isRowFromEnd(1),
                roundedCell("Cell 3"),
                roundedCell("Cell 4"),
                roundedCell("Cell 5"),
                roundedCell("Cell 6"));

        then(button).rendersAs("""
                <div class="fixed-grid has-4-cols">
                    <div class="grid">
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 1</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal is-row-from-end-1">Cell 2</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 3</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 4</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 5</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 6</div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderRowSpan() {
        var button = fixedGrid(4).content(
                roundedCell("Cell 1"),
                roundedCell("Cell 2").isRowSpan(2),
                roundedCell("Cell 3"),
                roundedCell("Cell 4"),
                roundedCell("Cell 5"),
                roundedCell("Cell 6"));

        then(button).rendersAs("""
                <div class="fixed-grid has-4-cols">
                    <div class="grid">
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 1</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal is-row-span-2">Cell 2</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 3</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 4</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 5</div>
                        <div class="cell py-3 px-4 has-background-primary has-text-primary-invert has-radius-normal">Cell 6</div>
                    </div>
                </div>
                """);
    }
}
