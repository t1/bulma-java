package test.components;

import com.github.t1.bulmajava.basic.Alignment;
import com.github.t1.bulmajava.components.BreadcrumbSeparator;
import com.github.t1.bulmajava.basic.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.span;
import static com.github.t1.bulmajava.components.Breadcrumb.active;
import static com.github.t1.bulmajava.components.Breadcrumb.breadcrumb;
import static com.github.t1.bulmajava.elements.Icon.icon;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class BreadcrumbTest {
    @Test void shouldRenderBreadcrumb() {
        var nav = breadcrumb().contains(
                a().href("#").contains("Bulma"),
                a().href("#").contains("Documentation"),
                a().href("#").contains("Components"),
                active(a().href("#").contains("Breadcrumb")));

        then(nav).rendersAs("""
                <nav class="breadcrumb" aria-label="breadcrumbs">
                    <ul>
                        <li>
                            <a href="#">Bulma</a>
                        </li>
                        <li>
                            <a href="#">Documentation</a>
                        </li>
                        <li>
                            <a href="#">Components</a>
                        </li>
                        <li class="is-active">
                            <a href="#" aria-current="page">Breadcrumb</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderAlignedBreadcrumb(Alignment alignment) {
        var nav = breadcrumb().contains(
                        a().href("#").contains("Bulma"),
                        a().href("#").contains("Documentation"),
                        a().href("#").contains("Components"),
                        active(a().href("#").contains("Breadcrumb")))
                .is(alignment);

        then(nav).rendersAs("""
                <nav class="breadcrumb is-$alignment" aria-label="breadcrumbs">
                    <ul>
                        <li>
                            <a href="#">Bulma</a>
                        </li>
                        <li>
                            <a href="#">Documentation</a>
                        </li>
                        <li>
                            <a href="#">Components</a>
                        </li>
                        <li class="is-active">
                            <a href="#" aria-current="page">Breadcrumb</a>
                        </li>
                    </ul>
                </nav>
                """.replace("$alignment", alignment.key()));
    }

    @Test void shouldRenderBreadcrumbWithIcons() {
        var nav = breadcrumb().contains(
                a().contains(icon("home"), span("Bulma")).href("#"),
                a().contains(icon("book"), span("Documentation")).href("#"),
                a().contains(icon("puzzle-piece"), span("Components")).href("#"),
                active(a().contains(icon("thumbs-up"), span("Breadcrumb"))).href("#"));

        then(nav).rendersAs("""
                <nav class="breadcrumb" aria-label="breadcrumbs">
                    <ul>
                        <li>
                            <a href="#">
                                <span class="icon is-small"><i class="fas fa-home" aria-hidden="true"></i></span>
                                <span>Bulma</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="icon is-small"><i class="fas fa-book" aria-hidden="true"></i></span>
                                <span>Documentation</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="icon is-small"><i class="fas fa-puzzle-piece" aria-hidden="true"></i></span>
                                <span>Components</span>
                            </a>
                        </li>
                        <li class="is-active">
                            <a href="#" aria-current="page">
                                <span class="icon is-small"><i class="fas fa-thumbs-up" aria-hidden="true"></i></span>
                                <span>Breadcrumb</span>
                            </a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @ParameterizedTest @EnumSource void shouldRenderBreadcrumbWithAlternateSeparators(BreadcrumbSeparator separator) {
        var nav = breadcrumb().contains(
                        a().href("#").contains("Bulma"),
                        a().href("#").contains("Documentation"),
                        a().href("#").contains("Components"),
                        active(a().href("#").contains("Breadcrumb")))
                .is(separator);

        then(nav).rendersAs("""
                <nav class="breadcrumb has-$separator-separator" aria-label="breadcrumbs">
                    <ul>
                        <li>
                            <a href="#">Bulma</a>
                        </li>
                        <li>
                            <a href="#">Documentation</a>
                        </li>
                        <li>
                            <a href="#">Components</a>
                        </li>
                        <li class="is-active">
                            <a href="#" aria-current="page">Breadcrumb</a>
                        </li>
                    </ul>
                </nav>
                """.replace("$separator", separator.key()));
    }

    @ParameterizedTest @EnumSource void shouldRenderSizedBreadcrumbs(Size size) {
        var nav = breadcrumb().contains(
                        a().href("#").contains("Bulma"),
                        a().href("#").contains("Documentation"),
                        a().href("#").contains("Components"),
                        active(a().href("#").contains("Breadcrumb")))
                .is(size);

        then(nav).rendersAs("""
                <nav class="breadcrumb is-$size" aria-label="breadcrumbs">
                    <ul>
                        <li>
                            <a href="#">Bulma</a>
                        </li>
                        <li>
                            <a href="#">Documentation</a>
                        </li>
                        <li>
                            <a href="#">Components</a>
                        </li>
                        <li class="is-active">
                            <a href="#" aria-current="page">Breadcrumb</a>
                        </li>
                    </ul>
                </nav>
                """.replace("$size", size.key()));
    }
}
