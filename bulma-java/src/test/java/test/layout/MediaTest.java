package test.layout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Color.INFO;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.columns.ScreenSize.MOBILE;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Content.content_;
import static com.github.t1.bulmajava.elements.Delete.delete;
import static com.github.t1.bulmajava.elements.Icon.icon;
import static com.github.t1.bulmajava.elements.Image.figure;
import static com.github.t1.bulmajava.elements.Image.imageP;
import static com.github.t1.bulmajava.elements.Image.img;
import static com.github.t1.bulmajava.elements.ImageSize._128x128;
import static com.github.t1.bulmajava.elements.ImageSize._48x48;
import static com.github.t1.bulmajava.elements.ImageSize._64x64;
import static com.github.t1.bulmajava.form.Checkbox.checkbox;
import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Textarea.textarea;
import static com.github.t1.bulmajava.layout.Level.level;
import static com.github.t1.bulmajava.layout.Media.media;
import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.htmljava.Basic.br;
import static com.github.t1.htmljava.Basic.div;
import static com.github.t1.htmljava.Basic.p;
import static com.github.t1.htmljava.Basic.small;
import static com.github.t1.htmljava.Basic.strong;
import static com.github.t1.htmljava.Renderable.RenderableString.string;
import static test.CustomAssertions.then;
import static test.RenderTestExtension.loremIpsum;
import static test.RenderTestExtension.loremIpsumS;
import static test.RenderTestExtension.placeholder;

