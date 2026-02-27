# AGENTS.md

**VERY IMPORTANT** Be very critical and honest to what I say. And when I ask a question,
it's just a question, not a suggestion.
**Do NOT use local/private auto memory.** Store all learnings and conventions in this file (or other project files),
so they are shared with everyone working on the project.

Don't forget to update the documentation when you change the code. **VERY IMPORTANT**

If you create a new file, also stage exactly this file to git, but not any other files that are not staged.

If you commit something, don't include that co-author hint.

**For comprehensive project documentation, architecture, testing, and coding conventions,
see [README.adoc](README.adoc).**

## Documentation Strategy

**VERY IMPORTANT**: This project uses `package-info.java` files for package-level documentation and architecture.

When working with code:
- **Always read the relevant `package-info.java` files** to understand the architecture, patterns, and conventions for that package
- Use `package-info.java` to understand package responsibilities, dependencies, and relationships
- See `README.md` for project overview, tech stack, setup instructions, development guidelines, and coding conventions
- For package-specific details, refer to the co-located `package-info.java` files

## Before Making Changes

- **ALWAYS read files before editing them.** Never propose changes to code you haven't read.
- Use the Task tool with `subagent_type=Explore` when exploring the codebase to understand structure or find where
  functionality is implemented (not for specific file/class lookups).

## Anti-Patterns to Avoid

- **Don't bypass type safety** - Use the fluent API, not string concatenation. **VERY IMPORTANT**
- **Don't bypass HTML escaping** - Only use `unsafeAppend()` or `UnsafeString` for trusted HTML content, never for
  user-provided content. **VERY IMPORTANT**
- **Don't use builders** - The API is already fluent. Every method returns `self()` for chaining.
- **Don't add features beyond what's requested** - Avoid over-engineering. No unnecessary error handling, abstractions,
  helpers, or "future-proofing."
- **Don't add documentation unless asked** - Don't add docstrings, comments, or type annotations to code you didn't
  change. Only comment where logic isn't self-evident.
- **Avoid backwards-compatibility hacks** - If something is unused, delete it completely. No `_vars`, re-exports,
  `// removed` comments, etc.

## After Making Changes

Don't forget to update the documentation when you change the code. **VERY IMPORTANT**

If you create a new file, also stage exactly this file to git, but not any other files that are not staged.

If you commit something, don't include that co-author hint.

**VERY IMPORTANT** Always check changed files with the IDE MCP `get_file_problems` tool with `errorsOnly: false`
(to see warnings, not just errors) and all qualified names are replaced by imports (if possible).

## TDD

See `TDD.md` for Test-Driven Development practices that must be followed every time a new feature is added
or a bug is fixed (but not when just refactoring). The clean code principles from `CLEAN_CODE.md` are applied
automatically during the TDD refactor phase via a subagent. **VERY IMPORTANT**

## Clean Code

**VERY IMPORTANT**: If you do a refactoring (i.e. change the production code without affecting the tests)
or review existing code, then use a **Clean Code Review (Subagent)**:
Use the Task tool with `subagent_type=general-purpose` to spawn a clean code review subagent.
The subagent's prompt must instruct it to:

1. Read `CLEAN_CODE.md`
2. Read the implementation needing refactoring
3. Analyze the code against all clean code principles in priority order
   (naming, code smells, SOLID, method design, structure)
4. Return a prioritized list of specific, actionable refactoring suggestions
   Apply the returned suggestions, then ensure all tests continue to pass after each change.
