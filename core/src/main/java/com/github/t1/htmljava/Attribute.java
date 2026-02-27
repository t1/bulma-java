package com.github.t1.htmljava;

import lombok.NonNull;

import java.util.Comparator;

public interface Attribute extends Renderable {
    static Attribute of(@NonNull String key) {return new NoValueAttribute(key);}

    static Attribute of(@NonNull String key, @NonNull String value) {
        return switch (key) {
            case Styles.KEY -> Styles.of(value);
            case Classes.KEY -> Classes.of(value);
            default -> new StringAttribute(key, value, false);
        };
    }

    /// We want some attributes to be in a specific order:
    /// 1. `id`
    /// 2. `class`
    /// 3. `rel`
    /// 4. `href`
    /// 5. everything else
    Comparator<String> KEY_COMPARATOR = (left, right) -> {
        if (left.equals(right)) return 0;
        return switch (left) {
            case "id" -> -1;
            case "class" -> right.equals("id") ? 1 : -1;
            case "rel" -> right.equals("id") || right.equals("class") ? 1 : -1;
            case "href" -> right.equals("id") || right.equals("class") || right.equals("rel") ? 1 : -1;
            default -> 0;
        };
    };
    Comparator<Attribute> COMPARATOR = (left, right) -> KEY_COMPARATOR.compare(left.key(), right.key());


    String key();

    default boolean hasKey(String expected) {return key().equals(expected);}

    boolean matches(Attribute attribute);

    @Override default void render(Renderer renderer) {
        renderer.unsafeAppend(key()).unsafeAppend("=\"");
        renderValue(renderer);
        renderer.unsafeAppend("\"");
    }

    void renderValue(Renderer renderer);

    default String value() {
        var renderer = new Renderer();
        renderValue(renderer);
        return renderer.render();
    }

    record StringAttribute(String key, String value, boolean unsafe) implements Attribute {
        public static Attribute unsafeStringAttribute(@NonNull String key, @NonNull String value) {return new StringAttribute(key, value, true);}

        @Override public boolean matches(Attribute attribute) {
            return hasKey(attribute.key()) && attribute instanceof StringAttribute str && value.equals(str.value);
        }

        @Override public String key() {return key;}

        @Override public void renderValue(Renderer renderer) {
            if (unsafe) renderer.unsafeAppend(value);
            else renderer.safeAppend(value);
        }
    }

    record NoValueAttribute(String key) implements Attribute {
        @Override public String key() {return key;}

        @Override public boolean matches(Attribute attribute) {return this.hasKey(attribute.key());}

        @Override public void render(Renderer renderer) {renderer.unsafeAppend(key());}

        @Override public void renderValue(Renderer renderer) {}
    }
}
