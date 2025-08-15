package com.github.t1.htmljava;

import lombok.NonNull;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableSet;

public record Classes(@NonNull Set<String> set) implements Attribute {
    public static Classes of(Stream<String> classes) {return of(classes.toArray(String[]::new));}

    public static Classes of(String... classes) {
        return (classes.length == 0) ? null :
                Classes.of(Set.of()).plus(classes); // ignore null classes
    }

    public static Classes of(Set<String> classes) {return new Classes(unmodifiableSet(new LinkedHashSet<>(classes)));}

    @Override public @NonNull String toString() {return render();}


    @Override public String key() {return "class";}

    public boolean empty() {return set.isEmpty();}

    public boolean hasClass(String name) {return set.contains(name);}

    @Override public boolean matches(Attribute attribute) {
        return attribute instanceof Classes that && that.set.equals(this.set);
    }


    public Classes and(Attribute classes) {return plus((Classes) classes);}

    public Classes plus(Classes classes) {return plus(classes.set.toArray(String[]::new));}

    public Classes plus(String... classes) {
        var copy = new LinkedHashSet<>(this.set);
        Stream.of(classes).filter(Objects::nonNull).forEach(copy::add);
        return Classes.of(copy);
    }

    public Classes minus(Classes classes) {
        var copy = new LinkedHashSet<>(this.set);
        classes.set.forEach(copy::remove);
        return Classes.of(copy);
    }


    @Override public void renderValue(Renderer renderer) {
        var first = true;
        for (var aClass : set) {
            if (first) first = false;
            else renderer.unsafeAppend(" ");
            renderer.unsafeAppend(aClass);
        }
    }
}
