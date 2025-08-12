package test.form;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.form.Radio.RadioGroup.radioGroup;
import static com.github.t1.bulmajava.form.Radio.radio;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class RadioTest {
    @Test void shouldRenderRadio() {
        var field = radioGroup("answer")
                .content(radio("y", "Yes"))
                .content(radio("n", "No"));

        then(field).rendersAs("""
                <div class="radios">
                    <label class="radio">
                        <input type="radio" value="y" name="answer" />
                        Yes
                    </label>
                    <label class="radio">
                        <input type="radio" value="n" name="answer" />
                        No
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderDisabledRadio() {
        var field = radioGroup("rsvp")
                .content(radio("y", "Going"))
                .content(radio("n", "Not going"))
                .content(radio("m", "Maybe").disabled());

        //noinspection HtmlUnknownAttribute // the disabled label is actually correct
        then(field).rendersAs("""
                <div class="radios">
                    <label class="radio">
                        <input type="radio" value="y" name="rsvp" />
                        Going
                    </label>
                    <label class="radio">
                        <input type="radio" value="n" name="rsvp" />
                        Not going
                    </label>
                    <label class="radio" disabled>
                        <input type="radio" value="m" disabled name="rsvp" />
                        Maybe
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderCheckedRadio() {
        var field = radioGroup("foobar")
                .content(radio("foo", "Foo"))
                .content(radio("bar", "Bar").checked());

        then(field).rendersAs("""
                <div class="radios">
                    <label class="radio">
                        <input type="radio" value="foo" name="foobar" />
                        Foo
                    </label>
                    <label class="radio">
                        <input type="radio" value="bar" checked name="foobar" />
                        Bar
                    </label>
                </div>
                """);
    }
}
