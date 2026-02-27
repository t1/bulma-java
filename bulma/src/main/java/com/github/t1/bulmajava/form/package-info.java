/// Bulma [form controls](https://bulma.io/documentation/form/):
/// {@link com.github.t1.bulmajava.form.Form Form},
/// {@link com.github.t1.bulmajava.form.Field Field},
/// {@link com.github.t1.bulmajava.form.Input Input},
/// {@link com.github.t1.bulmajava.form.Select Select},
/// {@link com.github.t1.bulmajava.form.Textarea Textarea},
/// {@link com.github.t1.bulmajava.form.Checkbox Checkbox},
/// {@link com.github.t1.bulmajava.form.Radio Radio},
/// {@link com.github.t1.bulmajava.form.FileInput FileInput}.
///
/// ## Design Decisions
///
/// - **Field does the heavy lifting**: {@link com.github.t1.bulmajava.form.Field} generates
///   the complex Bulma markup (label, control wrapper, icons, addons, help text) from a
///   simple fluent API. You don't need to manually nest `div.control` elements.
/// - **Form auto-wraps inputs**: Adding an {@link com.github.t1.bulmajava.form.Input} or
///   {@link com.github.t1.bulmajava.form.Textarea} directly to a
///   {@link com.github.t1.bulmajava.form.Form} wraps it in a {@link com.github.t1.bulmajava.form.Field}
///   automatically.
/// - **Select uses option(), not content()**: The `content()` methods on
///   {@link com.github.t1.bulmajava.form.Select} are deprecated.
///   Use `option()` and `options()` instead.
/// - **Input has no content**: Calling `content()` on {@link com.github.t1.bulmajava.form.Input}
///   throws `UnsupportedOperationException` — `<input>` is a void element.
package com.github.t1.bulmajava.form;
