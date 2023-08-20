package test.form;

import com.github.t1.bulmajava.basic.Color;
import com.github.t1.bulmajava.basic.Size;
import com.github.t1.bulmajava.basic.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Basic.label;
import static com.github.t1.bulmajava.basic.Size.*;
import static com.github.t1.bulmajava.basic.State.LOADING;
import static com.github.t1.bulmajava.basic.Style.ROUNDED;
import static com.github.t1.bulmajava.basic.Style.STATIC;
import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Input.*;
import static com.github.t1.bulmajava.form.InputType.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class InputTest {
    @Test void shouldRenderSubmitButton() {
        var button = submit("Submit input");

        then(button).rendersAs("""
                <input class="button" type="submit" value="Submit input">
                """);
    }

    @Test void shouldRenderResetButton() {
        var button = reset("Reset input");

        then(button).rendersAs("""
                <input class="button" type="reset" value="Reset input">
                """);
    }

    @Test void shouldRenderInput() {
        var input = input(TEXT).placeholder("Text input");

        then(input).rendersAs("""
                <input class="input" type="text" placeholder="Text input">
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderColorInput(Color color) {
        var input = input(TEXT).placeholder(color.key() + " input").is(color);

        then(input).rendersAs("""
                <input class="input is-$color" type="text" placeholder="$color input">
                """.replace("$color", color.key()));
    }

    @ParameterizedTest @EnumSource void shouldRenderSizeInput(Size size) {
        var input = input(TEXT).placeholder(size.key() + " input").is(size);

        then(input).rendersAs("""
                <input class="input is-$size" type="text" placeholder="$size input">
                """.replace("$size", size.key()));
    }

    @Test void shouldRenderRoundedInput() {
        var input = input(TEXT).placeholder("Rounded input").is(ROUNDED);

        then(input).rendersAs("""
                <input class="input is-rounded" type="text" placeholder="Rounded input">
                """);
    }

    @ParameterizedTest @EnumSource(mode = EXCLUDE, names = "LOADING") void shouldRenderStateInput(State state) {
        var input = field().containsControl(input(TEXT).placeholder(state.key() + " input").is(state));

        then(input).rendersAs("""
                <div class="field">
                    <div class="control">
                        <input class="input is-$state" type="text" placeholder="$state input">
                    </div>
                </div>
                """.replace("$state", state.key()));
    }

    @Test void shouldRenderLoadingInput() {
        var input = field().containsControl(input(TEXT).placeholder("Loading input"), LOADING);

        then(input).rendersAs("""
                <div class="field">
                    <div class="control is-loading">
                        <input class="input" type="text" placeholder="Loading input">
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderSizedLoadingInput() {
        var input = div().style("width: 800px;").contains(
                field().containsControl(input(TEXT).placeholder("Small loading input").is(SMALL), SMALL, LOADING),
                field().containsControl(input(TEXT).placeholder("Normal loading input"), LOADING),
                field().containsControl(input(TEXT).placeholder("Medium loading input").is(MEDIUM), MEDIUM, LOADING),
                field().containsControl(input(TEXT).placeholder("Large loading input").is(LARGE), LARGE, LOADING));

        then(input).rendersAs("""
                <div style="width: 800px;">
                    <div class="field">
                        <div class="control is-small is-loading">
                            <input class="input is-small" type="text" placeholder="Small loading input">
                        </div>
                    </div>
                    <div class="field">
                        <div class="control is-loading">
                            <input class="input" type="text" placeholder="Normal loading input">
                        </div>
                    </div>
                    <div class="field">
                        <div class="control is-medium is-loading">
                            <input class="input is-medium" type="text" placeholder="Medium loading input">
                        </div>
                    </div>
                    <div class="field">
                        <div class="control is-large is-loading">
                            <input class="input is-large" type="text" placeholder="Large loading input">
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderDisabledInput() {
        var input = field().containsControl(input(TEXT).placeholder("Disabled input").disabled());

        then(input).rendersAs("""
                <div class="field">
                    <div class="control">
                        <input class="input" type="text" placeholder="Disabled input" disabled>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderReadonlyInput() {
        var input = field().containsControl(input(TEXT).value("This text is readonly").readonly());

        then(input).rendersAs("""
                <div class="field">
                    <div class="control">
                        <input class="input" type="text" value="This text is readonly" readonly>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderReadonlyStaticInput() {
        var input = div().style("width: 800px;").contains(
                div().classes("field", "is-horizontal").contains(
                        div().classes("field-label").is(NORMAL).contains(
                                label("From")),
                        div().classes("field-body").contains(
                                field().containsControl(input(EMAIL).is(STATIC).value("me@example.com").readonly()))),
                div().classes("field", "is-horizontal").contains(
                        div().classes("field-label").is(NORMAL).contains(
                                label("To")),
                        div().classes("field-body").contains(
                                field().containsControl(input(EMAIL).placeholder("Recipient email")))));

        then(input).rendersAs("""
                <div style="width: 800px;">
                    <div class="field is-horizontal">
                        <div class="field-label is-normal">
                            <label class="label">From</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <input class="input is-static" type="email" value="me@example.com" readonly>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-normal">
                            <label class="label">To</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <input class="input" type="email" placeholder="Recipient email">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderInputWithIcons() {
        var input = div().style("width: 500px;").contains(
                field().containsControl(input(EMAIL).placeholder("Email"))
                        .iconLeft("envelope")
                        .iconRight("check"),
                field().containsControl(input(PASSWORD).placeholder("Password"))
                        .iconLeft("lock"));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input" type="email" placeholder="Email">
                            <span class="icon is-small is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left">
                            <input class="input" type="password" placeholder="Password">
                            <span class="icon is-small is-left"><i class="fas fa-lock"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderInputWithSmallIcon() {
        var input = div().style("width: 500px;").contains(
                field().containsControl(input(EMAIL).placeholder("Email").is(SMALL))
                        .iconLeft("envelope")
                        .iconRight("check"));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-small" type="email" placeholder="Email">
                            <span class="icon is-small is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderInputWithNormalIcon() {
        var input = div().style("width: 500px;").contains(
                field().containsControl(input(EMAIL).placeholder("Email"))
                        .iconLeft("envelope")
                        .iconRight("check"));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input" type="email" placeholder="Email">
                            <span class="icon is-small is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderInputWithMediumIcon() {
        var input = div().style("width: 500px;").contains(
                field().containsControl(input(EMAIL).placeholder("Email").is(MEDIUM))
                        .iconLeft("envelope")
                        .iconRight("check"));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-medium" type="email" placeholder="Email">
                            <span class="icon is-small is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderInputWithLargeIcon() {
        var input = div().style("width: 500px;").contains(
                field().containsControl(input(EMAIL).placeholder("Email").is(LARGE))
                        .iconLeft("envelope")
                        .iconRight("check"));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-large" type="email" placeholder="Email">
                            <span class="icon is-small is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }
}
