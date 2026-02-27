package com.github.t1.bulmajava.grid;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.htmljava.Renderable;
import lombok.experimental.SuperBuilder;

/// Bulma CSS grid. Content added to a {@link FixedGrid} is routed to its inner Grid.
@SuperBuilder(toBuilder = true)
public class Grid extends BulmaElement<Grid> {
    public static Cell cell() {return new Cell();}

    public static Grid grid() {return new Grid();}

    public static FixedGrid fixedGrid() {return new FixedGrid();}


    public static FixedGrid fixedGrid(int columns) {return new FixedGrid(columns);}

    private Grid() {super("div", "grid");}

    public Grid colMin(int n) {return classes("is-col-min-" + n);}

    public Grid gap(int n) {return classes("is-gap-" + n);}

    public Grid colGap(int n) {return classes("is-column-gap-" + n);}

    public Grid rowGap(int n) {return classes("is-row-gap-" + n);}


    @SuperBuilder(toBuilder = true)
    public static class FixedGrid extends BulmaElement<FixedGrid> {
        private FixedGrid(int columns) {
            this();
            classes("has-" + columns + "-cols");
        }

        private FixedGrid() {
            super("div", "fixed-grid");
            super.content(grid(), LAST);
        }

        @Override public FixedGrid content(Renderable content, int index) {
            ((Grid) content()).content(content, index);
            return this;
        }

        public FixedGrid autoCount() {return classes("has-auto-count");}
    }

    @SuperBuilder(toBuilder = true)
    public static class Cell extends BulmaElement<Cell> {
        private Cell() {
            super("div", "cell");
        }

        public Cell isColStart(int col) {return classes("is-col-start-" + col);}

        public Cell isColFromEnd(int col) {return classes("is-col-from-end-" + col);}

        public Cell isColSpan(int col) {return classes("is-col-span-" + col);}

        public Cell isRowStart(int row) {return classes("is-row-start-" + row);}

        public Cell isRowFromEnd(int row) {return classes("is-row-from-end-" + row);}

        public Cell isRowSpan(int row) {return classes("is-row-span-" + row);}
    }
}
