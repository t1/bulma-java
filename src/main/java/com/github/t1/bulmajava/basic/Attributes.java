package com.github.t1.bulmajava.basic;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Attribute.StringAttribute.stringAttribute;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor @Accessors(fluent = true, chain = true) @EqualsAndHashCode
public class Attributes implements Renderable {
    public static Attributes of(Attribute... attributes) {
        return new Attributes(Stream.of(attributes).filter(Objects::nonNull).collect(toList()));
    }


    private final List<Attribute> attributes;

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


    public Attributes add(@NonNull Attribute attribute) {
        var copy = this.attributes;
        boolean found = false;
        for (int i = 0; i < copy.size(); i++) {
            var item = copy.get(i);
            if (item.hasKey(attribute.key())) {
                found = true;
                copy.set(i, item.and(attribute));
                break;
            }
        }
        if (!found) copy.add(attribute);
        copy.sort(Attribute.COMPARATOR);
        return this;
    }

    public void remove(Attribute attribute) {replace(attribute, null);}

    public Attributes replace(Attribute existing, Attribute replacement) {
        boolean found = false;
        for (int i = 0; i < this.attributes.size(); i++) {
            var item = this.attributes.get(i);
            if (item.hasKey(existing.key())) {
                found = true;
                if (replacement == null) this.attributes.remove(i);
                else this.attributes.set(i, replacement);
                break;
            }
        }
        if (!found) throw new IllegalStateException("exiting not found");
        return this;
    }

    public void render(Renderer renderer) {
        for (int i = 0; i < attributes.size(); i++) {
            if (i > 0) renderer.unsafeAppend(" ");
            attributes.get(i).render(renderer);
        }
    }
}
