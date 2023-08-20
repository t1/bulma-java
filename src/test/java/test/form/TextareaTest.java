package test.form;

import com.github.t1.bulmajava.basic.Color;
import com.github.t1.bulmajava.basic.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Size.*;
import static com.github.t1.bulmajava.basic.State.*;
import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Textarea.textarea;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class TextareaTest {
    @Test void shouldRenderTextArea() {
        var textarea = textarea().placeholder("e.g. Hello world");

        then(textarea).rendersAs("""
                <textarea class="textarea" placeholder="e.g. Hello world"></textarea>
                """);
    }

    @Test void shouldRenderRowsTextArea() {
        var textarea = textarea().placeholder("10 lines of textarea").rows(10);

        then(textarea).rendersAs("""
                <textarea class="textarea" placeholder="10 lines of textarea" rows="10"></textarea>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderColorTextArea(Color color) {
        var textarea = textarea().placeholder(color.key() + " textarea").is(color);

        then(textarea).rendersAs("""
                <textarea class="textarea is-$color" placeholder="$color textarea"></textarea>
                """.replace("$color", color.key()));
    }

    @ParameterizedTest @EnumSource void shouldRenderSizeTextArea(Size size) {
        var textarea = textarea().placeholder(size.key() + " textarea").is(size);

        then(textarea).rendersAs("""
                <textarea class="textarea is-$size" placeholder="$size textarea"></textarea>
                """.replace("$size", size.key()));
    }

    @Test void shouldRenderHoverTextArea() {
        var textarea = field().containsControl(
                textarea().placeholder("Hovered textarea").is(HOVERED));

        then(textarea).rendersAs("""
                <div class="field">
                    <div class="control">
                        <textarea class="textarea is-hovered" placeholder="Hovered textarea"></textarea>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFocusTextArea() {
        var textarea = field().containsControl(
                textarea().placeholder("Focused textarea").is(FOCUSED));

        then(textarea).rendersAs("""
                <div class="field">
                    <div class="control">
                        <textarea class="textarea is-focused" placeholder="Focused textarea"></textarea>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderLoadingTextArea() {
        var textarea = field().containsControl(
                textarea().placeholder("Loading textarea"), LOADING);

        then(textarea).rendersAs("""
                <div class="field">
                    <div class="control is-loading">
                        <textarea class="textarea" placeholder="Loading textarea"></textarea>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderSizedLoadingTextArea() {
        var textarea = div().style("width: 300px;").contains(
                field().containsControl(textarea().placeholder("Small loading textarea").is(SMALL), SMALL, LOADING),
                field().containsControl(textarea().placeholder("Normal loading textarea"), LOADING),
                field().containsControl(textarea().placeholder("Medium loading textarea").is(MEDIUM), MEDIUM, LOADING),
                field().containsControl(textarea().placeholder("Large loading textarea").is(LARGE), LARGE, LOADING));

        then(textarea).rendersAs("""
                <div style="width: 300px;">
                    <div class="field">
                        <div class="control is-small is-loading">
                            <textarea class="textarea is-small" placeholder="Small loading textarea"></textarea>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control is-loading">
                            <textarea class="textarea" placeholder="Normal loading textarea"></textarea>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control is-medium is-loading">
                            <textarea class="textarea is-medium" placeholder="Medium loading textarea"></textarea>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control is-large is-loading">
                            <textarea class="textarea is-large" placeholder="Large loading textarea"></textarea>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderDisabledTextArea() {
        var textarea = field().containsControl(
                textarea().placeholder("Disabled textarea").disabled());

        then(textarea).rendersAs("""
                <div class="field">
                    <div class="control">
                        <textarea class="textarea" placeholder="Disabled textarea" disabled></textarea>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderReadonlyTextArea() {
        var textarea = field().containsControl(
                textarea().contains("This content is readonly").readonly());

        then(textarea).rendersAs("""
                <div class="field">
                    <div class="control">
                        <textarea class="textarea" readonly>This content is readonly</textarea>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFixedSizeTextArea() {
        var textarea = field().containsControl(
                textarea().fixedSize().placeholder("Fixed size textarea"));

        then(textarea).rendersAs("""
                <div class="field">
                    <div class="control">
                        <textarea class="textarea has-fixed-size" placeholder="Fixed size textarea"></textarea>
                    </div>
                </div>
                """);
    }
}
