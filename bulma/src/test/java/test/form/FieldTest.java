package test.form;

import com.github.t1.bulmajava.basic.Size;
import com.github.t1.htmljava.Anchor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import java.util.List;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Color.DANGER;
import static com.github.t1.bulmajava.basic.Color.INFO;
import static com.github.t1.bulmajava.basic.Color.LINK;
import static com.github.t1.bulmajava.basic.Color.PRIMARY;
import static com.github.t1.bulmajava.basic.Color.SUCCESS;
import static com.github.t1.bulmajava.basic.Size.LARGE;
import static com.github.t1.bulmajava.basic.Size.MEDIUM;
import static com.github.t1.bulmajava.basic.Size.NORMAL;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.Style.FULLWIDTH;
import static com.github.t1.bulmajava.basic.Style.LIGHT;
import static com.github.t1.bulmajava.basic.Style.STATIC;
import static com.github.t1.bulmajava.elements.Button.button;
import static com.github.t1.bulmajava.elements.Button.buttons;
import static com.github.t1.bulmajava.elements.Icon.icon;
import static com.github.t1.bulmajava.elements.IconSize.LG;
import static com.github.t1.bulmajava.elements.IconSize.SM;
import static com.github.t1.bulmajava.elements.IconSize.XS;
import static com.github.t1.bulmajava.elements.Tag.tag;
import static com.github.t1.bulmajava.form.Checkbox.checkbox;
import static com.github.t1.bulmajava.form.Field.EXPANDED;
import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Field.fieldset;
import static com.github.t1.bulmajava.form.Form.form;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.EMAIL;
import static com.github.t1.bulmajava.form.InputType.PASSWORD;
import static com.github.t1.bulmajava.form.InputType.TEL;
import static com.github.t1.bulmajava.form.InputType.TEXT;
import static com.github.t1.bulmajava.form.Radio.radios;
import static com.github.t1.bulmajava.form.Select.select;
import static com.github.t1.bulmajava.form.Textarea.textarea;
import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.htmljava.HtmlBasics.div;
import static com.github.t1.htmljava.HtmlBasics.em;
import static com.github.t1.htmljava.HtmlBasics.p;
import static com.github.t1.htmljava.HtmlBasics.span;
import static com.github.t1.htmljava.Renderable.RenderableString.string;
import static org.assertj.core.api.BDDAssertions.catchThrowableOfType;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class FieldTest {
    @Test void shouldRenderForm() {
        var form = div().style("width: 400px;").content(
                field("Name")
                        .content(input(TEXT).placeholder("Text input")),
                field("Username")
                        .content(input(TEXT).is(SUCCESS).placeholder("Text input").value("bulma"))
                        .iconLeft("user")
                        .iconRight("check")
                        .help("This username is available", SUCCESS),
                field("Email")
                        .content(input(EMAIL).is(DANGER).placeholder("Email input").value("hello@"))
                        .iconLeft("envelope")
                        .iconRight("exclamation-triangle")
                        .help(p().content(string("This email is "), em("invalid")), DANGER),
                field("Subject")
                        .content(select(null)
                                .option("1", "Select dropdown")
                                .option("2", "With options")),
                field("Message")
                        .content(textarea().placeholder("Textarea")),
                field().content(
                        checkbox().content(
                                string("I agree to the"), a("terms and conditions").href("#"))),
                field().content(radios("question")
                        .option("y", "Yes")
                        .option("n", "No")),
                field().content(buttons().content(
                        button("Submit").is(LINK),
                        button("Cancel").is(LINK, LIGHT))));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Name</label>
                        <div class="control">
                            <input class="input" type="text" placeholder="Text input" />
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Username</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-success" type="text" placeholder="Text input" value="bulma" />
                            <span class="icon is-left is-small"><i class="fas fa-user"></i></span>
                            <span class="icon is-right is-small"><i class="fas fa-check"></i></span>
                        </div>
                        <p class="help is-success">This username is available</p>
                    </div>
                    <div class="field">
                        <label class="label">Email</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-danger" type="email" placeholder="Email input" value="hello@" />
                            <span class="icon is-left is-small"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-right is-small"><i class="fas fa-exclamation-triangle"></i></span>
                        </div>
                        <p class="help is-danger">This email is <em>invalid</em></p>
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
                                <input type="checkbox" />
                                I agree to the
                                <a href="#">terms and conditions</a>
                            </label>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control">
                            <div class="radios">
                                <label class="radio">
                                    <input type="radio" value="y" name="question" />
                                    Yes
                                </label>
                                <label class="radio">
                                    <input type="radio" value="n" name="question" />
                                    No
                                </label>
                            </div>
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

    @Test void shouldRenderFieldWithRichLabel() {
        var field = field().label(span("petId"), tag("path"))
                .content(input(TEXT).attr("name", "petId"));

        then(field).rendersAs("""
                <div class="field">
                    <label class="label">
                        <span>petId</span>
                        <span class="tag">path</span>
                    </label>
                    <div class="control">
                        <input class="input" type="text" name="petId" />
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderField() {
        var field = field("Label")
                .content(input(TEXT).placeholder("Text input"))
                .help("This is a help text");

        then(field).rendersAs("""
                <div class="field">
                    <label class="label">Label</label>
                    <div class="control">
                        <input class="input" type="text" placeholder="Text input" />
                    </div>
                    <p class="help">This is a help text</p>
                </div>
                """);
    }

    @Test void shouldRenderSpacedFormFields() {
        var form = div().style("width: 400px;").content(
                field("Name")
                        .content(input(TEXT).placeholder("e.g Alex Smith")),
                field("Email")
                        .content(input(EMAIL).placeholder("e.g. alexsmith@gmail.com")));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Name</label>
                        <div class="control">
                            <input class="input" type="text" placeholder="e.g Alex Smith" />
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Email</label>
                        <div class="control">
                            <input class="input" type="email" placeholder="e.g. alexsmith@gmail.com" />
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFormWithIcon() {
        var form = div().style("width: 400px;").content(
                field().content(input(EMAIL).placeholder("Email"))
                        .iconLeft("envelope")
                        .iconRight("check"),
                field().content(input(PASSWORD).placeholder("Password"))
                        .iconLeft("lock"),
                field().content(button("Login").is(SUCCESS)));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input" type="email" placeholder="Email" />
                            <span class="icon is-left is-small"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-right is-small"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left">
                            <input class="input" type="password" placeholder="Password" />
                            <span class="icon is-left is-small"><i class="fas fa-lock"></i></span>
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
                field().content(select(null)
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
                            <span class="icon is-left is-small"><i class="fas fa-globe"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderSmallInputWithIcon() {
        var form = div().style("width: 400px;").content(
                field("Small input").is(SMALL)
                        .content(input(EMAIL).placeholder("Normal"))
                        .iconLeft("envelope")
                        .iconRight("check"));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label is-small">Small input</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-small" type="email" placeholder="Normal" />
                            <span class="icon is-left is-small"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-right is-small"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderNormalSizeInputWithIcon() {
        var form = div().style("width: 400px;").content(
                field("Normal input")
                        .content(input(EMAIL).placeholder("Extra small"))
                        .iconLeft("envelope", XS)
                        .iconRight("check", XS),
                field()
                        .content(input(EMAIL).placeholder("Normal"))
                        .iconLeft("envelope", NORMAL)
                        .iconRight("check", NORMAL));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label">Normal input</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input" type="email" placeholder="Extra small" />
                            <span class="icon is-left is-small"><i class="fas fa-envelope fa-xs"></i></span>
                            <span class="icon is-right is-small"><i class="fas fa-check fa-xs"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input" type="email" placeholder="Normal" />
                            <span class="icon is-normal is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-normal is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderMediumSizeInputWithIcon() {
        var form = div().style("width: 400px;").content(
                field("Medium input").is(MEDIUM)
                        .content(input(EMAIL).placeholder("Extra small"))
                        .iconLeft("envelope", XS)
                        .iconRight("check", XS),
                field().is(MEDIUM)
                        .content(input(EMAIL).placeholder("Small"))
                        .iconLeft("envelope", SM, NORMAL)
                        .iconRight("check", SM, NORMAL),
                field().is(MEDIUM)
                        .content(input(EMAIL).placeholder("Normal"))
                        .iconLeft("envelope", MEDIUM)
                        .iconRight("check", MEDIUM));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label is-medium">Medium input</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-medium" type="email" placeholder="Extra small" />
                            <span class="icon is-left is-small"><i class="fas fa-envelope fa-xs"></i></span>
                            <span class="icon is-right is-small"><i class="fas fa-check fa-xs"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-medium" type="email" placeholder="Small" />
                            <span class="icon is-normal is-left"><i class="fas fa-envelope fa-sm"></i></span>
                            <span class="icon is-normal is-right"><i class="fas fa-check fa-sm"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-medium" type="email" placeholder="Normal" />
                            <span class="icon is-medium is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-medium is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderLargeSizeInputWithIcon() {
        var form = div().style("width: 400px;").content(
                field("Large input").is(LARGE)
                        .content(input(EMAIL).placeholder("Extra small"))
                        .iconLeft("envelope", XS)
                        .iconRight("check", XS),
                field().is(LARGE)
                        .content(input(EMAIL).placeholder("Small"))
                        .iconLeft("envelope", SM, NORMAL)
                        .iconRight("check", SM, NORMAL),
                field().is(LARGE)
                        .content(input(EMAIL).placeholder("Normal"))
                        .iconLeft("envelope", LARGE)
                        .iconRight("check", LARGE),
                field().is(LARGE)
                        .content(input(EMAIL).placeholder("Large"))
                        .iconLeft("envelope", LG, LARGE)
                        .iconRight("check", LG, LARGE));

        then(form).rendersAs("""
                <div style="width: 400px;">
                    <div class="field">
                        <label class="label is-large">Large input</label>
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-large" type="email" placeholder="Extra small" />
                            <span class="icon is-left is-small"><i class="fas fa-envelope fa-xs"></i></span>
                            <span class="icon is-right is-small"><i class="fas fa-check fa-xs"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-large" type="email" placeholder="Small" />
                            <span class="icon is-normal is-left"><i class="fas fa-envelope fa-sm"></i></span>
                            <span class="icon is-normal is-right"><i class="fas fa-check fa-sm"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-large" type="email" placeholder="Normal" />
                            <span class="icon is-large is-left"><i class="fas fa-envelope"></i></span>
                            <span class="icon is-large is-right"><i class="fas fa-check"></i></span>
                        </div>
                    </div>
                    <div class="field">
                        <div class="control has-icons-left has-icons-right">
                            <input class="input is-large" type="email" placeholder="Large" />
                            <span class="icon is-large is-left"><i class="fas fa-envelope fa-lg"></i></span>
                            <span class="icon is-large is-right"><i class="fas fa-check fa-lg"></i></span>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithButtonAddon() {
        var field = field()
                .content(input(TEXT).placeholder("Find a repository"))
                .addonRight(a("Search").is(INFO));

        then(field).rendersAs("""
                <div class="field has-addons">
                    <div class="control">
                        <input class="input" type="text" placeholder="Find a repository" />
                    </div>
                    <div class="control">
                        <a class="is-info button">Search</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithStaticButtonAddon() {
        var field = field()
                .content(input(TEXT).placeholder("Your email"))
                .addonRight(a("@gmail.com").is(STATIC));

        then(field).rendersAs("""
                <div class="field has-addons">
                    <div class="control">
                        <input class="input" type="text" placeholder="Your email" />
                    </div>
                    <div class="control">
                        <a class="is-static button">@gmail.com</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithLeftAndRightAddon() {
        var form = field()
                .content(input(TEXT).placeholder("Amount of money"))
                .addonLeft(select(null).options("$", "£", "€"))
                .addonRight(a("Transfer"));

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
                        <input class="input" type="text" placeholder="Amount of money" />
                    </div>
                    <div class="control">
                        <a class="button">Transfer</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithExpandedFieldAndLeftAndRightAddon() {
        var form = field()
                .content(input(TEXT).placeholder("Amount of money").expanded())
                .addonLeft(select(null).options(List.of("$", "£", "€")))
                .addonRight(a("Transfer"));

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
                        <input class="input" type="text" placeholder="Amount of money" />
                    </div>
                    <div class="control">
                        <a class="button">Transfer</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithDisabledAutofocus() {
        var form = field()
                .content(input(TEXT).disabled().autofocus().expanded());

        then(form).rendersAs("""
                <div class="field">
                    <div class="control is-expanded">
                        <input class="input" type="text" disabled autofocus />
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithTabindex() {
        var form = field()
                .content(input(TEXT).tabindex(-1).expanded());

        then(form).rendersAs("""
                <div class="field">
                    <div class="control is-expanded">
                        <input class="input" type="text" tabindex="-1" />
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderReadonlyFields() {
        var form = field().allReadonly().content(
                input(TEXT).fieldName("a").value("A"),
                input(TEXT).fieldName("b").value("B"));

        then(form).rendersAs("""
                <div class="field">
                    <div class="control">
                        <input class="input" type="text" name="a" value="A" readonly />
                    </div>
                    <div class="control">
                        <input class="input" type="text" name="b" value="B" readonly />
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldPreventingEscapeKey() {
        var form = field()
                .content(input(TEXT).onkeydown("Escape", "event.preventDefault();").expanded());

        // We accept the warning about `event`: see https://stackoverflow.com/a/58341967/3333174
        //noinspection JSDeprecatedSymbols
        then(form).rendersAs("""
                <div class="field">
                    <div class="control is-expanded">
                        <input class="input" type="text" onkeydown="if (event.key === 'Escape') { event.preventDefault(); }" />
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithOnclick() {
        var form = field()
                .content(input(TEXT).onclick("window.location.href='about:blank'").expanded());

        then(form).rendersAs("""
                <div class="field">
                    <div class="control is-expanded">
                        <input class="input" type="text" onclick="window.location.href='about:blank'" />
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldLoggingEnterKey() {
        var form = field()
                .content(input(TEXT).onkeyup("Enter", "console.debug('enter pressed');").expanded());

        // We accept the warning about `event`: see https://stackoverflow.com/a/58341967/3333174
        //noinspection JSDeprecatedSymbols
        then(form).rendersAs("""
                <div class="field">
                    <div class="control is-expanded">
                        <input class="input" type="text" onkeyup="if (event.key === 'Enter') { console.debug('enter pressed'); }" />
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithFieldAndLeftAndExpandedRightAddon() {
        var form = field()
                .content(input(TEXT).placeholder("Amount of money"))
                .addonLeft(select(null).options("$", "£", "€"))
                .addonRight(input(TEXT).placeholder("Target account").expanded());

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
                        <input class="input" type="text" placeholder="Amount of money" />
                    </div>
                    <div class="control is-expanded">
                        <input class="input" type="text" placeholder="Target account" />
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithExpandedLeftAddonAndRightAddon() {
        var form = field()
                .content(input(TEXT).placeholder("Amount of money"))
                .addonLeft(input(TEXT).placeholder("Currency").expanded())
                .addonRight(a("Transfer"));

        then(form).rendersAs("""
                <div class="field has-addons">
                    <div class="control is-expanded">
                        <input class="input" type="text" placeholder="Currency" />
                    </div>
                    <div class="control">
                        <input class="input" type="text" placeholder="Amount of money" />
                    </div>
                    <div class="control">
                        <a class="button">Transfer</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderFieldWithExpandedSelect() {
        var field = field()
                .content(select("country").is(FULLWIDTH, EXPANDED)
                        .options("Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador",
                                "Guyana", "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela"))
                .addonRight(button("Choose").submit().is(PRIMARY));

        then(field).rendersAs("""
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
        // TODO has-addons-centered should be the alignment of the field
        var form = field().classes("has-addons-centered")
                .content(input(TEXT).placeholder("Amount of money"))
                .addonLeft(select("currency").options("$", "£", "€"))
                .addonRight(a("Transfer").is(PRIMARY));

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
                        <input class="input" type="text" placeholder="Amount of money" />
                    </div>
                    <div class="control">
                        <a class="is-primary button">Transfer</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderRightFieldWithAddons() {
        // TODO has-addons-right should be the alignment of the field
        var form = field().classes("has-addons-right")
                .content(input(TEXT).placeholder("Amount of money"))
                .addonLeft(select("currency").options("$", "£", "€"))
                .addonRight(a("Transfer").is(PRIMARY));

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
                        <input class="input" type="text" placeholder="Amount of money" />
                    </div>
                    <div class="control">
                        <a class="is-primary button">Transfer</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderGroupedFields() {
        var form = field().grouped().content(
                a("Submit").is(PRIMARY),
                a("Cancel").is(LIGHT));

        then(form).rendersAs("""
                <div class="field is-grouped">
                    <div class="control">
                        <a class="is-primary button">Submit</a>
                    </div>
                    <div class="control">
                        <a class="is-light button">Cancel</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderGroupedFieldsCentered() {
        var form = field().groupedCentered().content(
                a("Submit").is(PRIMARY),
                a("Cancel").is(LIGHT));

        then(form).rendersAs("""
                <div class="field is-grouped is-grouped-centered">
                    <div class="control">
                        <a class="is-primary button">Submit</a>
                    </div>
                    <div class="control">
                        <a class="is-light button">Cancel</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderGroupedFieldsRight() {
        var form = field().groupedRight()
                .content(a("Submit").is(PRIMARY))
                .content(a("Cancel").is(LIGHT));

        then(form).rendersAs("""
                <div class="field is-grouped is-grouped-right">
                    <div class="control">
                        <a class="is-primary button">Submit</a>
                    </div>
                    <div class="control">
                        <a class="is-light button">Cancel</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderGroupedFieldsExpanded() {
        var form = field().grouped().content(
                input(TEXT).placeholder("Find a repository").expanded(),
                a("Search").is(INFO));

        then(form).rendersAs("""
                <div class="field is-grouped">
                    <div class="control is-expanded">
                        <input class="input" type="text" placeholder="Find a repository" />
                    </div>
                    <div class="control">
                        <a class="is-info button">Search</a>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderGroupedMultilineFieldsExpanded() {
        var form = div().style("width: 400px;").content(
                field().groupedMultiline()
                        .content(Stream.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
                                        "Nine", "Ten", "Eleven", "Twelve", "Thirteen")
                                .map(Anchor::a)));

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
        var form = form().style("width: 1000px;").horizontal().content(
                field("From")
                        .content(input(TEXT).placeholder("Name").expanded())
                        .iconLeft("user")
                        .content(input(EMAIL).is(SUCCESS).placeholder("Email").value("alex@smith.com").expanded())
                        .iconLeft("envelope")
                        .iconRight("check"),
                field()
                        .content(input(TEL).placeholder("Your phone number").expanded())
                        .addonLeft(a("+44").href("#").is(STATIC))
                        .help("Do not enter the first zero"),
                field("Department")
                        .content(select("department")
                                .options("Business development", "Marketing", "Sales")),
                field("Already a member?")
                        .content(radios("member")
                                .option("y", "Yes")
                                .option("n", "No")),
                field("Subject")
                        .content(input(TEXT).is(DANGER).placeholder("e.g. Partnership opportunity"))
                        .help("This field is required", DANGER),
                field("Question")
                        .content(textarea().placeholder("Explain how we can help you")),
                field()
                        .content(button("Send message").is(PRIMARY)));

        // Bulma docs: the Department `select` is `fullwidth`, while the nested `field` is `narrow`;
        // also, the Already-a-member?-`field` is `narrow`... makes no sense, so we removed those.
        // The `has-addons` in the phone number field is no problem, and the `is-expanded` is not necessary.
        then(form).rendersAs("""
                <form style="width: 1000px;">
                    <div class="field is-horizontal">
                        <div class="field-label is-normal">
                            <label class="label">From</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control has-icons-left is-expanded">
                                    <input class="input" type="text" placeholder="Name" />
                                    <span class="icon is-left is-small"><i class="fas fa-user"></i></span>
                                </div>
                            </div>
                            <div class="field">
                                <div class="control has-icons-left has-icons-right is-expanded">
                                    <input class="input is-success" type="email" placeholder="Email" value="alex@smith.com" />
                                    <span class="icon is-left is-small"><i class="fas fa-envelope"></i></span>
                                    <span class="icon is-right is-small"><i class="fas fa-check"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field has-addons is-horizontal">
                        <div class="field-label is-normal"></div>
                        <div class="field-body">
                            <div class="field">
                                <div class="field has-addons">
                                    <div class="control">
                                        <a class="is-static button" href="#">+44</a>
                                    </div>
                                    <div class="control is-expanded">
                                        <input class="input" type="tel" placeholder="Your phone number" />
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
                            <div class="field">
                                <div class="control">
                                    <div class="select">
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
                            <div class="field">
                                <div class="control">
                                    <div class="radios">
                                        <label class="radio">
                                            <input type="radio" value="y" name="member" />
                                            Yes
                                        </label>
                                        <label class="radio">
                                            <input type="radio" value="n" name="member" />
                                            No
                                        </label>
                                    </div>
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
                                    <input class="input is-danger" type="text" placeholder="e.g. Partnership opportunity" />
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
                        <div class="field-label is-normal"></div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <button class="button is-primary">Send message</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                """);
    }

    @Test void shouldRenderHorizontalField() {
        var field = field().horizontal().content(input(TEXT).value("test"));

        then(field).rendersAs("""
                <div class="field is-horizontal">
                    <div class="field-label is-normal"></div>
                    <div class="field-body">
                        <div class="field">
                            <div class="control">
                                <input class="input" type="text" value="test" />
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderHorizontalFormWithSizes() {
        var form = div().style("width: 1000px;").content(
                field("No padding").horizontal()
                        .content(checkbox().content("Checkbox")),
                field("Small padding").horizontal().is(SMALL)
                        .content(input(TEXT).placeholder("Small sized input")),
                field("Normal label").horizontal().is(NORMAL)
                        .content(input(TEXT).placeholder("Normal sized input")),
                field("Medium label").horizontal().is(MEDIUM)
                        .content(input(TEXT).placeholder("Medium sized input")),
                field("Large label").horizontal().is(LARGE)
                        .content(input(TEXT).placeholder("Large sized input")));

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
                                        <input type="checkbox" />
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
                                    <input class="input is-small" type="text" placeholder="Small sized input" />
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
                                    <input class="input is-normal" type="text" placeholder="Normal sized input" />
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
                                    <input class="input is-medium" type="text" placeholder="Medium sized input" />
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
                                    <input class="input is-large" type="text" placeholder="Large sized input" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderHorizontalFormWithSize(Size size) {
        var form = div().style("width: 1000px;").content(
                field(size.key() + " select").horizontal().is(size)
                        .content(select(null)
                                .option("1", "Select dropdown")
                                .option("2", "With options")),
                field(size.key() + " textarea").horizontal().is(size)
                        .content(textarea().placeholder("Textarea")),
                field(size.key() + " check").horizontal().is(size)
                        .content(checkbox().content(string("I agree"))),
                field(size.key() + " radio").horizontal().is(size)
                        .content(radios("question")
                                .option("y", "Yes")
                                .option("n", "No")),
                field(size.key() + " button").horizontal().is(size)
                        .content(button(size.key() + " button")),
                field(size.key() + " input").horizontal().is(size)
                        .content(input(TEXT).placeholder(size.key() + " sized input")));

        then(form).rendersAs("""
                <div style="width: 1000px;">
                    <div class="field is-horizontal">
                        <div class="field-label is-${size}">
                            <label class="label">${size} select</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <div class="select is-${size}">
                                        <select>
                                            <option value="1">Select dropdown</option>
                                            <option value="2">With options</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-${size}">
                            <label class="label">${size} textarea</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <textarea class="textarea is-${size}" placeholder="Textarea"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-${size}">
                            <label class="label">${size} check</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <label class="checkbox is-${size}">
                                        <input type="checkbox" />
                                        I agree
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-${size}">
                            <label class="label">${size} radio</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <div class="radios is-${size}">
                                        <label class="radio">
                                            <input type="radio" value="y" name="question" />
                                            Yes
                                        </label>
                                        <label class="radio">
                                            <input type="radio" value="n" name="question" />
                                            No
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-${size}">
                            <label class="label">${size} button</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <button class="button is-${size}">${size} button</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-label is-${size}">
                            <label class="label">${size} input</label>
                        </div>
                        <div class="field-body">
                            <div class="field">
                                <div class="control">
                                    <input class="input is-${size}" type="text" placeholder="${size} sized input" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                """.replace("${size}", size.key()));
    }

    @Test void shouldRenderDisabledForm() {
        var form = div().style("width: 1000px;").content(
                fieldset().disabled().content(
                        field("Name").content(input(TEXT).placeholder("e.g Alex Smith")),
                        field("Email").content(input(EMAIL).placeholder("e.g. alexsmith@gmail.com"))));

        then(form).rendersAs("""
                <div style="width: 1000px;">
                    <fieldset disabled>
                        <div class="field">
                            <label class="label">Name</label>
                            <div class="control">
                                <input class="input" type="text" placeholder="e.g Alex Smith" />
                            </div>
                        </div>
                        <div class="field">
                            <label class="label">Email</label>
                            <div class="control">
                                <input class="input" type="email" placeholder="e.g. alexsmith@gmail.com" />
                            </div>
                        </div>
                    </fieldset>
                </div>
                """);
    }

    @Test void shouldFailToBuildLeftFieldIconWithoutContent() {
        var exception = catchThrowableOfType(AssertionError.class, () -> field()
                .iconLeft("left"));

        then(exception).hasMessage("You must add a content (e.g. input) before setting an icon");
    }

    @Test void shouldFailToBuildRightFieldIconWithoutContent() {
        var exception = catchThrowableOfType(AssertionError.class, () -> field()
                .iconRight("right"));

        then(exception).hasMessage("You must add a content (e.g. input) before setting an icon");
    }

    @Test void shouldFailToBuildFieldWithTwoLeftIcons() {
        var exception = catchThrowableOfType(AssertionError.class, () -> field()
                .content(input(TEXT))
                .iconLeft("left")
                .iconLeft("left2"));

        then(exception).hasMessage("Field already has left icon, cannot add another");
    }

    @Test void shouldFailToBuildFieldWithTwoRightIcons() {
        var exception = catchThrowableOfType(AssertionError.class, () -> field()
                .content(input(TEXT))
                .iconRight("right")
                .iconRight("right2"));

        then(exception).hasMessage("Field already has right icon, cannot add another");
    }

    @Test void shouldFailToBuildLeftFieldAddonWithoutContent() {
        var exception = catchThrowableOfType(AssertionError.class, () -> field()
                .addonLeft(button("left")));

        then(exception).hasMessage("You must add a content (e.g. input) before adding addons to it");
    }

    @Test void shouldFailToBuildRightFieldAddonWithoutContent() {
        var exception = catchThrowableOfType(AssertionError.class, () -> field()
                .addonRight(button("right")));

        then(exception).hasMessage("You must add a content (e.g. input) before adding addons to it");
    }

    @Test void shouldRenderFieldWithIconAddon() {
        var field = field()
                .content(input(TEXT).placeholder("Find a repository"))
                .addonRight(icon("search"));
                // TODO this breaks the styling: .help("this is a help text");

        then(field).rendersAs("""
                <div class="field has-addons">
                    <div class="control">
                        <input class="input" type="text" placeholder="Find a repository" />
                    </div>
                    <div class="control">
                        <button class="button">
                            <span class="icon"><i class="fas fa-search"></i></span>
                        </button>
                    </div>
                </div>
                """);
    }
}
