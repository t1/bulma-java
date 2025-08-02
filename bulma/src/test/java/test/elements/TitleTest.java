package test.elements;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Basic.span;
import static com.github.t1.bulmajava.basic.Color.DANGER;
import static com.github.t1.bulmajava.basic.Color.WARNING;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.basic.Style.SPACED;
import static com.github.t1.bulmajava.elements.Title.subtitle;
import static com.github.t1.bulmajava.elements.Title.title;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class TitleTest {
    @Test void shouldRenderTitle() {
        var h1 = title(1, "Title");

        then(h1).rendersAs("""
                <h1 class="title is-1">Title</h1>
                """);
    }

    @Test void shouldRenderTitleContains() {
        var h1 = title(2).content("Title");

        then(h1).rendersAs("""
                <h2 class="title is-2">Title</h2>
                """);
    }

    @ParameterizedTest @ValueSource(ints = {1, 2, 3, 4, 5, 6}) void shouldRenderTitleSize(int size) {
        var h1 = title(size, "Title");

        then(h1).rendersAs("""
                <h$size class="title is-$size">Title</h$size>
                """.replace("$size", Integer.toString(size)));
    }


    @Test void shouldRenderSubtitle() {
        var h1 = subtitle(2, "Subtitle");

        then(h1).rendersAs("""
                <h2 class="subtitle is-2">Subtitle</h2>
                """);
    }

    @Test void shouldRenderSubtitleContains() {
        var h1 = subtitle(3, "Subtitle");

        then(h1).rendersAs("""
                <h3 class="subtitle is-3">Subtitle</h3>
                """);
    }

    @ParameterizedTest @ValueSource(ints = {1, 2, 3, 4, 5, 6}) void shouldRenderSubtitleSize(int size) {
        var h4 = subtitle(size, "Subtitle");

        then(h4).rendersAs("""
                <h$size class="subtitle is-$size">Subtitle</h$size>
                """.replace("$size", Integer.toString(size)));
    }

    @Test void shouldRenderNonSpacedTitleAndSubtitle() {
        var div = div().content(
                title(2, "Title 2"),
                subtitle(4, "Subtitle 4"));

        then(div).rendersAs("""
                <div>
                    <h2 class="title is-2">Title 2</h2>
                    <h4 class="subtitle is-4">Subtitle 4</h4>
                </div>
                """);
    }

    @Test void shouldRenderSpacedTitleAndSubtitle() {
        var div = div().content(
                title(2, "Title 2").is(SPACED),
                subtitle(4, "Subtitle 4"));

        then(div).rendersAs("""
                <div>
                    <h2 class="title is-2 is-spaced">Title 2</h2>
                    <h4 class="subtitle is-4">Subtitle 4</h4>
                </div>
                """);
    }

    @Test void shouldRenderStyledTitle() {
        var div = div().content(
                title(2, string("Title With"), span("Color").hasText(DANGER)));

        then(div).rendersAs("""
                <div>
                    <h2 class="title is-2">
                        Title With
                        <span class="has-text-danger">Color</span>
                    </h2>
                </div>
                """);
    }

    @Test void shouldRenderStyledSubtitle() {
        var div = div().content(
                subtitle(2, string("Subtitle With"), span("Color").hasText(WARNING)));

        then(div).rendersAs("""
                <div>
                    <h2 class="subtitle is-2">
                        Subtitle With
                        <span class="has-text-warning">Color</span>
                    </h2>
                </div>
                """);
    }
}
