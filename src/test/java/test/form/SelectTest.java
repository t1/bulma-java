package test.form;

import com.github.t1.bulmajava.basic.Color;
import com.github.t1.bulmajava.basic.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Size.*;
import static com.github.t1.bulmajava.basic.State.*;
import static com.github.t1.bulmajava.basic.Style.ROUNDED;
import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Select.select;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class SelectTest {
    @Test void shouldRenderSelect() {
        var select = select(null)
                .option(null, "Select dropdown")
                .option(null, "With options");

        then(select).rendersAs("""
                <div class="select">
                    <select>
                        <option>Select dropdown</option>
                        <option>With options</option>
                    </select>
                </div>
                """);
    }

    @Test void shouldRenderMultipleSelectWithoutSize() {
        var select = select(null).multiple()
                .options("Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador", "Guyana",
                        "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela");

        then(select).rendersAs("""
                <div class="select is-multiple">
                    <select multiple>
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
                """);
    }

    @Test void shouldRenderMultipleSelectWithSize() {
        var select = select(null).multiple(8)
                .options("Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador", "Guyana",
                        "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela");

        then(select).rendersAs("""
                <div class="select is-multiple">
                    <select multiple size="8">
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
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderColorSelect(Color color) {
        var select = select(null).is(color)
                .option(null, "Select dropdown")
                .option(null, "With options");

        then(select).rendersAs("""
                <div class="select is-$color">
                    <select>
                        <option>Select dropdown</option>
                        <option>With options</option>
                    </select>
                </div>
                """.replace("$color", color.key()));
    }

    @Test void shouldRenderRoundedSelect() {
        var select = select(null).is(ROUNDED)
                .option(null, "Rounded dropdown")
                .option(null, "With options");

        then(select).rendersAs("""
                <div class="select is-rounded">
                    <select>
                        <option>Rounded dropdown</option>
                        <option>With options</option>
                    </select>
                </div>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderSizeSelect(Size size) {
        var select = select(null).is(size)
                .option(null, "Select dropdown")
                .option(null, "With options");

        then(select).rendersAs("""
                <div class="select is-$size">
                    <select>
                        <option>Select dropdown</option>
                        <option>With options</option>
                    </select>
                </div>
                """.replace("$size", size.key()));
    }

    @Test void shouldRenderHoveredSelect() {
        var select = select(null).is(HOVERED)
                .option(null, "Select dropdown")
                .option(null, "With options");

        then(select).rendersAs("""
                <div class="select">
                    <select class="is-hovered">
                        <option>Select dropdown</option>
                        <option>With options</option>
                    </select>
                </div>
                """);
    }

    @Test void shouldRenderFocusedSelect() {
        var select = select(null).is(FOCUSED)
                .option(null, "Select dropdown")
                .option(null, "With options");

        then(select).rendersAs("""
                <div class="select">
                    <select class="is-focused">
                        <option>Select dropdown</option>
                        <option>With options</option>
                    </select>
                </div>
                """);
    }

    @Test void shouldRenderLoadingSelect() {
        var select = select(null).is(LOADING)
                .option(null, "Select dropdown")
                .option(null, "With options");

        then(select).rendersAs("""
                <div class="select is-loading">
                    <select>
                        <option>Select dropdown</option>
                        <option>With options</option>
                    </select>
                </div>
                """);
    }

    @Test void shouldRenderSelectWithLeftIcon() {
        var select = field().control(select(null)
                        .option(null, "Country").selected()
                        .option(null, "Select dropdown")
                        .option(null, "With options"))
                .iconLeft("globe");

        then(select).rendersAs("""
                <div class="field">
                    <div class="control has-icons-left">
                        <div class="select">
                            <select>
                                <option selected>Country</option>
                                <option>Select dropdown</option>
                                <option>With options</option>
                            </select>
                        </div>
                        <span class="icon is-small is-left"><i class="fas fa-globe"></i></span>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderSmallSelectWithLeftIcon() {
        var select = field().control(select(null).is(SMALL)
                        .option(null, "Country").selected()
                        .option(null, "Select dropdown")
                        .option(null, "With options"))
                .iconLeft("globe");

        then(select).rendersAs("""
                <div class="field">
                    <div class="control has-icons-left">
                        <div class="select is-small">
                            <select>
                                <option selected>Country</option>
                                <option>Select dropdown</option>
                                <option>With options</option>
                            </select>
                        </div>
                        <span class="icon is-small is-left"><i class="fas fa-globe"></i></span>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderNormalSelectWithLeftIcon() {
        var select = field().control(select(null)
                        .option(null, "Country").selected()
                        .option(null, "Select dropdown")
                        .option(null, "With options"))
                .iconLeft("globe", NORMAL);

        then(select).rendersAs("""
                <div class="field">
                    <div class="control has-icons-left">
                        <div class="select">
                            <select>
                                <option selected>Country</option>
                                <option>Select dropdown</option>
                                <option>With options</option>
                            </select>
                        </div>
                        <span class="icon is-normal is-left"><i class="fas fa-globe"></i></span>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderMediumSelectWithLeftIcon() {
        var select = field().control(select(null).is(MEDIUM)
                        .option(null, "Country").selected()
                        .option(null, "Select dropdown")
                        .option(null, "With options"))
                .iconLeft("globe", MEDIUM);

        then(select).rendersAs("""
                <div class="field">
                    <div class="control has-icons-left">
                        <div class="select is-medium">
                            <select>
                                <option selected>Country</option>
                                <option>Select dropdown</option>
                                <option>With options</option>
                            </select>
                        </div>
                        <span class="icon is-medium is-left"><i class="fas fa-globe"></i></span>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderLargeSelectWithLeftIcon() {
        var select = field().control(select(null).is(LARGE)
                        .option(null, "Country").selected()
                        .option(null, "Select dropdown")
                        .option(null, "With options"))
                .iconLeft("globe", LARGE);

        then(select).rendersAs("""
                <div class="field">
                    <div class="control has-icons-left">
                        <div class="select is-large">
                            <select>
                                <option selected>Country</option>
                                <option>Select dropdown</option>
                                <option>With options</option>
                            </select>
                        </div>
                        <span class="icon is-large is-left"><i class="fas fa-globe"></i></span>
                    </div>
                </div>
                """);
    }
}
