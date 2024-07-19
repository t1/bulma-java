package test.components;

import com.github.t1.bulmajava.components.Pagination;
import com.github.t1.htmljava.Modifier;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Alignment.CENTERED;
import static com.github.t1.bulmajava.basic.Alignment.RIGHT;
import static com.github.t1.bulmajava.basic.Size.LARGE;
import static com.github.t1.bulmajava.basic.Size.MEDIUM;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.Style.ROUNDED;
import static com.github.t1.bulmajava.components.Pagination.pagination;
import static com.github.t1.htmljava.Renderable.RenderableString.string;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static test.CustomAssertions.then;
import static test.RenderTestExtension.render;

@ExtendWith(RenderTestExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class PaginationTest {
    @Order(10)
    @Test void shouldFailToRenderPaginationWithCurrentBelowMin() {
        var exception = catchException(() -> pagination(10, 9, 20)
                .previous("Previous")
                .next("Next page")
                .pages());

        then(exception).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("current value 9 is below minimum 10");
        render(string("        " + exception));
    }

    @Order(11)
    @Test void shouldFailToRenderPaginationWithCurrentAboveMax() {
        var exception = catchException(() -> pagination(10, 21, 20)
                .previous("Previous")
                .next("Next page")
                .pages());

        then(exception).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("current value 21 is above maximum 20");
        render(string("        " + exception));
    }


    @Order(101)
    @Test void shouldRenderPaginationOneOfOne() {
        var pagination = pagination(1, 1, 1)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous is-disabled">Previous</a>
                    <a class="pagination-next is-disabled">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 1" aria-current="page">1</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(201)
    @Test void shouldRenderPaginationOneOfTwo() {
        var pagination = pagination(1, 1, 2)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous is-disabled">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 1" aria-current="page">1</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 2">2</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(202)
    @Test void shouldRenderPaginationTwoOfTwo() {
        var pagination = pagination(1, 2, 2)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next is-disabled">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 2" aria-current="page">2</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(301)
    @Test void shouldRenderPaginationOneOfThree() {
        var pagination = pagination(1, 1, 3)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous is-disabled">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 1" aria-current="page">1</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 2">2</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 3">3</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(302)
    @Test void shouldRenderPaginationTwoOfThree() {
        var pagination = pagination(1, 2, 3)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 2" aria-current="page">2</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 3">3</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(303)
    @Test void shouldRenderPaginationThreeOfThree() {
        var pagination = pagination(1, 3, 3)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next is-disabled">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 2">2</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 3" aria-current="page">3</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(401)
    @Test void shouldRenderPaginationOneOfFour() {
        var pagination = pagination(1, 1, 4)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous is-disabled">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 1" aria-current="page">1</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 2">2</a>
                        </li>
                        <li>
                            <span class="pagination-ellipsis">&hellip;</span>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 4">4</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(402)
    @Test void shouldRenderPaginationTwoOfFour() {
        var pagination = pagination(1, 2, 4)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 2" aria-current="page">2</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 3">3</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 4">4</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(403)
    @Test void shouldRenderPaginationThreeOfFour() {
        var pagination = pagination(1, 3, 4)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 2">2</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 3" aria-current="page">3</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 4">4</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(404)
    @Test void shouldRenderPaginationFourOfFour() {
        var pagination = pagination(1, 4, 4)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next is-disabled">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <span class="pagination-ellipsis">&hellip;</span>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 3">3</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 4" aria-current="page">4</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(501)
    @Test void shouldRenderPaginationOneOfFive() {
        var pagination = pagination(1, 1, 5)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous is-disabled">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 1" aria-current="page">1</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 2">2</a>
                        </li>
                        <li>
                            <span class="pagination-ellipsis">&hellip;</span>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 5">5</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(502)
    @Test void shouldRenderPaginationTwoOfFive() {
        var pagination = pagination(1, 2, 5)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 2" aria-current="page">2</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 3">3</a>
                        </li>
                        <li>
                            <span class="pagination-ellipsis">&hellip;</span>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 5">5</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(503)
    @Test void shouldRenderPaginationThreeOfFive() {
        var pagination = pagination(1, 3, 5)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 2">2</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 3" aria-current="page">3</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 4">4</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 5">5</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(504)
    @Test void shouldRenderPaginationFourOfFive() {
        var pagination = pagination(1, 4, 5)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <span class="pagination-ellipsis">&hellip;</span>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 3">3</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 4" aria-current="page">4</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 5">5</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(505)
    @Test void shouldRenderPaginationFiveOfFive() {
        var pagination = pagination(1, 5, 5)
                .previous("Previous")
                .next("Next page")
                .pages();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next is-disabled">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <span class="pagination-ellipsis">&hellip;</span>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 4">4</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 5" aria-current="page">5</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(1000)
    @Test void shouldRenderPagination() {
        var pagination = somePagination();

        then(pagination).rendersAs("""
                <nav class="pagination" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <span class="pagination-ellipsis">&hellip;</span>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 45">45</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 46" aria-current="page">46</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 47">47</a>
                        </li>
                        <li>
                            <span class="pagination-ellipsis">&hellip;</span>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 86">86</a>
                        </li>
                    </ul>
                </nav>
                """);
    }

    @Order(1010)
    @Test void shouldRenderPaginationCentered() {
        var pagination = somePagination().is(CENTERED);

        then(pagination).rendersAs(styledPagination(CENTERED));
    }

    @Order(1020)
    @Test void shouldRenderPaginationRight() {
        var pagination = somePagination().is(RIGHT);

        then(pagination).rendersAs(styledPagination(RIGHT));
    }

    @Order(1050)
    @Test void shouldRenderPaginationRounded() {
        var pagination = somePagination().is(ROUNDED);

        then(pagination).rendersAs(styledPagination(ROUNDED));
    }

    @Order(1100)
    @Test void shouldRenderPaginationSmall() {
        var pagination = somePagination().is(SMALL);

        then(pagination).rendersAs(styledPagination(SMALL));
    }

    @Order(1110)
    @Test void shouldRenderPaginationMedium() {
        var pagination = somePagination().is(MEDIUM);

        then(pagination).rendersAs(styledPagination(MEDIUM));
    }

    @Order(1120)
    @Test void shouldRenderPaginationLarge() {
        var pagination = somePagination().is(LARGE);

        then(pagination).rendersAs(styledPagination(LARGE));
    }

    private static Pagination somePagination() {
        return pagination(1, 46, 86)
                .previous("Previous")
                .next("Next page")
                .pages();
    }

    private static String styledPagination(Modifier modifier) {
        return """
                <nav class="pagination $style" role="navigation" aria-label="pagination">
                    <a class="pagination-previous">Previous</a>
                    <a class="pagination-next">Next page</a>
                    <ul class="pagination-list">
                        <li>
                            <a class="pagination-link" aria-label="Goto page 1">1</a>
                        </li>
                        <li>
                            <span class="pagination-ellipsis">&hellip;</span>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 45">45</a>
                        </li>
                        <li>
                            <a class="pagination-link is-current" aria-label="Page 46" aria-current="page">46</a>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 47">47</a>
                        </li>
                        <li>
                            <span class="pagination-ellipsis">&hellip;</span>
                        </li>
                        <li>
                            <a class="pagination-link" aria-label="Goto page 86">86</a>
                        </li>
                    </ul>
                </nav>
                """.replace("$style", modifier.className());
    }
}
