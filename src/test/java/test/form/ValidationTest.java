package test.form;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Color.*;
import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.TEXT;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class ValidationTest {
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
}
