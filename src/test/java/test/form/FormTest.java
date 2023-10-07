package test.form;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.form;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.TEXT;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class FormTest {
    @Test void shouldRenderForm() {
        var form = form().content(
                input(TEXT).name("chat_message"));

        then(form).rendersAs("""
                <form>
                    <input class="input" type="text" name="chat_message">
                </form>
                """);
    }
}
