package test.basic;

import com.github.t1.bulmajava.basic.ListType;
import com.github.t1.bulmajava.basic.Renderable.RenderableString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.*;
import static com.github.t1.bulmajava.basic.Color.PRIMARY;
import static com.github.t1.bulmajava.basic.Renderable.ConcatenatedRenderable.concat;
import static com.github.t1.bulmajava.basic.Renderable.ConcatenatedRenderable.toRenderable;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.elements.Content.content_;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class ElementTest {
    @Test void shouldRenderString() {
        var tag = string("hello");

        then(tag).rendersAs("hello");
    }

    @Test void shouldRenderEmptyString() {
        var tag = string("");

        then(tag).rendersAs("");
    }

    @Test void shouldRenderNullString() {
        var tag = string(null);

        then(tag).rendersAs("");
    }

    @Test void shouldRenderStringArray() {
        var tag = concat(string("1"), string("2"), string("3"));

        then(tag).rendersAs("123");
    }

    @Test void shouldRenderStringStream() {
        var tag = concat(Stream.of("1", "2", "3").map(RenderableString::string));

        then(tag).rendersAs("123");
    }

    @Test void shouldRenderStringStreamCollector() {
        var tag = Stream.of("1", "2", "3").map(RenderableString::string).collect(toRenderable());

        then(tag).rendersAs("123");
    }

    @Test void shouldRenderSpan() {
        var tag = span();

        then(tag).rendersAs("<span></span>\n");
    }

    @Test void shouldRenderTextSpan() {
        var tag = span("Hello World");

        then(tag).rendersAs("<span>Hello World</span>\n");
    }

    @Test void shouldRenderTextSpanWithConcatenatedStyles() {
        var tag = span("Hello World").style("background-color: beige;").style("font-style: italic;");

        then(tag).rendersAs("""
                <span style="background-color: beige; font-style: italic;">Hello World</span>
                """);
    }

    @Test void shouldRenderTextSpanWithAndWithoutClass() {
        var tag = span("Hello World").is(PRIMARY).notClasses(PRIMARY.className());

        then(tag).rendersAs("""
                <span>Hello World</span>
                """);
    }

    @Test void shouldRenderH1() {
        var tag = h1("Hello World");

        then(tag).rendersAs("<h1>Hello World</h1>\n");
    }

    @Test void shouldRenderH2() {
        var tag = h2("Hello World");

        then(tag).rendersAs("<h2>Hello World</h2>\n");
    }

    @Test void shouldRenderH3() {
        var tag = h3("Hello World");

        then(tag).rendersAs("<h3>Hello World</h3>\n");
    }

    @Test void shouldRenderH4() {
        var tag = h4("Hello World");

        then(tag).rendersAs("<h4>Hello World</h4>\n");
    }

    @Test void shouldRenderH5() {
        var tag = h5("Hello World");

        then(tag).rendersAs("<h5>Hello World</h5>\n");
    }

    @Test void shouldRenderH6() {
        var tag = h6("Hello World");

        then(tag).rendersAs("<h6>Hello World</h6>\n");
    }

    @Test void shouldRenderNav() {
        var tag = nav().content(a("foo"));

        then(tag).rendersAs("""
                <nav>
                    <a>foo</a>
                </nav>
                """);
    }

    @Test void shouldRenderTagWithClasses() {
        var tag = element("span")
                .classes("cls1", "cls2")
                .classes("cls3")
                .content("Foo");

        then(tag).rendersAs("<span class=\"cls1 cls2 cls3\">Foo</span>\n");
    }

    @Test void shouldRenderTagWithContent() {
        var tag = element("span").content("Foo");

        then(tag).rendersAs("<span>Foo</span>\n");
    }

    // see https://cheatsheetseries.owasp.org/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.html#output-encoding-for-html-contexts
    @Test void shouldEscapeStringContent() {
        var tag = element("span").content("&<>\"'");

        then(tag).rendersAs("<span>&amp;&lt;&gt;&quot;&#x27;</span>\n");
    }

    @Test void shouldRenderUnorderedList() {
        var content = content_().content(
                ul().content(
                        li("Coffee"),
                        li("Tea"),
                        li("Milk")));

        then(content).rendersAs("""
                <div class="content">
                    <ul>
                        <li>Coffee</li>
                        <li>Tea</li>
                        <li>Milk</li>
                    </ul>
                </div>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderOrderedListTypes(ListType listType) {
        var content = content_().content(
                ol(listType).content(
                        li("Coffee"),
                        li("Tea"),
                        li("Milk")));

        //noinspection HtmlWrongAttributeValue
        then(content).rendersAs("""
                <div class="content">
                    <ol type="$type">
                        <li>Coffee</li>
                        <li>Tea</li>
                        <li>Milk</li>
                    </ol>
                </div>
                """.replace("$type", listType.code()));
    }

    @ParameterizedTest @EnumSource void shouldRenderOrderedListClasses(ListType listType) {
        var content = content_().content(
                ol().classes(listType.variant()).content(
                        li("Coffee"),
                        li("Tea"),
                        li("Milk")));

        then(content).rendersAs("""
                <div class="content">
                    <ol$class>
                        <li>Coffee</li>
                        <li>Tea</li>
                        <li>Milk</li>
                    </ol>
                </div>
                """.replace("$class", (listType.variant() == null) ? ""
                : (" class=\"" + listType.variant() + "\"")));
    }

    @Test void shouldGetEmptyContentStream() {
        var element = div();

        then(element.contentStream()).hasSize(0);
    }

    @Test void shouldGetSingleContentStream() {
        var element = div().content(div());

        then(element.contentStream()).hasSize(1);
    }

    @Test void shouldGetConcatenatedContentStream() {
        var element = div().content(div(), div());

        then(element.contentStream()).hasSize(2);
    }
}