@ExtendWith(RenderTestExtension.class)
class MediaTest {
    @Test void shouldRenderMediaObject() {
        var div = div().style("width: 400px;").content(media()
                .left(figure().content(imageP(_64x64).content(
                        img(placeholder(_128x128), "bulma"))))
                .content(
                        div().classes("content").content(p().content(
                                strong("John Smith"), small("@johnsmith"), small("31m"),
                                br(),
                                loremIpsumS())),
                        level().is(MOBILE)
                                .leftA(icon("reply").is(SMALL))
                                .leftA(icon("retweet").is(SMALL))
                                .leftA(icon("heart").is(SMALL)))
                .right(div().content(delete())));

        // the aria-label and img-alt where not in the docs
        then(div).rendersAs("""
                <div style="width: 400px;">
                    <article class="media">
                        <figure class="media-left">
                            <p class="image is-64x64">
                                <img src="https://bulma.io/assets/images/placeholders/128x128.png" alt="bulma">
                            </p>
                        </figure>
                        <div class="media-content">
                            <div class="content">
                                <p>
                                    <strong>John Smith</strong>
                                    <small>@johnsmith</small>
                                    <small>31m</small>
                                    <br>
                                    $loremIpsum
                                </p>
                            </div>
                            <nav class="level is-mobile">
                                <div class="level-left">
                                    <a class="level-item">
                                        <span class="icon is-small"><i class="fas fa-reply"></i></span>
                                    </a>
                                    <a class="level-item">
                                        <span class="icon is-small"><i class="fas fa-retweet"></i></span>
                                    </a>
                                    <a class="level-item">
                                        <span class="icon is-small"><i class="fas fa-heart"></i></span>
                                    </a>
                                </div>
                            </nav>
                        </div>
                        <div class="media-right">
                            <button class="delete" aria-label="delete"></button>
                        </div>
                    </article>
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }

    @Test void shouldRenderMediaObjectWithTextarea() {
        var div = div().style("width: 400px;").content(media()
                .left(figure().content(imageP(_64x64).content(
                        img(placeholder(_128x128), "bulma"))))
                .content(
                        field().control(textarea().placeholder("Add a comment...")),
                        level()
                                .left(a("Submit").button().is(INFO))
                                .right(checkbox().content("Press enter to submit"))));

        // the img-alt was not in the docs
        then(div).rendersAs("""
                <div style="width: 400px;">
                    <article class="media">
                        <figure class="media-left">
                            <p class="image is-64x64">
                                <img src="https://bulma.io/assets/images/placeholders/128x128.png" alt="bulma">
                            </p>
                        </figure>
                        <div class="media-content">
                            <div class="field">
                                <div class="control">
                                    <textarea class="textarea" placeholder="Add a comment..."></textarea>
                                </div>
                            </div>
                            <nav class="level">
                                <div class="level-left">
                                    <div class="level-item">
                                        <a class="button is-info">Submit</a>
                                    </div>
                                </div>
                                <div class="level-right">
                                    <div class="level-item">
                                        <label class="checkbox">
                                            <input type="checkbox">
                                            Press enter to submit
                                        </label>
                                    </div>
                                </div>
                            </nav>
                        </div>
                    </article>
                </div>
                """);
    }

    @Test void shouldRenderNestedMediaObjects() {
        var div = div().style("width: 800px;").content(media()
                        .left(figure().content(imageP(_64x64).content(
                                img(placeholder(_128x128), "bulma"))))
                        .content(content_().content(
                                        p().content(
                                                strong("Barbara Middleton"), br(),
                                                loremIpsumS(), br(),
                                                small().content(a("Like"), string("·"), a("Reply"), string("·"), string("3 hrs")))),
                                media()
                                        .left(figure().content(imageP(_48x48).content(
                                                img(placeholder("96x96"), "bulma"))))
                                        .content(content_().content(p().content(
                                                        strong("Sean Brown"), br(),
                                                        loremIpsumS(), br(),
                                                        small().content(a("Like"), string("·"), a("Reply"), string("·"), string("2 hrs")))),
                                                media().content("Vivamus quis semper metus, non tincidunt dolor. Vivamus in mi eu lorem cursus ullamcorper sit amet nec massa."),
                                                media().content("Morbi vitae diam et purus tincidunt porttitor vel vitae augue. Praesent malesuada metus sed pharetra euismod. Cras tellus odio, tincidunt iaculis diam non, porta aliquet tortor.")),
                                media()
                                        .left(figure().content(imageP(_48x48).content(
                                                img(placeholder("96x96"), "bulma"))))
                                        .content(content_().content(p().content(
                                                strong("Kayli Eunice"), br(),
                                                loremIpsumS(), br(),
                                                small().content(a("Like"), string("·"), a("Reply"), string("·"), string("2 hrs")))))),
                media()
                        .left(figure().content(imageP(_64x64).content(
                                img("https://bulma.io/assets/images/placeholders/128x128.png", "bulma"))))
                        .content(
                                field().control(textarea().placeholder("Add a comment...")),
                                field().control(button("Post comment"))));

        // the img-alts where not in the docs
        // the two article-only medias where missing the media-content in the docs
        then(div).rendersAs("""
                <div style="width: 800px;">
                    <article class="media">
                        <figure class="media-left">
                            <p class="image is-64x64">
                                <img src="https://bulma.io/assets/images/placeholders/128x128.png" alt="bulma">
                            </p>
                        </figure>
                        <div class="media-content">
                            <div class="content">
                                <p>
                                    <strong>Barbara Middleton</strong>
                                    <br>
                                    $loremIpsum
                                    <br>
                                    <small>
                                        <a>Like</a>
                                        ·
                                        <a>Reply</a>
                                        ·
                                        3 hrs
                                    </small>
                                </p>
                            </div>
                            <article class="media">
                                <figure class="media-left">
                                    <p class="image is-48x48">
                                        <img src="https://bulma.io/assets/images/placeholders/96x96.png" alt="bulma">
                                    </p>
                                </figure>
                                <div class="media-content">
                                    <div class="content">
                                        <p>
                                            <strong>Sean Brown</strong>
                                            <br>
                                            $loremIpsum
                                            <br>
                                            <small>
                                                <a>Like</a>
                                                ·
                                                <a>Reply</a>
                                                ·
                                                2 hrs
                                            </small>
                                        </p>
                                    </div>
                                    <article class="media">
                                        <div class="media-content">Vivamus quis semper metus, non tincidunt dolor. Vivamus in mi eu lorem cursus ullamcorper sit amet nec massa.</div>
                                    </article>
                                    <article class="media">
                                        <div class="media-content">Morbi vitae diam et purus tincidunt porttitor vel vitae augue. Praesent malesuada metus sed pharetra euismod. Cras tellus odio, tincidunt iaculis diam non, porta aliquet tortor.</div>
                                    </article>
                                </div>
                            </article>
                            <article class="media">
                                <figure class="media-left">
                                    <p class="image is-48x48">
                                        <img src="https://bulma.io/assets/images/placeholders/96x96.png" alt="bulma">
                                    </p>
                                </figure>
                                <div class="media-content">
                                    <div class="content">
                                        <p>
                                            <strong>Kayli Eunice</strong>
                                            <br>
                                            $loremIpsum
                                            <br>
                                            <small>
                                                <a>Like</a>
                                                ·
                                                <a>Reply</a>
                                                ·
                                                2 hrs
                                            </small>
                                        </p>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </article>
                    <article class="media">
                        <figure class="media-left">
                            <p class="image is-64x64">
                                <img src="https://bulma.io/assets/images/placeholders/128x128.png" alt="bulma">
                            </p>
                        </figure>
                        <div class="media-content">
                            <div class="field">
                                <div class="control">
                                    <textarea class="textarea" placeholder="Add a comment..."></textarea>
                                </div>
                            </div>
                            <div class="field">
                                <div class="control">
                                    <button class="button">Post comment</button>
                                </div>
                            </div>
                        </div>
                    </article>
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }
}
