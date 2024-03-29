package test;

import com.github.t1.bulmajava.basic.Renderable;
import com.github.t1.bulmajava.basic.Renderer;
import com.github.t1.bulmajava.elements.ImageSize;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.LauncherSessionListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.t1.bulmajava.basic.Basic.hr;
import static com.github.t1.bulmajava.basic.Html.html;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.elements.Title.title;
import static com.github.t1.bulmajava.layout.Container.container;
import static com.github.t1.bulmajava.layout.Section.section;

public class RenderTestExtension implements Extension, BeforeAllCallback, BeforeEachCallback, AfterEachCallback, AfterAllCallback, LauncherSessionListener {
    @SuppressWarnings("HtmlUnknownTarget")
    private static final Renderer ALL = new Renderer()
            .unsafeAppend(html("Bulma-Java Demo")
                    .stylesheet("https://bulma.io/vendor/fontawesome-free-5.15.2-web/css/all.min.css")
                    .stylesheet("https://cdnjs.cloudflare.com/ajax/libs/bulma/1.0.0/css/bulma.min.css")
                    .script("test-classes/main.js")
                    .script("test-classes/klmn.js")
                    .close(false).render())
            .unsafeAppend("<body>\n");
    //            <!--suppress HtmlFormInputWithoutLabel -->


    public static void render(Renderable renderable) {renderable.render(ALL);}

    public static Renderable loremIpsumS() {return string(loremIpsum());}

    public static String loremIpsum() {
        return "Lorem ipsum dolor sit amet, consectetur adipisici elit, " +
               "sed eiusmod tempor incidunt ut labore et dolore magna aliqua. " +
               "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
               "ut aliquid ex ea commodi consequat. Quis aute iure reprehenderit " +
               "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
               "Excepteur sint obcaecat cupiditat non proident, sunt in culpa " +
               "qui officia deserunt mollit anim id est laborum.";
    }

    public static String placeholder(ImageSize size) {return placeholder(size.key());}

    public static String placeholder(String size) {
        return "https://bulma.io/assets/images/placeholders/" + size + ".png";
    }

    @Override public void beforeAll(ExtensionContext extensionContext) {
        if (addSectionWrapper(extensionContext))
            ALL.unsafeAppend(section().content(container().close(false)).close(false).render()).in().in();
        ALL.unsafeAppend("        " + title(extensionContext.getDisplayName()));
    }

    private static boolean addSectionWrapper(ExtensionContext extensionContext) {
        return !extensionContext.getRequiredTestClass().isAnnotationPresent(NoSectionWrapper.class);
    }

    @Override public void beforeEach(ExtensionContext extensionContext) {
        var name = extensionContext.getDisplayName();
        if (name.startsWith("[")) name = extensionContext.getParent().orElseThrow().getDisplayName() + ": " + name;
        if (name.startsWith("shouldRender")) name = name.substring(12);
        if (name.endsWith("()")) name = name.substring(0, name.length() - 2);
        ALL.unsafeAppend("        " + hr() + "        " + title(6, name));
    }

    @Override public void afterEach(ExtensionContext extensionContext) {ALL.nl();}

    @Override public void afterAll(ExtensionContext extensionContext) {
        if (addSectionWrapper(extensionContext))
            ALL.out().out().unsafeAppend("""
                        </div>
                    </section>
                                        
                                        
                    """);
    }

    @SneakyThrows(IOException.class)
    @Override public void launcherSessionClosed(LauncherSession session) {
        ALL.unsafeAppend("""
                </body>
                </html>
                """);
        Files.writeString(Path.of("target/all-tests.html"), ALL.render());
    }
}
