package test.layout;

import com.github.t1.bulmajava.basic.Basic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Color.INFO;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.columns.ScreenSize.MOBILE;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Content.content_;
import static com.github.t1.bulmajava.elements.Delete.delete;
import static com.github.t1.bulmajava.elements.Icon.icon;
import static com.github.t1.bulmajava.elements.Image.*;
import static com.github.t1.bulmajava.elements.ImageSize._48x48;
import static com.github.t1.bulmajava.elements.ImageSize._64x64;
import static com.github.t1.bulmajava.form.Checkbox.checkbox;
import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Textarea.textarea;
import static com.github.t1.bulmajava.layout.Level.level;
import static com.github.t1.bulmajava.layout.Media.media;
import static test.CustomAssertions.then;
import static test.RenderTestExtension.loremIpsum;
import static test.RenderTestExtension.loremIpsumS;

@ExtendWith(RenderTestExtension.class)
class MediaTest {
    @Test void shouldRenderMediaObject() {
        var div = Basic.div().style("width: 400px;").contains(media()
                .containsLeft(figure().contains(imageP(_64x64).contains(
                        img("https://bulma.io/images/placeholders/128x128.png", "bulma"))))
                .containsContent(
                        Basic.div().classes("content").contains(Basic.p().contains(
                                Basic.strong("John Smith"), Basic.small("@johnsmith"), Basic.small("31m"),
                                Basic.br(),
                                loremIpsumS())),
                        level().is(MOBILE)
                                .containsLeftA(icon("reply").is(SMALL))
                                .containsLeftA(icon("retweet").is(SMALL))
                                .containsLeftA(icon("heart").is(SMALL)))
                .containsRight(Basic.div().contains(delete())));

        // the aria-label and img-alt where not in the docs
        then(div).rendersAs("""
                <div style="width: 400px;">
                    <article class="media">
                        <figure class="media-left">
                            <p class="image is-64x64">
                                <img src="https://bulma.io/images/placeholders/128x128.png" alt="bulma">
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
        var div = Basic.div().style("width: 400px;").contains(media()
                .containsLeft(figure().contains(imageP(_64x64).contains(
                        img("https://bulma.io/images/placeholders/128x128.png", "bulma"))))
                .containsContent(
                        field().containsControl(textarea().placeholder("Add a comment...")),
                        level()
                                .containsLeft(a("Submit").button().is(INFO))
                                .containsRight(checkbox().contains("Press enter to submit"))));

        // the img-alt was not in the docs
        then(div).rendersAs("""
                <div style="width: 400px;">
                    <article class="media">
                        <figure class="media-left">
                            <p class="image is-64x64">
                                <img src="https://bulma.io/images/placeholders/128x128.png" alt="bulma">
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
        var div = Basic.div().style("width: 800px;").contains(media()
                        .containsLeft(figure().contains(imageP(_64x64).contains(
                                img("https://bulma.io/images/placeholders/128x128.png", "bulma"))))
                        .containsContent(content_().contains(
                                        Basic.p().contains(
                                                Basic.strong("Barbara Middleton"), Basic.br(),
                                                loremIpsumS(), Basic.br(),
                                                Basic.small().contains(a("Like"), string("·"), a("Reply"), string("·"), string("3 hrs")))),
                                media()
                                        .containsLeft(figure().contains(imageP(_48x48).contains(
                                                img("https://bulma.io/images/placeholders/96x96.png", "bulma"))))
                                        .containsContent(content_().contains(Basic.p().contains(
                                                        Basic.strong("Sean Brown"), Basic.br(),
                                                        loremIpsumS(), Basic.br(),
                                                        Basic.small().contains(a("Like"), string("·"), a("Reply"), string("·"), string("2 hrs")))),
                                                media().containsContent("Vivamus quis semper metus, non tincidunt dolor. Vivamus in mi eu lorem cursus ullamcorper sit amet nec massa."),
                                                media().containsContent("Morbi vitae diam et purus tincidunt porttitor vel vitae augue. Praesent malesuada metus sed pharetra euismod. Cras tellus odio, tincidunt iaculis diam non, porta aliquet tortor.")),
                                media()
                                        .containsLeft(figure().contains(imageP(_48x48).contains(
                                                img("https://bulma.io/images/placeholders/96x96.png", "bulma"))))
                                        .containsContent(content_().contains(Basic.p().contains(
                                                Basic.strong("Kayli Eunice"), Basic.br(),
                                                loremIpsumS(), Basic.br(),
                                                Basic.small().contains(a("Like"), string("·"), a("Reply"), string("·"), string("2 hrs")))))),
                media()
                        .containsLeft(figure().contains(imageP(_64x64).contains(
                                img("https://bulma.io/images/placeholders/128x128.png", "bulma"))))
                        .containsContent(
                                field().containsControl(textarea().placeholder("Add a comment...")),
                                field().containsControl(button("Post comment"))));

        // the img-alts where not in the docs
        then(div).rendersAs("""
                <div style="width: 800px;">
                    <article class="media">
                        <figure class="media-left">
                            <p class="image is-64x64">
                                <img src="https://bulma.io/images/placeholders/128x128.png" alt="bulma">
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
                                        <img src="https://bulma.io/images/placeholders/96x96.png" alt="bulma">
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
                                    <article class="media">Vivamus quis semper metus, non tincidunt dolor. Vivamus in mi eu lorem cursus ullamcorper sit amet nec massa.</article>
                                    <article class="media">Morbi vitae diam et purus tincidunt porttitor vel vitae augue. Praesent malesuada metus sed pharetra euismod. Cras tellus odio, tincidunt iaculis diam non, porta aliquet tortor.</article>
                                </div>
                            </article>
                            <article class="media">
                                <figure class="media-left">
                                    <p class="image is-48x48">
                                        <img src="https://bulma.io/images/placeholders/96x96.png" alt="bulma">
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
                                <img src="https://bulma.io/images/placeholders/128x128.png" alt="bulma">
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
