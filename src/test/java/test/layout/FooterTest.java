package test.layout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.p;
import static com.github.t1.bulmajava.basic.Basic.strong;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.elements.Content.content_;
import static com.github.t1.bulmajava.layout.Footer.footer;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class FooterTest {
    @Test void shouldRenderFooter() {
        var section = footer().contains(
                content_().classes("has-text-centered").contains(
                        p().contains(
                                strong("Bulma"), string(" by "), a("Jeremy Thomas").href("https://jgthms.com").rendersOnSeparateLines(false),
                                string(". The source code is licensed "), a("MIT").href("http://opensource.org/licenses/mit-license.php").rendersOnSeparateLines(false),
                                string(". The website content is licensed "), a("CC BY NC SA 4.0").href("http://creativecommons.org/licenses/by-nc-sa/4.0/").rendersOnSeparateLines(false),
                                string("."))));

        then(section).rendersAs("""
                <footer class="footer">
                    <div class="content has-text-centered">
                        <p><strong>Bulma</strong> by <a href="https://jgthms.com">Jeremy Thomas</a>. The source code is licensed <a href="http://opensource.org/licenses/mit-license.php">MIT</a>. The website content is licensed <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY NC SA 4.0</a>.</p>
                    </div>
                </footer>
                """);
    }
}
