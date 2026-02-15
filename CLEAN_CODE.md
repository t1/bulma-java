# Clean Code Principles

We use **Clean Code**, a disciplined development approach originally described by Robert C. Martin.
This mode enforces high-quality code through structured ideals when writing code.

## Philosophy and Mindset

### Why Clean Code Matters

- **Investment in Low Cost of Change**: Clean code enables continuous modification
  and extension without exponential complexity growth
- **Defect Minimization**: Well-structured, readable code reduces bugs at the source
- **Professional Responsibility**: Code is read far more often than it is written - optimize for readers
- **Boy Scout Rule**: Always leave code cleaner than you found it

### Core Values

- **Simplicity**: Always prefer the simpler solution (KISS - Keep It Simple, Stupid)
- **Clarity**: Code should reveal intent; readers shouldn't need to decipher
- **Standards**: Follow project conventions consistently
- **Root Cause Analysis**: Fix problems at their source, not symptoms
- **Reasoned Design**: Every decision should have a clear rationale

## Core Principles (Always Apply)

These principles form the foundation of clean code. Apply them continuously throughout development:

### 1. Loose Coupling

- **Components know little about each other**
- Dependencies flow through well-defined APIs - only these are `public`
- Changes in one component minimally affect others
- Prefer composition over inheritance
- Depend on abstractions, not concretions

### 2. High Cohesion

- **Elements that belong together stay together**
- Each module has a single, well-defined purpose
- Related functionality is grouped
- Unrelated functionality is separated
- Strong internal relationships, weak external dependencies
- Internals are not `public` but have limited visibility

### 3. Change is Local

- **Modifications are contained within boundaries**
- A feature change affects minimal files/modules
- Architectural boundaries prevent ripple effects
- Stable interfaces protect implementations
- New features extend without modifying existing code

### 4. It is Easy to Remove

- **Simplify by removing complexity**
- Delete dead code immediately
- Remove unused abstractions
- Eliminate unnecessary features
- Code should be easy to delete, not just easy to add

### 5. Mind-sized Components

- **Create graspable, understandable units**
- Functions: one screen or less
- Classes: under 100 lines as a guideline
- Modules: comprehensible purpose
- Systems: clear component boundaries
- If you can't hold it in your head, it's too big

## SOLID Principles for Class Design

Apply these principles when designing classes and modules:

### Single Responsibility Principle (SRP)

- **A class should have one, and only one, reason to change**
- Each class addresses a single concern
- Responsibilities are clearly separated
- Changes to one aspect don't affect unrelated functionality
- Test: Can you describe the class without using "and" or "or"?

### Open/Closed Principle (OCP)

- **Open for extension, closed for modification**
- Extend behavior through inheritance or composition
- Don't modify existing, working code to add features
- Use abstractions to enable extension points
- New requirements should add code, not change it

### Liskov Substitution Principle (LSP)

- **Subtypes must be substitutable for their base types**
- Derived classes honor the contracts of base classes
- No surprising behavior when using polymorphism
- Preconditions cannot be strengthened
- Postconditions cannot be weakened

### Dependency Inversion Principle (DIP)

- **Depend on abstractions, not concretions**
- High-level modules don't depend on low-level modules
- Both depend on abstractions (interfaces)
- Abstractions don't depend on details
- Details depend on abstractions

### Interface Segregation Principle (ISP)

- **Clients should not be forced to depend on interfaces they don't use**
- Many client-specific interfaces are better than one general-purpose interface
- Avoid "fat" interfaces
- Split large interfaces into smaller, focused ones
- Clients depend only on methods they actually use

## Code Smells to Detect and Eliminate

Watch for these anti-patterns during every refactor phase:

### Structural Smells

- **Rigidity**: Hard to change because changes cascade
- **Fragility**: Changes break unexpected parts
- **Immobility**: Code can't be reused in other contexts
- **Viscosity**: Doing the right thing is harder than hacks
- **Needless Complexity**: Over-engineering for hypothetical futures
- **Needless Repetition**: Duplication of code or knowledge
- **Opacity**: Code is hard to understand

### Design Smells

- **Feature Envy**: Method uses more of another class than its own
- **Inappropriate Intimacy**: Classes know too much about each other's internals
- **Artificial Coupling**: Unrelated elements are coupled
- **Hidden Temporal Coupling**: Operations must occur in sequence but code doesn't enforce it
- **Transitive Navigation**: Long chains like `a.getB().getC().doSomething()` (Law of Demeter violation)

### Implementation Smells

