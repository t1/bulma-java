package test;

import com.github.t1.bulmajava.basic.Renderable;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.BDDAssertions;

/**
 * We use this mainly to be able to go to a xml-compare in the future
 */
public class CustomAssertions extends BDDAssertions {

    public static RenderableAssert then(Renderable renderable) {return new RenderableAssert(renderable);}

    @RequiredArgsConstructor
    public static class RenderableAssert {
        private final Renderable renderable;

        /**
         * Checks that the renderable renders like the expected string.
         * Also renders the renderable into the file <code>target.all-tests.html</code>,
         * so you can view and check it visually.
         * <br/>
         * Note that the result is first written to the file and then checked, so you can check
         * the output in the browser, even when it doesn't yet match the expected html.
         */
        public void rendersAs(String expected) {
            RenderTestExtension.render(renderable);
            rendersAs_notAll(expected);
        }

        /**
         * Like #rendersAs, but it <em>doesn't</em> render it into the <code>all-tests.html</code>.
         * This is necessary, e.g., for tests that render a <code>html</code> tag.
         */
        public void rendersAs_notAll(String expected) {
            then(renderable.render()).isEqualTo(expected);
        }
    }
}
