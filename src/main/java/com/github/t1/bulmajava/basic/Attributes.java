package com.github.t1.bulmajava.basic;

import lombok.NonNull;
import lombok.Value;
import lombok.With;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Attribute.StringAttribute.stringAttribute;
import static java.util.Collections.unmodifiableList;
import static lombok.AccessLevel.PRIVATE;

@Value @Accessors(fluent = true)
public class Attributes implements Renderable {
    public static Attributes of(Attribute... attributes) {
        return new Attributes(Stream.of(attributes).filter(Objects::nonNull).toList());
    }


    @With(PRIVATE) @NonNull List<Attribute> attributes;

    @Override public String toString() {return render();}


    @Override public Optional<Renderable> find(Predicate<Renderable> predicate) {
        return attributes.stream().filter(predicate).findFirst().map(Renderable.class::cast);
    }

    public boolean hasAttribute(String key, String value) {return hasAttribute(stringAttribute(key, value));}

    public boolean hasAttribute(Attribute attribute) {return hasAttribute(attribute::matches);}

    public boolean hasAttribute(Predicate<Attribute> predicate) {return findAttribute(predicate).isPresent();}

    public Optional<Attribute> findAttribute(Predicate<Attribute> predicate) {return findAttributes(predicate).findAny();}

    public Stream<Attribute> findAttributes(Predicate<Attribute> predicate) {return attributes.stream().filter(predicate);}

    public boolean isEmpty() {return attributes.isEmpty();}


    public Attributes and(@NonNull Attribute attribute) {
        var copy = new ArrayList<>(this.attributes);
        boolean found = false;
        for (int i = 0; i < copy.size(); i++) {
            var item = copy.get(i);
            if (item.hasKey(attribute.key())) {
                found = true;
                copy.set(i, item.and(attribute));
                break;
            }
        }
        if (!found) {
            // the href and class attributes are most relevant when developing, so we always put it first
            if (attribute.hasKey("href") || attribute.hasKey("class"))
                copy.add(0, attribute);
            else copy.add(attribute);
        }
        return withAttributes(unmodifiableList(copy));
    }

    public Attributes replace(Attribute existing, Attribute replacement) {
        var copy = new ArrayList<>(this.attributes);
        boolean found = false;
        for (int i = 0; i < copy.size(); i++) {
            var item = copy.get(i);
            if (item.hasKey(existing.key())) {
                found = true;
                if (replacement == null) copy.remove(i);
                else copy.set(i, replacement);
                break;
            }
        }
        if (!found) throw new IllegalStateException("exiting not found");
        return withAttributes(unmodifiableList(copy));
    }

    public void render(Renderer renderer) {
        for (int i = 0; i < attributes.size(); i++) {
            if (i > 0) renderer.append(" ");
            attributes.get(i).render(renderer);
        }
    }
}
