package test.components;

import com.github.t1.bulmajava.basic.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Alignment.LEFT;
import static com.github.t1.bulmajava.basic.Color.LINK;
import static com.github.t1.bulmajava.basic.State.ACTIVE;
import static com.github.t1.bulmajava.basic.Style.FULLWIDTH;
import static com.github.t1.bulmajava.basic.Style.OUTLINED;
import static com.github.t1.bulmajava.components.Panel.panel;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Icon.icon;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.CHECKBOX;
import static com.github.t1.bulmajava.form.InputType.TEXT;
import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.htmljava.Basic.div;
import static com.github.t1.htmljava.Basic.element;
import static com.github.t1.htmljava.Basic.i;
import static com.github.t1.htmljava.Basic.p;
import static com.github.t1.htmljava.Basic.span;
import static com.github.t1.htmljava.Renderable.RenderableString.string;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class PanelTest {
    @Test void shouldRenderPanel() {
        var panel = panel("nav").style("width: 400px;")
                .heading("Repositories")
                .block(div().content(
                        p().classes("control has-icons-left").content(
                                input(TEXT).placeholder("Search"),
                                icon("search").is(LEFT).ariaHidden(true))))
                .tabs(
                        a("All").is(ACTIVE),
                        a("Public"),
                        a("Private"),
                        a("Sources"),
                        a("Forks"))
                .block(a().is(ACTIVE).content(
                        span().classes("panel-icon").content(
                                i().classes("fas fa-book").ariaHidden(true)),
                        string("bulma")))
                .block(a().content(
                        span().classes("panel-icon").content(
                                i().classes("fas fa-book").ariaHidden(true)),
                        string("marksheet")))
                .block(a().content(
                        span().classes("panel-icon").content(
                                i().classes("fas fa-book").ariaHidden(true)),
                        string("minireset.css")))
                .block(a().content(
                        span().classes("panel-icon").content(
                                i().classes("fas fa-book").ariaHidden(true)),
                        string("jgthms.github.io")))
                .block(a().content(
                        span().classes("panel-icon").content(
                                i().classes("fas fa-code-branch").ariaHidden(true)),
                        string("daniellowtw/infboard")))
                .block(a().content(
                        span().classes("panel-icon").content(
                                i().classes("fas fa-code-branch").ariaHidden(true)),
                        string("mojs")))
                .block(element("label").content(
                        input(CHECKBOX).notClasses("input"),
                        string("remember me")))
                .block(div().content(
                        button("Reset all filters").is(LINK, OUTLINED, FULLWIDTH)));

        then(panel).rendersAs("""
                <nav class="panel" style="width: 400px;">
                    <p class="panel-heading">Repositories</p>
                    <div class="panel-block">
                        <p class="control has-icons-left">
                            <input class="input" type="text" placeholder="Search">
                            <span class="icon is-left"><i class="fas fa-search" aria-hidden="true"></i></span>
                        </p>
                    </div>
                    <p class="panel-tabs">
                        <a class="is-active">All</a>
                        <a>Public</a>
                        <a>Private</a>
                        <a>Sources</a>
                        <a>Forks</a>
                    </p>
                    <a class="is-active panel-block">
                        <span class="panel-icon"><i class="fas fa-book" aria-hidden="true"></i></span>
                        bulma
                    </a>
                    <a class="panel-block">
                        <span class="panel-icon"><i class="fas fa-book" aria-hidden="true"></i></span>
                        marksheet
                    </a>
                    <a class="panel-block">
                        <span class="panel-icon"><i class="fas fa-book" aria-hidden="true"></i></span>
                        minireset.css
                    </a>
                    <a class="panel-block">
                        <span class="panel-icon"><i class="fas fa-book" aria-hidden="true"></i></span>
                        jgthms.github.io
                    </a>
                    <a class="panel-block">
                        <span class="panel-icon"><i class="fas fa-code-branch" aria-hidden="true"></i></span>
                        daniellowtw/infboard
                    </a>
                    <a class="panel-block">
                        <span class="panel-icon"><i class="fas fa-code-branch" aria-hidden="true"></i></span>
                        mojs
                    </a>
                    <label class="panel-block">
                        <input type="checkbox">
                        remember me
                    </label>
                    <div class="panel-block">
                        <button class="button is-link is-outlined is-fullwidth">Reset all filters</button>
                    </div>
                </nav>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderColorPanel(Color color) {
        var panel = panel().style("width: 400px;").is(color)
                .heading(p(color.key()))
                .tabs(
                        a("All").is(ACTIVE),
                        a("Public"),
                        a("Private"),
                        a("Sources"),
                        a("Forks"))
                .block(div().content(
                        p().classes("control has-icons-left").content(
                                input(TEXT).placeholder("Search").is(color),
                                icon("search").is(LEFT).ariaHidden(true))))
                .block(a().is(ACTIVE).content(
                        span().classes("panel-icon").content(
                                i().classes("fas fa-book").ariaHidden(true)),
                        string("bulma")))
                .block(a().content(
                        span().classes("panel-icon").content(
                                i().classes("fas fa-book").ariaHidden(true)),
                        string("marksheet")))
                .block(a().content(
                        span().classes("panel-icon").content(
                                i().classes("fas fa-book").ariaHidden(true)),
                        string("minireset.css")))
                .block(a().content(
                        span().classes("panel-icon").content(
                                i().classes("fas fa-book").ariaHidden(true)),
                        string("jgthms.github.io")));

        then(panel).rendersAs("""
                <article class="panel is-$color" style="width: 400px;">
                    <p class="panel-heading">$color</p>
                    <p class="panel-tabs">
                        <a class="is-active">All</a>
                        <a>Public</a>
                        <a>Private</a>
                        <a>Sources</a>
                        <a>Forks</a>
                    </p>
                    <div class="panel-block">
                        <p class="control has-icons-left">
                            <input class="input is-$color" type="text" placeholder="Search">
                            <span class="icon is-left"><i class="fas fa-search" aria-hidden="true"></i></span>
                        </p>
                    </div>
                    <a class="is-active panel-block">
                        <span class="panel-icon"><i class="fas fa-book" aria-hidden="true"></i></span>
                        bulma
                    </a>
                    <a class="panel-block">
                        <span class="panel-icon"><i class="fas fa-book" aria-hidden="true"></i></span>
                        marksheet
                    </a>
                    <a class="panel-block">
                        <span class="panel-icon"><i class="fas fa-book" aria-hidden="true"></i></span>
                        minireset.css
                    </a>
                    <a class="panel-block">
                        <span class="panel-icon"><i class="fas fa-book" aria-hidden="true"></i></span>
                        jgthms.github.io
                    </a>
                </article>
                """.replace("$color", color.key()));
    }

    @Test
    void shouldRenderBlockStreamPanel() {
        var panel = panel().style("width: 400px;")
                .heading(p("Stream"))
                .blocks(Stream.of("One", "Two", "Three")
                        .map(text -> div().content(a(text))));

        then(panel).rendersAs("""
                <article class="panel" style="width: 400px;">
                    <p class="panel-heading">Stream</p>
                    <div class="panel-block">
                        <a>One</a>
                    </div>
                    <div class="panel-block">
                        <a>Two</a>
                    </div>
                    <div class="panel-block">
                        <a>Three</a>
                    </div>
                </article>
                """);
    }
}
