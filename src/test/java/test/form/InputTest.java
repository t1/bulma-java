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

    @ParameterizedTest @EnumSource void shouldRenderColoredInput(Color color) {
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
        var input = field().control(input(TEXT).placeholder(state.key() + " input").is(state));

        then(input).rendersAs("""
                <div class="field">
                    <div class="control">
                        <input class="input is-$state" type="text" placeholder="$state input">
                    </div>
                </div>
                """.replace("$state", state.key()));
    }

    @Test void shouldRenderLoadingInput() {
        var input = field().control(input(TEXT).placeholder("Loading input"), LOADING);

        then(input).rendersAs("""
                <div class="field">
                    <div class="control is-loading">
                        <input class="input" type="text" placeholder="Loading input">
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderSizedLoadingInput() {
        var input = div().style("width: 800px;").content(
                field().control(input(TEXT).placeholder("Small loading input").is(SMALL), SMALL, LOADING),
                field().control(input(TEXT).placeholder("Normal loading input"), LOADING),
                field().control(input(TEXT).placeholder("Medium loading input").is(MEDIUM), MEDIUM, LOADING),
                field().control(input(TEXT).placeholder("Large loading input").is(LARGE), LARGE, LOADING));

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
        var input = field().control(input(TEXT).placeholder("Disabled input").disabled());

        then(input).rendersAs("""
                <div class="field">
                    <div class="control">
                        <input class="input" type="text" placeholder="Disabled input" disabled>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderReadonlyInput() {
        var input = field().control(input(TEXT).value("This text is readonly").readonly());

        then(input).rendersAs("""
                <div class="field">
                    <div class="control">
                        <input class="input" type="text" value="This text is readonly" readonly>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderNullInput() {
        var input = field().control(input(TEXT).value(null));

        then(input).rendersAs("""
                <div class="field">
                    <div class="control">
                        <input class="input" type="text">
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderUnsafeInput() {
        var input = field().control(input(TEXT).value("<>&\"'"));

        then(input).rendersAs("""
                <div class="field">
                    <div class="control">
                        <input class="input" type="text" value="&lt;&gt;&amp;&quot;&#x27;">
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderReadonlyStaticInput() {
        var input = div().style("width: 800px;").content(
                field().horizontal()
                        .label("From", NORMAL)
                        .control(field().control(input(EMAIL).is(STATIC).value("me@example.com").readonly())),
                field().horizontal().label("To", NORMAL)
                        .control(field().control(input(EMAIL).placeholder("Recipient email"))));

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
        var input = div().style("width: 500px;").content(
                field().control(input(EMAIL).placeholder("Email"))
                        .iconLeft("envelope")
                        .iconRight("check"),
                field().control(input(PASSWORD).placeholder("Password"))
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
        var input = div().style("width: 500px;").content(
                field().control(input(EMAIL).placeholder("Email").is(SMALL))
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
        var input = div().style("width: 500px;").content(
                field().control(input(EMAIL).placeholder("Email"))
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
        var input = div().style("width: 500px;").content(
                field().control(input(EMAIL).placeholder("Email").is(MEDIUM))
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
        var input = div().style("width: 500px;").content(
                field().control(input(EMAIL).placeholder("Email").is(LARGE))
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

    @Test void shouldRenderColorInput() {
        var input = div().style("width: 500px;").content(
                field().control(input(COLOR).value("#f6b73c")));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <div class="field">
                        <div class="control">
                            <input class="input" type="color" value="#f6b73c">
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderDateInput() {
        var input = div().style("width: 500px;").content(
                field().control(input(DATE).value("2023-10-09")));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <div class="field">
                        <div class="control">
                            <input class="input" type="date" value="2023-10-09">
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderDatetimeLocalInput() {
        var input = div().style("width: 500px;").content(
                field().control(input(DATETIME_LOCAL).value("2023-10-09T12:34")));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <div class="field">
                        <div class="control">
                            <input class="input" type="datetime-local" value="2023-10-09T12:34">
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderEmailInput() {
        var input = div().style("width: 500px;").content(
                field().control(input(EMAIL).value("foo@bar.baz")));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <div class="field">
                        <div class="control">
                            <input class="input" type="email" value="foo@bar.baz">
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderHiddenInput() {
        var input = div().style("width: 500px;").content(
                field().control(input(HIDDEN).value("secret-sauce")));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <div class="field">
                        <div class="control">
                            <input class="input" type="hidden" value="secret-sauce">
                        </div>
                    </div>
                </div>
                """);
    }

    // TODO IMAGE, MONTH, NUMBER

    @Test void shouldRenderRawRangeInput() {
        var input = div().style("width: 500px;").content(
                input(RANGE).notClasses("input").name("cowbell").min(0).max(100).value(80).step(20));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <input type="range" name="cowbell" min="0" max="100" value="80" step="20">
                </div>
                """);
    }

    @Test void shouldRenderRangeField() {
        var input = div().style("width: 500px;").content(
                field()
                        .label("Cowbell")
                        .control(input(RANGE).name("cowbell").min(0).max(100).value(90).step(10)));

        then(input).rendersAs("""
                <div style="width: 500px;">
                    <div class="field">
                        <label class="label">Cowbell</label>
                        <div class="control">
                            <input class="input" type="range" name="cowbell" min="0" max="100" value="90" step="10">
                        </div>
                    </div>
                </div>
                """);
    }

    // TODO RANGE-list, RANGE-orientation

    // TODO SEARCH, TIME, URL, WEEK
}
