package test.elements;

import com.github.t1.bulmajava.basic.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.elements.Delete.delete;
import static com.github.t1.bulmajava.elements.Notification.notification;
import static com.github.t1.bulmajava.basic.Style.LIGHT;
import static test.CustomAssertions.then;
import static test.RenderTestExtension.loremIpsum;
import static test.RenderTestExtension.loremIpsumS;

@ExtendWith(RenderTestExtension.class)
class NotificationTest {
    @Test void shouldRenderNotification() {
        var notification = notification().contains(
                delete(),
                loremIpsumS()
        );

        then(notification).rendersAs("""
                <div class="notification">
                    <button class="delete" aria-label="delete"></button>
                    $loremIpsum
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }

    @ParameterizedTest @EnumSource void shouldRenderColorNotification(Color color) {
        var notification = notification().is(color).contains(
                delete(),
                loremIpsumS()
        );

        then(notification).rendersAs("""
                <div class="notification is-$color">
                    <button class="delete" aria-label="delete"></button>
                    $loremIpsum
                </div>
                """
                .replace("$color", color.key())
                .replace("$loremIpsum", loremIpsum()));
    }

    @ParameterizedTest @EnumSource void shouldRenderLightColorNotification(Color color) {
        var notification = notification().is(color).is(LIGHT).contains(
                delete(),
                loremIpsumS()
        );

        then(notification).rendersAs("""
                <div class="notification is-$color is-light">
                    <button class="delete" aria-label="delete"></button>
                    $loremIpsum
                </div>
                """
                .replace("$color", color.key())
                .replace("$loremIpsum", loremIpsum()));
    }
}
