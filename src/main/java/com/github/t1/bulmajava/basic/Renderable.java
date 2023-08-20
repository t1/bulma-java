package com.github.t1.bulmajava.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

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

    default Renderable replace(Renderable existing, Renderable replacement) {
        throw new UnsupportedOperationException("can't replace in " + getClass().getSimpleName());
    }


    record RenderableString(String string, boolean raw) implements Renderable {
        public static Renderable string(String string) {return new RenderableString(string, false);}

        public static Renderable rawString(String string) {return new RenderableString(string, true);}

        @Override public void render(Renderer renderer) {
            if (raw) renderer.append(string);
            else renderer.safeAppend(string);
        }
    }

    record ConcatenatedRenderable(List<Renderable> renderables) implements Renderable {
        public static ConcatenatedRenderable concat(Renderable... renderables) {
            return new ConcatenatedRenderable(Stream.of(renderables)
                    .flatMap(ConcatenatedRenderable::merge)
                    .toList());
        }

        private static Stream<Renderable> merge(Renderable renderable) {
            return (renderable instanceof ConcatenatedRenderable concatenatedRenderable)
                    ? concatenatedRenderable.renderables.stream()
                    : Stream.of(renderable);
        }

        public ConcatenatedRenderable contains(Renderable renderable) {return concat(this, renderable);}

        @Override public Optional<Renderable> find(Predicate<Renderable> predicate) {
            return renderables.stream().filter(predicate).findFirst();
        }

        @Override public Renderable replace(Renderable existing, Renderable replacement) {
            for (int i = 0; i < renderables.size(); i++) {
                if (renderables.get(i) == existing) {
                    var list = new ArrayList<>(renderables);
                    list.set(i, replacement);
                    return new ConcatenatedRenderable(list);
                }
            }
            throw new IllegalStateException("existing not found");
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
