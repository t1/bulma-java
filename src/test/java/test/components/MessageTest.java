package test.components;

import com.github.t1.bulmajava.basic.Color;
import com.github.t1.bulmajava.basic.Renderable;
import com.github.t1.bulmajava.basic.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.*;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.Style.DARK;
import static com.github.t1.bulmajava.components.Message.*;
import static com.github.t1.bulmajava.elements.Delete.delete;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class MessageTest {
    @Test void shouldRenderMessage() {
        var message = message(
                messageHeader().content(
                        p("Hello World"),
                        delete()),
                messageBody().content(
                        textItems()
                )).style("width: 440px;");

        then(message).rendersAs("""
                <article class="message" style="width: 440px;">
                    <div class="message-header">
                        <p>Hello World</p>
                        <button class="delete" aria-label="delete"></button>
                    </div>
                    <div class="message-body">
                        $loremIpsum
                    </div>
                </article>
                """.replace("$loremIpsum", loremIpsumWithStyle()));
    }

    @Test void shouldRenderDarkMessage() {
        var message = message(
                messageHeader().content(
                        p("Dark"),
                        delete()),
                messageBody().content(
                        textItems()
                )).is(DARK).style("width: 440px;");

        then(message).rendersAs("""
                <article class="message is-dark" style="width: 440px;">
                    <div class="message-header">
                        <p>Dark</p>
                        <button class="delete" aria-label="delete"></button>
                    </div>
                    <div class="message-body">
                        $loremIpsum
                    </div>
                </article>
                """.replace("$loremIpsum", loremIpsumWithStyle()));
    }

    @ParameterizedTest @EnumSource void shouldRenderColorMessage(Color color) {
        var message = message(
                messageHeader().content(
                        p(color.key()),
                        delete()),
                messageBody().content(
                        textItems()
                )).is(color).style("width: 440px;");

        then(message).rendersAs("""
                <article class="message is-$color" style="width: 440px;">
                    <div class="message-header">
                        <p>$color</p>
                        <button class="delete" aria-label="delete"></button>
                    </div>
                    <div class="message-body">
                        $loremIpsum
                    </div>
                </article>
                """
                .replace("$color", color.key())
                .replace("$loremIpsum", loremIpsumWithStyle()));
    }

    @Test void shouldRenderTextOnlyMessage() {
        var message = message(
                messageBody().content(
                        textItems()
                )).style("width: 440px;");

        then(message).rendersAs("""
                <article class="message" style="width: 440px;">
                    <div class="message-body">
                        $loremIpsum
                    </div>
                </article>
                """.replace("$loremIpsum", loremIpsumWithStyle()));
    }

    @Test void shouldRenderDarkTextOnlyMessage() {
        var message = message(
                messageBody().content(
                        textItems()
                )).is(DARK).style("width: 440px;");

        then(message).rendersAs("""
                <article class="message is-dark" style="width: 440px;">
                    <div class="message-body">
                        $loremIpsum
                    </div>
                </article>
                """.replace("$loremIpsum", loremIpsumWithStyle()));
    }

    @ParameterizedTest @EnumSource void shouldRenderTextOnlyColorMessage(Color color) {
        var message = message(
                messageBody().content(
                        textItems()
                )).is(color).style("width: 440px;");

        then(message).rendersAs("""
                <article class="message is-$color" style="width: 440px;">
                    <div class="message-body">
                        $loremIpsum
                    </div>
                </article>
                """
                .replace("$color", color.key())
                .replace("$loremIpsum", loremIpsumWithStyle()));
    }

    @ParameterizedTest @EnumSource void shouldRenderSizeMessage(Size size) {
        var message = message(
                messageHeader().content(
                        p(size.key() + " message"),
                        delete().is(SMALL)),
                messageBody().content(
                        textItems()
                )).is(size).style("width: 440px;");

        then(message).rendersAs("""
                <article class="message is-$size" style="width: 440px;">
                    <div class="message-header">
                        <p>$size message</p>
                        <button class="delete is-small" aria-label="delete"></button>
                    </div>
                    <div class="message-body">
                        $loremIpsum
                    </div>
                </article>
                """
                .replace("$size", size.key())
                .replace("$loremIpsum", loremIpsumWithStyle()));
    }

    private static Renderable[] textItems() {
        return new Renderable[]{
                string("Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
                strong("Pellentesque risus mi"),
                string(", tempus quis placerat ut, porta nec nulla. Vestibulum rhoncus ac ex sit amet fringilla. " +
                       "Nullam gravida purus diam, et dictum"),
                a("felis venenatis"),
                string("efficitur. Aenean ac"),
                em("eleifend lacus"),
                string(", in mollis lectus. Donec sodales, arcu et sollicitudin porttitor, tortor urna tempor ligula, " +
                       "id porttitor mi magna a neque. Donec dui urna, vehicula et sem eget, facilisis sodales sem.")
        };
    }

    private static String loremIpsumWithStyle() {
        return """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        <strong>Pellentesque risus mi</strong>
                        , tempus quis placerat ut, porta nec nulla. Vestibulum rhoncus ac ex sit amet fringilla. Nullam gravida purus diam, et dictum
                        <a>felis venenatis</a>
                        efficitur. Aenean ac
                        <em>eleifend lacus</em>
                        , in mollis lectus. Donec sodales, arcu et sollicitudin porttitor, tortor urna tempor ligula, id porttitor mi magna a neque. Donec dui urna, vehicula et sem eget, facilisis sodales sem.""";
    }
}