- **Magic Numbers/Strings**: Unexplained literals in code
- **Dead Code**: Unreachable or unused code
- **Commented-Out Code**: Version control is for history
- **Inconsistent Naming**: Similar things named differently
- **Long Methods**: Methods doing too many things
- **Long Parameter Lists**: Too many arguments (>3 is suspicious)
- **Data Clumps**: Same group of data items appearing together
- **Primitive Obsession**: Using primitives instead of small objects

## Naming Conventions

Names are the primary form of documentation. Choose them carefully.

### General Naming Rules

1. **Choose Descriptive and Unambiguous Names**
    - Name reveals intent completely
    - No mental mapping required
    - Pronounceable and searchable
    - Example: `daysToExpiration` not `dTE` or `d`

2. **Use Names at Appropriate Level of Abstraction**
    - High-level modules: abstract, conceptual names
    - Low-level modules: concrete, specific names
    - Don't leak implementation details upward
    - Example: `save()` not `writeToDatabase()`

3. **Long Names for Long Scopes**
    - **Fields**: Longer, descriptive names (`customerOrderHistory`)
    - **Parameters**: Moderately long (`orderItems`)
    - **Local variables**: Shorter (`items`)
    - **Loop variables**: Very short (`i`, `j`)

4. **Names Describe Side Effects**
    - If a method does more than one thing, the name should reflect that
    - Example: `checkPasswordAndInitializeSession()` not just `checkPassword()`
    - Better: Eliminate the side effect and make it do one thing

5. **No Encodings in Names**
    - No Hungarian notation (`strName`)
    - No type prefixes (`IInterface`, `AbstractClass`)
    - No member prefixes (`m_name`, `_name`)
    - If we absolutely need an interface, even though there is only one implementation (e.g. to inverse a dependency),
      the interface name should be bare, and the implementation reflect the kind of implementation
      (e.g. `ReportWriter` implemented by `TestReportWriter`)

### Method Naming Patterns

- **Predicates**: `isValid()`, `hasChildren()`, `canExecute()`
- **Queries**: `getName()`, `calculateTotal()`, `findById()`
- **Commands**: `save()`, `delete()`, `execute()`
- **Boolean getters**: `isActive()` not `getActive()`
- **Factory methods**: `createAccount()`, `fromString()`

## Method Design Rules

Methods are the fundamental units of behavior. Keep them clean.

### Methods Should Do One Thing

- **One thing at one level of abstraction**
- If you can extract another method with a name that isn't a restatement, it does more than one thing
- Test: Can you describe the method without using "and" or "or"?
- The method should tell a story at a consistent level of abstraction

### One Level of Abstraction Per Method

- **Don't mix high-level and low-level operations**
- High-level: `renderPageWithSetupsAndTeardowns()`
- Mid-level: `includeSetupPages()`
- Low-level: `pathParser.render(pagePath)`
- All statements in a method should be at the same level

### Order by Flow

- If a public method needs private helper methods, the public method goes first and the helper methods follow in the
  order they are called
- This nests for helper-helper methods, etc.
- If several methods call the same helper method, the helper method goes after the last method that calls it

### Limit Arguments

- **Ideal: 0 arguments (niladic)**
- **Good: 1 argument (monadic)**
- **Acceptable: 2 arguments (dyadic)**
- **Questionable: 3 arguments (triadic)**
- **Smelly: 4+ arguments (polyadic)**
- Consider argument objects for >3 parameters

### No Output Arguments

- **Reconsider output/ref arguments**
- If a function must change state, have it change the state of its owning object
- Example: `appendFooter(report)` should be `report.appendFooter()`

### No Flag Arguments

- **No boolean/selector arguments**
- Flag arguments loudly proclaim the function does more than one thing
- Split into separate functions
- Example: Don't use `render(boolean isSuite)`, use `renderForSuite()` and `renderForTest()`

### Command-Query Separation

- **Do stuff OR know about others, not both**
- **Commands**: Change state, return nothing
- **Queries**: Return information, change nothing
- Example: `if (set("username", "bob"))` violates this - unclear if checking or setting
- Better: `if (attributeExists("username"))` then `setAttribute("username", "bob")`

### Keep Methods Small

- **Methods should be small, and then smaller than that**
- Guideline: 5-20 lines
- Most classes under 100 lines
- If it's hard to name concisely, it's doing too much

## Clean Code Refactoring Patterns

Apply these patterns during the Refactor phase:

### Reconcile Differences - Unify Similar Code

- When you see duplication with slight variations
- Extract the variation into parameters
- Unify the common structure
- Example: Three similar loops with different operations → one loop with strategy parameter

