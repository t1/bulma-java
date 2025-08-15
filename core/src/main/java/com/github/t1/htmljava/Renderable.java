package com.github.t1.htmljava;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public interface Renderable {
    /// The index for adding a content element at the end.
    int LAST = Integer.MAX_VALUE;

    /// Fluent API to add an element to a list at a specific index.
    static <T> AddElementStep1<T> add(T element) {return new AddElementStep1<>(element);}

    record AddElementStep1<T>(T element) {
        public AddElementStep2<T> as(int index) {return new AddElementStep2<>(element, index);}
    }

    record AddElementStep2<T>(T element, int index) {
        public void in(List<T> list) {
            if (index >= list.size()) list.add(element);
            else list.add(index, element);
        }
    }

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

    default void render(OutputStream stream) {new PrintStream(stream).append(this.render());}

    default boolean rendersOnSeparateLines() {return false;}


    record RenderableString(String string) implements Renderable {
        public static Renderable string(String string) {return new RenderableString(string);}

        @Override public void render(Renderer renderer) {renderer.safeAppend(string);}
    }

    record UnsafeString(String string) implements Renderable {
        public static Renderable unsafeString(String string) {return new UnsafeString(string);}

        @Override public void render(Renderer renderer) {renderer.unsafeAppend(string);}
    }

    record Indented(Renderable renderable) implements Renderable {
        public static Renderable indented(Renderable renderable) {return new Indented(renderable);}

        @Override public void render(Renderer renderer) {
            renderer.nl().in();
            renderable.render().lines().forEach(line -> {
                if (!line.isBlank()) renderer.indent().unsafeAppend(line);
                renderer.unsafeAppend("\n");
            });
            renderer.out().indent();
        }
    }

    record ConcatenatedRenderable(List<Renderable> renderables) implements Renderable {
        public ConcatenatedRenderable {renderables = List.copyOf(renderables); /* unmodifiable */}

        public static Collector<Renderable, List<Renderable>, ConcatenatedRenderable> toRenderable() {
            return new Collector<>() {
                @Override public Supplier<List<Renderable>> supplier() {return ArrayList::new;}

                @Override public BiConsumer<List<Renderable>, Renderable> accumulator() {return List::add;}

                @Override public BinaryOperator<List<Renderable>> combiner() {
                    return (l1, l2) -> {
                        l1.addAll(l2);
                        return l1;
                    };
                }

                @Override
                public Function<List<Renderable>, ConcatenatedRenderable> finisher() {return ConcatenatedRenderable::new;}

                @Override public Set<Characteristics> characteristics() {return Set.of();}
            };
        }

        public static Renderable concat(Renderable... renderables) {
            return concat(Stream.of(renderables));
        }

        public static Renderable concat(Stream<Renderable> renderables) {
            return new ConcatenatedRenderable(renderables
                    .flatMap(ConcatenatedRenderable::merge)
                    .collect(toList()));
        }

        private static Stream<Renderable> merge(Renderable renderable) {
            return (renderable instanceof ConcatenatedRenderable concatenatedRenderable)
                    ? concatenatedRenderable.renderables.stream()
                    : Stream.of(renderable);
        }

        public ConcatenatedRenderable content(Renderable renderable) {return plus(renderable, LAST);}

        public ConcatenatedRenderable plus(Renderable renderable, int index) {
            var list = new ArrayList<>(renderables);
            add(renderable).as(index).in(list);
            return new ConcatenatedRenderable(list);
        }

        public Renderable last() {return renderables.get(renderables.size() - 1);}

        @Override public Optional<Renderable> find(Predicate<Renderable> predicate) {
            return renderables.stream().filter(predicate).findFirst();
        }

        @Override public boolean rendersOnSeparateLines() {
            return renderables.stream().anyMatch(Renderable::rendersOnSeparateLines);
        }

        @Override public void render(Renderer renderer) {
            renderables.forEach(renderable -> {
                if (rendersOnSeparateLines() && !renderable.rendersOnSeparateLines())
                    renderer.nl().indent();
                renderable.render(renderer);
                if (rendersOnSeparateLines() && !renderable.rendersOnSeparateLines())
                    renderer.nl();
            });
        }
    }
}
