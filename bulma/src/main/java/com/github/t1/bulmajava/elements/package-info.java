/// Bulma [elements](https://bulma.io/documentation/elements/):
/// {@link com.github.t1.bulmajava.elements.Block Block},
/// {@link com.github.t1.bulmajava.elements.Box Box},
/// {@link com.github.t1.bulmajava.elements.Button Button},
/// {@link com.github.t1.bulmajava.elements.Content Content},
/// {@link com.github.t1.bulmajava.elements.Delete Delete},
/// {@link com.github.t1.bulmajava.elements.Icon Icon},
/// {@link com.github.t1.bulmajava.elements.Image Image},
/// {@link com.github.t1.bulmajava.elements.Notification Notification},
/// {@link com.github.t1.bulmajava.elements.ProgressBar ProgressBar},
/// {@link com.github.t1.bulmajava.elements.Table Table},
/// {@link com.github.t1.bulmajava.elements.Tag Tag},
/// {@link com.github.t1.bulmajava.elements.Title Title}.
///
/// ## Design Decisions
///
/// - **Icons are FontAwesome-only**: {@link com.github.t1.bulmajava.elements.Icon} generates
///   FontAwesome markup (`<i class="fas fa-{name}">`). {@link com.github.t1.bulmajava.elements.IconStyle}
///   maps to FA style prefixes (`fas`, `far`, `fal`, `fab`, `fad`).
/// - **Content underscore**: The factory method is `content_()` (with trailing underscore)
///   because `content()` clashes with the inherited method on {@link com.github.t1.htmljava.AbstractElement}.
/// - **Table uses head/body/foot**: The `content()` methods on {@link com.github.t1.bulmajava.elements.Table}
///   are deprecated. Use `head()`, `body()`, and `foot()` instead.
/// - **Title defaults**: `title("text")` defaults to `<h1>`, `subtitle("text")` to `<h2>`.
///   Use `title(3, "text")` for a specific heading level.
package com.github.t1.bulmajava.elements;
