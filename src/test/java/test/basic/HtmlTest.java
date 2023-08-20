package test.basic;

import org.junit.jupiter.api.Test;

import static com.github.t1.bulmajava.basic.Basic.h1;
import static com.github.t1.bulmajava.basic.Html.html;
import static test.CustomAssertions.then;

class HtmlTest {
    @Test void shouldRenderHtml() {
        var tag = html(null);

        //noinspection HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                    </head>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithStylesheet() {
        var tag = html(null).stylesheet("bulma.min.css");

        //noinspection HtmlUnknownTarget,HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <link href="bulma.min.css" rel="stylesheet">
                    </head>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithScript() {
        var tag = html(null).script("main.js");

        //noinspection HtmlUnknownTarget,HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <script src="main.js"></script>
                    </head>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithMeta() {
        var tag = html(null).meta("about", "foo");

        //noinspection HtmlUnknownTarget,HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <meta about="foo">
                    </head>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithNamedMeta() {
        var tag = html(null).metaName("author", "me");

        //noinspection HtmlUnknownTarget,HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <meta name="author" content="me">
                    </head>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithTwoPartMeta() {
        var tag = html(null).meta("http-equiv", "refresh", "content", "3;url=https://example.org");

        //noinspection HtmlUnknownTarget,HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <meta http-equiv="refresh" content="3;url=https://example.org">
                    </head>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithTitle() {
        var tag = html("The Title");

        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <title>The Title</title>
                    </head>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithBody() {
        var tag = html(null).body(h1("Hello"));

        //noinspection HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                    </head>
                    <body>
                        <h1>Hello</h1>
                    </body>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithImplicitBody() {
        var tag = html(null).contains(h1("Hello"));

        //noinspection HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                    </head>
                    <body>
                        <h1>Hello</h1>
                    </body>
                </html>
                """);
    }
}
