/// Bulma [column grid system](https://bulma.io/documentation/columns/).
///
/// Use {@link com.github.t1.bulmajava.columns.Columns#columns()} as the container
/// and add {@link com.github.t1.bulmajava.columns.Column}s with fractional or numeric sizes.
///
/// ## Design Decisions
///
/// - **Responsive sizes**: {@link com.github.t1.bulmajava.columns.Column#column(ColumnSize, ColumnSize, ColumnSize, ColumnSize, ColumnSize)}
///   takes one size per breakpoint (mobile, tablet, desktop, widescreen, fullhd),
///   generating suffix classes like `is-half-mobile`, `is-full-tablet`.
/// - **Numeric sizes**: `Column.column(4)` produces `is-4` (for 12-column grid),
///   while `Column.column(ColumnSize.ONE_THIRD)` produces `is-one-third`.
package com.github.t1.bulmajava.columns;
