package test.components;

import com.github.t1.bulmajava.basic.Color;
import com.github.t1.bulmajava.elements.Title;
import com.github.t1.htmljava.Anchor;
import com.github.t1.htmljava.Renderable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Alignment.RIGHT;
import static com.github.t1.bulmajava.basic.Color.PRIMARY;
import static com.github.t1.bulmajava.basic.Style.LIGHT;
import static com.github.t1.bulmajava.components.Navbar.NavbarDropdown.navbarDropdown;
import static com.github.t1.bulmajava.components.Navbar.navbar;
import static com.github.t1.bulmajava.elements.Button.buttons;
import static com.github.t1.bulmajava.elements.Image.img;
import static com.github.t1.bulmajava.elements.MenuActivationType.ACTIVE;
import static com.github.t1.bulmajava.elements.MenuActivationType.HOVERABLE;
import static com.github.t1.bulmajava.layout.Section.section;
import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.htmljava.Basic.control;
import static com.github.t1.htmljava.Basic.div;
import static com.github.t1.htmljava.Basic.hr;
import static com.github.t1.htmljava.Basic.strong;
import static com.github.t1.htmljava.Renderable.RenderableString.string;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class NavbarTest {
    @Test void shouldRenderNavbar() {
        var nav = control().content(navbar("navbarBasicExample")
                        .brand(bulmaLogo())
                        .burger()
                        .start(
                                a("Home"),
                                a("Documentation"),
                                navbarDropdown("More", HOVERABLE).content(
                                        a("About"),
                                        a("Jobs"),
                                        a("Contact"),
                                        hr(),
                                        a("Report an issue")))
                        .end(div().content(
                                buttons().content(
                                        a().content(strong("Sign up")).classes("button").is(PRIMARY),
                                        a("Log in").classes("button").is(LIGHT)))))
                .style("margin-bottom: 200px;");

        then(nav).rendersAs("""
                <div class="control" style="margin-bottom: 200px;">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/assets/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                            <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                            </a>
                        </div>
                        <div id="navbarBasicExample" class="navbar-menu">
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
        var nav = control().content(navbar("navbarMenuRightExample")
                        .brand(bulmaLogo())
                        .burger()
                        .start(
                                div().content(
                                        buttons().content(
                                                a().content(strong("Sign up")).classes("button").is(PRIMARY),
                                                a("Log in").classes("button").is(LIGHT))))
                        .end(
                                a("Home"),
                                a("Documentation"),
                                navbarDropdown("More", HOVERABLE).content(
                                        a("About"),
                                        a("Jobs"),
                                        a("Contact"),
                                        hr(),
                                        a("Report an issue"))))
                .style("margin-bottom: 200px;");

        then(nav).rendersAs("""
                <div class="control" style="margin-bottom: 200px;">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/assets/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                            <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false" data-target="navbarMenuRightExample">
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                            </a>
                        </div>
                        <div id="navbarMenuRightExample" class="navbar-menu">
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
        var nav = control().content(navbar("navbarMenuNoLeftButRightExample")
                        .brand(bulmaLogo())
                        .burger()
                        .end(
                                a("Home"),
                                a("Documentation"),
                                navbarDropdown("More", HOVERABLE).content(
                                        a("About"),
                                        a("Jobs"),
                                        a("Contact"),
                                        hr(),
                                        a("Report an issue"))))
                .style("margin-bottom: 200px;");

        then(nav).rendersAs("""
                <div class="control" style="margin-bottom: 200px;">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/assets/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                            <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false" data-target="navbarMenuNoLeftButRightExample">
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                                <span aria-hidden="true"></span>
                            </a>
                        </div>
                        <div id="navbarMenuNoLeftButRightExample" class="navbar-menu">
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
        var navbar = control().content(navbar(null)
                .brand(bulmaLogo())
                .burger());

        then(navbar).rendersAs("""
                <div class="control">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/assets/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
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
        var navbar = control().content(
                navbar(null)
                        .isTransparent()
                        .brand(bulmaLogo())
                        .burger());

        then(navbar).rendersAs("""
                <div class="control">
                    <nav class="navbar is-transparent" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/assets/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
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
        var navbar = control().content(navbar(null)
                .isFixedTop()
                .brand(bulmaLogo())
                .burger());

        then(navbar).rendersAs_notAll("""
                <div class="control">
                    <nav class="navbar is-fixed-top" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/assets/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
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
        var navbar = control().content(navbar(null)
                .isFixedBottom()
                .brand(bulmaLogo())
                .burger());

        then(navbar).rendersAs_notAll("""
                <div class="control">
                    <nav class="navbar is-fixed-bottom" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/assets/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
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
        var div = control().style("width: 300px;").content(
                navbar("navbarDropdownMenu")
                        .start(
                                navbarDropdown("Left", ACTIVE).content(
                                        menu()))
                        .end(
                                navbarDropdown("Right", ACTIVE).is(RIGHT).content(menu())),
                section().classes("hero").is(PRIMARY).content(
                        div().classes("hero-body").content(
                                Title.titleP("Docs"),
                                Title.subtitleP("Everything you need to ").content(
                                        strong("create a website"),
                                        string(" with Bulma")))));

        then(div).rendersAs("""
                <div class="control" style="width: 300px;">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div id="navbarDropdownMenu" class="navbar-menu">
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
        var div = control().content(
                        section().classes("hero").is(PRIMARY).content(
                                div().classes("hero-body").content(
                                        Title.titleP("Docs"),
                                        Title.subtitleP("Everything you need to ").content(
                                                strong("create a website"),
                                                string(" with Bulma")))),
                        navbar("navbarDropupMenu").start(
                                navbarDropdown("Dropup", ACTIVE).content(
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
                        <div id="navbarDropupMenu" class="navbar-menu">
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
        var navbar = control().content(
                        navbar("arrowlessNavbarDropupMenu")
                                .start(
                                        navbarDropdown("Link without arrow", HOVERABLE).arrowless()
                                                .content(menu())))
                .style("margin-bottom: 200px;");

        then(navbar).rendersAs("""
                <div class="control" style="margin-bottom: 200px;">
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div id="arrowlessNavbarDropupMenu" class="navbar-menu">
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
        var nav = control().content(navbar("navbar-" + color + "-example")
                .is(color)
                .brand(bulmaLogo())
                .start(
                        a("Home"),
                        a("Documentation"),
                        navbarDropdown("More", HOVERABLE).content(a("About")))
                .end(div().content(
                        buttons().content(
                                a().content(strong("Sign up")).classes("button").is(PRIMARY),
                                a("Log in").classes("button").is(LIGHT)))));

        then(nav).rendersAs("""
                <div class="control">
                    <nav class="navbar is-$color" role="navigation" aria-label="navigation">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="https://bulma.io">
                                <img src="https://bulma.io/assets/images/bulma-logo.png" alt="bulma logo" width="112" height="28">
                            </a>
                        </div>
                        <div id="$menuId" class="navbar-menu">
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
        return a().href("https://bulma.io").content(
                img("https://bulma.io/assets/images/bulma-logo.png", "bulma logo", "112", "28"));
    }

    private static Renderable[] menu() {
        return new Renderable[]{
                a("Overview"),
                a("Elements"),
                a("Components"),
                hr(),
                div().content("Version 0.9.4")};
    }
}
