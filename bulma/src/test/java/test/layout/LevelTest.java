package test.layout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.*;
import static com.github.t1.bulmajava.basic.Color.INFO;
import static com.github.t1.bulmajava.basic.Color.SUCCESS;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.columns.ScreenSize.MOBILE;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Image.img;
import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.TEXT;
import static com.github.t1.bulmajava.layout.Level.level;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class LevelTest {
    @Test void shouldRenderLevel() {
        var level = level()
                .left(
                        p().classes("subtitle", "is-5").content(
                                strong("123"), string(" posts")),
                        field()
                                .control(input(TEXT).placeholder("Find a post"))
                                .containsAddonRight(button("Search")))
                .right(
                        p().content(strong("All")),
                        a("Published"),
                        a("Drafts"),
                        a("Deleted"),
                        button("New").is(SUCCESS));

        // in the docs, all items in right are not in separate divs, but it should be like in left
        // and it looks the same
        then(level).rendersAs("""
                <nav class="level">
                    <div class="level-left">
                        <div class="level-item">
                            <p class="subtitle is-5"><strong>123</strong> posts</p>
                        </div>
                        <div class="level-item">
                            <div class="field has-addons">
                                <div class="control">
                                    <input class="input" type="text" placeholder="Find a post">
                                </div>
                                <div class="control">
                                    <button class="button">Search</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="level-right">
                        <div class="level-item">
                            <p><strong>All</strong></p>
                        </div>
                        <div class="level-item">
                            <a>Published</a>
                        </div>
                        <div class="level-item">
                            <a>Drafts</a>
                        </div>
                        <div class="level-item">
                            <a>Deleted</a>
                        </div>
                        <div class="level-item">
                            <button class="button is-success">New</button>
                        </div>
                    </div>
                </nav>
                """);
    }

    @Test void shouldRenderCenteredLevel() {
        var level = level().item(div().content(
                                p("Tweets").classes("heading"),
                                p("3,456").classes("title")),
                        "has-text-centered")
                .item(div().content(
                                p("Following").classes("heading"),
                                p("123").classes("title")),
                        "has-text-centered")
                .item(div().content(
                                p("Followers").classes("heading"),
                                p("456K").classes("title")),
                        "has-text-centered")
                .item(div().content(
                                p("Likes").classes("heading"),
                                p("789").classes("title")),
                        "has-text-centered");

        then(level).rendersAs("""
                <nav class="level">
                    <div class="level-item has-text-centered">
                        <div>
                            <p class="heading">Tweets</p>
                            <p class="title">3,456</p>
                        </div>
                    </div>
                    <div class="level-item has-text-centered">
                        <div>
                            <p class="heading">Following</p>
                            <p class="title">123</p>
                        </div>
                    </div>
                    <div class="level-item has-text-centered">
                        <div>
                            <p class="heading">Followers</p>
                            <p class="title">456K</p>
                        </div>
                    </div>
                    <div class="level-item has-text-centered">
                        <div>
                            <p class="heading">Likes</p>
                            <p class="title">789</p>
                        </div>
                    </div>
                </nav>
                """);
    }

    @Test void shouldRenderCenteredLevel2() {
        var level = level()
                .item(a("Home").classes("link").is(INFO), "has-text-centered")
                .item(a("Menu").classes("link").is(INFO), "has-text-centered")
                .item(img("https://bulma.io/assets/images/bulma-type.png", "").style("height: 30px;"), "has-text-centered")
                .item(a("Reservations").classes("link").is(INFO), "has-text-centered")
                .item(a("Contact").classes("link").is(INFO), "has-text-centered");

        then(level).rendersAs("""
                <nav class="level">
                    <div class="level-item has-text-centered">
                        <a class="link is-info">Home</a>
                    </div>
                    <div class="level-item has-text-centered">
                        <a class="link is-info">Menu</a>
                    </div>
                    <div class="level-item has-text-centered">
                        <img src="https://bulma.io/assets/images/bulma-type.png" alt="" style="height: 30px;">
                    </div>
                    <div class="level-item has-text-centered">
                        <a class="link is-info">Reservations</a>
                    </div>
                    <div class="level-item has-text-centered">
                        <a class="link is-info">Contact</a>
                    </div>
                </nav>
                """);
    }

    @Test void shouldRenderMobileLevel() {
        var level = level().is(MOBILE).item(div().content(
                                p("Tweets").classes("heading"),
                                p("3,456").classes("title")),
                        "has-text-centered")
                .item(div().content(
                                p("Following").classes("heading"),
                                p("123").classes("title")),
                        "has-text-centered")
                .item(div().content(
                                p("Followers").classes("heading"),
                                p("456K").classes("title")),
                        "has-text-centered")
                .item(div().content(
                                p("Likes").classes("heading"),
                                p("789").classes("title")),
                        "has-text-centered");

        then(level).rendersAs("""
                <nav class="level is-mobile">
                    <div class="level-item has-text-centered">
                        <div>
                            <p class="heading">Tweets</p>
                            <p class="title">3,456</p>
                        </div>
                    </div>
                    <div class="level-item has-text-centered">
                        <div>
                            <p class="heading">Following</p>
                            <p class="title">123</p>
                        </div>
                    </div>
                    <div class="level-item has-text-centered">
                        <div>
                            <p class="heading">Followers</p>
                            <p class="title">456K</p>
                        </div>
                    </div>
                    <div class="level-item has-text-centered">
                        <div>
                            <p class="heading">Likes</p>
                            <p class="title">789</p>
                        </div>
                    </div>
                </nav>
                """);
    }
}
