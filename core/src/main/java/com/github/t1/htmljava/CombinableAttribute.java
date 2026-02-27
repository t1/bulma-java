package com.github.t1.htmljava;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableSet;

/// Attributes that merge rather than replace when added twice to the same element.
/// Without this, calling `.classes("a")` then `.classes("b")` would lose `"a"`.
/// {@link Classes} and {@link Styles} extend this; regular attributes throw on duplicates.
@EqualsAndHashCode
public abstract class CombinableAttribute implements Attribute {
    private final Set<String> values;

    protected CombinableAttribute(@NonNull Set<String> values) {
        this.values = unmodifiableSet(new LinkedHashSet<>(values));
    }

    protected abstract CombinableAttribute create(Set<String> values);

    public Set<String> values() {return values;}

    public boolean empty() {return values.isEmpty();}

    public boolean has(String value) {return values.contains(value);}

    @Override public boolean matches(Attribute attribute) {
        return getClass().isInstance(attribute) && ((CombinableAttribute) attribute).values.equals(this.values);
    }

    public CombinableAttribute mergeWith(Attribute incoming) {return plus((CombinableAttribute) incoming);}

    public CombinableAttribute plus(CombinableAttribute other) {return plus(other.values.toArray(String[]::new));}

    public CombinableAttribute plus(String... newValues) {
        var copy = new LinkedHashSet<>(this.values);
        Stream.of(newValues).filter(Objects::nonNull).forEach(copy::add);
        return create(copy);
    }

    public CombinableAttribute minus(CombinableAttribute other) {
        var copy = new LinkedHashSet<>(this.values);
        other.values.forEach(copy::remove);
        return create(copy);
    }

    @Override public @NonNull String toString() {return render();}

    @Override public void renderValue(Renderer renderer) {
        var first = true;
        for (var value : values) {
            if (first) first = false;
            else renderer.unsafeAppend(" ");
            renderer.unsafeAppend(value);
        }
    }
}
