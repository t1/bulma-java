# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

bulma-java is a type-safe, fluent Java API for generating HTML with Bulma CSS components. Three Maven modules:

- **core** (`java-html`) — Pure HTML rendering foundation. Zero runtime dependencies. Java 17.
- **bulma** (`bulma-java`) — Bulma CSS components built on core. Java 17.
- **forms-test** (`bulma-java-forms-test`) — Quarkus demo/integration app. Java 21. Not published.

See README.adoc for full documentation.

## Build Commands

```bash
mvn install                                          # build everything
mvn test -pl bulma                                   # test bulma module only
mvn test -pl bulma -Dtest=ButtonTest                 # single test class
mvn test -pl bulma -Dtest=ButtonTest#shouldRender    # single test method
cd forms-test && mvn quarkus:dev                     # run demo app on localhost:8080
```

## Architecture

Every element uses a generic self-type (`AbstractElement<SELF extends AbstractElement<?>>`) for type-safe fluent chaining. CSS classes are applied via typed modifiers (`IsModifier` → `is-*`, `HasModifier` → `has-*`), not raw strings. All components are created via static factory methods designed for static import.

See README.adoc § Architecture for details (modifier hierarchy, rendering pipeline, Field internals).

## Testing

- JUnit 5 + AssertJ with custom `CustomAssertions.then(renderable).rendersAs("expected html")`
- Tests aggregate output to `bulma/target/all-tests.html` for visual verification in browser
- Modifier coverage via `@EnumSource` parameterized tests

## SKILL.md

SKILL.md contains the comprehensive component reference and usage guide. Consult it when writing code that uses bulma-java components.
