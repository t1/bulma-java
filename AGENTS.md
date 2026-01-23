# AGENTS.md

This file provides guidance to Claude Code (claude.ai/code) or other Agentic coding tools when working with code in this repository.

**For comprehensive project documentation, architecture, testing, and coding conventions, see [README.adoc](README.adoc).**

## Module Quick Reference

This is a Maven multi-module project:

- **`core/`** - Foundation library (`com.github.t1.htmljava`): `Renderable`, `Renderer`, `AbstractElement`, `Modifier`
- **`bulma/`** - Bulma components (`com.github.t1.bulmajava`): organized into `basic/`, `elements/`, `components/`, `form/`, `layout/`, `columns/`
- **`forms-test/`** - Demo Quarkus application (not published)

## Running Tests

Run specific test:
```bash
mvn test -pl bulma -Dtest=ButtonTest#shouldRenderButton
```

View test output visually: After running `mvn test` in the bulma module, open `bulma/target/all-tests.html` to see all test cases rendered with Bulma styling.

Set `-DBULMA_JAVA_OFFLINE=true` to load Bulma/FontAwesome from localhost:8080 instead of CDN.

## AI-Specific Guidance

### Before Making Changes

- **ALWAYS read files before editing them.** Never propose changes to code you haven't read.
- Use the Task tool with `subagent_type=Explore` when exploring the codebase to understand structure or find where functionality is implemented (not for specific file/class lookups).

### Anti-Patterns to Avoid

- **Don't use builders** - The API is already fluent. Every method returns `self()` for chaining.
- **Don't add features beyond what's requested** - Avoid over-engineering. No unnecessary error handling, abstractions, helpers, or "future-proofing."
- **Don't add documentation unless asked** - Don't add docstrings, comments, or type annotations to code you didn't change. Only comment where logic isn't self-evident.
- **Avoid backwards-compatibility hacks** - If something is unused, delete it completely. No `_vars`, re-exports, `// removed` comments, etc.
- **Don't bypass type safety** - Use the fluent API, not string concatenation.

### Security

- HTML escaping is automatic via `Renderer.safeAppend()`
- Only use `unsafeAppend()` or `UnsafeString` for trusted HTML content
- Never bypass escaping for user-provided content
