package test.html;

import com.github.t1.htmljava.Renderer;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static com.github.t1.htmljava.Html.html;
import static com.github.t1.htmljava.HtmlBasics.h1;
import static com.github.t1.htmljava.HtmlBasics.p;
import static org.assertj.core.api.BDDAssertions.then;

@SuppressWarnings("HtmlUnknownTarget")
class HtmlTest {
    @Test void shouldRenderHtml() {
        var tag = html(null);

        then(tag.render()).isEqualTo("""
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

    @Test void shouldRenderHtmlWithStylesheetString() {
        var tag = html(null).stylesheet("bulma.min.css");

        then(tag.render()).isEqualTo("""
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

    @Test void shouldRenderHtmlWithStylesheetUri() {
        var tag = html(null).stylesheet(URI.create("bulma.min.css"));

        then(tag.render()).isEqualTo("""
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

    @Test void shouldRenderHtmlWithStyleElement() {
        var tag = html(null).styleElement("body {\n    background-color: #f0f0f0;\n}");

        then(tag.render()).isEqualTo("""
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <style>
                            body {
                                background-color: #f0f0f0;
                            }
                        </style>
                    </head>
                </html>
                """);
    }

    @Test void shouldRenderHtmlWithScriptString() {
        var tag = html(null).script("main.js");

        then(tag.render()).isEqualTo("""
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

    @Test void shouldRenderHtmlWithScriptUri() {
        var tag = html(null).script(URI.create("main.js"));

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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

        then(tag.render()).isEqualTo("""
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
