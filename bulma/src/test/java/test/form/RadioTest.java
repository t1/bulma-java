package test.form;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.form.Radio.radios;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class RadioTest {
    @Test void shouldRenderRadio() {
        var field = radios("answer")
                .option("y", "Yes")
                .option("n", "No");

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
        var field = radios("rsvp")
                .option("y", "Going")
                .option("n", "Not going")
                .option("m", "Maybe").disabled();

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
                        <input type="radio" value="m" name="rsvp" disabled />
                        Maybe
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderCheckedRadio() {
        var field = radios("foobar")
                .option("foo", "Foo")
                .option("bar", "Bar").checked();

        then(field).rendersAs("""
                <div class="radios">
                    <label class="radio">
                        <input type="radio" value="foo" name="foobar" />
                        Foo
                    </label>
                    <label class="radio">
                        <input type="radio" value="bar" name="foobar" checked />
                        Bar
                    </label>
                </div>
                """);
    }
}