### Isolate Change

- Wrap unstable or external dependencies
- Create abstractions around things that might change
- Protect core logic from external volatility
- Example: Wrap third-party APIs, databases, file systems
- This comes at a cost, so do it only if it also simplifies our own code

### Small Refactorings with Working Code In-Between

- **Never break tests during refactoring**
- Make tiny changes, run tests, commit
- If tests fail, undo and take smaller steps
- Working code → working code → working code

### Refactor Before Adding Functionality

- **Make the change easy, then make the easy change**
- Prepare the codebase structure for the new feature
- Then adding the feature becomes trivial
- Example: Extract method first, then add new behavior to the extracted method

### Encapsulate Conditionals

- Extract complex conditions into well-named methods
- Example: `if (timer.hasExpired() && !timer.isRecurrent())` better as `if (shouldBeDeleted(timer))`

### Remove Dead Code and Comments

- Unused code: delete it
- Commented-out code: delete it (version control remembers)
- Obsolete comments: delete them
- Code should be self-documenting; comments explain "why", not "what"

## TDD-Specific Clean Code Rules

### Test-Specific Principles

1. **A Test Checks One Feature**
    - Test all aspects of one feature, no more
    - Multiple assertions for the same feature are fine
    - Multiple features in one test are not fine

2. **Test Domain Specific Language**
    - Build helpers: builders, fluent APIs
    - Custom assertion helpers
    - Make tests read like specifications
    - Example: `givenUserWithRole("admin").whenAccessingResource("/admin").thenShouldSucceed()`

3. **FIRST Properties of Good Tests**
    - **Fast**: Tests should run quickly
    - **Isolated**: Tests should not depend on each other
    - **Repeatable**: Same results every time in any environment
    - **Self-Validating**: Boolean output (pass/fail), no manual verification
    - **Timely**: Written just before production code (TDD)

### Test Code is Production Code

- Apply the same quality standards to tests
- No code smells in tests
- Clean naming in tests
- Refactor tests during the Refactor phase
- Tests should be maintainable and readable

## Exception Handling Rules

### When and What to Catch

1. **Catch Specific Exceptions**
    - Catch the most specific exception possible
    - Don't catch `Exception` or `Throwable` unless absolutely necessary
    - Example: Catch `FileNotFoundException`, not `IOException`

2. **Catch Where You Can React Meaningfully**
    - Only catch where you can do something useful or add important context to the message
    - If you can't handle it, let it propagate
    - Don't catch just to log and rethrow

3. **Use Exceptions, Not Return Codes or Null**
    - Exceptions separate error handling from normal flow
    - Return codes mix error handling with business logic
    - Null returns require checks everywhere (use Optional or exceptions)
    - If something is a "normal" business state, don't use an exception to model control flow;
      the Result Object Pattern may be a good alternative

4. **Fail Fast**
    - Detect and report errors as early as possible
    - Don't allow invalid state to persist
    - Validate at boundaries

5. **Never Swallow Exceptions**
    - Empty catch blocks hide problems
    - At minimum, log with full context
    - Better: don't catch if you can't handle

## TDD Red-Green-Refactor Cycle with Clean Code Checkpoints

Test-Driven Development is very helpful when developing Clean Code.
See `start-tdd.md` for the core TDD process. This section provides Clean Code-specific checkpoints for each phase.

### RED Phase: Clean Code Checkpoints

