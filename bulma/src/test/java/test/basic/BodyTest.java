package test.basic;

import org.junit.jupiter.api.Test;

import static com.github.t1.bulmajava.basic.Body.body;
import static test.CustomAssertions.then;

class BodyTest {
    @Test void shouldRenderBody() {
        var tag = body();

        then(tag).rendersAs_notAll("""
                <body></body>
                """);
    }

    @Test void shouldRenderBodyWithNavbarFixedTop() {
        var tag = body().hasNavbarFixedTop();

        then(tag).rendersAs_notAll("""
                <body class="has-navbar-fixed-top"></body>
                """);
    }

    @Test void shouldRenderBodyWithNavbarFixedBottom() {
        var tag = body().hasNavbarFixedBottom();

        then(tag).rendersAs_notAll("""
                <body class="has-navbar-fixed-bottom"></body>
                """);
    }
}
