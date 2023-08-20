package test.layout;

import com.github.t1.bulmajava.basic.Basic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Color.PRIMARY;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.columns.ScreenSize.FULLHD;
import static com.github.t1.bulmajava.columns.ScreenSize.WIDESCREEN;
import static com.github.t1.bulmajava.elements.Notification.notification;
import static com.github.t1.bulmajava.layout.Container.container;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class ContainerTest {
    @Test void shouldRenderContainer() {
        var container = container().contains(
                notification().is(PRIMARY).contains(
                        string("This container is "), Basic.strong("centered"), string(" on desktop and larger viewports.")));

        then(container).rendersAs("""
                <div class="container">
                    <div class="notification is-primary">This container is <strong>centered</strong> on desktop and larger viewports.</div>
                </div>
                """);
    }

    @Test void shouldRenderWidescreenContainer() {
        var container = container().is(WIDESCREEN).contains(
                notification().is(PRIMARY).contains(
                        string("This container is "), Basic.strong("fullwidth"), string(" "),
                        Basic.em("until"), string(" the "), Basic.code("$widescreen"), string(" breakpoint.")));

        then(container).rendersAs("""
                <div class="container is-widescreen">
                    <div class="notification is-primary">This container is <strong>fullwidth</strong> <em>until</em> the <code>$widescreen</code> breakpoint.</div>
                </div>
                """);
    }

    @Test void shouldRenderFullhdContainer() {
        var container = container().is(FULLHD).contains(
                notification().is(PRIMARY).contains(
                        string("This container is "), Basic.strong("fullwidth"), string(" "),
                        Basic.em("until"), string(" the "), Basic.code("$fullhd"), string(" breakpoint.")));

        then(container).rendersAs("""
                <div class="container is-fullhd">
                    <div class="notification is-primary">This container is <strong>fullwidth</strong> <em>until</em> the <code>$fullhd</code> breakpoint.</div>
                </div>
                """);
    }

    @Test void shouldRenderMaxDesktopContainer() {
        var container = container().isMaxDesktop().contains(
                notification().is(PRIMARY).contains(
                        string("This container has a "), Basic.code("max-width"), string(" of "),
                        Basic.code("$desktop - $container-offset"), string(" on widescreen and fullhd.")));

        then(container).rendersAs("""
                <div class="container is-max-desktop">
                    <div class="notification is-primary">This container has a <code>max-width</code> of <code>$desktop - $container-offset</code> on widescreen and fullhd.</div>
                </div>
                """);
    }

    @Test void shouldRenderMaxWidescreenContainer() {
        var container = container().isMaxWidescreen().contains(
                notification().is(PRIMARY).contains(
                        string("This container has a "), Basic.code("max-width"), string(" of "),
                        Basic.code("$widescreen - $container-offset"), string(" on fullhd.")));

        then(container).rendersAs("""
                <div class="container is-max-widescreen">
                    <div class="notification is-primary">This container has a <code>max-width</code> of <code>$widescreen - $container-offset</code> on fullhd.</div>
                </div>
                """);
    }

    @Test void shouldRenderFluidContainer() {
        var container = container().isFluid().contains(
                notification().is(PRIMARY).contains(
                        string("This container is "), Basic.strong("fluid"),
                        string(": it will have a 32px gap on either side, on any viewport size.")));

        then(container).rendersAs("""
                <div class="container is-fluid">
                    <div class="notification is-primary">This container is <strong>fluid</strong>: it will have a 32px gap on either side, on any viewport size.</div>
                </div>
                """);
    }
}
