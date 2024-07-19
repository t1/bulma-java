package test.components;

import com.github.t1.bulmajava.elements.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import java.time.LocalDate;

import static com.github.t1.bulmajava.basic.Alignment.CENTERED;
import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.*;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.components.Card.*;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Content.content_;
import static com.github.t1.bulmajava.elements.Image.image;
import static com.github.t1.bulmajava.elements.ImageRatio._4by3;
import static com.github.t1.bulmajava.elements.ImageSize._48x48;
import static com.github.t1.bulmajava.layout.Media.media;
import static test.CustomAssertions.then;
import static test.RenderTestExtension.*;

@ExtendWith(RenderTestExtension.class)
class CardTest {
    @Test void shouldRenderCard() {
        var card = card().style("width: 260px;")
                .image(image(_4by3, placeholder("1280x960"), "Placeholder image"))
                .content(
                        media()
                                .left(image(_48x48, placeholder("96x96"), "Small image"))
                                .content(
                                        Title.titleP("John Smith").is(4),
                                        Title.subtitleP("@johnsmith").is(6)),
                        content_().content(
                                string("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec iaculis mauris."),
                                a("@bulmaio"),
                                string("."),
                                a("#css").href("#"),
                                a("#responsive").href("#"),
                                br(),
                                time(LocalDate.of(2016, 1, 1)).content("11:09 PM - 1 Jan 2016")));

        then(card).rendersAs("""
                <div class="card" style="width: 260px;">
                    <div class="card-image">
                        <figure class="image is-4by3">
                            <img src="https://bulma.io/assets/images/placeholders/1280x960.png" alt="Placeholder image">
                        </figure>
                    </div>
                    <div class="card-content">
                        <article class="media">
                            <figure class="image is-48x48 media-left">
                                <img src="https://bulma.io/assets/images/placeholders/96x96.png" alt="Small image">
                            </figure>
                            <div class="media-content">
                                <p class="title is-4">John Smith</p>
                                <p class="subtitle is-6">@johnsmith</p>
                            </div>
                        </article>
                        <div class="content">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec iaculis mauris.
                            <a>@bulmaio</a>
                            .
                            <a href="#">#css</a>
                            <a href="#">#responsive</a>
                            <br>
                            <time datetime="2016-01-01">11:09 PM - 1 Jan 2016</time>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderCardHeader() {
        var card = card().style("width: 260px;").content(
                cardHeader().content(
                        p("Card header"),
                        button().ariaLabel("more options")
                                .icon("angle-down")));

        then(card).rendersAs("""
                <div class="card" style="width: 260px;">
                    <header class="card-header">
                        <p class="card-header-title">Card header</p>
                        <button class="card-header-icon" aria-label="more options">
                            <span class="icon"><i class="fas fa-angle-down" aria-hidden="true"></i></span>
                        </button>
                    </header>
                </div>
                """);
    }

    @Test void shouldRenderCenteredCardHeader() {
        var card = card().style("width: 260px;").content(
                cardHeader().content(
                        p("Card header").is(CENTERED),
                        button().ariaLabel("more options")
                                .icon("angle-down")));

        then(card).rendersAs("""
                <div class="card" style="width: 260px;">
                    <header class="card-header">
                        <p class="is-centered card-header-title">Card header</p>
                        <button class="card-header-icon" aria-label="more options">
                            <span class="icon"><i class="fas fa-angle-down" aria-hidden="true"></i></span>
                        </button>
                    </header>
                </div>
                """);
    }

    @Test void shouldRenderCardImage() {
        var card = card().style("width: 260px;").content(
                cardImage().content(
                        image(_4by3, placeholder("1280x960"), "Placeholder image")));

        then(card).rendersAs("""
                <div class="card" style="width: 260px;">
                    <div class="card-image">
                        <figure class="image is-4by3">
                            <img src="https://bulma.io/assets/images/placeholders/1280x960.png" alt="Placeholder image">
                        </figure>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderCardContent() {
        var card = card().style("width: 260px;").content(
                cardContent().content(loremIpsum()));

        // the extra content-div is not necessary
        then(card).rendersAs("""
                <div class="card" style="width: 260px;">
                    <div class="card-content">$loremIpsum</div>
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }

    @Test void shouldRenderCardFooter() {
        var card = card().style("width: 260px;").content(
                cardFooter().content(
                        a("Save").href("#"),
                        a("Edit").href("#"),
                        a("Delete").href("#")));

        then(card).rendersAs("""
                <div class="card" style="width: 260px;">
                    <footer class="card-footer">
                        <a class="card-footer-item" href="#">Save</a>
                        <a class="card-footer-item" href="#">Edit</a>
                        <a class="card-footer-item" href="#">Delete</a>
                    </footer>
                </div>
                """);
    }

    @Test void shouldRenderCardExample1() {
        var card = card().style("width: 260px;")
                .header(
                        p("Component"),
                        button().ariaLabel("more options").icon("angle-down"))
                .content(
                        loremIpsumS(),
                        a("@bulmaio").href("#"),
                        string("."),
                        a("#css").href("#"),
                        a("#responsive").href("#"),
                        br(),
                        time(LocalDate.of(2016, 1, 1)).content("11:09 PM - 1 Jan 2016"))
                .footer(
                        a("Save").href("#"),
                        a("Edit").href("#"),
                        a("Delete").href("#"));

        // the extra content-div is not necessary
        then(card).rendersAs("""
                <div class="card" style="width: 260px;">
                    <header class="card-header">
                        <p class="card-header-title">Component</p>
                        <button class="card-header-icon" aria-label="more options">
                            <span class="icon"><i class="fas fa-angle-down" aria-hidden="true"></i></span>
                        </button>
                    </header>
                    <div class="card-content">
                        $loremIpsum
                        <a href="#">@bulmaio</a>
                        .
                        <a href="#">#css</a>
                        <a href="#">#responsive</a>
                        <br>
                        <time datetime="2016-01-01">11:09 PM - 1 Jan 2016</time>
                    </div>
                    <footer class="card-footer">
                        <a class="card-footer-item" href="#">Save</a>
                        <a class="card-footer-item" href="#">Edit</a>
                        <a class="card-footer-item" href="#">Delete</a>
                    </footer>
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }

    @Test void shouldRenderCardExample2() {
        var card = card().style("width: 260px;")
                .content(
                        Title.titleP("“There are two hard things in computer science: cache invalidation, naming things, and off-by-one errors.”"),
                        Title.subtitleP("Jeff Atwood"))
                .footer(
                        p().content(
                                span().content(
                                        string("View on"),
                                        a("Twitter").href("https://twitter.com/codinghorror/status/506010907021828096"))),
                        p().content(
                                span().content(
                                        string("Share on"),
                                        a("Facebook").href("#"))));

        then(card).rendersAs("""
                <div class="card" style="width: 260px;">
                    <div class="card-content">
                        <p class="title">“There are two hard things in computer science: cache invalidation, naming things, and off-by-one errors.”</p>
                        <p class="subtitle">Jeff Atwood</p>
                    </div>
                    <footer class="card-footer">
                        <p class="card-footer-item">
                            <span>
                                View on
                                <a href="https://twitter.com/codinghorror/status/506010907021828096">Twitter</a>
                            </span>
                        </p>
                        <p class="card-footer-item">
                            <span>
                                Share on
                                <a href="#">Facebook</a>
                            </span>
                        </p>
                    </footer>
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }
}
