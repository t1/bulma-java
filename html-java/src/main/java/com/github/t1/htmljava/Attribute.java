package com.github.t1.htmljava;

import lombok.NonNull;

import java.util.Comparator;

public interface Attribute extends Renderable {
    /**
     * We want some attributes to be in a specific order:
     * <ol>
     *     <li><code>id</code></li>
     *     <li><code>class</code></li>
     *     <li><code>rel</code></li>
     *     <li><code>href</code></li>
     *     <li>everything else</li>
     * </ol>
     */
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

    default Attribute and(Attribute attribute) {throw new UnsupportedOperationException();}

    record StringAttribute(String key, String value, boolean unsafe) implements Attribute {
        public static Attribute stringAttribute(@NonNull String key, @NonNull String value) {return new StringAttribute(key, value, false);}

        public static Attribute unsafeStringAttribute(@NonNull String key, @NonNull String value) {return new StringAttribute(key, value, true);}

        @Override public boolean matches(Attribute attribute) {
            return hasKey(attribute.key()) && attribute instanceof StringAttribute str && value.equals(str.value);
        }

        @Override public String key() {return key;}

        @Override public Attribute and(Attribute attribute) {
            if ("style".equals(key()))
                return stringAttribute(key, value + " " + ((StringAttribute) attribute).value);
            throw new UnsupportedOperationException("can't add to a '" + key() + "' attribute");
        }

        @Override public void renderValue(Renderer renderer) {
            if (unsafe) renderer.unsafeAppend(value);
            else renderer.safeAppend(value);
        }
    }

    record NoValueAttribute(String key) implements Attribute {
        public static Attribute noValueAttribute(@NonNull String key) {return new NoValueAttribute(key);}

        @Override public String key() {return key;}

        @Override public boolean matches(Attribute attribute) {return attribute.hasKey(attribute.key());}

        @Override public void render(Renderer renderer) {renderer.unsafeAppend(key());}

        @Override public void renderValue(Renderer renderer) {}
    }
}
