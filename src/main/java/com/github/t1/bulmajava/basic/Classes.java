package com.github.t1.bulmajava.basic;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableSet;

@Value @RequiredArgsConstructor @Accessors(fluent = true)
@SuppressWarnings("ClassCanBeRecord")
public class Classes implements Attribute {
    public static Classes of(String... classes) {
        return (classes.length == 0) ? null :
                Classes.of(Set.of()).plus(classes); // ignore null classes
    }

    public static Classes of(Set<String> classes) {return new Classes(unmodifiableSet(classes));}


    @NonNull Set<String> set;

    @Override public String toString() {return render();}


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
        var set = new LinkedHashSet<>(this.set);
        set.removeAll(classes.set);
        return set.isEmpty() ? null : Classes.of(set);
    }


    @Override public void renderValue(Renderer renderer) {
        var first = true;
        for (var aClass : set) {
            if (first) first = false;
            else renderer.append(" ");
            renderer.append(aClass);
        }
    }
}
