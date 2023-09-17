package test.elements;

import com.github.t1.bulmajava.basic.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Basic.element;
import static com.github.t1.bulmajava.basic.Color.DANGER;
import static com.github.t1.bulmajava.basic.Color.INFO;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.elements.Delete.delete;
import static test.CustomAssertions.then;
import static test.RenderTestExtension.loremIpsum;
import static test.RenderTestExtension.loremIpsumS;

@ExtendWith(RenderTestExtension.class)
class DeleteTest {
    @Test void shouldRenderDelete() {
        var button = delete();

        then(button).rendersAs("""
                <button class="delete" aria-label="delete"></button>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderDelete(Size size) {
        var button = delete().is(size);

        then(button).rendersAs("""
                <button class="delete $size" aria-label="delete"></button>
                """.replace("$size", size.className()));
    }

    @Test void shouldRenderDeleteNotification() {
        var tag = div().classes("notification").is(DANGER).content(
                delete(),
                loremIpsumS());

        then(tag).rendersAs("""
                <div class="notification is-danger">
                    <button class="delete" aria-label="delete"></button>
                    $loremIpsum
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }

    @Test void shouldRenderDeleteMessage() {
        var tag = element("article").classes("message").is(INFO).content(
                div().classes("message-header").content(string("Info"), delete()),
                div().classes("message-body").content(loremIpsum()));

        then(tag).rendersAs("""
                <article class="message is-info">
                    <div class="message-header">
                        Info
                        <button class="delete" aria-label="delete"></button>
                    </div>
                    <div class="message-body">$loremIpsum</div>
                </article>
                """.replace("$loremIpsum", loremIpsum()));
    }
}