- ✅ **Test name clearly describes behavior** (not implementation)
- ✅ **Test shows whole truth** (no hidden setup in other methods)
- ✅ **Test is isolated** (doesn't depend on other tests)
- ✅ **Test is at right level of abstraction** (domain language, not implementation details)
- ✅ **Given-When-Then sections are clear**

### GREEN Phase: Clean Code Checkpoints

- ✅ **Methods do one thing** (even in minimal implementation)
- ✅ **No magic numbers or strings** (use named constants)
- ✅ **No unnecessary complexity** (simplest thing that works)
- ✅ **All tests pass** (current and previous)

### REFACTOR Phase: Clean Code Checkpoint - Priority Order

**Priority 1: Naming (CRITICAL)**

- ✅ **Evaluate all names** for descriptiveness and intent
- ✅ **Check abstraction level** of names
- ✅ **Rename if purpose has evolved** through new tests
- ✅ **Ensure names reveal side effects** if any exist
- Example: Did `add()` become `addAndNotify()`? Rename or refactor to separate concerns.

**Priority 2: Code Smells Detection**

- ✅ **Check for duplication** (DRY principle)
- ✅ **Look for long methods** (>20 lines is suspicious)
- ✅ **Check for complex conditionals** (can they be encapsulated?)
- ✅ **Detect feature envy** (methods using other classes more than their own)
- ✅ **Find primitive obsession** (should primitives be objects?)
- ✅ **Identify magic numbers/strings** (extract to constants)

**Priority 3: SOLID Principles**

- ✅ **SRP**: Does each class have one reason to change?
- ✅ **OCP**: Can we extend without modifying?
- ✅ **LSP**: Are subtypes properly substitutable?
- ✅ **DIP**: Do we depend on abstractions?
- ✅ **ISP**: Are interfaces client-specific?

**Priority 4: Method Design**

- ✅ **One thing per method** at one level of abstraction
- ✅ **Argument count** (0-2 ideal, >3 needs refactoring)
- ✅ **No flag arguments** (split into separate methods)
- ✅ **No output arguments** (change object state instead)
- ✅ **Command-Query Separation** (do OR know, not both)

**Priority 5: Structure**

- ✅ **Core Principles**: Loose coupling, high cohesion, local change
- ✅ **Remove dead code** (unused methods, commented code)
- ✅ **Check dependencies** (no cycles, proper direction)
- ✅ **Ensure mind-sized components** (graspable units)
- ✅ **Unused imports removed** (project requirement)

**Priority 6: Documentation**

- ✅ **Update documentation** if code changed (project requirement)
- ✅ **Remove obsolete comments**
- ✅ **Ensure comments explain "why"** not "what"

## Design for Testability Principles

To write testable code, follow these design principles:

### Constructor Simplicity

- **Constructors should be simple**
- No complex logic or network calls in constructors
- Assign dependencies, don't construct them
- Easy to instantiate in tests

### Constructor Lifetime Rule

- **Dependencies must live as long or longer than the dependent**
- Don't pass short-lived dependencies to long-lived objects
- Violation makes testing difficult

### Abstraction Layers at System Boundaries

- **Wrap external dependencies**
- File systems, databases, networks, time, random
- Makes testing possible through fakes/mocks

### Isolation from Environment via Fakes

- **Use test doubles effectively**
- Fakes: Working implementations for testing
- Mocks: Verify interactions
- Stubs: Return predetermined values
- Don't test implementation details, test behavior

## Clean Code Refactoring Checklist (Quick Reference)

Use this checklist during every REFACTOR phase:

### Names

- [ ] All names are descriptive and unambiguous
- [ ] Names are at appropriate level of abstraction
- [ ] Names reveal any side effects
- [ ] No encodings or prefixes in names

### Methods

- [ ] Each method does one thing
- [ ] One level of abstraction per method
- [ ] 0-2 arguments (3+ needs refactoring)
- [ ] No flag arguments
- [ ] No output arguments
- [ ] Command-query separation maintained
- [ ] Methods are small (<20 lines guideline)

### Code Smells

- [ ] No duplication (DRY)
- [ ] No magic numbers/strings
- [ ] No dead code
- [ ] No commented-out code
- [ ] No long methods
- [ ] No long parameter lists
- [ ] No feature envy
- [ ] No primitive obsession
- [ ] Conditionals are encapsulated

### SOLID

- [ ] Single Responsibility (one reason to change)
- [ ] Open/Closed (extend without modifying)
- [ ] Liskov Substitution (subtypes substitutable)
- [ ] Dependency Inversion (depend on abstractions)
- [ ] Interface Segregation (client-specific interfaces)

### Structure

- [ ] Loose coupling
- [ ] High cohesion
- [ ] Change is local
- [ ] Easy to remove
- [ ] Mind-sized components

### Project-Specific

- [ ] No unused imports
- [ ] All qualified names replaced by imports (if possible)
- [ ] Documentation updated
- [ ] Code style followed (var, BDD, etc.)
- [ ] Test coverage adequate

### Tests

- [ ] Tests follow Given-When-Then
- [ ] Test names describe behavior
- [ ] Tests are isolated and independent
- [ ] Tests use domain language
- [ ] Tests are simple and readable

## Core Mantras

- **Boy Scout Rule**: "Always leave the code cleaner than you found it"
- **KISS**: Simpler is always better - complexity is the enemy
- **Follow Conventions**: Consistency is more valuable than personal preference
- **Root Cause Analysis**: Fix problems at their source, not symptoms
- **Don't Be Arbitrary**: Every design choice should have a rationale

**This is not optional. This is the standard.**

Stop at every checkpoint. Apply every principle. Eliminate every smell. Write clean code.
