package test.elements;

import com.github.t1.bulmajava.elements.ImageRatio;
import com.github.t1.bulmajava.elements.ImageSize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Style.ROUNDED;
import static com.github.t1.bulmajava.elements.Image.image;
import static com.github.t1.bulmajava.elements.Image.movie;
import static com.github.t1.bulmajava.elements.ImageRatio._16by9;
import static com.github.t1.bulmajava.elements.ImageSize._128x128;
import static com.github.t1.htmljava.Basic.div;
import static test.CustomAssertions.then;
import static test.RenderTestExtension.placeholder;

@ExtendWith(RenderTestExtension.class)
class ImageTest {
    @ParameterizedTest @EnumSource void shouldRenderImage(ImageSize size) {
        var image = image(size, placeholder(size), "xxx");

        //noinspection HtmlUnknownTarget
        then(image).rendersAs("""
                <figure class="image is-$size">
                    <img src="https://bulma.io/assets/images/placeholders/$size.png" alt="xxx">
                </figure>
                """.replace("$size", size.key()));
    }

    @ParameterizedTest @EnumSource void shouldRenderRoundedImage(ImageSize size) {
        var image = image(size, placeholder(size), "xxx", ROUNDED);

        //noinspection HtmlUnknownTarget
        then(image).rendersAs("""
                <figure class="image is-$size">
                    <img class="is-rounded" src="https://bulma.io/assets/images/placeholders/$size.png" alt="xxx">
                </figure>
                """.replace("$size", size.key()));
    }

    @Test void shouldRenderRetinaImage() {
        var image = image(_128x128, placeholder("256x256"), "xyz");

        //noinspection HtmlUnknownTarget
        then(image).rendersAs("""
                <figure class="image is-128x128">
                    <img src="https://bulma.io/assets/images/placeholders/256x256.png" alt="xyz">
                </figure>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderImageRatios(ImageRatio imageRatio) {
        var fileName = fileNameFor(imageRatio);
        var image = div().style("width: 240px;").content(
                image(imageRatio, placeholder(fileName), "xxx"));

        //noinspection HtmlUnknownTarget
        then(image).rendersAs("""
                <div style="width: 240px;">
                    <figure class="image $size">
                        <img src="https://bulma.io/assets/images/placeholders/$file.png" alt="xxx">
                    </figure>
                </div>
                """.replace("$size", imageRatio.className()).replace("$file", fileName));
    }

    private String fileNameFor(ImageRatio imageRatio) {
        return switch (imageRatio) {
            case square, _1by1 -> "480x480";
            case _5by4 -> "600x480";
            case _4by3 -> "640x480";
            case _3by2 -> "480x320";
            case _5by3 -> "800x480";
            case _16by9 -> "640x360";
            case _2by1 -> "640x320";
            case _3by1 -> "720x240";
            case _4by5 -> "480x600";
            case _3by4 -> "480x640";
            case _2by3 -> "320x480";
            case _3by5 -> "480x800";
            case _9by16 -> "360x640";
            case _1by2 -> "320x640";
            case _1by3 -> "240x720";
        };
    }

    @Test void shouldRenderIFrameRatio() {
        var div = movie(_16by9, "https://www.youtube.com/embed/YE7VzlLtp-4", "640", "360");

        then(div).rendersAs("""
                <figure class="image is-16by9">
                    <iframe class="has-ratio" src="https://www.youtube.com/embed/YE7VzlLtp-4" width="640" height="360" allowfullscreen></iframe>
                </figure>
                """);
    }
}
