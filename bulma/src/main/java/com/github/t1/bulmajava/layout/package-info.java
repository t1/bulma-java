/// Bulma [layout](https://bulma.io/documentation/layout/) components:
/// {@link com.github.t1.bulmajava.layout.Container Container},
/// {@link com.github.t1.bulmajava.layout.Footer Footer},
/// {@link com.github.t1.bulmajava.layout.Hero Hero},
/// {@link com.github.t1.bulmajava.layout.Level Level},
/// {@link com.github.t1.bulmajava.layout.Media Media},
/// {@link com.github.t1.bulmajava.layout.Section Section}.
///
/// ## Design Decisions
///
/// - **Hero uses body(), not content()**: Like {@link com.github.t1.bulmajava.elements.Table},
///   the `content()` methods on {@link com.github.t1.bulmajava.layout.Hero} are deprecated.
///   Use `head()`, `body()`, and `foot()` instead.
/// - **Level auto-wraps**: Content added to a {@link com.github.t1.bulmajava.layout.Level}
///   is auto-wrapped in `div.level-item` unless it's already a `level-left` or `level-right`.
/// - **Media content routing**: {@link com.github.t1.bulmajava.layout.Media Media}'s `content()`
///   wraps in `div.media-content`. Use `left()` and `right()` for side content.
package com.github.t1.bulmajava.layout;
