package test.basic;

import com.github.t1.bulmajava.basic.Renderer;
import org.junit.jupiter.api.Test;

import static com.github.t1.bulmajava.basic.Basic.h1;
import static com.github.t1.bulmajava.basic.Basic.p;
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

    @Test void shouldRenderHtmlWithTwoSpaces() {
        var renderer = new Renderer().indentString("  ");

        var tag = html(null);

        tag.render(renderer);
        //noinspection HtmlRequiredTitleElement
        then(renderer.render()).isEqualTo("""
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

    @Test void shouldRenderHtmlWithTabs() {
        var renderer = new Renderer().indentString("\t");

        var tag = html(null);

        tag.render(renderer);
        //noinspection HtmlRequiredTitleElement
        then(renderer.render()).isEqualTo("""
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
                        <link rel="stylesheet" href="bulma.min.css">
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

    @Test void shouldRenderHtmlWithEcmascript() {
        var tag = html(null).script("main.js", "application/ecmascript");

        //noinspection HtmlUnknownTarget,HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <script src="main.js" type="application/ecmascript"></script>
                    </head>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithScriptBody() {
        var tag = html(null).body(p("foo")).scriptBody("main.js");

        //noinspection HtmlUnknownTarget,HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                    </head>
                    <body>
                        <p>foo</p>
                        <script src="main.js"></script>
                    </body>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithEcmascriptScriptBody() {
        var tag = html(null).body(p("foo")).scriptBody("main.js", "application/ecmascript");

        //noinspection HtmlUnknownTarget,HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                    </head>
                    <body>
                        <p>foo</p>
                        <script src="main.js" type="application/ecmascript"></script>
                    </body>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithJavascriptBody() {
        var tag = html(null).body(p("foo")).javaScriptBody("main.js");

        //noinspection HtmlUnknownTarget,HtmlRequiredTitleElement
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                    </head>
                    <body>
                        <p>foo</p>
                        <script src="main.js" type="application/javascript"></script>
                    </body>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithJavaScriptHeader() {
        @SuppressWarnings("JSUnusedLocalSymbols")
        var tag = html(null).javaScript("foo");

        //noinspection HtmlRequiredTitleElement,HtmlUnknownTarget
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <script src="foo" type="application/javascript"></script>
                    </head>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithJavaScriptBody() {
        @SuppressWarnings("JSUnusedLocalSymbols")
        var tag = html(null).body(p("body")).javaScript("foo");

        //noinspection HtmlRequiredTitleElement,HtmlUnknownTarget
        then(tag).rendersAs_notAll("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <script src="foo" type="application/javascript"></script>
                    </head>
                    <body>
                        <p>body</p>
                    </body>
                </html>
                """);
    }

    @SuppressWarnings("TrailingWhitespacesInTextBlock")
    @Test void shouldRenderHtmlWithJavaScriptCode() {
        @SuppressWarnings("JSUnusedLocalSymbols")
        var tag = html(null).body(p("foo")).javaScriptCode("""
                function bodyFoo() {
                    console.log("foo");
                }

                function bodyBar() {
                    console.log("bar");
                    
                    console.log("baz");
                }
                """);

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
                        <p>foo</p>
                        <script type="application/javascript">
                            function bodyFoo() {
                                console.log("foo");
                            }
                            
                            function bodyBar() {
                                console.log("bar");
                                
                                console.log("baz");
                            }
                        </script>
                    </body>
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

    @Test void shouldRenderHtmlWithExplicitTitle() {
        var tag = html(null).title("The Title");

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
        var tag = html(null).content(h1("Hello"));

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
