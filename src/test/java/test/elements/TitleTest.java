package test.elements;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Style.SPACED;
import static com.github.t1.bulmajava.elements.Title.subtitle;
import static com.github.t1.bulmajava.elements.Title.title;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class TitleTest {
    @Test void shouldRenderTitle() {
        var h1 = title(1, "Title");

        then(h1).rendersAs("""
                <h1 class="title">Title</h1>
                """);
    }

    @Test void shouldRenderTitleContains() {
        var h1 = title(2).content("Title");

        then(h1).rendersAs("""
                <h2 class="title">Title</h2>
                """);
    }

    @ParameterizedTest @ValueSource(ints = {1, 2, 3, 4, 5, 6}) void shouldRenderTitleSize(int size) {
        var h1 = title(1, "Title").is(size);

        then(h1).rendersAs("""
                <h1 class="title is-$size">Title</h1>
                """.replace("$size", Integer.toString(size)));
    }


    @Test void shouldRenderSubtitle() {
        var h1 = subtitle(2, "Subtitle");

        then(h1).rendersAs("""
                <h2 class="subtitle">Subtitle</h2>
                """);
    }

    @Test void shouldRenderSubtitleContains() {
        var h1 = subtitle(3, "Subtitle");

        then(h1).rendersAs("""
                <h3 class="subtitle">Subtitle</h3>
                """);
    }

    @ParameterizedTest @ValueSource(ints = {1, 2, 3, 4, 5, 6}) void shouldRenderSubtitleSize(int size) {
        var h4 = subtitle(4, "Subtitle").is(size);

        then(h4).rendersAs("""
                <h4 class="subtitle is-$size">Subtitle</h4>
                """.replace("$size", Integer.toString(size)));
    }

    @Test void shouldRenderNonSpacedTitleAndSubtitle() {
        var div = div().content(
                title("Title 2").is(2),
                subtitle("Subtitle 4").is(4));

        then(div).rendersAs("""
                <div>
                    <h1 class="title is-2">Title 2</h1>
                    <h2 class="subtitle is-4">Subtitle 4</h2>
                </div>
                """);
    }

    @Test void shouldRenderSpacedTitleAndSubtitle() {
        var div = div().content(
                title("Title 2").is(2).is(SPACED),
                subtitle("Subtitle 4").is(4));

        then(div).rendersAs("""
                <div>
                    <h1 class="title is-2 is-spaced">Title 2</h1>
                    <h2 class="subtitle is-4">Subtitle 4</h2>
                </div>
                """);
    }
}
