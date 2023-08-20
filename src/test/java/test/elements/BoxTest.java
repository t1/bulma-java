package test.elements;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.elements.Box.box;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class BoxTest {
    @Test void shouldRenderBox() {
        var box = box("I am in a box.");

        then(box).rendersAs("""
                <div class="box">I am in a box.</div>
                """);
    }
}
