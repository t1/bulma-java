package test.layout;

import com.github.t1.bulmajava.basic.Basic;
import com.github.t1.bulmajava.basic.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.NoSectionWrapper;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Color.*;
import static com.github.t1.bulmajava.basic.Style.*;
import static com.github.t1.bulmajava.components.Navbar.navbar;
import static com.github.t1.bulmajava.components.Tabs.navTabs;
import static com.github.t1.bulmajava.elements.Button.buttons;
import static com.github.t1.bulmajava.elements.Icon.icon;
import static com.github.t1.bulmajava.elements.IconStyle.BOLD;
import static com.github.t1.bulmajava.elements.Image.img;
import static com.github.t1.bulmajava.elements.MenuActivationType.ACTIVE;
import static com.github.t1.bulmajava.elements.Title.subtitleP;
import static com.github.t1.bulmajava.elements.Title.titleP;
import static com.github.t1.bulmajava.layout.Hero.hero;
import static com.github.t1.bulmajava.layout.HeroSize.*;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
@NoSectionWrapper
class HeroTest {
    @Test void shouldRenderHero() {
        var hero = hero().containsBody(
                titleP("Hero title"),
                subtitleP("Hero subtitle"));

        then(hero).rendersAs("""
                <section class="hero">
                    <div class="hero-body">
                        <p class="title">Hero title</p>
                        <p class="subtitle">Hero subtitle</p>
                    </div>
                </section>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderColorHero(Color color) {
        var hero = hero().is(color).containsBody(
                titleP(color.key() + " hero"),
                subtitleP(color.key() + " subtitle"));

        then(hero).rendersAs("""
                <section class="hero is-$color">
                    <div class="hero-body">
                        <p class="title">$color hero</p>
                        <p class="subtitle">$color subtitle</p>
                    </div>
                </section>
                """.replace("$color", color.key()));
    }

    @Test void shouldRenderSmallHero() {
        var hero = hero().is(SMALL, PRIMARY).containsBody(
                titleP("Small hero"),
                subtitleP("Small subtitle"));

        then(hero).rendersAs("""
                <section class="hero is-small is-primary">
                    <div class="hero-body">
                        <p class="title">Small hero</p>
                        <p class="subtitle">Small subtitle</p>
                    </div>
                </section>
                """);
    }

    @Test void shouldRenderMediumHero() {
        var hero = hero().is(MEDIUM, LINK).containsBody(
                titleP("Medium hero"),
                subtitleP("Medium subtitle"));

        then(hero).rendersAs("""
                <section class="hero is-medium is-link">
                    <div class="hero-body">
                        <p class="title">Medium hero</p>
                        <p class="subtitle">Medium subtitle</p>
                    </div>
                </section>
                """);
    }

    @Test void shouldRenderLargeHero() {
        var hero = hero().is(LARGE, INFO).containsBody(
                titleP("Large hero"),
                subtitleP("Large subtitle"));

        then(hero).rendersAs("""
                <section class="hero is-large is-info">
                    <div class="hero-body">
                        <p class="title">Large hero</p>
                        <p class="subtitle">Large subtitle</p>
                    </div>
                </section>
                """);
    }

    @Test void shouldRenderHalfheightHero() {
        var hero = hero().is(SUCCESS, HALFHEIGHT).containsBody(Basic.div().classes("").contains(
                titleP("Half height hero"),
                subtitleP("Half height subtitle")));

        then(hero).rendersAs("""
                <section class="hero is-success is-halfheight">
                    <div class="hero-body">
                        <div class="">
                            <p class="title">Half height hero</p>
                            <p class="subtitle">Half height subtitle</p>
                        </div>
                    </div>
                </section>
                """);
    }

    @Test void shouldRenderFullheightHero() {
        var hero = hero().is(DANGER, FULLHEIGHT).containsBody(Basic.div().classes("").contains(
                titleP("Fullheight hero"),
                subtitleP("Fullheight subtitle")));

        then(hero).rendersAs("""
                <section class="hero is-danger is-fullheight">
                    <div class="hero-body">
                        <div class="">
                            <p class="title">Fullheight hero</p>
                            <p class="subtitle">Fullheight subtitle</p>
                        </div>
                    </div>
                </section>
                """);
    }

    @Test void shouldRenderFullheightHeroWithNavbar() {
        var div = Basic.div().contains(
                navbar("fullheightHeroWithNavbarExample")
                        .containsStart(
                                a("Home"),
                                a("Documentation"))
                        .containsEnd(Basic.div().contains(
                                buttons().contains(
                                        a("Github").classes("button").is(DARK),
                                        a("Download").classes("button").is(LINK)))),
                hero().is(LINK).isFullheightWithNavbar().containsBody(
                        titleP("Fullheight hero with navbar")));

        // nav role and aria-label where missing in the docs
        // I've also removed the empty container
        then(div).rendersAs("""
                <div>
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div class="navbar-menu" id="fullheightHeroWithNavbarExample">
                            <div class="navbar-start">
                                <a class="navbar-item">Home</a>
                                <a class="navbar-item">Documentation</a>
                            </div>
                            <div class="navbar-end">
                                <div class="navbar-item">
                                    <div class="buttons">
                                        <a class="button is-dark">Github</a>
                                        <a class="button is-link">Download</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                    <section class="hero is-link is-fullheight-with-navbar">
                        <div class="hero-body">
                            <p class="title">Fullheight hero with navbar</p>
                        </div>
                    </section>
                </div>
                """);
    }

    @Test void shouldRenderMediumThreePartHero() {
        var div = hero().is(PRIMARY, MEDIUM)
                .containsHead(navbar("navbarMenuHeroA")
                        .brand(a().contains(
                                img("https://bulma.io/images/bulma-type-white.png", "Logo")))
                        .burger()
                        .containsEnd(
                                a("Home").is(ACTIVE),
                                a("Examples"),
                                a("Documentation"),
                                Basic.div().contains(
                                        a().button().is(PRIMARY, INVERTED).contains(icon("github", BOLD), Basic.span("Download")))))
                .containsBody(Basic.div().classes("container", "has-text-centered").contains(
                        titleP("Title"),
                        subtitleP("Subtitle")))
                .containsFoot(navTabs().contains(
                        Basic.li().is(ACTIVE).contains(a("Overview")),
                        Basic.li().contains(a("Modifiers")),
                        Basic.li().contains(a("Grid")),
                        Basic.li().contains(a("Elements")),
                        Basic.li().contains(a("Components")),
                        Basic.li().contains(a("Layout"))));

        // nav role and aria-label where missing in the docs
        // I've also removed the empty container
        then(div).rendersAs("""
                <section class="hero is-primary is-medium">
                    <div class="hero-head">
                        <nav class="navbar" role="navigation" aria-label="navigation">
                            <div class="navbar-brand">
                                <a class="navbar-item">
                                    <img src="https://bulma.io/images/bulma-type-white.png" alt="Logo">
                                </a>
                                <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false" data-target="navbarMenuHeroA">
                                    <span aria-hidden="true"></span>
                                    <span aria-hidden="true"></span>
                                    <span aria-hidden="true"></span>
                                </a>
                            </div>
                            <div class="navbar-menu" id="navbarMenuHeroA">
                                <div class="navbar-end">
                                    <a class="is-active navbar-item">Home</a>
                                    <a class="navbar-item">Examples</a>
                                    <a class="navbar-item">Documentation</a>
                                    <div class="navbar-item">
                                        <a class="button is-primary is-inverted">
                                            <span class="icon"><i class="fab fa-github"></i></span>
                                            <span>Download</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </div>
                    <div class="hero-body">
                        <div class="container has-text-centered">
                            <p class="title">Title</p>
                            <p class="subtitle">Subtitle</p>
                        </div>
                    </div>
                    <div class="hero-foot">
                        <nav class="tabs">
                            <ul>
                                <li class="is-active">
                                    <a>Overview</a>
                                </li>
                                <li>
                                    <a>Modifiers</a>
                                </li>
                                <li>
                                    <a>Grid</a>
                                </li>
                                <li>
                                    <a>Elements</a>
                                </li>
                                <li>
                                    <a>Components</a>
                                </li>
                                <li>
                                    <a>Layout</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </section>
                """);
    }

    @Test void shouldRenderLargeThreePartHero() {
        var div = hero().is(INFO, LARGE)
                .containsHead(navbar("navbarMenuHeroB")
                        .brand(a().contains(
                                img("https://bulma.io/images/bulma-type-white.png", "Logo")))
                        .burger()
                        .containsEnd(
                                a("Home").is(ACTIVE),
                                a("Examples"),
                                a("Documentation"),
                                Basic.div().contains(
                                        a().button().is(INFO, INVERTED).contains(icon("github", BOLD), Basic.span("Download")))))
                .containsBody(Basic.div().classes("container", "has-text-centered").contains(
                        titleP("Title"),
                        subtitleP("Subtitle")))
                .containsFoot(navTabs().isBoxed().is(FULLWIDTH).contains(
                        Basic.li().is(ACTIVE).contains(a("Overview")),
                        Basic.li().contains(a("Modifiers")),
                        Basic.li().contains(a("Grid")),
                        Basic.li().contains(a("Elements")),
                        Basic.li().contains(a("Components")),
                        Basic.li().contains(a("Layout"))));

        // nav role and aria-label where missing in the docs
        // I've also removed the empty container
        then(div).rendersAs("""
                <section class="hero is-info is-large">
                    <div class="hero-head">
                        <nav class="navbar" role="navigation" aria-label="navigation">
                            <div class="navbar-brand">
                                <a class="navbar-item">
                                    <img src="https://bulma.io/images/bulma-type-white.png" alt="Logo">
                                </a>
                                <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false" data-target="navbarMenuHeroB">
                                    <span aria-hidden="true"></span>
                                    <span aria-hidden="true"></span>
                                    <span aria-hidden="true"></span>
                                </a>
                            </div>
                            <div class="navbar-menu" id="navbarMenuHeroB">
                                <div class="navbar-end">
                                    <a class="is-active navbar-item">Home</a>
                                    <a class="navbar-item">Examples</a>
                                    <a class="navbar-item">Documentation</a>
                                    <div class="navbar-item">
                                        <a class="button is-info is-inverted">
                                            <span class="icon"><i class="fab fa-github"></i></span>
                                            <span>Download</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </div>
                    <div class="hero-body">
                        <div class="container has-text-centered">
                            <p class="title">Title</p>
                            <p class="subtitle">Subtitle</p>
                        </div>
                    </div>
                    <div class="hero-foot">
                        <nav class="tabs is-boxed is-fullwidth">
                            <ul>
                                <li class="is-active">
                                    <a>Overview</a>
                                </li>
                                <li>
                                    <a>Modifiers</a>
                                </li>
                                <li>
                                    <a>Grid</a>
                                </li>
                                <li>
                                    <a>Elements</a>
                                </li>
                                <li>
                                    <a>Components</a>
                                </li>
                                <li>
                                    <a>Layout</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </section>
                """);
    }

    @Test void shouldRenderFullHeightThreePartHero() {
        var div = hero().is(SUCCESS, FULLHEIGHT)
                .containsHead(navbar("navbarMenuHeroC")
                        .brand(a().contains(
                                img("https://bulma.io/images/bulma-type-white.png", "Logo")))
                        .burger()
                        .containsEnd(
                                a("Home").is(ACTIVE),
                                a("Examples"),
                                a("Documentation"),
                                Basic.div().contains(
                                        a().button().is(SUCCESS, INVERTED).contains(icon("github", BOLD), Basic.span("Download")))))
                .containsBody(Basic.div().classes("container", "has-text-centered").contains(
                        titleP("Title"),
                        subtitleP("Subtitle")))
                .containsFoot(navTabs().isBoxed().is(FULLWIDTH).contains(
                        Basic.li().is(ACTIVE).contains(a("Overview")),
                        Basic.li().contains(a("Modifiers")),
                        Basic.li().contains(a("Grid")),
                        Basic.li().contains(a("Elements")),
                        Basic.li().contains(a("Components")),
                        Basic.li().contains(a("Layout"))));

        // nav role and aria-label where missing in the docs
        // I've also removed the empty container
        then(div).rendersAs("""
                <section class="hero is-success is-fullheight">
                    <div class="hero-head">
                        <nav class="navbar" role="navigation" aria-label="navigation">
                            <div class="navbar-brand">
                                <a class="navbar-item">
                                    <img src="https://bulma.io/images/bulma-type-white.png" alt="Logo">
                                </a>
                                <a class="navbar-burger" role="button" aria-label="menu" aria-expanded="false" data-target="navbarMenuHeroC">
                                    <span aria-hidden="true"></span>
                                    <span aria-hidden="true"></span>
                                    <span aria-hidden="true"></span>
                                </a>
                            </div>
                            <div class="navbar-menu" id="navbarMenuHeroC">
                                <div class="navbar-end">
                                    <a class="is-active navbar-item">Home</a>
                                    <a class="navbar-item">Examples</a>
                                    <a class="navbar-item">Documentation</a>
                                    <div class="navbar-item">
                                        <a class="button is-success is-inverted">
                                            <span class="icon"><i class="fab fa-github"></i></span>
                                            <span>Download</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </div>
                    <div class="hero-body">
                        <div class="container has-text-centered">
                            <p class="title">Title</p>
                            <p class="subtitle">Subtitle</p>
                        </div>
                    </div>
                    <div class="hero-foot">
                        <nav class="tabs is-boxed is-fullwidth">
                            <ul>
                                <li class="is-active">
                                    <a>Overview</a>
                                </li>
                                <li>
                                    <a>Modifiers</a>
                                </li>
                                <li>
                                    <a>Grid</a>
                                </li>
                                <li>
                                    <a>Elements</a>
                                </li>
                                <li>
                                    <a>Components</a>
                                </li>
                                <li>
                                    <a>Layout</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </section>
                """);
    }
}
