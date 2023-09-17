package com.github.t1.bulmajava.basic;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public interface Renderable {
    default <T extends Renderable> Optional<T> find(Class<T> type) {return find(type::isInstance).map(type::cast);}

    default Optional<Renderable> find(Predicate<Renderable> predicate) {
        return predicate.test(this) ? Optional.of(this) : Optional.empty();
    }


    void render(Renderer renderer);

    default String render() {
        var renderer = new Renderer();
        render(renderer);
        return renderer.render();
    }

    default boolean rendersOnSeparateLines() {return false;}


    record RenderableString(String string) implements Renderable {
        public static Renderable string(String string) {return new RenderableString(string);}

        @Override public void render(Renderer renderer) {renderer.safeAppend(string);}
    }

    record UnsafeString(String string) implements Renderable {
        public static Renderable unsafeString(String string) {return new UnsafeString(string);}

        @Override public void render(Renderer renderer) {renderer.append(string);}
    }

    record Indented(Renderable renderable) implements Renderable {
        public static Renderable indented(Renderable renderable) {return new Indented(renderable);}

        @Override public void render(Renderer renderer) {
            renderer.nl().in();
            renderable.render().lines().forEach(line -> {
                if (!line.isBlank()) renderer.appendIndent().append(line);
                renderer.append("\n");
            });
            renderer.out().appendIndent();
        }
    }

    record ConcatenatedRenderable(List<Renderable> renderables) implements Renderable {
        public static ConcatenatedRenderable concat(Renderable... renderables) {
            return new ConcatenatedRenderable(Stream.of(renderables)
                    .flatMap(ConcatenatedRenderable::merge)
                    .collect(toList()));
        }

        private static Stream<Renderable> merge(Renderable renderable) {
            return (renderable instanceof ConcatenatedRenderable concatenatedRenderable)
                    ? concatenatedRenderable.renderables.stream()
                    : Stream.of(renderable);
        }

        public ConcatenatedRenderable content(Renderable renderable) {
            renderables.add(renderable);
            return this;
        }

        @Override public Optional<Renderable> find(Predicate<Renderable> predicate) {
            return renderables.stream().filter(predicate).findFirst();
        }

        @Override public boolean rendersOnSeparateLines() {
            return renderables.stream().anyMatch(Renderable::rendersOnSeparateLines);
        }

        @Override public void render(Renderer renderer) {
            renderables.forEach(renderable -> {
                if (rendersOnSeparateLines() && !renderable.rendersOnSeparateLines())
                    renderer.nl().appendIndent();
                renderable.render(renderer);
                if (rendersOnSeparateLines() && !renderable.rendersOnSeparateLines())
                    renderer.nl();
            });
        }

        public Renderable last() {return renderables.get(renderables.size() - 1);}
    }
}
