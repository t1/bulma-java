package test.form;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.form.Checkbox.checkbox;
import static com.github.t1.bulmajava.form.Field.field;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class CheckboxTest {
    @Test void shouldRenderCheckbox() {
        var field = field().control(checkbox().content("Remember me"));

        then(field).rendersAs("""
                <div class="field">
                    <div class="control">
                        <label class="checkbox">
                            <input type="checkbox">
                            Remember me
                        </label>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderCheckboxWithLink() {
        var field = field().control(checkbox().content(
                string("I agree to the"),
                a("terms and conditions").href("#")));

        then(field).rendersAs("""
                <div class="field">
                    <div class="control">
                        <label class="checkbox">
                            <input type="checkbox">
                            I agree to the
                            <a href="#">terms and conditions</a>
                        </label>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderDisabledCheckbox() {
        var field = field().control(checkbox().content("Save my preferences").disabled());

        //noinspection HtmlUnknownAttribute // the disabled label is actually correct
        then(field).rendersAs("""
                <div class="field">
                    <div class="control">
                        <label class="checkbox" disabled>
                            <input type="checkbox" disabled>
                            Save my preferences
                        </label>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderCheckedCheckbox() {
        var field = field().control(checkbox().content("Save my preferences").checked());

        then(field).rendersAs("""
                <div class="field">
                    <div class="control">
                        <label class="checkbox">
                            <input type="checkbox" checked>
                            Save my preferences
                        </label>
                    </div>
                </div>
                """);
    }
}
