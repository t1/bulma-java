package test.elements;

import com.github.t1.bulmajava.basic.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.*;
import static com.github.t1.bulmajava.elements.Content.content_;
import static test.CustomAssertions.then;
import static test.RenderTestExtension.loremIpsum;

@ExtendWith(RenderTestExtension.class)
class ContentTest {
    @Test void shouldRenderContent() {
        var content = content_().content(
                h1("Hello World"),
                p(loremIpsum()),
                h2("Second level"));

        then(content).rendersAs("""
                <div class="content">
                    <h1>Hello World</h1>
                    <p>$loremIpsum</p>
                    <h2>Second level</h2>
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }

    @ParameterizedTest @EnumSource void shouldRenderSizeContent(Size size) {
        var content = content_().is(size).content(
                h1("Hello World"),
                p(loremIpsum()));

        then(content).rendersAs("""
                <div class="content $size">
                    <h1>Hello World</h1>
                    <p>$loremIpsum</p>
                </div>
                """
                .replace("$size", size.className())
                .replace("$loremIpsum", loremIpsum()));
    }
}
