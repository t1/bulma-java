package test.html;

import com.github.t1.htmljava.Attribute;
import com.github.t1.htmljava.AttributeModifier;
import com.github.t1.htmljava.ClassModifier;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Renderable.RenderableString;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.htmljava.HtmlBasics.comment;
import static com.github.t1.htmljava.HtmlBasics.div;
import static com.github.t1.htmljava.HtmlBasics.element;
import static com.github.t1.htmljava.HtmlBasics.h1;
import static com.github.t1.htmljava.HtmlBasics.h2;
import static com.github.t1.htmljava.HtmlBasics.h3;
import static com.github.t1.htmljava.HtmlBasics.h4;
import static com.github.t1.htmljava.HtmlBasics.h5;
import static com.github.t1.htmljava.HtmlBasics.h6;
import static com.github.t1.htmljava.HtmlBasics.nav;
import static com.github.t1.htmljava.HtmlBasics.nbsp;
import static com.github.t1.htmljava.HtmlBasics.p;
import static com.github.t1.htmljava.HtmlBasics.span;
import static com.github.t1.htmljava.Renderable.ConcatenatedRenderable.concat;
import static com.github.t1.htmljava.Renderable.ConcatenatedRenderable.toRenderable;
import static com.github.t1.htmljava.Renderable.RenderableString.string;
import static org.assertj.core.api.BDDAssertions.then;

class ElementTest {
    private static final ClassModifier FOO = () -> "foo";
    private static final AttributeModifier FOOBAR = new AttributeModifier() {
        @Override public String key() {return "foo";}

        @Override public String value() {return "bar";}
    };

    @Test void shouldRenderString() {
        var tag = string("hello");

        then(tag.render()).isEqualTo("hello");
    }

    @Test void shouldRenderEmptyString() {
        var tag = string("");

        then(tag.render()).isEqualTo("");
    }

    @Test void shouldRenderNullString() {
        var tag = string(null);

        then(tag.render()).isEqualTo("");
    }

    @Test void shouldRenderStringArray() {
        var tag = concat(string("1"), string("2"), string("3"));

        then(tag.render()).isEqualTo("123");
    }

    @Test void shouldRenderStringStream() {
        var tag = concat(Stream.of("1", "2", "3").map(RenderableString::string));

        then(tag.render()).isEqualTo("123");
    }

    @Test void shouldRenderStringStreamCollector() {
        var tag = Stream.of("1", "2", "3").map(RenderableString::string).collect(toRenderable());

        then(tag.render()).isEqualTo("123");
    }

    @Test void shouldRenderNonBreakingSpace() {
        var tag = p().content(nbsp());

        then(tag.render()).isEqualTo("<p>&nbsp;</p>\n");
    }

    @Test void shouldRenderSpan() {
        var tag = span();

        then(tag.render()).isEqualTo("<span></span>\n");
    }

    @Test void shouldRenderTextSpan() {
        var tag = span("Hello World");

        then(tag.render()).isEqualTo("<span>Hello World</span>\n");
    }

    @Test void shouldRenderTextSpanWithConcatenatedStyles() {
        var tag = span("Hello World")
                .style("background-color: beige;")
                .attr("style", "background-color: beige;") // manual and duplicate will be removed
                .style("font-style: italic;");

        then(tag.hasStyle("background-color: beige;")).isTrue();
        then(tag.hasStyle("font-style: italic;")).isTrue();
        then(tag.hasStyle("color: red;")).isFalse();
        then(tag.render()).isEqualTo("""
                <span style="background-color: beige; font-style: italic;">Hello World</span>
                """);
    }

    @Test void shouldRenderTextSpanWithAndWithoutClass() {
        var tag = span("Hello World").classes("foo").notClasses("foo");

        then(tag.render()).isEqualTo("""
                <span>Hello World</span>
                """);
    }

    @Test void shouldRenderTextSpanWithClassModifier() {
        var tag = span("Hello World").is(FOO);

        then(tag.render()).isEqualTo("""
                <span class="foo">Hello World</span>
                """);
    }

    @Test void shouldRenderTextSpanWithAndWithoutClassModifier() {
        var tag = span("Hello World").is(FOO).not(FOO);

        then(tag.render()).isEqualTo("""
                <span>Hello World</span>
                """);
    }

    @Test void shouldRenderTextSpanWithAttributeModifier() {
        var tag = span("Hello World").is(FOOBAR);

        then(tag.render()).isEqualTo("""
                <span foo="bar">Hello World</span>
                """);
    }

