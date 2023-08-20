package test.components;

import com.github.t1.bulmajava.basic.Anchor;
import com.github.t1.bulmajava.basic.Basic;
import com.github.t1.bulmajava.basic.Color;
import com.github.t1.bulmajava.basic.Renderable;
import com.github.t1.bulmajava.elements.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Alignment.RIGHT;
import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Color.PRIMARY;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.basic.Style.LIGHT;
import static com.github.t1.bulmajava.components.Navbar.NavbarDropdown.navbarDropdown;
import static com.github.t1.bulmajava.components.Navbar.navbar;
import static com.github.t1.bulmajava.elements.Button.buttons;
import static com.github.t1.bulmajava.elements.Image.img;
import static com.github.t1.bulmajava.elements.MenuActivationType.ACTIVE;
import static com.github.t1.bulmajava.elements.MenuActivationType.HOVERABLE;
import static com.github.t1.bulmajava.layout.Section.section;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class NavbarTest {
    @Test void shouldRenderNavbar() {
        var nav = Basic.control().contains(navbar("navbarBasicExample")
                        .brand(bulmaLogo())
                        .burger()
                        .containsStart(
                                a("Home"),
                                a("Documentation"),
                                navbarDropdown("More", HOVERABLE).contains(
                                        a("About"),
                                        a("Jobs"),
                                        a("Contact"),
                                        Basic.hr(),
                                        a("Report an issue")))
                        .containsEnd(Basic.div().contains(
                                buttons().contains(
                                        a().contains(Basic.strong("Sign up")).classes("button").is(PRIMARY),
                                        a("Log in").classes("button").is(LIGHT)))))
                .style("margin-bottom: 200px;");

        then(nav).rendersAs("""
                <div class="control" style="margin-bottom: 200px;">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                            <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                            </a>
                        </div>
                        <div class="navbar-menu" id="navbarBasicExample">
                            <div class="navbar-start">
                                <a class="navbar-item">Home</a>
                                <a class="navbar-item">Documentation</a>
                                <div class="has-dropdown is-hoverable navbar-item">
                                    <a class="navbar-link">More</a>
                                    <div class="navbar-dropdown">
                                        <a class="navbar-item">About</a>
                                        <a class="navbar-item">Jobs</a>
                                        <a class="navbar-item">Contact</a>
                                        <hr class="navbar-divider">
                                        <a class="navbar-item">Report an issue</a>
                                    </div>
                                </div>
                            </div>
                            <div class="navbar-end">
                                <div class="navbar-item">
                                    <div class="buttons">
                                        <a class="button is-primary"><strong>Sign up</strong></a>
                                        <a class="button is-light">Log in</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
                """);
    }

    @Test void shouldRenderNavbarWithMenuRight() {
        var nav = Basic.control().contains(navbar("navbarMenuRightExample")
                        .brand(bulmaLogo())
                        .burger()
                        .containsStart(
                                Basic.div().contains(
                                        buttons().contains(
                                                a().contains(Basic.strong("Sign up")).classes("button").is(PRIMARY),
                                                a("Log in").classes("button").is(LIGHT))))
                        .containsEnd(
                                a("Home"),
                                a("Documentation"),
                                navbarDropdown("More", HOVERABLE).contains(
                                        a("About"),
                                        a("Jobs"),
                                        a("Contact"),
                                        Basic.hr(),
                                        a("Report an issue"))))
                .style("margin-bottom: 200px;");

        then(nav).rendersAs("""
                <div class="control" style="margin-bottom: 200px;">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                            <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false" data-target="navbarMenuRightExample">
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                            </a>
                        </div>
                        <div class="navbar-menu" id="navbarMenuRightExample">
                            <div class="navbar-start">
                                <div class="navbar-item">
                                    <div class="buttons">
                                        <a class="button is-primary"><strong>Sign up</strong></a>
                                        <a class="button is-light">Log in</a>
                                    </div>
                                </div>
                            </div>
                            <div class="navbar-end">
                                <a class="navbar-item">Home</a>
                                <a class="navbar-item">Documentation</a>
                                <div class="has-dropdown is-hoverable navbar-item">
                                    <a class="navbar-link">More</a>
                                    <div class="navbar-dropdown">
                                        <a class="navbar-item">About</a>
                                        <a class="navbar-item">Jobs</a>
                                        <a class="navbar-item">Contact</a>
                                        <hr class="navbar-divider">
                                        <a class="navbar-item">Report an issue</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
                """);
    }

    @Test void shouldRenderNavbarNoLeftButMenuRight() {
        var nav = Basic.control().contains(navbar("navbarMenuNoLeftButRightExample")
                        .brand(bulmaLogo())
                        .burger()
                        .containsEnd(
                                a("Home"),
                                a("Documentation"),
                                navbarDropdown("More", HOVERABLE).contains(
                                        a("About"),
                                        a("Jobs"),
                                        a("Contact"),
                                        Basic.hr(),
                                        a("Report an issue"))))
                .style("margin-bottom: 200px;");

        then(nav).rendersAs("""
                <div class="control" style="margin-bottom: 200px;">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                            <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false" data-target="navbarMenuNoLeftButRightExample">
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                            </a>
                        </div>
                        <div class="navbar-menu" id="navbarMenuNoLeftButRightExample">
                            <div class="navbar-end">
                                <a class="navbar-item">Home</a>
                                <a class="navbar-item">Documentation</a>
                                <div class="has-dropdown is-hoverable navbar-item">
                                    <a class="navbar-link">More</a>
                                    <div class="navbar-dropdown">
                                        <a class="navbar-item">About</a>
                                        <a class="navbar-item">Jobs</a>
                                        <a class="navbar-item">Contact</a>
                                        <hr class="navbar-divider">
                                        <a class="navbar-item">Report an issue</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
                """);
    }

    @Test void shouldRenderNavbarBrand() {
        var navbar = Basic.control().contains(navbar(null)
                .brand(bulmaLogo())
                .burger());

        then(navbar).rendersAs("""
                <div class="control">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                            <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false">
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                            </a>
                        </div>
                    </nav>
                </div>
                """);
    }

    @Test void shouldRenderTransparentNavbarBrand() {
        var navbar = Basic.control().contains(
                navbar(null)
                        .isTransparent()
                        .brand(bulmaLogo())
                        .burger());

        then(navbar).rendersAs("""
                <div class="control">
                    <nav class="navbar is-transparent" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                            <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false">
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                            </a>
                        </div>
                    </nav>
                </div>
                """);
    }

    @Test void shouldRenderFixedTopNavbarBrand() {
        var navbar = Basic.control().contains(navbar(null)
                .isFixedTop()
                .brand(bulmaLogo())
                .burger());

        then(navbar).rendersAs_notAll("""
                <div class="control">
                    <nav class="navbar is-fixed-top" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                            <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false">
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                            </a>
                        </div>
                    </nav>
                </div>
                """);
    }

    @Test void shouldRenderFixedBottomNavbarBrand() {
        var navbar = Basic.control().contains(navbar(null)
                .isFixedBottom()
                .brand(bulmaLogo())
                .burger());

        then(navbar).rendersAs_notAll("""
                <div class="control">
                    <nav class="navbar is-fixed-bottom" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                            <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false">
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                            </a>
                        </div>
                    </nav>
                </div>
                """);
    }

    @Test void shouldRenderRightDropdownNavbar() {
        var div = Basic.control().style("width: 300px;").contains(
                navbar("navbarDropdownMenu")
                        .containsStart(
                                navbarDropdown("Left", ACTIVE).contains(
                                        menu()))
                        .containsEnd(
                                navbarDropdown("Right", ACTIVE).is(RIGHT).contains(menu())),
                section().classes("hero").is(PRIMARY).contains(
                        Basic.div().classes("hero-body").contains(
                                Title.titleP("Docs"),
                                Title.subtitleP("Everything you need to ").contains(
                                        Basic.strong("create a website"),
                                        string(" with Bulma")))));

        then(div).rendersAs("""
                <div class="control" style="width: 300px;">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-menu" id="navbarDropdownMenu">
                            <div class="navbar-start">
                                <div class="has-dropdown is-active navbar-item">
                                    <a class="navbar-link">Left</a>
                                    <div class="navbar-dropdown">
                                        <a class="navbar-item">Overview</a>
                                        <a class="navbar-item">Elements</a>
                                        <a class="navbar-item">Components</a>
                                        <hr class="navbar-divider">
                                        <div class="navbar-item">Version 0.9.4</div>
                                    </div>
                                </div>
                            </div>
                            <div class="navbar-end">
                                <div class="has-dropdown is-active navbar-item">
                                    <a class="navbar-link">Right</a>
                                    <div class="navbar-dropdown is-right">
                                        <a class="navbar-item">Overview</a>
                                        <a class="navbar-item">Elements</a>
                                        <a class="navbar-item">Components</a>
                                        <hr class="navbar-divider">
                                        <div class="navbar-item">Version 0.9.4</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                    <section class="section hero is-primary">
                        <div class="hero-body">
                            <p class="title">Docs</p>
                            <p class="subtitle">Everything you need to <strong>create a website</strong> with Bulma</p>
                        </div>
                    </section>
                </div>
                """);
    }

    @Test void shouldRenderDropupNavbar() {
        var div = Basic.control().contains(
                        section().classes("hero").is(PRIMARY).contains(
                                Basic.div().classes("hero-body").contains(
                                        Title.titleP("Docs"),
                                        Title.subtitleP("Everything you need to ").contains(
                                                Basic.strong("create a website"),
                                                string(" with Bulma")))),
                        navbar("navbarDropupMenu").containsStart(
                                navbarDropdown("Dropup", ACTIVE).contains(
                                        menu()).classes("has-dropdown-up")))
                .style("width: 400px");

        then(div).rendersAs("""
                <div class="control" style="width: 400px">
                    <section class="section hero is-primary">
                        <div class="hero-body">
                            <p class="title">Docs</p>
                            <p class="subtitle">Everything you need to <strong>create a website</strong> with Bulma</p>
                        </div>
                    </section>
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-menu" id="navbarDropupMenu">
                            <div class="navbar-start">
                                <div class="has-dropdown is-active has-dropdown-up navbar-item">
                                    <a class="navbar-link">Dropup</a>
                                    <div class="navbar-dropdown">
                                        <a class="navbar-item">Overview</a>
                                        <a class="navbar-item">Elements</a>
                                        <a class="navbar-item">Components</a>
                                        <hr class="navbar-divider">
                                        <div class="navbar-item">Version 0.9.4</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
                """);
    }

    @Test void shouldRenderArrowlessDropdownNavbar() {
        var navbar = Basic.control().contains(
                        navbar("arrowlessNavbarDropupMenu")
                                .containsStart(
                                        navbarDropdown("Link without arrow", HOVERABLE).isArrowless()
                                                .contains(menu())))
                .style("margin-bottom: 200px;");

        then(navbar).rendersAs("""
                <div class="control" style="margin-bottom: 200px;">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-menu" id="arrowlessNavbarDropupMenu">
                            <div class="navbar-start">
                                <div class="has-dropdown is-hoverable navbar-item">
                                    <a class="navbar-link is-arrowless">Link without arrow</a>
                                    <div class="navbar-dropdown">
                                        <a class="navbar-item">Overview</a>
                                        <a class="navbar-item">Elements</a>
                                        <a class="navbar-item">Components</a>
                                        <hr class="navbar-divider">
                                        <div class="navbar-item">Version 0.9.4</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderColorNavbar(Color color) {
        var nav = Basic.control().contains(navbar("navbar-" + color + "-example")
                .is(color)
                .brand(bulmaLogo())
                .containsStart(
                        a("Home"),
                        a("Documentation"),
                        navbarDropdown("More", HOVERABLE).contains(a("About")))
                .containsEnd(Basic.div().contains(
                        buttons().contains(
                                a().contains(Basic.strong("Sign up")).classes("button").is(PRIMARY),
                                a("Log in").classes("button").is(LIGHT)))));

        then(nav).rendersAs("""
                <div class="control">
                    <nav class="navbar is-$color" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                        </div>
                        <div class="navbar-menu" id="$menuId">
                            <div class="navbar-start">
                                <a class="navbar-item">Home</a>
                                <a class="navbar-item">Documentation</a>
                                <div class="has-dropdown is-hoverable navbar-item">
                                    <a class="navbar-link">More</a>
                                    <div class="navbar-dropdown">
                                        <a class="navbar-item">About</a>
                                    </div>
                                </div>
                            </div>
                            <div class="navbar-end">
                                <div class="navbar-item">
                                    <div class="buttons">
                                        <a class="button is-primary"><strong>Sign up</strong></a>
                                        <a class="button is-light">Log in</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
                """
                .replace("$color", color.key())
                .replace("$menuId", "navbar-" + color + "-example"));
    }


    private static Anchor bulmaLogo() {
        return a().href("https://bulma.io").contains(
                img("https://bulma.io/images/bulma-logo.png", "bulma logo", "112", "28"));
    }

    private static Renderable[] menu() {
        return new Renderable[]{
                a("Overview"),
                a("Elements"),
                a("Components"),
                Basic.hr(),
                Basic.div().contains("Version 0.9.4")};
    }
}
