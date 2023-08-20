package test.form;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Radio.radio;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class RadioTest {
    @Test void shouldRenderRadio() {
        var field = field()
                .containsControl(radio("answer").contains("Yes"))
                .containsControl(radio("answer").contains("No"));

        then(field).rendersAs("""
                <div class="field">
                    <div class="control">
                        <label class="radio">
                            <input type="radio" name="answer">
                            Yes
                        </label>
                        <label class="radio">
                            <input type="radio" name="answer">
                            No
                        </label>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderDisabledRadio() {
        var field = field()
                .containsControl(radio("rsvp").contains("Going"))
                .containsControl(radio("rsvp").contains("Not going"))
                .containsControl(radio("rsvp").contains("Maybe").disabled());

        //noinspection HtmlUnknownAttribute // the disabled label is actually correct
        then(field).rendersAs("""
                <div class="field">
                    <div class="control">
                        <label class="radio">
                            <input type="radio" name="rsvp">
                            Going
                        </label>
                        <label class="radio">
                            <input type="radio" name="rsvp">
                            Not going
                        </label>
                        <label class="radio" disabled>
                            <input type="radio" name="rsvp" disabled>
                            Maybe
                        </label>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderCheckedRadio() {
        var field = field()
                .containsControl(radio("foobar").contains("Foo"))
                .containsControl(radio("foobar").contains("Bar").checked());

        then(field).rendersAs("""
                <div class="field">
                    <div class="control">
                        <label class="radio">
                            <input type="radio" name="foobar">
                            Foo
                        </label>
                        <label class="radio">
                            <input type="radio" name="foobar" checked>
                            Bar
                        </label>
                    </div>
                </div>
                """);
    }
}
