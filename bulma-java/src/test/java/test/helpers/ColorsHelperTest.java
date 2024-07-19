package test.helpers;

import com.github.t1.bulmajava.basic.Color;
import com.github.t1.bulmajava.basic.Style;
import com.github.t1.htmljava.Modifier;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.elements.Notification.notification;
import static com.github.t1.bulmajava.helpers.ColorsHelper.dark;
import static com.github.t1.bulmajava.helpers.ColorsHelper.light;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class ColorsHelperTest {
    @ParameterizedTest @EnumSource void shouldRenderTextColor(Color color) {shouldRenderTextColorX(color.key());}

    @ParameterizedTest @ValueSource(strings = {"WHITE", "BLACK", "LIGHT", "DARK"})
    void shouldRenderTextColor(Style color) {shouldRenderTextColorX(color.key());}

    @ParameterizedTest @ValueSource(strings = {"black-bis", "black-ter", "grey-darker", "grey-dark",
            "grey", "grey-light", "grey-lighter", "white-ter", "white-bis"})
    void shouldRenderTextColor(String color) {shouldRenderTextColorX(color);}

    @ParameterizedTest @EnumSource
    void shouldRenderLightTextColor(Color color) {shouldRenderTextColorX(light(color).key());}

    @ParameterizedTest @EnumSource
    void shouldRenderDarkTextColor(Color color) {shouldRenderTextColorX(dark(color).key());}

    private static void shouldRenderTextColorX(String color) {
        var contrast = contrast(() -> color);
        var text = notification().hasBackground(() -> contrast).hasText(() -> color).content("Text");

        then(text).rendersAs("""
                <div class="notification has-background-$contrast has-text-$color">Text</div>
                """
                .replace("$color", color)
                .replace("$contrast", contrast));
    }


    @ParameterizedTest @EnumSource void shouldRenderBgColor(Color color) {shouldRenderBgColorX(color.key());}

    @ParameterizedTest @ValueSource(strings = {"WHITE", "BLACK", "LIGHT", "DARK"})
    void shouldRenderBgColor(Style color) {shouldRenderBgColorX(color.key());}

    @ParameterizedTest @ValueSource(strings = {"black-bis", "black-ter", "grey-darker", "grey-dark",
            "grey", "grey-light", "grey-lighter", "white-ter", "white-bis"})
    void shouldRenderBgColor(String color) {shouldRenderBgColorX(color);}

    @ParameterizedTest @EnumSource
    void shouldRenderLightBgColor(Color color) {shouldRenderBgColorX(color.key() + "-light");}

    @ParameterizedTest @EnumSource
    void shouldRenderDarkBgColor(Color color) {shouldRenderBgColorX(color.key() + "-dark");}

    private static void shouldRenderBgColorX(String color) {
        var contrast = contrast(() -> color);
        var text = notification().hasText(() -> contrast).hasBackground(() -> color).content("Text");

        then(text).rendersAs("""
                <div class="notification has-text-$contrast has-background-$color">Text</div>
                """
                .replace("$color", color)
                .replace("$contrast", contrast));
    }


    private static String contrast(Modifier color) {
        String colorName = color.key();
        return colorName.endsWith("light") ? "grey" : colorName.startsWith("white") ? "dark" : "light";
    }
}
