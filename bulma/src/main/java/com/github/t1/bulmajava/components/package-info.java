/// Bulma [components](https://bulma.io/documentation/components/):
/// {@link com.github.t1.bulmajava.components.Breadcrumb Breadcrumb},
/// {@link com.github.t1.bulmajava.components.Card Card},
/// {@link com.github.t1.bulmajava.components.Dropdown Dropdown},
/// {@link com.github.t1.bulmajava.components.Menu Menu},
/// {@link com.github.t1.bulmajava.components.Message Message},
/// {@link com.github.t1.bulmajava.components.Modal Modal},
/// {@link com.github.t1.bulmajava.components.Navbar Navbar},
/// {@link com.github.t1.bulmajava.components.Pagination Pagination},
/// {@link com.github.t1.bulmajava.components.Panel Panel},
/// {@link com.github.t1.bulmajava.components.Tabs Tabs}.
///
/// ## Design Decisions
///
/// - **Auto-wrapping**: Components automatically wrap child content in the correct Bulma
///   structure. E.g., `Card.content()` wraps in `div.card-content`,
///   and `Menu.content()` wraps items in `ul.menu-list > li`.
/// - **Navbar routing**: {@link com.github.t1.bulmajava.components.Navbar} routes content
///   via `start()` and `end()`, not `content()`.
///   The deprecated `content()` overrides exist only for backwards compatibility.
/// - **Accessibility**: Components add ARIA attributes automatically
///   (e.g., `role="navigation"` on Navbar/Pagination, `aria-label` on Breadcrumb).
package com.github.t1.bulmajava.components;
