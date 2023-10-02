package test.layout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.NoSectionWrapper;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.strong;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.basic.Size.LARGE;
import static com.github.t1.bulmajava.basic.Size.MEDIUM;
import static com.github.t1.bulmajava.elements.Title.subtitle;
import static com.github.t1.bulmajava.elements.Title.title;
import static com.github.t1.bulmajava.layout.Section.section;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
@NoSectionWrapper
class SectionTest {
    @Test void shouldRenderSection() {
        var section = section().content(
                title("Section"),
                subtitle(2).content(
                        string("A simple container to divide your page into "),
                        strong("sections"),
                        string(", like the one you're currently reading.")
                )
        );

        then(section).rendersAs("""
                <section class="section">
                    <h1 class="title is-1">Section</h1>
                    <h2 class="subtitle is-2">A simple container to divide your page into <strong>sections</strong>, like the one you&#x27;re currently reading.</h2>
                </section>
                """);
    }

    @Test void shouldRenderMediumSection() {
        var section = section().is(MEDIUM).content(
                title("Medium section"),
                subtitle(2).content(
                        string("A simple container to divide your page into "),
                        strong("sections"),
                        string(", like the one you're currently reading.")
                )
        );

        then(section).rendersAs("""
                <section class="section is-medium">
                    <h1 class="title is-1">Medium section</h1>
                    <h2 class="subtitle is-2">A simple container to divide your page into <strong>sections</strong>, like the one you&#x27;re currently reading.</h2>
                </section>
                """);
    }

    @Test void shouldRenderLargeSection() {
        var section = section().is(LARGE).content(
                title("Large section"),
                subtitle(2).content(
                        string("A simple container to divide your page into "),
                        strong("sections"),
                        string(", like the one you're currently reading.")
                )
        );

        then(section).rendersAs("""
                <section class="section is-large">
                    <h1 class="title is-1">Large section</h1>
                    <h2 class="subtitle is-2">A simple container to divide your page into <strong>sections</strong>, like the one you&#x27;re currently reading.</h2>
                </section>
                """);
    }
}
