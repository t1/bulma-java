package com.github.t1.bulmajava.basic;

import lombok.NonNull;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public record Classes(@NonNull Set<String> set) implements Attribute {
    public static Classes of(String... classes) {
        return (classes.length == 0) ? null :
                Classes.of(Set.of()).plus(classes); // ignore null classes
    }

    public static Classes of(Set<String> classes) {return new Classes(new LinkedHashSet<>(classes));}

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

    public void minus(Classes classes) {set.removeAll(classes.set);}


    @Override public void renderValue(Renderer renderer) {
        var first = true;
        for (var aClass : set) {
            if (first) first = false;
            else renderer.append(" ");
            renderer.append(aClass);
        }
    }
}
