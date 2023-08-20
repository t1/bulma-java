package test.components;

import com.github.t1.bulmajava.basic.Basic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Alignment.LEFT;
import static com.github.t1.bulmajava.basic.Alignment.RIGHT;
import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Renderable.RenderableString.string;
import static com.github.t1.bulmajava.components.Dropdown.dropdown;
import static com.github.t1.bulmajava.components.Dropdown.dropup;
import static com.github.t1.bulmajava.basic.State.ACTIVE;
import static com.github.t1.bulmajava.elements.TableStyle.HOVERABLE;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class DropdownTest {
    @Test void shouldRenderDropdown() {
        var dropdown = dropdown("Dropdown button", "dropdown-menu1").contains(
                        a("Dropdown item").href("#"),
                        a("Other dropdown item"),
                        a("Active dropdown item").is(ACTIVE).href("#"),
                        a("Other dropdown item").href("#"),
                        Basic.hr(),
                        a("With a divider").href("#"))
                .is(ACTIVE).style("margin-bottom: 210px;");

        then(dropdown).rendersAs("""
                <div class="dropdown is-active" style="margin-bottom: 210px;">
                    <div class="dropdown-trigger">
                        <button class="button" aria-haspopup="true" aria-controls="dropdown-menu1">
                            <span>Dropdown button</span>
                            <span class="icon is-small"><i class="fas fa-angle-down" aria-hidden="true"></i></span>
                        </button>
                    </div>
                    <div class="dropdown-menu" id="dropdown-menu1" role="menu">
                        <div class="dropdown-content">
                            <a class="dropdown-item" href="#">Dropdown item</a>
                            <a class="dropdown-item">Other dropdown item</a>
                            <a href="#" class="is-active dropdown-item">Active dropdown item</a>
                            <a class="dropdown-item" href="#">Other dropdown item</a>
                            <hr class="dropdown-divider">
                            <a class="dropdown-item" href="#">With a divider</a>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderDropdownWithAnyContent() {
        var dropdown = dropdown("Content", "dropdown-menu2").contains(
                        Basic.div().contains(
                                Basic.p().contains(
                                        string("You can insert "),
                                        Basic.strong("any type of content"),
                                        string(" within the dropdown menu.")
                                )),
                        Basic.hr(),
                        Basic.div().contains(
                                Basic.p().contains(
                                        string("You simply need to use a "),
                                        Basic.code("<div>"),
                                        string(" instead.")
                                )),
                        Basic.hr(),
                        a("This is a link").href("#"))
                .is(ACTIVE).style("margin-bottom: 210px;");

        then(dropdown).rendersAs("""
                <div class="dropdown is-active" style="margin-bottom: 210px;">
                    <div class="dropdown-trigger">
                        <button class="button" aria-haspopup="true" aria-controls="dropdown-menu2">
                            <span>Content</span>
                            <span class="icon is-small"><i class="fas fa-angle-down" aria-hidden="true"></i></span>
                        </button>
                    </div>
                    <div class="dropdown-menu" id="dropdown-menu2" role="menu">
                        <div class="dropdown-content">
                            <div class="dropdown-item">
                                <p>You can insert <strong>any type of content</strong> within the dropdown menu.</p>
                            </div>
                            <hr class="dropdown-divider">
                            <div class="dropdown-item">
                                <p>You simply need to use a <code>&lt;div&gt;</code> instead.</p>
                            </div>
                            <hr class="dropdown-divider">
                            <a class="dropdown-item" href="#">This is a link</a>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderClickableDropdownMenu() {
        var dropdown = dropdown("Click me", "dropdown-menu3").contains(
                a("Overview").href("#"),
                a("Modifiers").href("#"),
                a("Grid").href("#"),
                a("Form").href("#"),
                a("Elements").href("#"),
                a("Components").href("#"),
                a("Layout").href("#"),
                Basic.hr(),
                a("More").href("#"));

        then(dropdown).rendersAs("""
                <div class="dropdown">
                    <div class="dropdown-trigger">
                        <button class="button" aria-haspopup="true" aria-controls="dropdown-menu3">
                            <span>Click me</span>
                            <span class="icon is-small"><i class="fas fa-angle-down" aria-hidden="true"></i></span>
                        </button>
                    </div>
                    <div class="dropdown-menu" id="dropdown-menu3" role="menu">
                        <div class="dropdown-content">
                            <a class="dropdown-item" href="#">Overview</a>
                            <a class="dropdown-item" href="#">Modifiers</a>
                            <a class="dropdown-item" href="#">Grid</a>
                            <a class="dropdown-item" href="#">Form</a>
                            <a class="dropdown-item" href="#">Elements</a>
                            <a class="dropdown-item" href="#">Components</a>
                            <a class="dropdown-item" href="#">Layout</a>
                            <hr class="dropdown-divider">
                            <a class="dropdown-item" href="#">More</a>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderHoverableDropdownMenu() {
        var dropdown = dropdown("Hover me", "dropdown-menu4").contains(
                Basic.div().contains(Basic.p("You can insert any type of content within the dropdown menu."))
        ).is(HOVERABLE).style("margin-bottom: 100px;");

        then(dropdown).rendersAs("""
                <div class="dropdown is-hoverable" style="margin-bottom: 100px;">
                    <div class="dropdown-trigger">
                        <button class="button" aria-haspopup="true" aria-controls="dropdown-menu4">
                            <span>Hover me</span>
                            <span class="icon is-small"><i class="fas fa-angle-down" aria-hidden="true"></i></span>
                        </button>
                    </div>
                    <div class="dropdown-menu" id="dropdown-menu4" role="menu">
                        <div class="dropdown-content">
                            <div class="dropdown-item">
                                <p>You can insert any type of content within the dropdown menu.</p>
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderLeftAlignedDropdownMenu() {
        var dropdown = dropdown("Left aligned", "dropdown-menu5").contains(
                Basic.div().contains(Basic.p("The dropdown is left-aligned by default."))
        ).is(LEFT, ACTIVE).style("margin-bottom: 80px;");

        then(dropdown).rendersAs("""
                <div class="dropdown is-left is-active" style="margin-bottom: 80px;">
                    <div class="dropdown-trigger">
                        <button class="button" aria-haspopup="true" aria-controls="dropdown-menu5">
                            <span>Left aligned</span>
                            <span class="icon is-small"><i class="fas fa-angle-down" aria-hidden="true"></i></span>
                        </button>
                    </div>
                    <div class="dropdown-menu" id="dropdown-menu5" role="menu">
                        <div class="dropdown-content">
                            <div class="dropdown-item">
                                <p>The dropdown is left-aligned by default.</p>
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderRightAlignedDropdownMenu() {
        var dropdown = dropdown("Right aligned", "dropdown-menu6").contains(
                Basic.div().contains(Basic.p("Add the is-right modifier for a right-aligned dropdown."))
        ).is(RIGHT, ACTIVE).style("margin-bottom: 100px;");

        then(dropdown).rendersAs("""
                <div class="dropdown is-right is-active" style="margin-bottom: 100px;">
                    <div class="dropdown-trigger">
                        <button class="button" aria-haspopup="true" aria-controls="dropdown-menu6">
                            <span>Right aligned</span>
                            <span class="icon is-small"><i class="fas fa-angle-down" aria-hidden="true"></i></span>
                        </button>
                    </div>
                    <div class="dropdown-menu" id="dropdown-menu6" role="menu">
                        <div class="dropdown-content">
                            <div class="dropdown-item">
                                <p>Add the is-right modifier for a right-aligned dropdown.</p>
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }

    @Test void shouldRenderDropupMenu() {
        var dropdown = dropup("Dropup button", "dropdown-menu7").contains(
                Basic.div().contains(Basic.p("You can add the is-up modifier to have a dropdown menu that appears above the dropdown button."))
        ).is(ACTIVE).style("margin-top: 120px;");

        then(dropdown).rendersAs("""
                <div class="dropdown is-up is-active" style="margin-top: 120px;">
                    <div class="dropdown-trigger">
                        <button class="button" aria-haspopup="true" aria-controls="dropdown-menu7">
                            <span>Dropup button</span>
                            <span class="icon is-small"><i class="fas fa-angle-up" aria-hidden="true"></i></span>
                        </button>
                    </div>
                    <div class="dropdown-menu" id="dropdown-menu7" role="menu">
                        <div class="dropdown-content">
                            <div class="dropdown-item">
                                <p>You can add the is-up modifier to have a dropdown menu that appears above the dropdown button.</p>
                            </div>
                        </div>
                    </div>
                </div>
                """);
    }
}
