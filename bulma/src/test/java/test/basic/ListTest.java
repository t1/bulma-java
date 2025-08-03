package test.basic;

import com.github.t1.bulmajava.basic.ListType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.elements.Content.content_;
import static com.github.t1.htmljava.HtmlBasics.li;
import static com.github.t1.htmljava.HtmlBasics.ol;
import static com.github.t1.htmljava.HtmlBasics.ul;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class ListTest {
    @Test void shouldRenderUnorderedList() {
        var content = content_().content(
                ul().content(
                        li("Coffee"),
                        li("Tea"),
                        li("Milk")));

        then(content.render()).isEqualTo("""
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
}
