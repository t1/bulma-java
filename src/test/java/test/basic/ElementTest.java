package test.basic;

import com.github.t1.bulmajava.basic.Basic;
import com.github.t1.bulmajava.basic.ListType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.element;
import static com.github.t1.bulmajava.basic.Color.PRIMARY;
import static com.github.t1.bulmajava.elements.Content.content_;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class ElementTest {
    @Test void shouldRenderSpan() {
        var tag = Basic.span();

        then(tag).rendersAs("<span></span>\n");
    }

    @Test void shouldRenderTextSpan() {
        var tag = Basic.span("Hello World");

        then(tag).rendersAs("<span>Hello World</span>\n");
    }

    @Test void shouldRenderTextSpanWithConcatenatedStyles() {
        var tag = Basic.span("Hello World").style("background-color: beige;").style("font-style: italic;");

        then(tag).rendersAs("""
                <span style="background-color: beige; font-style: italic;">Hello World</span>
                """);
    }

    @Test void shouldRenderTextSpanWithAndWithoutClass() {
        var tag = Basic.span("Hello World").is(PRIMARY).notClasses(PRIMARY.className());

        then(tag).rendersAs("""
                <span>Hello World</span>
                """);
    }

    @Test void shouldRenderH1() {
        var tag = Basic.h1("Hello World");

        then(tag).rendersAs("<h1>Hello World</h1>\n");
    }

    @Test void shouldRenderH2() {
        var tag = Basic.h2("Hello World");

        then(tag).rendersAs("<h2>Hello World</h2>\n");
    }

    @Test void shouldRenderH3() {
        var tag = Basic.h3("Hello World");

        then(tag).rendersAs("<h3>Hello World</h3>\n");
    }

    @Test void shouldRenderH4() {
        var tag = Basic.h4("Hello World");

        then(tag).rendersAs("<h4>Hello World</h4>\n");
    }

    @Test void shouldRenderH5() {
        var tag = Basic.h5("Hello World");

        then(tag).rendersAs("<h5>Hello World</h5>\n");
    }

    @Test void shouldRenderH6() {
        var tag = Basic.h6("Hello World");

        then(tag).rendersAs("<h6>Hello World</h6>\n");
    }

    @Test void shouldRenderNav() {
        var tag = Basic.nav().contains(a("foo"));

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
                .contains("Foo");

        then(tag).rendersAs("<span class=\"cls1 cls2 cls3\">Foo</span>\n");
    }

    @Test void shouldRenderTagWithContent() {
        var tag = element("span").contains("Foo");

        then(tag).rendersAs("<span>Foo</span>\n");
    }

    // see https://cheatsheetseries.owasp.org/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.html#output-encoding-for-html-contexts
    @Test void shouldEscapeStringContent() {
        var tag = element("span").contains("&<>\"'");

        then(tag).rendersAs("<span>&amp;&lt;&gt;&quot;&#x27;</span>\n");
    }

    @Test void shouldRenderUnorderedList() {
        var content = content_().contains(
                Basic.ul().contains(
                        Basic.li("Coffee"),
                        Basic.li("Tea"),
                        Basic.li("Milk")));

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
        var content = content_().contains(
                Basic.ol(listType).contains(
                        Basic.li("Coffee"),
                        Basic.li("Tea"),
                        Basic.li("Milk")));

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
        var content = content_().contains(
                Basic.ol().classes(listType.variant()).contains(
                        Basic.li("Coffee"),
                        Basic.li("Tea"),
                        Basic.li("Milk")));

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
}
