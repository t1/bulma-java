package test.form;

import com.github.t1.bulmajava.basic.Style;
import com.github.t1.bulmajava.form.Checkbox;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.*;
import static com.github.t1.bulmajava.basic.Color.*;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.basic.Size.*;
import static com.github.t1.bulmajava.basic.Style.LIGHT;
import static com.github.t1.bulmajava.basic.Style.STATIC;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Button.buttons;
import static com.github.t1.bulmajava.elements.IconSize.*;
import static com.github.t1.bulmajava.elements.TableStyle.NARROW;
import static com.github.t1.bulmajava.form.Field.*;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.*;
import static com.github.t1.bulmajava.form.Radio.radio;
import static com.github.t1.bulmajava.form.Select.select;
import static com.github.t1.bulmajava.form.Textarea.textarea;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class GeneralTest {
    @Test void shouldRenderForm() {
        var form = div().style("width: 400px;").content(
                field().label("Name").control(
                        input(TEXT).placeholder("Text input")),
                field().label("Username").control(
                                input(TEXT).is(SUCCESS).placeholder("Text input").value("bulma"))
                        .iconLeft("user")
                        .iconRight("check")
                        .help("This username is available", SUCCESS),
                field().label("Email").control(
                                input(EMAIL).is(DANGER).placeholder("Email input").value("hello@"))
                        .iconLeft("envelope")
                        .iconRight("exclamation-triangle")
                        .help("This email is invalid", DANGER),
                field().label("Subject").control(select(null)
                        .option("1", "Select dropdown")
                        .option("2", "With options")),
                field().label("Message").control(
                        textarea().placeholder("Textarea")),
                field().control(
                        Checkbox.checkbox().content(
                                string("I agree to the"),
                                a("terms and conditions").href("#"))),
                field()
                        .control(radio("question").content(string("Yes")))
                        .control(radio("question").content(string("No"))),
                field().control(buttons().content(
                        button("Submit").is(LINK),
                        button("Cancel").is(LINK, LIGHT))));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Name</label>
                        <div class="control">
                            <input class="input" type="text" placeholder="Text input">
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Username</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-success" type="text" placeholder="Text input" value="bulma">
                            <span class="icon is-small is-left"><i class="fas fa-user"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-check"></i></span>
                        </div>
                        <p class="help is-success">This username is available</p>
                    </div>
                    <div class="field">
                        <label class="label">Email</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-danger" type="email" placeholder="Email input" value="hello@">
                            <span class="icon is-small is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-exclamation-triangle"></i></span>
                        </div>
                        <p class="help is-danger">This email is invalid</p>
                    </div>
                    <div class="field">
                        <label class="label">Subject</label>
                        <div class="control">
                            <div class="select">
                                <select>
                                    <option value="1">Select dropdown</option>
                                    <option value="2">With options</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Message</label>
                        <div class="control">
                            <textarea class="textarea" placeholder="Textarea"></textarea>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control">
                            <label class="checkbox">
                                <input type="checkbox">
                                I agree to the
                                <a href="#">terms and conditions</a>
                            </label>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control">
                            <label class="radio">
                                <input type="radio" name="question">
                                Yes
                            </label>
                            <label class="radio">
                                <input type="radio" name="question">
                                No
                            </label>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control">
                            <div class="buttons">
                                <button class="button is-link">Submit</button>
                                <button class="button is-link is-light">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFormField() {
        var form = div().style("width: 400px;").content(
                field().label("Label").control(
                                input(TEXT).placeholder("Text input"))
                        .help("This is a help text"));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Label</label>
                        <div class="control">
                            <input class="input" type="text" placeholder="Text input">
                        </div>
                        <p class="help">This is a help text</p>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderSpacedFormFields() {
        var form = div().style("width: 400px;").content(
                field().label("Name").control(
                        input(TEXT).placeholder("e.g Alex Smith")),
                field().label("Email").control(
                        input(EMAIL).placeholder("e.g. alexsmith@gmail.com")));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Name</label>
                        <div class="control">
                            <input class="input" type="text" placeholder="e.g Alex Smith">
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Email</label>
                        <div class="control">
                            <input class="input" type="email" placeholder="e.g. alexsmith@gmail.com">
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFormWithIcon() {
        var form = div().style("width: 400px;").content(
                field().control(input(EMAIL).placeholder("Email"))
                        .iconLeft("envelope")
                        .iconRight("check"),
                field().control(input(PASSWORD).placeholder("Password"))
                        .iconLeft("lock"),
                field().control(button("Login").is(SUCCESS)));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input" type="email" placeholder="Email">
                            <span class="icon is-small is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left">
                            <input class="input" type="password" placeholder="Password">
                            <span class="icon is-small is-left"><i class="fas fa-lock"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control">
                            <button class="button is-success">Login</button>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderSelectWithIcon() {
        var form = div().style("width: 400px;").content(
                field().control(select(null)
                                .option("1", "Country").selected()
                                .option("2", "Select dropdown")
                                .option("3", "With options"))
                        .iconLeft("globe"));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <div class="control has-icons-left">
                            <div class="select">
                                <select>
                                    <option value="1" selected>Country</option>
                                    <option value="2">Select dropdown</option>
                                    <option value="3">With options</option>
                                </select>
                            </div>
                            <span class="icon is-small is-left"><i class="fas fa-globe"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderSmallInputWithIcon() {
        var form = div().style("width: 400px;").content(
                field().label("Small input", SMALL).control(
                                input(EMAIL).is(SMALL).placeholder("Normal"))
                        .iconLeft("envelope")
                        .iconRight("check"));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label is-small">Small input</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-small" type="email" placeholder="Normal">
                            <span class="icon is-small is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderNormalSizeInputWithIcon() {
        var form = div().style("width: 400px;").content(
                field().label("Normal input").control(
                                input(EMAIL).placeholder("Extra small"))
                        .iconLeft("envelope", XS)
                        .iconRight("check", XS),
                field().control(
                                input(EMAIL).placeholder("Normal"))
                        .iconLeft("envelope", NORMAL)
                        .iconRight("check", NORMAL));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Normal input</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input" type="email" placeholder="Extra small">
                            <span class="icon is-small is-left"><i class="fas fa-envelope fa-xs"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-check fa-xs"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input" type="email" placeholder="Normal">
                            <span class="icon is-normal is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-normal is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderMediumSizeInputWithIcon() {
        var form = div().style("width: 400px;").content(
                field().label("Medium input", MEDIUM).control(
                                input(EMAIL).is(MEDIUM).placeholder("Extra small"))
                        .iconLeft("envelope", XS)
                        .iconRight("check", XS),
                field().control(
                                input(EMAIL).is(MEDIUM).placeholder("Small"))
                        .iconLeft("envelope", SM, NORMAL)
                        .iconRight("check", SM, NORMAL),
                field().control(
                                input(EMAIL).is(MEDIUM).placeholder("Normal"))
                        .iconLeft("envelope", MEDIUM)
                        .iconRight("check", MEDIUM));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label is-medium">Medium input</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-medium" type="email" placeholder="Extra small">
                            <span class="icon is-small is-left"><i class="fas fa-envelope fa-xs"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-check fa-xs"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-medium" type="email" placeholder="Small">
                            <span class="icon is-normal is-left"><i class="fas fa-envelope fa-sm"></i></span>
                            <span class="icon is-normal is-right"><i class="fas fa-check fa-sm"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-medium" type="email" placeholder="Normal">
                            <span class="icon is-medium is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-medium is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderLargeSizeInputWithIcon() {
        var form = div().style("width: 400px;").content(
                field().label("Large input", LARGE).control(
                                input(EMAIL).is(LARGE).placeholder("Extra small"))
                        .iconLeft("envelope", XS)
                        .iconRight("check", XS),
                field().control(
                                input(EMAIL).is(LARGE).placeholder("Small"))
                        .iconLeft("envelope", SM, NORMAL)
                        .iconRight("check", SM, NORMAL),
                field().control(
                                input(EMAIL).is(LARGE).placeholder("Normal"))
                        .iconLeft("envelope", LARGE)
                        .iconRight("check", LARGE),
                field().control(
                                input(EMAIL).is(LARGE).placeholder("Large"))
                        .iconLeft("envelope", LG, LARGE)
                        .iconRight("check", LG, LARGE));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label is-large">Large input</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-large" type="email" placeholder="Extra small">
                            <span class="icon is-small is-left"><i class="fas fa-envelope fa-xs"></i></span>
                            <span class="icon is-small is-right"><i class="fas fa-check fa-xs"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-large" type="email" placeholder="Small">
                            <span class="icon is-normal is-left"><i class="fas fa-envelope fa-sm"></i></span>
                            <span class="icon is-normal is-right"><i class="fas fa-check fa-sm"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-large" type="email" placeholder="Normal">
                            <span class="icon is-large is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-large is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-large" type="email" placeholder="Large">
                            <span class="icon is-large is-left"><i class="fas fa-envelope fa-lg"></i></span>
                            <span class="icon is-large is-right"><i class="fas fa-check fa-lg"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithButtonAddon() {
        var form = div().style("width: 400px;").content(field()
                .control(input(TEXT).placeholder("Find a repository"))
                .containsAddonRight(a("Search").button().is(INFO)));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field has-addons">
                        <div class="control">
                            <input class="input" type="text" placeholder="Find a repository">
                        </div>
                        <div class="control">
                            <a class="button is-info">Search</a>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithStaticButtonAddon() {
        var form = div().style("width: 400px;").content(field()
                .control(input(TEXT).placeholder("Your email"))
                .containsAddonRight(a("@gmail.com").button().is(STATIC)));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field has-addons">
                        <div class="control">
                            <input class="input" type="text" placeholder="Your email">
                        </div>
                        <div class="control">
                            <a class="button is-static">@gmail.com</a>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithLeftAndRightAddon() {
        var form = field()
                .control(input(TEXT).placeholder("Amount of money"))
                .containsAddonLeft(select(null).options("$", "£", "€"))
                .containsAddonRight(a("Transfer").button());

        then(form).rendersAs("""
                <div class="field has-addons">
                    <div class="control">
                        <div class="select">
                            <select>
                                <option value="$">$</option>
                                <option value="£">£</option>
                                <option value="€">€</option>
                            </select>
                        </div>
                    </div>
                    <div class="control">
                        <input class="input" type="text" placeholder="Amount of money">
                    </div>
                    <div class="control">
                        <a class="button">Transfer</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithExpandedFieldAndLeftAndRightAddon() {
        var form = field()
                .control(input(TEXT).placeholder("Amount of money"), EXPANDED)
                .containsAddonLeft(select(null).options("$", "£", "€"))
                .containsAddonRight(a("Transfer").button());

        then(form).rendersAs("""
                <div class="field has-addons">
                    <div class="control">
                        <div class="select">
                            <select>
                                <option value="$">$</option>
                                <option value="£">£</option>
                                <option value="€">€</option>
                            </select>
                        </div>
                    </div>
                    <div class="control is-expanded">
                        <input class="input" type="text" placeholder="Amount of money">
                    </div>
                    <div class="control">
                        <a class="button">Transfer</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithFieldAndLeftAndExpandedRightAddon() {
        var form = field()
                .control(input(TEXT).placeholder("Amount of money"))
                .containsAddonLeft(select(null).options("$", "£", "€"))
                .containsAddonRight(input(TEXT).placeholder("Target account"), EXPANDED);

        then(form).rendersAs("""
                <div class="field has-addons">
                    <div class="control">
                        <div class="select">
                            <select>
                                <option value="$">$</option>
                                <option value="£">£</option>
                                <option value="€">€</option>
                            </select>
                        </div>
                    </div>
                    <div class="control">
                        <input class="input" type="text" placeholder="Amount of money">
                    </div>
                    <div class="control is-expanded">
                        <input class="input" type="text" placeholder="Target account">
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithFieldAndExpandedLeftAddonAndRightAddon() {
        var form = field()
                .control(input(TEXT).placeholder("Amount of money"))
                .containsAddonLeft(input(TEXT).placeholder("Currency"), EXPANDED)
                .containsAddonRight(a("Transfer").button());

        then(form).rendersAs("""
                <div class="field has-addons">
                    <div class="control is-expanded">
                        <input class="input" type="text" placeholder="Currency">
                    </div>
                    <div class="control">
                        <input class="input" type="text" placeholder="Amount of money">
                    </div>
                    <div class="control">
                        <a class="button">Transfer</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithExpandedSelect() {
        var form = field()
                .control(select("country").is(Style.FULLWIDTH)
                        .options("Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador",
                                "Guyana", "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela"), EXPANDED)
                .containsAddonRight(button("Choose").submit().is(PRIMARY));

        then(form).rendersAs("""
                <div class="field has-addons">
                    <div class="control is-expanded">
                        <div class="select is-fullwidth">
                            <select name="country">
                                <option value="Argentina">Argentina</option>
                                <option value="Bolivia">Bolivia</option>
                                <option value="Brazil">Brazil</option>
                                <option value="Chile">Chile</option>
                                <option value="Colombia">Colombia</option>
                                <option value="Ecuador">Ecuador</option>
                                <option value="Guyana">Guyana</option>
                                <option value="Paraguay">Paraguay</option>
                                <option value="Peru">Peru</option>
                                <option value="Suriname">Suriname</option>
                                <option value="Uruguay">Uruguay</option>
                                <option value="Venezuela">Venezuela</option>
                            </select>
                        </div>
                    </div>
                    <div class="control">
                        <button class="button is-primary" type="submit">Choose</button>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderCenteredFieldWithAddons() {
        var form = field().classes("has-addons-centered")
                .control(input(TEXT).placeholder("Amount of money"))
                .containsAddonLeft(select("currency").options("$", "£", "€"))
                .containsAddonRight(a("Transfer").button().is(PRIMARY));

        then(form).rendersAs("""
                <div class="field has-addons-centered has-addons">
                    <div class="control">
                        <div class="select">
                            <select name="currency">
                                <option value="$">$</option>
                                <option value="£">£</option>
                                <option value="€">€</option>
                            </select>
                        </div>
                    </div>
                    <div class="control">
                        <input class="input" type="text" placeholder="Amount of money">
                    </div>
                    <div class="control">
                        <a class="button is-primary">Transfer</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderRightFieldWithAddons() {
        var form = field().classes("has-addons-right")
                .control(input(TEXT).placeholder("Amount of money"))
                .containsAddonLeft(select("currency").options("$", "£", "€"))
                .containsAddonRight(a("Transfer").button().is(PRIMARY));

        then(form).rendersAs("""
                <div class="field has-addons-right has-addons">
                    <div class="control">
                        <div class="select">
                            <select name="currency">
                                <option value="$">$</option>
                                <option value="£">£</option>
                                <option value="€">€</option>
                            </select>
                        </div>
                    </div>
                    <div class="control">
                        <input class="input" type="text" placeholder="Amount of money">
                    </div>
                    <div class="control">
                        <a class="button is-primary">Transfer</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderGroupedFields() {
        var form = field().grouped()
                .control(a("Submit").button().is(PRIMARY))
                .control(a("Cancel").button().is(LIGHT));

        then(form).rendersAs("""
                <div class="field is-grouped">
                    <div class="control">
                        <a class="button is-primary">Submit</a>
                    </div>
                    <div class="control">
                        <a class="button is-light">Cancel</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderGroupedFieldsCentered() {
        var form = field().grouped().classes("is-grouped-centered")
                .control(a("Submit").button().is(PRIMARY))
                .control(a("Cancel").button().is(LIGHT));

        then(form).rendersAs("""
                <div class="field is-grouped is-grouped-centered">
                    <div class="control">
                        <a class="button is-primary">Submit</a>
                    </div>
                    <div class="control">
                        <a class="button is-light">Cancel</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderGroupedFieldsRight() {
        var form = field().grouped().classes("is-grouped-right")
                .control(a("Submit").button().is(PRIMARY))
                .control(a("Cancel").button().is(LIGHT));

        then(form).rendersAs("""
                <div class="field is-grouped is-grouped-right">
                    <div class="control">
                        <a class="button is-primary">Submit</a>
                    </div>
                    <div class="control">
                        <a class="button is-light">Cancel</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderGroupedFieldsExpanded() {
        var form = field().grouped()
                .control(input(TEXT).placeholder("Find a repository"), EXPANDED)
                .control(a("Search").button().is(INFO));

        then(form).rendersAs("""
                <div class="field is-grouped">
                    <div class="control is-expanded">
                        <input class="input" type="text" placeholder="Find a repository">
                    </div>
                    <div class="control">
                        <a class="button is-info">Search</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderGroupedMultilineFieldsExpanded() {
        var form = div().style("width: 400px;").content(
                field().grouped().classes("is-grouped-multiline").control(
                        Stream.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen")
                                .map(name -> a(name).button())));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field is-grouped is-grouped-multiline">
                        <div class="control">
                            <a class="button">One</a>
                        </div>
                        <div class="control">
                            <a class="button">Two</a>
                        </div>
                        <div class="control">
                            <a class="button">Three</a>
                        </div>
                        <div class="control">
                            <a class="button">Four</a>
                        </div>
                        <div class="control">
                            <a class="button">Five</a>
                        </div>
                        <div class="control">
                            <a class="button">Six</a>
                        </div>
                        <div class="control">
                            <a class="button">Seven</a>
                        </div>
                        <div class="control">
                            <a class="button">Eight</a>
                        </div>
                        <div class="control">
                            <a class="button">Nine</a>
                        </div>
                        <div class="control">
                            <a class="button">Ten</a>
                        </div>
                        <div class="control">
                            <a class="button">Eleven</a>
                        </div>
                        <div class="control">
                            <a class="button">Twelve</a>
                        </div>
                        <div class="control">
                            <a class="button">Thirteen</a>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderHorizontalForm() {
        var form = div().style("width: 1000px;").content(
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label").is(NORMAL).content(
                                label("From")),
                        div().classes("field-body").content(
                                field().control(input(TEXT).placeholder("Name"), EXPANDED)
                                        .iconLeft("user"),
                                field().control(input(EMAIL).is(SUCCESS).placeholder("Email").value("alex@smith.com"), EXPANDED)
                                        .iconLeft("envelope")
                                        .iconRight("check"))),
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label"),
                        div().classes("field-body").content(
                                div().classes("field").is(EXPANDED).content(
                                        field().control(a("+44").button().is(STATIC))
                                                .containsAddonRight(input(TEL).placeholder("Your phone number"), EXPANDED),
                                        p("Do not enter the first zero").classes("help")))),
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label").is(NORMAL).content(
                                label("Department")),
                        div().classes("field-body").content(
                                field().is(NARROW).control(select("department").is(Style.FULLWIDTH)
                                        .options("Business development", "Marketing", "Sales")))),
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label").content(
                                label("Already a member?")),
                        div().classes("field-body").content(
                                field().is(NARROW)
                                        .control(radio("member").content(string("Yes")))
                                        .control(radio("member").content(string("No"))))),
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label").is(NORMAL).content(
                                label("Subject")),
                        div().classes("field-body").content(
                                field()
                                        .control(input(TEXT).is(DANGER).placeholder("e.g. Partnership opportunity"))
                                        .help("This field is required", DANGER))),
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label").is(NORMAL).content(
                                label("Question")),
                        div().classes("field-body").content(
                                field()
                                        .control(textarea().placeholder("Explain how we can help you")))),
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label"),
                        div().classes("field-body").content(
                                field()
                                        .control(button("Send message").is(PRIMARY)))));

        then(form).rendersAs("""
                <div style="width: 1000px;">
                    <div class="field is-horizontal">
                        <div class="field-label is-normal">
                            <label class="label">From</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control is-expanded has-icons-left">
                                    <input class="input" type="text" placeholder="Name">
                                    <span class="icon is-small is-left"><i class="fas fa-user"></i></span>
                                </div>
                            </div>
                            <div class="field">
                                <div class="control is-expanded has-icons-left has-icons-right">
                                    <input class="input is-success" type="email" placeholder="Email" value="alex@smith.com">
                                    <span class="icon is-small is-left"><i class="fas fa-envelope"></i></span>
                                    <span class="icon is-small is-right"><i class="fas fa-check"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label"></div>
                        <div class="field-body">
                            <div class="field is-expanded">
                                <div class="field has-addons">
                                    <div class="control">
                                        <a class="button is-static">+44</a>
                                    </div>
                                    <div class="control is-expanded">
                                        <input class="input" type="tel" placeholder="Your phone number">
                                    </div>
                                </div>
                                <p class="help">Do not enter the first zero</p>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-normal">
                            <label class="label">Department</label>
                        </div>
                        <div class="field-body">
                            <div class="field is-narrow">
                                <div class="control">
                                    <div class="select is-fullwidth">
                                        <select name="department">
                                            <option value="Business development">Business development</option>
                                            <option value="Marketing">Marketing</option>
                                            <option value="Sales">Sales</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label">
                            <label class="label">Already a member?</label>
                        </div>
                        <div class="field-body">
                            <div class="field is-narrow">
                                <div class="control">
                                    <label class="radio">
                                        <input type="radio" name="member">
                                        Yes
                                    </label>
                                    <label class="radio">
                                        <input type="radio" name="member">
                                        No
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-normal">
                            <label class="label">Subject</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <input class="input is-danger" type="text" placeholder="e.g. Partnership opportunity">
                                </div>
                                <p class="help is-danger">This field is required</p>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-normal">
                            <label class="label">Question</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <textarea class="textarea" placeholder="Explain how we can help you"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label"></div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <button class="button is-primary">Send message</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderHorizontalFormWithSizes() {
        var form = div().style("width: 1000px;").content(
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label").content(
                                label("No padding")),
                        div().classes("field-body").content(
                                field().control(Checkbox.checkbox().content("Checkbox")))),
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label").is(SMALL).content(
                                label("Small padding")),
                        div().classes("field-body").content(
                                field().control(input(TEXT).is(SMALL).placeholder("Small sized input")))),
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label").is(NORMAL).content(
                                label("Normal label")),
                        div().classes("field-body").content(
                                field().control(input(TEXT).placeholder("Normal sized input")))),
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label").is(MEDIUM).content(
                                label("Medium label")),
                        div().classes("field-body").content(
                                field().control(input(TEXT).is(MEDIUM).placeholder("Medium sized input")))),
                div().classes("field", "is-horizontal").content(
                        div().classes("field-label").is(LARGE).content(
                                label("Large label")),
                        div().classes("field-body").content(
                                field().control(input(TEXT).is(LARGE).placeholder("Large sized input")))));

        then(form).rendersAs("""
                <div style="width: 1000px;">
                    <div class="field is-horizontal">
                        <div class="field-label">
                            <label class="label">No padding</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <label class="checkbox">
                                        <input type="checkbox">
                                        Checkbox
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-small">
                            <label class="label">Small padding</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <input class="input is-small" type="text" placeholder="Small sized input">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-normal">
                            <label class="label">Normal label</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <input class="input" type="text" placeholder="Normal sized input">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-medium">
                            <label class="label">Medium label</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <input class="input is-medium" type="text" placeholder="Medium sized input">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-large">
                            <label class="label">Large label</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <input class="input is-large" type="text" placeholder="Large sized input">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderDisabledForm() {
        var form = div().style("width: 1000px;").content(
                fieldset().disabled().content(
                        field().label("Name").control(input(TEXT).placeholder("e.g Alex Smith")),
                        field().label("Email").control(input(EMAIL).placeholder("e.g. alexsmith@gmail.com"))));

        then(form).rendersAs("""
                <div style="width: 1000px;">
                    <fieldset disabled>
                        <div class="field">
                            <label class="label">Name</label>
                            <div class="control">
                                <input class="input" type="text" placeholder="e.g Alex Smith">
                            </div>
                        </div>
                        <div class="field">
                            <label class="label">Email</label>
                            <div class="control">
                                <input class="input" type="email" placeholder="e.g. alexsmith@gmail.com">
                            </div>
                        </div>
                    </fieldset>
                </div>
                """);
    }
}
