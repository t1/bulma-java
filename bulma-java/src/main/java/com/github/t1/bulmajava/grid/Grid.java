package com.github.t1.bulmajava.grid;

import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Renderable;

public class Grid extends AbstractElement<Grid> {
    public static Cell cell() {return new Cell();}

    public static Grid grid() {return new Grid();}

    public static FixedGrid fixedGrid() {return new FixedGrid();}


    public static FixedGrid fixedGrid(int columns) {return new FixedGrid(columns);}

    private Grid() {super("div", "grid");}

    public Grid colMin(int n) {return classes("is-col-min-" + n);}

    public Grid gap(int n) {return classes("is-gap-" + n);}

    public Grid colGap(int n) {return classes("is-column-gap-" + n);}

    public Grid rowGap(int n) {return classes("is-row-gap-" + n);}


    public static class FixedGrid extends AbstractElement<FixedGrid> {
        private FixedGrid(int columns) {
            this();
            classes("has-" + columns + "-cols");
        }

        private FixedGrid() {
            super("div", "fixed-grid");
            super.content(grid());
        }

        @Override public FixedGrid content(Renderable content) {
            ((Grid) content()).content(content);
            return this;
        }

        public FixedGrid autoCount() {return classes("has-auto-count");}
    }

    public static class Cell extends AbstractElement<Cell> {
        private Cell() {
            super("div", "cell");
        }

        public Renderable isColStart(int col) {return classes("is-col-start-" + col);}

        public Renderable isColFromEnd(int col) {return classes("is-col-from-end-" + col);}

        public Renderable isColSpan(int col) {return classes("is-col-span-" + col);}

        public Renderable isRowStart(int row) {return classes("is-row-start-" + row);}

        public Renderable isRowFromEnd(int row) {return classes("is-row-from-end-" + row);}

        public Renderable isRowSpan(int row) {return classes("is-row-span-" + row);}
    }
}
