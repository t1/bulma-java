package test.form;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import java.time.LocalDate;

import static com.github.t1.bulmajava.basic.Color.PRIMARY;
import static com.github.t1.bulmajava.basic.Color.SUCCESS;
import static com.github.t1.bulmajava.basic.Color.WARNING;
import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.DATE;
import static com.github.t1.bulmajava.form.InputType.TEXT;
import static com.github.t1.htmljava.Basic.div;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class ValidationTest {
    @Test void shouldRenderFieldWithColorIcon() {
        var form = div().style("width: 400px;").content(
                field().label("Label")
                        .control(input(TEXT).is(PRIMARY).placeholder("Text input"))
                        .iconLeft("check", SUCCESS)
                        .iconRight("exclamation-triangle", WARNING));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Label</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-primary" type="text" placeholder="Text input">
                            <span class="icon is-small is-left"><i class="fas fa-check has-text-success"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-exclamation-triangle has-text-warning"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderRequiredField() {
        var form = div().style("width: 400px;").content(
                field().label("Label")
                        .control(input(TEXT).placeholder("Text input").required()));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Label</label>
                        <div class="control">
                            <input class="input" type="text" placeholder="Text input" required>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderMinlengthField() {
        var form = div().style("width: 400px;").content(
                field().label("Label")
                        .control(input(TEXT).placeholder("Text input").minlength(5)));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Label</label>
                        <div class="control">
                            <input class="input" type="text" placeholder="Text input" minlength="5">
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderMaxlengthField() {
        var form = div().style("width: 400px;").content(
                field().label("Label")
                        .control(input(TEXT).placeholder("Text input").maxlength(5)));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Label</label>
                        <div class="control">
                            <input class="input" type="text" placeholder="Text input" maxlength="5">
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderMinDateField() {
        var form = div().style("width: 400px;").content(
                field().label("Label")
                        .control(input(DATE).min(LocalDate.of(2023, 10, 9))));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Label</label>
                        <div class="control">
                            <input class="input" type="date" min="2023-10-09">
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderMaxDateField() {
        var form = div().style("width: 400px;").content(
                field().label("Label")
                        .control(input(DATE).max(LocalDate.of(2023, 10, 19))));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Label</label>
                        <div class="control">
                            <input class="input" type="date" max="2023-10-19">
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderMinAndMaxDateField() {
        var form = div().style("width: 400px;").content(
                field().label("Label")
                        .control(input(DATE)
                                .min(LocalDate.of(2023, 10, 9))
                                .max(LocalDate.of(2023, 10, 19))));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Label</label>
                        <div class="control">
                            <input class="input" type="date" min="2023-10-09" max="2023-10-19">
                        </div>
                    </div>
                </div>
                """);
    }
}
