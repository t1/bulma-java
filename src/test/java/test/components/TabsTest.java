package test.components;

import com.github.t1.bulmajava.basic.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Alignment.CENTERED;
import static com.github.t1.bulmajava.basic.Alignment.RIGHT;
import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.li;
import static com.github.t1.bulmajava.basic.Basic.span;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.State.ACTIVE;
import static com.github.t1.bulmajava.basic.Style.FULLWIDTH;
import static com.github.t1.bulmajava.components.Tabs.tabs;
import static com.github.t1.bulmajava.elements.Icon.icon;
import static com.github.t1.bulmajava.elements.IconStyle.REGULAR;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class TabsTest {
    @Test void shouldRenderTabs() {
        var tabs = tabs().content(
                li().content(a("Pictures")).is(ACTIVE),
                li().content(a("Music")),
                li().content(a("Videos")),
                li().content(a("Documents")));

        then(tabs).rendersAs("""
                <div class="tabs">
                    <ul>
                        <li class="is-active">
                            <a>Pictures</a>
                        </li>
                        <li>
                            <a>Music</a>
                        </li>
                        <li>
                            <a>Videos</a>
                        </li>
                        <li>
                            <a>Documents</a>
                        </li>
                    </ul>
                </div>
                """);
    }

    @Test void shouldRenderCenteredTabs() {
        var tabs = tabs().is(CENTERED).content(
                li().content(a("Pictures")).is(ACTIVE),
                li().content(a("Music")),
                li().content(a("Videos")),
                li().content(a("Documents")));

        then(tabs).rendersAs("""
                <div class="tabs is-centered">
                    <ul>
                        <li class="is-active">
                            <a>Pictures</a>
                        </li>
                        <li>
                            <a>Music</a>
                        </li>
                        <li>
                            <a>Videos</a>
                        </li>
                        <li>
                            <a>Documents</a>
                        </li>
                    </ul>
                </div>
                """);
    }

    @Test void shouldRenderRightTabs() {
        var tabs = tabs().is(RIGHT).content(
                li().content(a("Pictures")).is(ACTIVE),
                li().content(a("Music")),
                li().content(a("Videos")),
                li().content(a("Documents")));

        then(tabs).rendersAs("""
                <div class="tabs is-right">
                    <ul>
                        <li class="is-active">
                            <a>Pictures</a>
                        </li>
                        <li>
                            <a>Music</a>
                        </li>
                        <li>
                            <a>Videos</a>
                        </li>
                        <li>
                            <a>Documents</a>
                        </li>
                    </ul>
                </div>
                """);
    }

    @Test void shouldRenderTabsWithIcons() {
        var tabs = tabs().is(CENTERED).content(
                li().content(a().content(
                        icon("image").is(SMALL).ariaHidden(true),
                        span("Pictures")
                )).is(ACTIVE),
                li().content(a().content(
                        icon("music").is(SMALL).ariaHidden(true),
                        span("Music")
                )),
                li().content(a().content(
                        icon("film").is(SMALL).ariaHidden(true),
                        span("Videos")
                )),
                li().content(a().content(
                        icon("file-alt", REGULAR).is(SMALL).ariaHidden(true),
                        span("Documents")
                )));

        then(tabs).rendersAs("""
                <div class="tabs is-centered">
                    <ul>
                        <li class="is-active">
                            <a>
                                <span class="icon is-small"><i class="fas fa-image" aria-hidden="true"></i></span>
                                <span>Pictures</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="fas fa-music" aria-hidden="true"></i></span>
                                <span>Music</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="fas fa-film" aria-hidden="true"></i></span>
                                <span>Videos</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="far fa-file-alt" aria-hidden="true"></i></span>
                                <span>Documents</span>
                            </a>
                        </li>
                    </ul>
                </div>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderSizedTabs(Size size) {
        var tabs = tabs().is(size).content(
                li().content(a("Pictures")).is(ACTIVE),
                li().content(a("Music")),
                li().content(a("Videos")),
                li().content(a("Documents")));

        then(tabs).rendersAs("""
                <div class="tabs is-$size">
                    <ul>
                        <li class="is-active">
                            <a>Pictures</a>
                        </li>
                        <li>
                            <a>Music</a>
                        </li>
                        <li>
                            <a>Videos</a>
                        </li>
                        <li>
                            <a>Documents</a>
                        </li>
                    </ul>
                </div>
                """.replace("$size", size.key()));
    }

    @Test void shouldRenderBoxedTabs() {
        var tabs = tabs().isBoxed().content(
                li().content(a().content(
                        icon("image").is(SMALL).ariaHidden(true),
                        span("Pictures")
                )).is(ACTIVE),
                li().content(a().content(
                        icon("music").is(SMALL).ariaHidden(true),
                        span("Music")
                )),
                li().content(a().content(
                        icon("film").is(SMALL).ariaHidden(true),
                        span("Videos")
                )),
                li().content(a().content(
                        icon("file-alt", REGULAR).is(SMALL).ariaHidden(true),
                        span("Documents")
                )));

        then(tabs).rendersAs("""
                <div class="tabs is-boxed">
                    <ul>
                        <li class="is-active">
                            <a>
                                <span class="icon is-small"><i class="fas fa-image" aria-hidden="true"></i></span>
                                <span>Pictures</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="fas fa-music" aria-hidden="true"></i></span>
                                <span>Music</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="fas fa-film" aria-hidden="true"></i></span>
                                <span>Videos</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="far fa-file-alt" aria-hidden="true"></i></span>
                                <span>Documents</span>
                            </a>
                        </li>
                    </ul>
                </div>
                """);
    }

    @Test void shouldRenderToggleTabs() {
        var tabs = tabs().isToggle().content(
                li().content(a().content(
                        icon("image").is(SMALL).ariaHidden(true),
                        span("Pictures")
                )).is(ACTIVE),
                li().content(a().content(
                        icon("music").is(SMALL).ariaHidden(true),
                        span("Music")
                )),
                li().content(a().content(
                        icon("film").is(SMALL).ariaHidden(true),
                        span("Videos")
                )),
                li().content(a().content(
                        icon("file-alt", REGULAR).is(SMALL).ariaHidden(true),
                        span("Documents")
                )));

        then(tabs).rendersAs("""
                <div class="tabs is-toggle">
                    <ul>
                        <li class="is-active">
                            <a>
                                <span class="icon is-small"><i class="fas fa-image" aria-hidden="true"></i></span>
                                <span>Pictures</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="fas fa-music" aria-hidden="true"></i></span>
                                <span>Music</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="fas fa-film" aria-hidden="true"></i></span>
                                <span>Videos</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="far fa-file-alt" aria-hidden="true"></i></span>
                                <span>Documents</span>
                            </a>
                        </li>
                    </ul>
                </div>
                """);
    }

    @Test void shouldRenderRoundedToggleTabs() {
        var tabs = tabs().isRoundedToggle().content(
                li().content(a().content(
                        icon("image").is(SMALL),
                        span("Pictures")
                )).is(ACTIVE),
                li().content(a().content(
                        icon("music").is(SMALL),
                        span("Music")
                )),
                li().content(a().content(
                        icon("film").is(SMALL),
                        span("Videos")
                )),
                li().content(a().content(
                        icon("file-alt").is(SMALL),
                        span("Documents")
                )));

        then(tabs).rendersAs("""
                <div class="tabs is-toggle is-toggle-rounded">
                    <ul>
                        <li class="is-active">
                            <a>
                                <span class="icon is-small"><i class="fas fa-image"></i></span>
                                <span>Pictures</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="fas fa-music"></i></span>
                                <span>Music</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="fas fa-film"></i></span>
                                <span>Videos</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon is-small"><i class="fas fa-file-alt"></i></span>
                                <span>Documents</span>
                            </a>
                        </li>
                    </ul>
                </div>
                """);
    }

    @Test void shouldRenderFullWidthTabs() {
        var tabs = tabs().is(FULLWIDTH).content(
                li().content(a().content(
                        icon("angle-left").ariaHidden(true),
                        span("Left")
                )),
                li().content(a().content(
                        icon("angle-up").ariaHidden(true),
                        span("Up")
                )),
                li().content(a().content(
                        span("Right"),
                        icon("angle-right").ariaHidden(true)
                )));

        then(tabs).rendersAs("""
                <div class="tabs is-fullwidth">
                    <ul>
                        <li>
                            <a>
                                <span class="icon"><i class="fas fa-angle-left" aria-hidden="true"></i></span>
                                <span>Left</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span class="icon"><i class="fas fa-angle-up" aria-hidden="true"></i></span>
                                <span>Up</span>
                            </a>
                        </li>
                        <li>
                            <a>
                                <span>Right</span>
                                <span class="icon"><i class="fas fa-angle-right" aria-hidden="true"></i></span>
                            </a>
                        </li>
                    </ul>
                </div>
                """);
    }
}
