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
                .control(radio("answer").content("Yes"))
                .control(radio("answer").content("No"));

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
                .control(radio("rsvp").content("Going"))
                .control(radio("rsvp").content("Not going"))
                .control(radio("rsvp").content("Maybe").disabled());

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
                .control(radio("foobar").content("Foo"))
                .control(radio("foobar").content("Bar").checked());

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
