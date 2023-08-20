package test.layout;

import com.github.t1.bulmajava.basic.Basic;
import com.github.t1.bulmajava.elements.Box;
import com.github.t1.bulmajava.elements.Content;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Color.*;
import static com.github.t1.bulmajava.basic.Style.FULLWIDTH;
import static com.github.t1.bulmajava.elements.Box.articleBox;
import static com.github.t1.bulmajava.elements.Box.box;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Content.content_;
import static com.github.t1.bulmajava.elements.Image.image;
import static com.github.t1.bulmajava.elements.ImageRatio._4by3;
import static com.github.t1.bulmajava.elements.Title.subtitleP;
import static com.github.t1.bulmajava.elements.Title.titleP;
import static com.github.t1.bulmajava.layout.Tile.*;
import static test.CustomAssertions.then;
import static test.RenderTestExtension.loremIpsum;
import static test.RenderTestExtension.loremIpsumS;

@ExtendWith(RenderTestExtension.class)
class TileTest {
    @Test void shouldRenderMagicalTile() {
        var tile = tile().contains(Basic.comment("The magical tile element!"), Basic.br());

        then(tile).rendersAs("""
                <div class="tile">
                    <!-- The magical tile element! -->
                    <br>
                </div>
                """);
    }

    @Test void shouldRenderTile() {
        var tile = ancestorTile().style("width: 950px;").contains(
                tile().vertical().is(8).contains(
                        tile().contains(
                                parentTile().vertical().containsChild(
                                        Basic.article().classes("notification").is(PRIMARY).contains(
                                                titleP("Vertical..."),
                                                subtitleP("Top tile"))).containsChild(
                                        Basic.article().classes("notification").is(WARNING).contains(
                                                titleP("...tiles"),
                                                subtitleP("Bottom tile"))),
                                parentTile().containsChild(
                                        Basic.article().classes("notification").is(INFO).contains(
                                                titleP("Middle tile"),
                                                subtitleP("With an image"),
                                                image(_4by3, "https://bulma.io/images/placeholders/640x480.png", "bulma")))),
                        parentTile().containsChild(
                                Basic.article().classes("notification").is(DANGER).contains(
                                        titleP("Wide tile"),
                                        subtitleP("Aligned with the right tile"),
                                        content_().contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ornare magna eros, eu pellentesque tortor vestibulum ut. Maecenas non massa sem. Etiam finibus odio quis feugiat facilisis.")))),
                parentTile().containsChild(
                        Basic.article().classes("notification").is(SUCCESS).contains(
                                content_().contains(
                                        titleP("Tall tile"),
                                        subtitleP("With even more content"),
                                        content_().contains(loremIpsumS(), Basic.br(), loremIpsumS())))));

        // the img-alt tags where missing
        then(tile).rendersAs("""
                <div class="tile is-ancestor" style="width: 950px;">
                    <div class="tile is-vertical is-8">
                        <div class="tile">
                            <div class="tile is-parent is-vertical">
                                <article class="notification is-primary tile is-child">
                                    <p class="title">Vertical...</p>
                                    <p class="subtitle">Top tile</p>
                                </article>
                                <article class="notification is-warning tile is-child">
                                    <p class="title">...tiles</p>
                                    <p class="subtitle">Bottom tile</p>
                                </article>
                            </div>
                            <div class="tile is-parent">
                                <article class="notification is-info tile is-child">
                                    <p class="title">Middle tile</p>
                                    <p class="subtitle">With an image</p>
                                    <figure class="image is-4by3">
                                        <img src="https://bulma.io/images/placeholders/640x480.png" alt="bulma">
                                    </figure>
                                </article>
                            </div>
                        </div>
                        <div class="tile is-parent">
                            <article class="notification is-danger tile is-child">
                                <p class="title">Wide tile</p>
                                <p class="subtitle">Aligned with the right tile</p>
                                <div class="content">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ornare magna eros, eu pellentesque tortor vestibulum ut. Maecenas non massa sem. Etiam finibus odio quis feugiat facilisis.</div>
                            </article>
                        </div>
                    </div>
                    <div class="tile is-parent">
                        <article class="notification is-success tile is-child">
                            <div class="content">
                                <p class="title">Tall tile</p>
                                <p class="subtitle">With even more content</p>
                                <div class="content">
                                    $loremIpsum
                                    <br>
                                    $loremIpsum
                                </div>
                            </div>
                        </article>
                    </div>
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }

    @Test void shouldRenderAncestorTile() {
        var tile = ancestorTile().style("width: 950px;").contains(
                tile().contains(button("Content 1").is(LINK, FULLWIDTH)),
                tile().contains(button("Content 2").is(PRIMARY, FULLWIDTH)));

        then(tile).rendersAs("""
                <div class="tile is-ancestor" style="width: 950px;">
                    <div class="tile">
                        <button class="button is-link is-fullwidth">Content 1</button>
                    </div>
                    <div class="tile">
                        <button class="button is-primary is-fullwidth">Content 2</button>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderSizedAncestorTile() {
        var tile = ancestorTile().style("width: 950px;").contains(
                tile().is(4).contains(button("Content 1").is(LINK, FULLWIDTH)),
                tile().contains(button("Content 2").is(PRIMARY, FULLWIDTH)));

        then(tile).rendersAs("""
                <div class="tile is-ancestor" style="width: 950px;">
                    <div class="tile is-4">
                        <button class="button is-link is-fullwidth">Content 1</button>
                    </div>
                    <div class="tile">
                        <button class="button is-primary is-fullwidth">Content 2</button>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderVerticalAncestorTile() {
        var tile = ancestorTile().style("width: 950px;").contains(
                tile().is(4).vertical().contains(
                        tile().contains(button("Content Top").is(LINK, FULLWIDTH)),
                        tile().contains(button("Content Bottom").is(PRIMARY, FULLWIDTH))),
                tile().contains(
                        box(loremIpsum())));

        then(tile).rendersAs("""
                <div class="tile is-ancestor" style="width: 950px;">
                    <div class="tile is-4 is-vertical">
                        <div class="tile">
                            <button class="button is-link is-fullwidth">Content Top</button>
                        </div>
                        <div class="tile">
                            <button class="button is-primary is-fullwidth">Content Bottom</button>
                        </div>
                    </div>
                    <div class="tile">
                        <div class="box">$loremIpsum</div>
                    </div>
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }

    @Test void shouldRenderContentTile() {
        var tile = ancestorTile().style("width: 950px;").contains(
                parentTile().is(4).vertical()
                        .containsChild(box().contains(
                                titleP("One"),
                                loremIpsumS()))
                        .containsChild(box().contains(
                                titleP("Two"),
                                loremIpsumS())),
                parentTile()
                        .containsChild(box().contains(
                                titleP("Three"),
                                loremIpsumS())));

        then(tile).rendersAs("""
                <div class="tile is-ancestor" style="width: 950px;">
                    <div class="tile is-parent is-4 is-vertical">
                        <div class="box tile is-child">
                            <p class="title">One</p>
                            $loremIpsum
                        </div>
                        <div class="box tile is-child">
                            <p class="title">Two</p>
                            $loremIpsum
                        </div>
                    </div>
                    <div class="tile is-parent">
                        <div class="box tile is-child">
                            <p class="title">Three</p>
                            $loremIpsum
                        </div>
                    </div>
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }

    @Test void shouldRenderFourColumnsTiles() {
        var div = Basic.div().style("width: 950px;").contains(
                ancestorTile().contains(
                        parentTile().containsChild(aBox("One")),
                        parentTile().containsChild(aBox("Two")),
                        parentTile().containsChild(aBox("Three")),
                        parentTile().containsChild(aBox("Four"))),
                ancestorTile().contains(
                        tile().vertical().is(9).contains(
                                tile().contains(
                                        parentTile().containsChild(aBox("Five").contains(loremIpsumP())),
                                        tile().is(8).vertical().contains(
                                                tile().contains(
                                                        parentTile().containsChild(aBox("Six")),
                                                        parentTile().containsChild(aBox("Seven"))),
                                                parentTile().containsChild(aBox("Eight")))
                                ),
                                tile().contains(
                                        parentTile().is(8).containsChild(aBox("Nine").contains(loremIpsumP())),
                                        parentTile().containsChild(aBox("Ten").contains(loremIpsumP())))
                        ),
                        parentTile().containsChild(articleBox().contains(content_().contains(
                                titleP("Eleven"), subtitleP("Subtitle")).contains(
                                content_().contains(
                                        Basic.p(loremIpsum()), Basic.p(loremIpsum())))))),
                ancestorTile().contains(
                        parentTile().containsChild(aBox("Twelve").contains(loremIpsumP())),
                        parentTile().is(6).containsChild(aBox("Thirteen").contains(loremIpsumP())),
                        parentTile().containsChild(aBox("Fourteen").contains(loremIpsumP()))));

        then(div).rendersAs("""
                <div style="width: 950px;">
                    <div class="tile is-ancestor">
                        <div class="tile is-parent">
                            <article class="box tile is-child">
                                <p class="title">One</p>
                                <p class="subtitle">Subtitle</p>
                            </article>
                        </div>
                        <div class="tile is-parent">
                            <article class="box tile is-child">
                                <p class="title">Two</p>
                                <p class="subtitle">Subtitle</p>
                            </article>
                        </div>
                        <div class="tile is-parent">
                            <article class="box tile is-child">
                                <p class="title">Three</p>
                                <p class="subtitle">Subtitle</p>
                            </article>
                        </div>
                        <div class="tile is-parent">
                            <article class="box tile is-child">
                                <p class="title">Four</p>
                                <p class="subtitle">Subtitle</p>
                            </article>
                        </div>
                    </div>
                    <div class="tile is-ancestor">
                        <div class="tile is-vertical is-9">
                            <div class="tile">
                                <div class="tile is-parent">
                                    <article class="box tile is-child">
                                        <p class="title">Five</p>
                                        <p class="subtitle">Subtitle</p>
                                        <div class="content">
                                            <p>$loremIpsum</p>
                                        </div>
                                    </article>
                                </div>
                                <div class="tile is-8 is-vertical">
                                    <div class="tile">
                                        <div class="tile is-parent">
                                            <article class="box tile is-child">
                                                <p class="title">Six</p>
                                                <p class="subtitle">Subtitle</p>
                                            </article>
                                        </div>
                                        <div class="tile is-parent">
                                            <article class="box tile is-child">
                                                <p class="title">Seven</p>
                                                <p class="subtitle">Subtitle</p>
                                            </article>
                                        </div>
                                    </div>
                                    <div class="tile is-parent">
                                        <article class="box tile is-child">
                                            <p class="title">Eight</p>
                                            <p class="subtitle">Subtitle</p>
                                        </article>
                                    </div>
                                </div>
                            </div>
                            <div class="tile">
                                <div class="tile is-parent is-8">
                                    <article class="box tile is-child">
                                        <p class="title">Nine</p>
                                        <p class="subtitle">Subtitle</p>
                                        <div class="content">
                                            <p>$loremIpsum</p>
                                        </div>
                                    </article>
                                </div>
                                <div class="tile is-parent">
                                    <article class="box tile is-child">
                                        <p class="title">Ten</p>
                                        <p class="subtitle">Subtitle</p>
                                        <div class="content">
                                            <p>$loremIpsum</p>
                                        </div>
                                    </article>
                                </div>
                            </div>
                        </div>
                        <div class="tile is-parent">
                            <article class="box tile is-child">
                                <div class="content">
                                    <p class="title">Eleven</p>
                                    <p class="subtitle">Subtitle</p>
                                    <div class="content">
                                        <p>$loremIpsum</p>
                                        <p>$loremIpsum</p>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </div>
                    <div class="tile is-ancestor">
                        <div class="tile is-parent">
                            <article class="box tile is-child">
                                <p class="title">Twelve</p>
                                <p class="subtitle">Subtitle</p>
                                <div class="content">
                                    <p>$loremIpsum</p>
                                </div>
                            </article>
                        </div>
                        <div class="tile is-parent is-6">
                            <article class="box tile is-child">
                                <p class="title">Thirteen</p>
                                <p class="subtitle">Subtitle</p>
                                <div class="content">
                                    <p>$loremIpsum</p>
                                </div>
                            </article>
                        </div>
                        <div class="tile is-parent">
                            <article class="box tile is-child">
                                <p class="title">Fourteen</p>
                                <p class="subtitle">Subtitle</p>
                                <div class="content">
                                    <p>$loremIpsum</p>
                                </div>
                            </article>
                        </div>
                    </div>
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }

    private static Content loremIpsumP() {
        return content_().contains(Basic.p(loremIpsum()));
    }

    private static Box aBox(String title) {
        return articleBox().contains(
                titleP(title), subtitleP("Subtitle"));
    }
}
