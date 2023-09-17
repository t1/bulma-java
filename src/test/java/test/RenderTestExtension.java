package test;

import com.github.t1.bulmajava.basic.Renderable;
import com.github.t1.bulmajava.basic.Renderer;
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
            .append(html("Bulma-Java Demo")
                    .stylesheet("https://bulma.io/vendor/fontawesome-free-5.15.2-web/css/all.min.css")
                    .stylesheet("https://cdnjs.cloudflare.com/ajax/libs/bulma/0.9.4/css/bulma.min.css")
                    .script("test-classes/main.js")
                    .script("test-classes/klmn.js")
                    .close(false).render())
            .append("<body>\n");
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

    @Override public void beforeAll(ExtensionContext extensionContext) {
        if (addSectionWrapper(extensionContext))
            ALL.append(section().content(container().close(false)).close(false).render()).in().in();
        ALL.append("        " + title(extensionContext.getDisplayName()));
    }

    private static boolean addSectionWrapper(ExtensionContext extensionContext) {
        return !extensionContext.getRequiredTestClass().isAnnotationPresent(NoSectionWrapper.class);
    }

    @Override public void beforeEach(ExtensionContext extensionContext) {
        var name = extensionContext.getDisplayName();
        if (name.startsWith("[")) name = extensionContext.getParent().orElseThrow().getDisplayName() + ": " + name;
        if (name.startsWith("shouldRender")) name = name.substring(12);
        if (name.endsWith("()")) name = name.substring(0, name.length() - 2);
        ALL.append("        " + hr() + "        " + title(6, name));
    }

    @Override public void afterEach(ExtensionContext extensionContext) {ALL.nl();}

    @Override public void afterAll(ExtensionContext extensionContext) {
        if (addSectionWrapper(extensionContext))
            ALL.out().out().append("""
                        </div>
                    </section>
                                        
                                        
                    """);
    }

    @SneakyThrows(IOException.class)
    @Override public void launcherSessionClosed(LauncherSession session) {
        ALL.append("""
                </body>
                </html>
                """);
        Files.writeString(Path.of("target/all-tests.html"), ALL.render());
    }
}
