package test.elements;

import com.github.t1.bulmajava.basic.Alignment;
import com.github.t1.bulmajava.basic.Color;
import com.github.t1.bulmajava.basic.FontSize;
import com.github.t1.bulmajava.basic.Size;
import com.github.t1.bulmajava.basic.State;
import com.github.t1.bulmajava.basic.Style;
import com.github.t1.bulmajava.elements.Button;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Color.DANGER;
import static com.github.t1.bulmajava.basic.Color.INFO;
import static com.github.t1.bulmajava.basic.Color.LINK;
import static com.github.t1.bulmajava.basic.Color.SUCCESS;
import static com.github.t1.bulmajava.basic.Size.LARGE;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.State.SELECTED;
import static com.github.t1.bulmajava.basic.Style.LIGHT;
import static com.github.t1.bulmajava.basic.Style.ROUNDED;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Button.buttons;
import static com.github.t1.bulmajava.elements.Button.buttonsAddon;
import static com.github.t1.bulmajava.elements.Button.fieldsAddon;
import static com.github.t1.bulmajava.elements.ButtonType.BUTTON;
import static com.github.t1.bulmajava.elements.ButtonType.RESET;
import static com.github.t1.bulmajava.elements.ButtonType.SUBMIT;
import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.htmljava.Basic.control;
import static com.github.t1.htmljava.Basic.group;
import static com.github.t1.htmljava.Basic.span;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class ButtonTest {
    @Test void shouldRenderButton() {
        var button = button("Button");

        then(button).rendersAs("""
                <button class="button">Button</button>
                """);
    }

    @Test void shouldRenderButtonWithStringContent() {
        var button = button().content("Button");

        then(button).rendersAs("""
                <button class="button">Button</button>
                """);
    }

    @Test void shouldRenderButtonWithDivContent() {
        var button = button().content(span().content("foo"));

        then(button).rendersAs("""
                <button class="button">
                    <span>foo</span>
                </button>
                """);
    }

    @Test void shouldRenderButtonWithCustomClass() {
        var button = button("Button").classes("custom");

        then(button).rendersAs("""
                <button class="button custom">Button</button>
                """);
    }

    @Test void shouldRenderAnchorButton() {
        var button = a().classes("button").content("Anchor");

        then(button).rendersAs("""
                <a class="button">Anchor</a>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderStyleButton(Style style) {
        var button = button(style.name()).is(style);

        then(button).rendersAs("<button class=\"button is-" + style.key() + "\">" + style.name() + "</button>\n");
    }

    @ParameterizedTest @EnumSource void shouldRenderColorButton(Color color) {
        var button = button(color.name()).is(color);

        then(button).rendersAs("<button class=\"button is-" + color.key() + "\">" + color.name() + "</button>\n");
    }

    @ParameterizedTest @EnumSource void shouldRenderSizeButton(Size size) {
        var button = button(size.name()).is(size);

        then(button).rendersAs("<button class=\"button is-" + size.key() + "\">" + size.name() + "</button>\n");
    }

    @ParameterizedTest @EnumSource
    void shouldRenderLightColorButton(Color color) {
        Button button1 = button(color.name()).is(color);
        var button = button1.is(LIGHT);

        then(button).rendersAs("<button class=\"button is-" + color.key() + " is-light\">" + color.name() + "</button>\n");
    }

    @Test void shouldRenderSmallButtons() {
        var buttons = buttons()
                .classes("are-small").content(
                        button("One"),
                        button("Two"));

        then(buttons).rendersAs("""
                <div class="buttons are-small">
                    <button class="button">One</button>
                    <button class="button">Two</button>
                </div>
                """);
    }

    @Test void shouldRenderLargeResponsiveButton() {
        var button = button("Button").is(LARGE).responsive();

        then(button).rendersAs("<button class=\"button is-large is-responsive\">Button</button>\n");
    }

    @ParameterizedTest @EnumSource void shouldRenderStateButton(State state) {
        var button = button(state.name()).is(state);

        then(button).rendersAs("<button class=\"button is-" + state.key() + "\">" + state.name() + "</button>\n");
    }

    @ParameterizedTest @EnumSource void shouldRenderDisabledColorButton(Color color) {
        var button = button("Disabled " + color.name()).disabled().is(color);

        then(button).rendersAs("<button class=\"button is-" + color.key() + "\" disabled>Disabled " + color.name() + "</button>\n");
    }

    @Test void shouldRenderIconOnlyButton() {
        var button = button().icon("bold");

        then(button).rendersAs("""
                <button class="button">
                    <span class="icon"><i class="fas fa-bold"></i></span>
                </button>
                """);
    }

    @Test void shouldRenderIconAndTextButton() {
        var button = button().content(span("Delete")).is(DANGER, ROUNDED).icon("times");

        then(button).rendersAs("""
                <button class="button is-danger is-rounded">
                    <span>Delete</span>
                    <span class="icon"><i class="fas fa-times"></i></span>
                </button>
                """);
    }

    @Test void shouldRenderIconAndTextAddonButton() {
        var button = buttonsAddon().content(
                button().content(span("Print")).is(SMALL, ROUNDED),
                button().icon("print").is(SMALL, ROUNDED));

        then(button).rendersAs("""
                <div class="buttons has-addons">
                    <button class="button is-small is-rounded">
                        <span>Print</span>
                    </button>
                    <button class="button is-small is-rounded">
                        <span class="icon"><i class="fas fa-print"></i></span>
                    </button>
                </div>
                """);
    }

    @Test void shouldRenderWideButtonsAddon() {
        var button = buttonsAddon().content(
                button().content(span("Print One")),
                button().content(span("Print Two")),
                button().content(span("Print Three")),
                button().content(span("Print Four")),
                button().content(span("Print Five")),
                button().content(span("Print Six")),
                button().content(span("Print Seven")),
                button().content(span("Print Eight")),
                button().content(span("Print Nine")),
                button().content(span("Print Ten")),
                button().icon("print"));

        then(button).rendersAs("""
                <div class="buttons has-addons">
                    <button class="button">
                        <span>Print One</span>
                    </button>
                    <button class="button">
                        <span>Print Two</span>
                    </button>
                    <button class="button">
                        <span>Print Three</span>
                    </button>
                    <button class="button">
                        <span>Print Four</span>
                    </button>
                    <button class="button">
                        <span>Print Five</span>
                    </button>
                    <button class="button">
                        <span>Print Six</span>
                    </button>
                    <button class="button">
                        <span>Print Seven</span>
                    </button>
                    <button class="button">
                        <span>Print Eight</span>
                    </button>
                    <button class="button">
                        <span>Print Nine</span>
                    </button>
                    <button class="button">
                        <span>Print Ten</span>
                    </button>
                    <button class="button">
                        <span class="icon"><i class="fas fa-print"></i></span>
                    </button>
                </div>
                    """);
    }

    @Test void shouldRenderLargeIconOnlyButton() {
        var button = button().is(LARGE).icon("heading", FontSize.x2);

        then(button).rendersAs("""
                <button class="button is-large">
                    <span class="icon"><i class="fas fa-heading fa-2x"></i></span>
                </button>
                """);
    }

    @Test void shouldRenderButtonGroup() {
        var button = group().content(
                control().content(button("Save changes").is(LINK)),
                control().content(button("Cancel")),
                control().content(button("Delete post").is(DANGER)));

        then(button).rendersAs("""
                <div class="field is-grouped">
                    <div class="control">
                        <button class="button is-link">Save changes</button>
                    </div>
                    <div class="control">
                        <button class="button">Cancel</button>
                    </div>
                    <div class="control">
                        <button class="button is-danger">Delete post</button>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldAddons() {
        var buttons = fieldsAddon().content(
                button().icon("align-left").content(span("Left")),
                button().icon("align-center").content(span("Center")),
                button().icon("align-right").content(span("Right")));

        then(buttons).rendersAs("""
                <div class="field has-addons">
                    <div class="control">
                        <button class="button">
                            <span class="icon"><i class="fas fa-align-left"></i></span>
                            <span>Left</span>
                        </button>
                    </div>
                    <div class="control">
                        <button class="button">
                            <span class="icon"><i class="fas fa-align-center"></i></span>
                            <span>Center</span>
                        </button>
                    </div>
                    <div class="control">
                        <button class="button">
                            <span class="icon"><i class="fas fa-align-right"></i></span>
                            <span>Right</span>
                        </button>
                    </div>
                </div>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderAlignedAddons(Alignment alignment) {
        var buttons = buttonsAddon().is(alignment).content(
                button("Yes"),
                button("Maybe"),
                button("No"));

        then(buttons).rendersAs("""
                <div class="buttons has-addons is-$alignment">
                    <button class="button">Yes</button>
                    <button class="button">Maybe</button>
                    <button class="button">No</button>
                </div>
                """.replace("$alignment", alignment.key()));
    }

    @Test void shouldRenderSelectedSuccess() {
        var buttons = buttonsAddon().content(
                button("Yes").is(SUCCESS, SELECTED),
                button("Maybe"),
                button("No"));

        then(buttons).rendersAs("""
                <div class="buttons has-addons">
                    <button class="button is-success is-selected">Yes</button>
                    <button class="button">Maybe</button>
                    <button class="button">No</button>
                </div>
                """);
    }

    @Test void shouldRenderSelectedInfo() {
        var buttons = buttonsAddon().content(
                button("Yes"),
                button("Maybe").is(INFO, SELECTED),
                button("No"));

        then(buttons).rendersAs("""
                <div class="buttons has-addons">
                    <button class="button">Yes</button>
                    <button class="button is-info is-selected">Maybe</button>
                    <button class="button">No</button>
                </div>
                """);
    }

    @Test void shouldRenderAddonsSelectedDanger() {
        var buttons = buttonsAddon().content(
                button("Yes"),
                button("Maybe"),
                button("No").is(DANGER, SELECTED));

        then(buttons).rendersAs("""
                <div class="buttons has-addons">
                    <button class="button">Yes</button>
                    <button class="button">Maybe</button>
                    <button class="button is-danger is-selected">No</button>
                </div>
                """);
    }

    @Test void shouldRenderSubmitButton() {
        var button = button("Button").type(SUBMIT);

        then(button).rendersAs("""
                <button class="button" type="submit">Button</button>
                """);
    }

    @Test void shouldRenderResetButton() {
        var button = button("Button").type(RESET);

        then(button).rendersAs("""
                <button class="button" type="reset">Button</button>
                """);
    }

    @Test void shouldRenderButtonButton() {
        var button = button("Button").type(BUTTON);

        then(button).rendersAs("""
                <button class="button" type="button">Button</button>
                """);
    }
}
