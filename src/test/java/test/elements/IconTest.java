package test.elements;

import com.github.t1.bulmajava.basic.Basic;
import com.github.t1.bulmajava.basic.FontSize;
import com.github.t1.bulmajava.basic.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import test.RenderTestExtension;

import java.util.ArrayList;
import java.util.List;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.elements.Block.blockP;
import static com.github.t1.bulmajava.elements.Content.content_;
import static com.github.t1.bulmajava.elements.Icon.*;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class IconTest {
    @Test void shouldRenderIcon() {
        var icon = icon("home");

        then(icon).rendersAs("""
                <span class="icon"><i class="fas fa-home"></i></span>
                """);
    }

    @Test void shouldRenderIconText() {
        var iconText = iconText().contains(
                icon("home"), Basic.span("Home"));

        then(iconText).rendersAs("""
                <span class="icon-text">
                    <span class="icon"><i class="fas fa-home"></i></span>
                    <span>Home</span>
                </span>
                """);
    }

    @Test void shouldRenderManyIconTexts() {
        var iconText = iconText().contains(
                icon("train"), Basic.span("Paris"),
                icon("arrow-right"), Basic.span("Budapest"),
                icon("arrow-right"), Basic.span("Bucharest"),
                icon("arrow-right"), Basic.span("Istanbul"),
                icon("flag-checkered"));

        then(iconText).rendersAs("""
                <span class="icon-text">
                    <span class="icon"><i class="fas fa-train"></i></span>
                    <span>Paris</span>
                    <span class="icon"><i class="fas fa-arrow-right"></i></span>
                    <span>Budapest</span>
                    <span class="icon"><i class="fas fa-arrow-right"></i></span>
                    <span>Bucharest</span>
                    <span class="icon"><i class="fas fa-arrow-right"></i></span>
                    <span>Istanbul</span>
                    <span class="icon"><i class="fas fa-flag-checkered"></i></span>
                </span>
                """);
    }

    @Test void shouldRenderInlineIcons() {
        var content = content_().contains(
                Basic.p().contains(
                        string("An invitation to"),
                        iconText().contains(
                                icon("utensils"),
                                Basic.span("dinner")),
                        string("was soon afterwards dispatched.")));

        then(content).rendersAs("""
                <div class="content">
                    <p>
                        An invitation to
                        <span class="icon-text">
                            <span class="icon"><i class="fas fa-utensils"></i></span>
                            <span>dinner</span>
                        </span>
                        was soon afterwards dispatched.
                    </p>
                </div>
                """);
    }

    @Test void shouldRenderInlineIconsFlex() {
        var content = content_().contains(
                iconTextFlex().contains(
                        icon("info-circle").classes("has-text-info"),
                        Basic.span("Information")),
                blockP().contains(
                        string("Your package will be delivered on "),
                        Basic.strong("Tuesday at 08:00"),
                        string(".")),
                iconTextFlex().contains(
                        icon("check-square").classes("has-text-success"),
                        Basic.span("Success")),
                blockP().contains("Your image has been successfully uploaded."),
                iconTextFlex().contains(
                        icon("exclamation-triangle").classes("has-text-warning"),
                        Basic.span("Warning")),
                blockP().contains(string("Some information is missing from your"),
                        a("profile").href("#"),
                        string("details.")),
                iconTextFlex().contains(
                        icon("ban").classes("has-text-danger"),
                        Basic.span("Danger")),
                blockP().contains(string("There was an error in your submission."),
                        a("Please try again").href("#"),
                        string(".")));

        then(content).rendersAs("""
                <div class="content">
                    <div class="icon-text">
                        <span class="icon has-text-info"><i class="fas fa-info-circle"></i></span>
                        <span>Information</span>
                    </div>
                    <p class="block">Your package will be delivered on <strong>Tuesday at 08:00</strong>.</p>
                    <div class="icon-text">
                        <span class="icon has-text-success"><i class="fas fa-check-square"></i></span>
                        <span>Success</span>
                    </div>
                    <p class="block">Your image has been successfully uploaded.</p>
                    <div class="icon-text">
                        <span class="icon has-text-warning"><i class="fas fa-exclamation-triangle"></i></span>
                        <span>Warning</span>
                    </div>
                    <p class="block">
                        Some information is missing from your
                        <a href="#">profile</a>
                        details.
                    </p>
                    <div class="icon-text">
                        <span class="icon has-text-danger"><i class="fas fa-ban"></i></span>
                        <span>Danger</span>
                    </div>
                    <p class="block">
                        There was an error in your submission.
                        <a href="#">Please try again</a>
                        .
                    </p>
                </div>
                """);
    }

    @Test void shouldRenderIconColors() {
        var content = content_().contains(
                icon("info-circle").classes("has-text-info"),
                icon("check-square").classes("has-text-success"),
                icon("exclamation-triangle").classes("has-text-warning"),
                icon("ban").classes("has-text-danger"));

        then(content).rendersAs("""
                <div class="content">
                    <span class="icon has-text-info"><i class="fas fa-info-circle"></i></span>
                    <span class="icon has-text-success"><i class="fas fa-check-square"></i></span>
                    <span class="icon has-text-warning"><i class="fas fa-exclamation-triangle"></i></span>
                    <span class="icon has-text-danger"><i class="fas fa-ban"></i></span>
                </div>
                """);
    }

    @Test void shouldRenderIconTextColors() {
        var content = content_().contains(
                iconText().classes("has-text-info").contains(icon("info-circle"), Basic.span("Info")),
                iconText().classes("has-text-success").contains(icon("check-square"), Basic.span("Success")),
                iconText().classes("has-text-warning").contains(icon("exclamation-triangle"), Basic.span("Warning")),
                iconText().classes("has-text-danger").contains(icon("ban"), Basic.span("Danger")));

        then(content).rendersAs("""
                <div class="content">
                    <span class="icon-text has-text-info">
                        <span class="icon"><i class="fas fa-info-circle"></i></span>
                        <span>Info</span>
                    </span>
                    <span class="icon-text has-text-success">
                        <span class="icon"><i class="fas fa-check-square"></i></span>
                        <span>Success</span>
                    </span>
                    <span class="icon-text has-text-warning">
                        <span class="icon"><i class="fas fa-exclamation-triangle"></i></span>
                        <span>Warning</span>
                    </span>
                    <span class="icon-text has-text-danger">
                        <span class="icon"><i class="fas fa-ban"></i></span>
                        <span>Danger</span>
                    </span>
                </div>
                """);
    }

    @ParameterizedTest @MethodSource
    void shouldRenderIconSize(Size iconSize, FontSize fontSize) {
        var icon = icon("home", fontSize).classes("has-background-grey-lighter");
        if (iconSize != null) icon = icon.is(iconSize);

        then(icon).rendersAs("""
                <span class="icon has-background-grey-lighter$iconSize"><i class="fas fa-home$fontSize"></i></span>
                """
                .replace("$iconSize", (iconSize == null) ? "" : (" " + iconSize.className()))
                .replace("$fontSize", (fontSize == null) ? "" : (" fa-" + fontSize.code())));
    }

    static List<Arguments> shouldRenderIconSize() {
        var arguments = new ArrayList<Arguments>();
        for (var iconSize : Size.values())
            for (var fontSize : FontSize.values())
                arguments.add(Arguments.arguments(iconSize, fontSize));
        return arguments;
    }

    @Test void shouldRenderFixedWidthIcon() {
        var icon = icon("home", "fa-fw").classes("has-background-grey-lighter");

        then(icon).rendersAs("""
                <span class="icon has-background-grey-lighter"><i class="fas fa-home fa-fw"></i></span>
                """);
    }

    @Test void shouldRenderBorderIcon() {
        var icon = icon("home", "fa-border");

        then(icon).rendersAs("""
                <span class="icon"><i class="fas fa-home fa-border"></i></span>
                """);
    }

    @Test void shouldRenderSpinnerIcon() {
        var icon = icon("spinner", "fa-pulse").classes("has-background-grey-lighter");

        then(icon).rendersAs("""
                <span class="icon has-background-grey-lighter"><i class="fas fa-spinner fa-pulse"></i></span>
                """);
    }

    @Test void shouldRenderIconStack() {
        var icon = iconStack(
                icon("camera"),
                icon("ban", "has-text-danger")
        );

        then(icon).rendersAs("""
                <span class="icon is-large">
                    <span class="fa-stack fa-lg"><i class="fas fa-camera fa-stack-1x"></i><i class="fas fa-ban has-text-danger fa-stack-2x"></i></span>
                </span>
                """);
    }
}