    @Test void shouldRenderTextSpanWithAndWithoutAttributeModifier() {
        var tag = span("Hello World").is(FOOBAR).not(FOOBAR);

        then(tag.render()).isEqualTo("""
                <span>Hello World</span>
                """);
    }

    @Test void shouldRenderH1() {
        var tag = h1("Hello World");

        then(tag.render()).isEqualTo("<h1>Hello World</h1>\n");
    }

    @Test void shouldRenderH2() {
        var tag = h2("Hello World");

        then(tag.render()).isEqualTo("<h2>Hello World</h2>\n");
    }

    @Test void shouldRenderH3() {
        var tag = h3("Hello World");

        then(tag.render()).isEqualTo("<h3>Hello World</h3>\n");
    }

    @Test void shouldRenderH4() {
        var tag = h4("Hello World");

        then(tag.render()).isEqualTo("<h4>Hello World</h4>\n");
    }

    @Test void shouldRenderH5() {
        var tag = h5("Hello World");

        then(tag.render()).isEqualTo("<h5>Hello World</h5>\n");
    }

    @Test void shouldRenderH6() {
        var tag = h6("Hello World");

        then(tag.render()).isEqualTo("<h6>Hello World</h6>\n");
    }

    @Test void shouldRenderNav() {
        var tag = nav().content(a("foo"));

        then(tag.render()).isEqualTo("""
                <nav>
                    <a>foo</a>
                </nav>
                """);
    }

    @Test void shouldRenderTagWithClasses() {
        var tag = element("span")
                .classes("cls1", "cls2")
                .attr("class", "cls3") // manual
                .classes("cls3") // duplicate will be removed
                .content("Foo");

        then(tag.render()).isEqualTo("<span class=\"cls1 cls2 cls3\">Foo</span>\n");
    }

    @Test void shouldRenderTagWithContent() {
        var tag = element("span").content("Foo");

        then(tag.render()).isEqualTo("<span>Foo</span>\n");
    }

    // see https://cheatsheetseries.owasp.org/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.html#output-encoding-for-html-contexts
    @Test void shouldRenderEscapeStringContent() {
        var tag = element("span").content("&<>\"'");

        then(tag.render()).isEqualTo("<span>&amp;&lt;&gt;&quot;&#x27;</span>\n");
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

    // this is not proper HTML, but we must make sure it's safe
    @Test void shouldRenderUnsafeElementName() {
        var h1 = element("foo&<>\"'");

        then(h1.render()).isEqualTo("""
                <foo&amp;&lt;&gt;&quot;&#x27;></foo&amp;&lt;&gt;&quot;&#x27;>
                """);
    }

    @Test void shouldRenderAttribute() {
        var h1 = element("foo").attr("bar", "baz");

        then(h1.render()).isEqualTo("""
                <foo bar="baz"></foo>
                """);
    }

    @Test void shouldRenderUriAttribute() {
        var h1 = element("a").attr("href", URI.create("http://example.com")).content("x");

        then(h1.render()).isEqualTo("""
                <a href="http://example.com">x</a>
                """);
    }

    @Test void shouldRenderNoValAttribute() {
        var h1 = element("foo").attr("bar");

        then(h1.render()).isEqualTo("""
                <foo bar></foo>
                """);
    }

    // this is not proper HTML, but we must make sure it's safe
    @Test void shouldRenderUnsafeAttribute() {
        var h1 = element("foo").attr("unsafe&amp;&lt;&gt;&quot;&#x27;");

        then(h1.render()).isEqualTo("""
                <foo unsafe&amp;&lt;&gt;&quot;&#x27;></foo>
                """);
    }

    @Test void shouldRenderVarargsAttributes() {
        var h1 = element("foo").attrs(Attribute.of("bar"), Attribute.of("baz", "qux"));

        then(h1.render()).isEqualTo("""
                <foo bar baz="qux"></foo>
                """);
    }

    @Test void shouldRenderAttributesStream() {
        var h1 = element("foo").attrs(IntStream.range(1, 4).mapToObj(i -> Attribute.of("x" + i)));

        then(h1.render()).isEqualTo("""
                <foo x1 x2 x3></foo>
                """);
    }

    @Test void shouldRenderDivWithConsumer() {
        var tag = div().content("hello").with(this::foobar);

        //noinspection HtmlUnknownAttribute
        then(tag.render()).isEqualTo("""
                <div foo="bar">hello</div>
                """);
    }

    private void foobar(Element element) {element.attr("foo", "bar");}

    @Test void shouldRenderComment() {
        var h1 = comment("foo");

        then(h1.render()).isEqualTo("<!--foo-->");
    }
}
