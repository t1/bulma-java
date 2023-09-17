package test.layout;

import com.github.t1.bulmajava.basic.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.NoSectionWrapper;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.*;
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
        var hero = hero().body(
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
        var hero = hero().is(color).body(
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
        var hero = hero().is(SMALL, PRIMARY).body(
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
        var hero = hero().is(MEDIUM, LINK).body(
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
        var hero = hero().is(LARGE, INFO).body(
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
        var hero = hero().is(SUCCESS, HALFHEIGHT).body(div().classes("").content(
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
        var hero = hero().is(DANGER, FULLHEIGHT).body(div().classes("").content(
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
        var div = div().content(
                navbar("fullheightHeroWithNavbarExample")
                        .start(
                                a("Home"),
                                a("Documentation"))
                        .end(div().content(
                                buttons().content(
                                        a("Github").classes("button").is(DARK),
                                        a("Download").classes("button").is(LINK)))),
                hero().is(LINK).isFullheightWithNavbar().body(
                        titleP("Fullheight hero with navbar")));

        // nav role and aria-label where missing in the docs
        // I've also removed the empty container
        then(div).rendersAs("""
                <div>
                    <nav class="navbar" role="navigation" aria-label="navigation">
                        <div id="fullheightHeroWithNavbarExample" class="navbar-menu">
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
                .head(navbar("navbarMenuHeroA")
                        .brand(a().content(
                                img("https://bulma.io/images/bulma-type-white.png", "Logo")))
                        .burger()
                        .end(
                                a("Home").is(ACTIVE),
                                a("Examples"),
                                a("Documentation"),
                                div().content(
                                        a().button().is(PRIMARY, INVERTED).content(icon("github", BOLD), span("Download")))))
                .body(div().classes("container", "has-text-centered").content(
                        titleP("Title"),
                        subtitleP("Subtitle")))
                .foot(navTabs().content(
                        li().is(ACTIVE).content(a("Overview")),
                        li().content(a("Modifiers")),
                        li().content(a("Grid")),
                        li().content(a("Elements")),
                        li().content(a("Components")),
                        li().content(a("Layout"))));

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
                            <div id="navbarMenuHeroA" class="navbar-menu">
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
                .head(navbar("navbarMenuHeroB")
                        .brand(a().content(
                                img("https://bulma.io/images/bulma-type-white.png", "Logo")))
                        .burger()
                        .end(
                                a("Home").is(ACTIVE),
                                a("Examples"),
                                a("Documentation"),
                                div().content(
                                        a().button().is(INFO, INVERTED).content(icon("github", BOLD), span("Download")))))
                .body(div().classes("container", "has-text-centered").content(
                        titleP("Title"),
                        subtitleP("Subtitle")))
                .foot(navTabs().isBoxed().is(FULLWIDTH).content(
                        li().is(ACTIVE).content(a("Overview")),
                        li().content(a("Modifiers")),
                        li().content(a("Grid")),
                        li().content(a("Elements")),
                        li().content(a("Components")),
                        li().content(a("Layout"))));

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
                            <div id="navbarMenuHeroB" class="navbar-menu">
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
                .head(navbar("navbarMenuHeroC")
                        .brand(a().content(
                                img("https://bulma.io/images/bulma-type-white.png", "Logo")))
                        .burger()
                        .end(
                                a("Home").is(ACTIVE),
                                a("Examples"),
                                a("Documentation"),
                                div().content(
                                        a().button().is(SUCCESS, INVERTED).content(icon("github", BOLD), span("Download")))))
                .body(div().classes("container", "has-text-centered").content(
                        titleP("Title"),
                        subtitleP("Subtitle")))
                .foot(navTabs().isBoxed().is(FULLWIDTH).content(
                        li().is(ACTIVE).content(a("Overview")),
                        li().content(a("Modifiers")),
                        li().content(a("Grid")),
                        li().content(a("Elements")),
                        li().content(a("Components")),
                        li().content(a("Layout"))));

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
                            <div id="navbarMenuHeroC" class="navbar-menu">
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
