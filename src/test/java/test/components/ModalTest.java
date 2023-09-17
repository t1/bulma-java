package test.components;

import com.github.t1.bulmajava.elements.Button;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Basic.*;
import static com.github.t1.bulmajava.basic.Color.PRIMARY;
import static com.github.t1.bulmajava.basic.Color.SUCCESS;
import static com.github.t1.bulmajava.components.Modal.*;
import static com.github.t1.bulmajava.elements.Box.box;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Delete.close;
import static com.github.t1.bulmajava.elements.Image.imageP;
import static com.github.t1.bulmajava.elements.Image.img;
import static com.github.t1.bulmajava.elements.ImageRatio._4by3;
import static com.github.t1.bulmajava.layout.Section.section;
import static test.CustomAssertions.then;
import static test.RenderTestExtension.loremIpsum;
import static test.RenderTestExtension.loremIpsumS;

@ExtendWith(RenderTestExtension.class)
class ModalTest {
    @Test void shouldRenderModal() {
        var modal = div().content(
                modal().id("modal-1").content(
                        modalContent().content(
                                box("Some text inside the modal.")),
                        modalCloseButton()),
                openModalButton("modal-1"));

        then(modal).rendersAs("""
                <div>
                    <div id="modal-1" class="modal">
                        <div class="modal-background"></div>
                        <div class="modal-content">
                            <div class="box">Some text inside the modal.</div>
                        </div>
                        <button class="modal-close is-large" aria-label="close"></button>
                    </div>
                    <button class="button js-modal-trigger is-primary" data-target="modal-1">Open modal</button>
                </div>
                """);
    }

    @Test void shouldRenderModalImage() {
        var modal = div().content(
                modal().id("modal-2").content(
                        modalContent().content(
                                imageP(_4by3).content(img("https://bulma.io/images/placeholders/1280x960.png", "xxx"))),
                        modalCloseButton()),
                openModalButton("modal-2"));

        then(modal).rendersAs("""
                <div>
                    <div id="modal-2" class="modal">
                        <div class="modal-background"></div>
                        <div class="modal-content">
                            <p class="image is-4by3">
                                <img src="https://bulma.io/images/placeholders/1280x960.png" alt="xxx">
                            </p>
                        </div>
                        <button class="modal-close is-large" aria-label="close"></button>
                    </div>
                    <button class="button js-modal-trigger is-primary" data-target="modal-2">Open modal</button>
                </div>
                """);
    }

    @Test void shouldRenderModalCard() {
        var modal = div().content(
                modal().id("modal-3").content(modalCard().content(
                        header().content(
                                modalCardTitle("Modal title"),
                                close()),
                        section().content(
                                h1("Hello World"),
                                loremIpsumS()),
                        footer().content(
                                button("Save changes").is(SUCCESS),
                                button("Cancel")
                        ))),
                openModalButton("modal-3"));

        then(modal).rendersAs("""
                <div>
                    <div id="modal-3" class="modal">
                        <div class="modal-background"></div>
                        <div class="modal-card">
                            <header class="modal-card-head">
                                <p class="modal-card-title">Modal title</p>
                                <button class="delete" aria-label="close"></button>
                            </header>
                            <section class="section modal-card-body">
                                <h1>Hello World</h1>
                                $loremIpsum
                            </section>
                            <footer class="modal-card-foot">
                                <button class="button is-success">Save changes</button>
                                <button class="button">Cancel</button>
                            </footer>
                        </div>
                    </div>
                    <button class="button js-modal-trigger is-primary" data-target="modal-3">Open modal</button>
                </div>
                """.replace("$loremIpsum", loremIpsum()));
    }

    private static Button openModalButton(String target) {
        return button("Open modal").classes("js-modal-trigger").attr("data-target", target)
                .is(PRIMARY);
    }
}
