package test.form;

import com.github.t1.bulmajava.basic.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Alignment.CENTERED;
import static com.github.t1.bulmajava.basic.Alignment.RIGHT;
import static com.github.t1.bulmajava.basic.Color.*;
import static com.github.t1.bulmajava.basic.Style.FULLWIDTH;
import static com.github.t1.bulmajava.form.FileInput.fileInput;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class FileInputTest {
    @Test void shouldRenderFileInput() {
        var field = fileInput("Choose a file…").icon("upload");

        then(field).rendersAs("""
                <div class="file">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">Choose a file…</span>
                        </span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderFileInputWithFieldName() {
        var field = fileInput("Choose a file…").name("foo");

        then(field).rendersAs("""
                <div class="file">
                    <label class="file-label">
                        <input class="file-input" type="file" name="foo">
                        <span class="file-cta">
                            <span class="file-label">Choose a file…</span>
                        </span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderFileInputWithFilename() {
        var field = fileInput("Choose a file…")
                .icon("upload")
                .fileName("Screen Shot 2017-07-29 at 15.54.25.png");

        then(field).rendersAs("""
                <div class="file has-name">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">Choose a file…</span>
                        </span>
                        <span class="file-name">Screen Shot 2017-07-29 at 15.54.25.png</span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderRightFileInputWithFilename() {
        var field = fileInput("Choose a file…")
                .icon("upload")
                .fileName("Screen Shot 2017-07-29 at 15.54.25.png")
                .is(RIGHT);

        then(field).rendersAs("""
                <div class="file has-name is-right">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">Choose a file…</span>
                        </span>
                        <span class="file-name">Screen Shot 2017-07-29 at 15.54.25.png</span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderFullwidthFileInput() {
        var field = fileInput("Choose a file…")
                .icon("upload")
                .fileName("Screen Shot 2017-07-29 at 15.54.25.png")
                .is(FULLWIDTH);

        then(field).rendersAs("""
                <div class="file has-name is-fullwidth">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">Choose a file…</span>
                        </span>
                        <span class="file-name">Screen Shot 2017-07-29 at 15.54.25.png</span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderBoxedFileInput() {
        var field = fileInput("Choose a file…")
                .icon("upload")
                .boxed();

        then(field).rendersAs("""
                <div class="file is-boxed">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">Choose a file…</span>
                        </span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderBoxedFileInputWithName() {
        var field = fileInput("Choose a file…")
                .fileName("Screen Shot 2017-07-29 at 15.54.25.png")
                .icon("upload")
                .boxed();

        then(field).rendersAs("""
                <div class="file has-name is-boxed">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">Choose a file…</span>
                        </span>
                        <span class="file-name">Screen Shot 2017-07-29 at 15.54.25.png</span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderPrimaryFileInput() {
        var field = fileInput("Primary file…").icon("upload").is(PRIMARY);

        then(field).rendersAs("""
                <div class="file is-primary">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">Primary file…</span>
                        </span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderInfoFileInputWithFilename() {
        var field = fileInput("Info file…")
                .is(INFO)
                .icon("upload")
                .fileName("Screen Shot 2017-07-29 at 15.54.25.png");

        then(field).rendersAs("""
                <div class="file is-info has-name">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">Info file…</span>
                        </span>
                        <span class="file-name">Screen Shot 2017-07-29 at 15.54.25.png</span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderBoxedWarningFileInput() {
        var field = fileInput("Warning file…")
                .is(WARNING)
                .icon("cloud-upload-alt")
                .boxed();

        then(field).rendersAs("""
                <div class="file is-warning is-boxed">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-cloud-upload-alt"></i></span>
                            <span class="file-label">Warning file…</span>
                        </span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderBoxedDangerFileInputWithName() {
        var field = fileInput("Danger file…")
                .is(DANGER)
                .fileName("Screen Shot 2017-07-29 at 15.54.25.png")
                .icon("cloud-upload-alt")
                .boxed();

        then(field).rendersAs("""
                <div class="file is-danger has-name is-boxed">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-cloud-upload-alt"></i></span>
                            <span class="file-label">Danger file…</span>
                        </span>
                        <span class="file-name">Screen Shot 2017-07-29 at 15.54.25.png</span>
                    </label>
                </div>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderSizedFileInput(Size size) {
        var field = fileInput(size.key() + " file…").icon("upload").is(size);

        then(field).rendersAs("""
                <div class="file is-$size">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">$size file…</span>
                        </span>
                    </label>
                </div>
                """.replace("$size", size.key()));
    }

    @ParameterizedTest @EnumSource void shouldRenderSizedFileInputWithFileName(Size size) {
        var field = fileInput(size.key() + " file…")
                .is(size)
                .icon("upload")
                .fileName("Screen Shot 2017-07-29 at 15.54.25.png");

        then(field).rendersAs("""
                <div class="file is-$size has-name">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">$size file…</span>
                        </span>
                        <span class="file-name">Screen Shot 2017-07-29 at 15.54.25.png</span>
                    </label>
                </div>
                """.replace("$size", size.key()));
    }

    @Test void shouldRenderCenteredBoxedSuccessFileInputWithName() {
        var field = fileInput("Centered file…").is(CENTERED).boxed().is(SUCCESS)
                .fileName("Screen Shot 2017-07-29 at 15.54.25.png")
                .icon("upload");

        then(field).rendersAs("""
                <div class="file is-centered is-boxed is-success has-name">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">Centered file…</span>
                        </span>
                        <span class="file-name">Screen Shot 2017-07-29 at 15.54.25.png</span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderRightInfoFileInputWithName() {
        var field = fileInput("Right file…").is(RIGHT, INFO)
                .fileName("Screen Shot 2017-07-29 at 15.54.25.png")
                .icon("upload");

        // has-name was missing
        then(field).rendersAs("""
                <div class="file is-right is-info has-name">
                    <label class="file-label">
                        <input class="file-input" type="file">
                        <span class="file-cta">
                            <span class="file-icon"><i class="fas fa-upload"></i></span>
                            <span class="file-label">Right file…</span>
                        </span>
                        <span class="file-name">Screen Shot 2017-07-29 at 15.54.25.png</span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderFileInputWithAccept() {
        var field = fileInput("Choose a file…").accept(".doc", ".docx", ".xml", "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        then(field).rendersAs("""
                <div class="file">
                    <label class="file-label">
                        <input class="file-input" type="file" accept=".doc,.docx,.xml,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document">
                        <span class="file-cta">
                            <span class="file-label">Choose a file…</span>
                        </span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderFileInputWithCaptureUser() {
        var field = fileInput("Choose a file…").captureUser();

        then(field).rendersAs("""
                <div class="file">
                    <label class="file-label">
                        <input class="file-input" type="file" capture="user">
                        <span class="file-cta">
                            <span class="file-label">Choose a file…</span>
                        </span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderFileInputWithCaptureEnvironment() {
        var field = fileInput("Choose a file…").captureEnvironment();

        then(field).rendersAs("""
                <div class="file">
                    <label class="file-label">
                        <input class="file-input" type="file" capture="environment">
                        <span class="file-cta">
                            <span class="file-label">Choose a file…</span>
                        </span>
                    </label>
                </div>
                """);
    }

    @Test void shouldRenderFileInputWithMultipleAccept() {
        var field = fileInput("Choose a file…").multiple().accept("application/pdf");

        then(field).rendersAs("""
                <div class="file">
                    <label class="file-label">
                        <input class="file-input" type="file" multiple accept="application/pdf">
                        <span class="file-cta">
                            <span class="file-label">Choose a file…</span>
                        </span>
                    </label>
                </div>
                """);
    }
}
