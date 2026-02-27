/// Bulma [CSS grid](https://bulma.io/documentation/grid/) system.
///
/// {@link com.github.t1.bulmajava.grid.Grid Grid} is the free-form grid,
/// {@link com.github.t1.bulmajava.grid.Grid.FixedGrid FixedGrid} constrains to a fixed column count,
/// and {@link com.github.t1.bulmajava.grid.Grid.Cell Cell} represents individual grid cells.
///
/// ## Design Decisions
///
/// - **FixedGrid delegates to Grid**: Content added to a `FixedGrid` is routed to its
///   inner `Grid` element, not added directly.
/// - **Cell positioning**: Use `isColStart()`, `isColSpan()`, `isRowStart()`, `isRowSpan()`
///   for explicit grid placement. `isColFromEnd()` and `isRowFromEnd()` count from the end.
package com.github.t1.bulmajava.grid;
