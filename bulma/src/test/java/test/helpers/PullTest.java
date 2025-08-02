package test.helpers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.htmljava.Basic.h1;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class PullTest {
    @Test void shouldRenderPullLeft() {
        var h1 = h1("I'm left").isPulledLeft();

        then(h1).rendersAs("""
                <h1 class="is-pulled-left">I&#x27;m left</h1>
                """);
    }

    @Test void shouldRenderPullRight() {
        var h1 = h1("I'm right").isPulledRight();

        then(h1).rendersAs("""
                <h1 class="is-pulled-right">I&#x27;m right</h1>
                """);
    }
}
