/// Bulma modifier enums and the {@link com.github.t1.bulmajava.basic.BulmaElement} base class.
///
/// For usage patterns and examples, see the
/// [Usage Patterns section in the README](https://github.com/t1/bulma-java#usage-patterns).
///
/// ## Design Decisions
///
/// - **IsModifier / HasModifier**: Bulma CSS classes follow two naming conventions:
///   `is-*` (e.g., `is-primary`) and `has-*` (e.g., `has-text-weight-bold`).
///   {@link com.github.t1.bulmajava.basic.IsModifier} and
///   {@link com.github.t1.bulmajava.basic.HasModifier} map these by providing the prefix;
///   the enum constant name is converted to kebab-case automatically.
/// - **Compound class names**: Some enums override `key()` to insert extra segments.
///   E.g., {@link com.github.t1.bulmajava.basic.FontWeight#BOLD} produces `has-text-weight-bold`,
///   not `has-bold`.
/// - **BulmaElement vs AbstractElement**: {@link com.github.t1.bulmajava.basic.BulmaElement}
///   adds Bulma-specific helpers like `hasText()` and `hasBackground()` for compound modifiers
///   (e.g., `has-text-primary`, `has-background-danger`).
/// - **FontSize is not a modifier**: {@link com.github.t1.bulmajava.basic.FontSize} holds
///   FontAwesome icon size codes (e.g., `2xs`, `3x`), not Bulma CSS class modifiers.
package com.github.t1.bulmajava.basic;
