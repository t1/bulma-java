package test.basic;

import com.github.t1.bulmajava.basic.FontAlignment;
import com.github.t1.bulmajava.basic.FontFamily;
import com.github.t1.bulmajava.basic.FontTransformation;
import com.github.t1.bulmajava.basic.FontWeight;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.htmljava.HtmlBasics.div;
import static com.github.t1.htmljava.HtmlBasics.span;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class FontModifiersTest {
    @ParameterizedTest @EnumSource void shouldRenderFontAlignment(FontAlignment fontAlignment) {
        var tag = div().content("Hello World").is(fontAlignment);

        then(tag).rendersAs("""
                <div class="${class-name}">Hello World</div>
                """.replace("${class-name}", fontAlignment.className()));
    }

    @ParameterizedTest @EnumSource void shouldRenderFontTransformation(FontTransformation fontTransformation) {
        var tag = span("hello World").is(fontTransformation);

        then(tag).rendersAs("""
                <span class="${class-name}">hello World</span>
                """.replace("${class-name}", fontTransformation.className()));
    }

    @ParameterizedTest @EnumSource void shouldRenderFontWeight(FontWeight fontWeight) {
        var tag = span("Hello World").is(fontWeight);

        then(tag).rendersAs("""
                <span class="${class-name}">Hello World</span>
                """.replace("${class-name}", fontWeight.className()));
    }

    @ParameterizedTest @EnumSource void shouldRenderFontFamily(FontFamily fontFamily) {
        var tag = span("Hello World").is(fontFamily);

        then(tag).rendersAs("""
                <span class="${class-name}">Hello World</span>
                """.replace("${class-name}", fontFamily.className()));
    }
}
