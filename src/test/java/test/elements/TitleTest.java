package test.elements;

import com.github.t1.bulmajava.elements.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Style.SPACED;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class TitleTest {
    @Test void shouldRenderTitle() {
        var h1 = Title.title(1, "Title");

        then(h1).rendersAs("""
                <h1 class="title">Title</h1>
                """);
    }

    @Test void shouldRenderTitleContains() {
        var h1 = Title.title(2).contains("Title");

        then(h1).rendersAs("""
                <h2 class="title">Title</h2>
                """);
    }

    @ParameterizedTest @ValueSource(ints = {1, 2, 3, 4, 5, 6}) void shouldRenderTitleSize(int size) {
        var h1 = Title.title(1, "Title").size(size);

        then(h1).rendersAs("""
                <h1 class="title is-$size">Title</h1>
                """.replace("$size", Integer.toString(size)));
    }


    @Test void shouldRenderSubtitle() {
        var h1 = Title.subtitle(2, "Subtitle");

        then(h1).rendersAs("""
                <h2 class="subtitle">Subtitle</h2>
                """);
    }

    @Test void shouldRenderSubtitleContains() {
        var h1 = Title.subtitle(3, "Subtitle");

        then(h1).rendersAs("""
                <h3 class="subtitle">Subtitle</h3>
                """);
    }

    @ParameterizedTest @ValueSource(ints = {1, 2, 3, 4, 5, 6}) void shouldRenderSubtitleSize(int size) {
        var h4 = Title.subtitle(4, "Subtitle").size(size);

        then(h4).rendersAs("""
                <h4 class="subtitle is-$size">Subtitle</h4>
                """.replace("$size", Integer.toString(size)));
    }

    @Test void shouldRenderNonSpacedTitleAndSubtitle() {
        var div = div().contains(
                Title.title("Title 2").size(2),
                Title.subtitle("Subtitle 4").size(4));

        then(div).rendersAs("""
                <div>
                    <h1 class="title is-2">Title 2</h1>
                    <h2 class="subtitle is-4">Subtitle 4</h2>
                </div>
                """);
    }

    @Test void shouldRenderSpacedTitleAndSubtitle() {
        var div = div().contains(
                Title.title("Title 2").size(2).is(SPACED),
                Title.subtitle("Subtitle 4").size(4));

        then(div).rendersAs("""
                <div>
                    <h1 class="title is-2 is-spaced">Title 2</h1>
                    <h2 class="subtitle is-4">Subtitle 4</h2>
                </div>
                """);
    }
}
