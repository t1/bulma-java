package test.form;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Basic.element;
import static com.github.t1.bulmajava.form.FileInput.fileInput;
import static com.github.t1.bulmajava.form.Form.form;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.FILE;
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

    @Test void shouldRenderUrlencodedForm() {
        var form = form()
                .urlencoded() // this is the default
                .post("/submit")
                .content(fileInput("Choose a file…").id("foo"));

        then(form).rendersAs("""
                <form enctype="application/x-www-form-urlencoded" method="post" action="/submit">
                    <div class="file">
                        <label class="file-label">
                            <input id="foo" class="file-input" type="file">
                            <span class="file-cta">
                                <span class="file-label">Choose a file…</span>
                            </span>
                        </label>
                    </div>
                </form>
                """);
    }

    @Test void shouldRenderMultipartForm() {
        var form = form()
                .multipart()
                .post("/submit")
                .content(fileInput("Choose a file…").id("foo"));

        then(form).rendersAs("""
                <form enctype="multipart/form-data" method="post" action="/submit">
                    <div class="file">
                        <label class="file-label">
                            <input id="foo" class="file-input" type="file">
                            <span class="file-cta">
                                <span class="file-label">Choose a file…</span>
                            </span>
                        </label>
                    </div>
                </form>
                """);
    }

    @Test void shouldRenderTextPlainForm() {
        var form = form()
                .plain()
                .post("/submit")
                .content(fileInput("Choose a file…").id("foo"));

        then(form).rendersAs("""
                <form enctype="text/plain" method="post" action="/submit">
                    <div class="file">
                        <label class="file-label">
                            <input id="foo" class="file-input" type="file">
                            <span class="file-cta">
                                <span class="file-label">Choose a file…</span>
                            </span>
                        </label>
                    </div>
                </form>
                """);
    }

    @Test void shouldRenderUnStyledFileInputForm() {
        var form = form().post().multipart().content(
                div().content(
                        element("label").content("Choose file to upload").attr("for", "foo"),
                        input(FILE).id("foo").notClasses("input").name("file").attr("multiple")),
                div().content(element("button").content("Submit")));

        then(form).rendersAs("""
                <form method="post" enctype="multipart/form-data">
                    <div>
                        <label for="foo">Choose file to upload</label>
                        <input id="foo" type="file" name="file" multiple>
                    </div>
                    <div>
                        <button>Submit</button>
                    </div>
                </form>
                """);
    }
}
