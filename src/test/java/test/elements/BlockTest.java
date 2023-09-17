package test.elements;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Basic.strong;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.elements.Block.block;
import static test.CustomAssertions.then;
import static test.RenderTestExtension.loremIpsum;

@ExtendWith(RenderTestExtension.class)
class BlockTest {
    @Test void shouldRenderThreeBlocks() {
        var blocks = div().content(
                block().content(
                        string("This text is within a "),
                        strong("block"),
                        string(".")),
                block().content(
                        string("This text is within a "),
                        strong("second block"),
                        string(". "),
                        string(loremIpsum())),
                block().content(
                        string("This text is within a "),
                        strong("third block"),
                        string(". This block has no margin at the bottom.")),
                string("bottom"));

        then(blocks).rendersAs("""
                <div>
                    <div class="block">This text is within a <strong>block</strong>.</div>
                    <div class="block">This text is within a <strong>second block</strong>. $loremIpsum</div>
                    <div class="block">This text is within a <strong>third block</strong>. This block has no margin at the bottom.</div>
                    bottom
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }
}
