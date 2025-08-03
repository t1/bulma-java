package test.helpers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.BulmaElement.PULLED_LEFT;
import static com.github.t1.bulmajava.basic.BulmaElement.PULLED_RIGHT;
import static com.github.t1.bulmajava.elements.Box.box;
import static com.github.t1.htmljava.HtmlBasics.h1;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class PullTest {
    @Test void shouldRenderH1PullLeft() {
        var h1 = h1("I'm left").is(PULLED_LEFT);

        then(h1).rendersAs("""
                <h1 class="is-pulled-left">I&#x27;m left</h1>
                """);
    }

    @Test void shouldRenderH1PullRight() {
        var h1 = h1("I'm right").is(PULLED_RIGHT);

        then(h1).rendersAs("""
                <h1 class="is-pulled-right">I&#x27;m right</h1>
                """);
    }

    @Test void shouldRenderBoxPullLeft() {
        var box = box().content("I'm left").is(PULLED_LEFT);

        then(box).rendersAs("""
                <div class="box is-pulled-left">I&#x27;m left</div>
                """);
    }

    @Test void shouldRenderBoxPullRight() {
        var box = box().content("I'm right").is(PULLED_RIGHT);

        then(box).rendersAs("""
                <div class="box is-pulled-right">I&#x27;m right</div>
                """);
    }
}
