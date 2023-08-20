package com.github.t1.bulmajava.basic;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

public interface Attribute extends Renderable {
    static Attribute noValueAttribute(@NonNull String key) {
        return new Attribute() {
            @Override public String key() {return key;}

            @Override public boolean matches(Attribute attribute) {return attribute.hasKey(attribute.key());}

            @Override public void render(Renderer renderer) {renderer.append(key());}

            @Override public void renderValue(Renderer renderer) {}
        };
    }


    String key();

    default boolean hasKey(String expected) {return key().equals(expected);}

    boolean matches(Attribute attribute);

    @Override default void render(Renderer renderer) {
        renderer.append(key()).append("=\"");
        renderValue(renderer);
        renderer.append("\"");
    }

    void renderValue(Renderer renderer);

    default Attribute and(Attribute attribute) {throw new UnsupportedOperationException();}

    @Value @RequiredArgsConstructor
    class StringAttribute implements Attribute {
        public static Attribute stringAttribute(@NonNull String key, @NonNull String value) {return new StringAttribute(key, value);}

        @NonNull String key;
        @NonNull String value;

        @Override public boolean matches(Attribute attribute) {
            return hasKey(attribute.key()) && attribute instanceof StringAttribute str && value.equals(str.value);
        }

        @Override public String key() {return key;}

        @Override public Attribute and(Attribute attribute) {
            if ("style".equals(key()))
                return stringAttribute(key, value + " " + ((StringAttribute) attribute).value);
            throw new UnsupportedOperationException();
        }

        @Override public void renderValue(Renderer renderer) {renderer.append(value);}
    }
}
