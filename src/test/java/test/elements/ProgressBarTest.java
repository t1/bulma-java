package test.elements;

import com.github.t1.bulmajava.basic.Color;
import com.github.t1.bulmajava.basic.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.elements.ProgressBar.progress;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class ProgressBarTest {
    @Test void shouldRenderProgressBar() {
        var progress = progress(15, 100).content("15%");

        then(progress).rendersAs("""
                <progress class="progress" value="15" max="100">15%</progress>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderColorProgress(Color color) {
        var value = (color.ordinal() + 1) * 15;
        var notification = progress(value, 100).is(color).content(value + "%");

        then(notification).rendersAs("""
                <progress class="progress is-$color" value="$value" max="100">$value%</progress>
                """
                .replace("$color", color.key())
                .replace("$value", Integer.toString(value)));
    }

    @ParameterizedTest @EnumSource void shouldSizedColorProgress(Size size) {
        var value = (size.ordinal() + 1) * 20;
        var notification = progress(value, 100).is(size).content(value + "%");

        then(notification).rendersAs("""
                <progress class="progress is-$size" value="$value" max="100">$value%</progress>
                """
                .replace("$size", size.key())
                .replace("$value", Integer.toString(value)));
    }

    @ParameterizedTest @EnumSource void shouldRenderIndeterminateColorProgress(Color color) {
        var notification = progress(null, 100).is(color).content("some value");

        then(notification).rendersAs("""
                <progress class="progress is-$color" max="100">some value</progress>
                """
                .replace("$color", color.key()));
    }
}
