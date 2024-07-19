package test.elements;

import com.github.t1.bulmajava.basic.Color;
import com.github.t1.bulmajava.basic.Style;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Color.DANGER;
import static com.github.t1.bulmajava.basic.Color.INFO;
import static com.github.t1.bulmajava.basic.Color.LINK;
import static com.github.t1.bulmajava.basic.Color.PRIMARY;
import static com.github.t1.bulmajava.basic.Color.SUCCESS;
import static com.github.t1.bulmajava.basic.Color.WARNING;
import static com.github.t1.bulmajava.basic.Size.LARGE;
import static com.github.t1.bulmajava.basic.Size.MEDIUM;
import static com.github.t1.bulmajava.basic.Size.NORMAL;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.Style.DARK;
import static com.github.t1.bulmajava.basic.Style.LIGHT;
import static com.github.t1.bulmajava.basic.Style.ROUNDED;
import static com.github.t1.bulmajava.elements.Block.block;
import static com.github.t1.bulmajava.elements.Delete.DELETE;
import static com.github.t1.bulmajava.elements.Delete.delete;
import static com.github.t1.bulmajava.elements.Tag.tag;
import static com.github.t1.bulmajava.elements.Tag.tagA;
import static com.github.t1.bulmajava.elements.Tag.tags;
import static com.github.t1.bulmajava.elements.Tag.tagsAddon;
import static com.github.t1.htmljava.Basic.div;
import static com.github.t1.htmljava.Basic.multilineGroup;
import static com.github.t1.htmljava.Renderable.RenderableString.string;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class TagTest {
    @Test void shouldRenderTag() {
        var tag = tag("Tag label");

        then(tag).rendersAs("""
                <span class="tag">Tag label</span>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderColorTag(Color color) {
        var tag = tag(color.key()).is(color);

        then(tag).rendersAs("""
                <span class="tag $colorClass">$colorName</span>
                """
                .replace("$colorClass", color.className())
                .replace("$colorName", color.key()));
    }

    @ParameterizedTest @EnumSource(names = {"BLACK", "WHITE", "DARK", "LIGHT"})
    void shouldRenderStyleTag(Style style) {
        var tag = tag(style.key()).is(style);

        then(tag).rendersAs("""
                <span class="tag $styleClass">$styleName</span>
                """
                .replace("$styleClass", style.className())
                .replace("$styleName", style.key()));
    }

    @ParameterizedTest @EnumSource
    void shouldRenderLightColorTag(Color color) {
        var tag = tag(color.key()).is(color, LIGHT);

        then(tag).rendersAs("""
                <span class="tag $colorClass is-light">$colorName</span>
                """
                .replace("$colorClass", color.className())
                .replace("$colorName", color.key()));
    }

    @Test void shouldRenderSizeTag() {
        var tag = div().content(
                tag("Normal").is(LINK, NORMAL),
                tag("Medium").is(PRIMARY, MEDIUM),
                tag("Large").is(INFO, LARGE));

        then(tag).rendersAs("""
                <div>
                    <span class="tag is-link is-normal">Normal</span>
                    <span class="tag is-primary is-medium">Medium</span>
                    <span class="tag is-info is-large">Large</span>
                </div>
                """);
    }

    @Test void shouldRenderMediumTags() {
        var tag = tags().classes("are-medium").content(
                tag("All"),
                tag("Medium"),
                tag("Size"));

        then(tag).rendersAs("""
                <div class="tags are-medium">
                    <span class="tag">All</span>
                    <span class="tag">Medium</span>
                    <span class="tag">Size</span>
                </div>
                """);
    }

    @Test void shouldRenderLargeTags() {
        var tag = tags().classes("are-large").content(
                tag("All"),
                tag("Medium"),
                tag("Size"));

        then(tag).rendersAs("""
                <div class="tags are-large">
                    <span class="tag">All</span>
                    <span class="tag">Medium</span>
                    <span class="tag">Size</span>
                </div>
                """);
    }

    @Test void shouldRenderRoundedTag() {
        var tag = tag("Rounded").is(ROUNDED, WARNING);

        then(tag).rendersAs("""
                <span class="tag is-rounded is-warning">Rounded</span>
                """);
    }

    @Test void shouldRenderDeleteTagAnchor() {
        var tag = tagA().is(DELETE);

        then(tag).rendersAs("""
                <a class="tag is-delete"></a>
                """);
    }

    @Test void shouldRenderDeleteTagBlock() {
        var tag = block().content(
                tag().is(SUCCESS).content(
                        string("Hello World"),
                        delete().is(SMALL)));

        then(tag).rendersAs("""
                <div class="block">
                    <span class="tag is-success">
                        Hello World
                        <button class="delete is-small" aria-label="delete"></button>
                    </span>
                </div>
                """);
    }

    @Test void shouldRenderTagCombinations() {
        var tags = tags().content(
                tag("Bar").is(SUCCESS).content(delete().is(SMALL)),
                tag("Hello").is(WARNING, MEDIUM).content(delete().is(SMALL)),
                tag("World").is(DANGER, LARGE).content(delete()));

        then(tags).rendersAs("""
                <div class="tags">
                    <span class="tag is-success">
                        Bar
                        <button class="delete is-small" aria-label="delete"></button>
                    </span>
                    <span class="tag is-warning is-medium">
                        Hello
                        <button class="delete is-small" aria-label="delete"></button>
                    </span>
                    <span class="tag is-danger is-large">
                        World
                        <button class="delete" aria-label="delete"></button>
                    </span>
                </div>
                """);
    }

    @Test void shouldRenderTagsAddon() {
        var tags = tagsAddon().content(
                tag("Package"),
                tag("Bulma").is(PRIMARY));

        then(tags).rendersAs("""
                <div class="tags has-addons">
                    <span class="tag">Package</span>
                    <span class="tag is-primary">Bulma</span>
                </div>
                """);
    }

    @Test void shouldRenderTagDeleteAddon() {
        var tags = tagsAddon().content(
                tag("Alex Smith").is(DANGER),
                tagA().is(DELETE));

        then(tags).rendersAs("""
                <div class="tags has-addons">
                    <span class="tag is-danger">Alex Smith</span>
                    <a class="tag is-delete"></a>
                </div>
                """);
    }

    @Test void shouldRenderMultilineAddonTags() {
        var tags = multilineGroup().content(
                tagsAddon().content(tag("npm").is(DARK), tag("0.9.4").is(INFO)),
                tagsAddon().content(tag("build").is(DARK), tag("passing").is(SUCCESS)),
                tagsAddon().content(tag("chat").is(DARK), tag("on gitter").is(PRIMARY))
        ).style("width: 240px;").classes("has-background-grey-lighter");

        then(tags).rendersAs("""
                <div class="field is-grouped is-grouped-multiline has-background-grey-lighter" style="width: 240px;">
                    <div class="control">
                        <div class="tags has-addons">
                            <span class="tag is-dark">npm</span>
                            <span class="tag is-info">0.9.4</span>
                        </div>
                    </div>
                    <div class="control">
                        <div class="tags has-addons">
                            <span class="tag is-dark">build</span>
                            <span class="tag is-success">passing</span>
                        </div>
                    </div>
                    <div class="control">
                        <div class="tags has-addons">
                            <span class="tag is-dark">chat</span>
                            <span class="tag is-primary">on gitter</span>
                        </div>
                    </div>
                </div>
                """);
    }
}
