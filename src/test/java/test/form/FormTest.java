package test.form;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.form.Form.form;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.TEXT;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class FormTest {
    @Test void shouldRenderPostForm() {
        var form = form()
                .post("/submit")
                .content(input(TEXT).name("chat_message"));

        then(form).rendersAs("""
                <form method="post" action="/submit">
                    <input class="input" type="text" name="chat_message">
                </form>
                """);
    }

    @Test void shouldRenderGetForm() {
        var form = form()
                .get("/submit")
                .content(input(TEXT).name("chat_message"));

        then(form).rendersAs("""
                <form method="get" action="/submit">
                    <input class="input" type="text" name="chat_message">
                </form>
                """);
    }

    @Test void shouldRenderDialogForm() {
        var form = form()
                .dialog("/submit")
                .content(input(TEXT).name("chat_message"));

        then(form).rendersAs("""
                <form method="dialog" action="/submit">
                    <input class="input" type="text" name="chat_message">
                </form>
                """);
    }

    @Test void shouldRenderFormWithoutMethod() {
        var form = form()
                .action("/submit")
                .content(input(TEXT).name("chat_message"));

        then(form).rendersAs("""
                <form action="/submit">
                    <input class="input" type="text" name="chat_message">
                </form>
                """);
    }

    @Test void shouldRenderCustomMethodForm() {
        var form = form()
                .action("/submit")
                .method("put")
                .content(input(TEXT).name("chat_message"));

        // put is not a valid method for a form, but we want to test that it's rendered as given
        //noinspection HtmlWrongAttributeValue
        then(form).rendersAs("""
                <form action="/submit" method="put">
                    <input class="input" type="text" name="chat_message">
                </form>
                """);
    }
}
