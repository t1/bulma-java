package com.github.t1.htmljava;

import java.time.temporal.Temporal;

import static com.github.t1.htmljava.Renderable.Indented.indented;
import static com.github.t1.htmljava.Renderable.UnsafeString.unsafeString;

public class HtmlBasics {
    public static final String APPLICATION_JAVASCRIPT = "application/javascript";

    public static Element element(String name) {return new Element(name);}

    public static Element scriptSrc(String src) {return element("script").attr("src", src);}

    public static Element scriptSrc(String src, String type) {return scriptSrc(src).attr("type", type);}

    public static Element javaScriptCode(String code) {
        return element("script").attr("type", APPLICATION_JAVASCRIPT)
                .content(indented(unsafeString(code)));
    }

    public static Element abbr(String title, String abbr) {
        return element("abbr").attr("title", title).content(abbr);
    }

    public static Element article() {return element("article");}

    public static Element aside() {return element("aside");}

    public static Element br() {return element("br").close(false);}

    public static Element code(String text) {return element("code").content(text).rendersOnSeparateLines(false);}

    public static Renderable comment(String content) {
        return renderer -> renderer.unsafeAppend("<!--").safeAppend(content).unsafeAppend("-->");
    }

    public static Element div() {return element("div");}

    public static Element em(String text) {return element("em").content(text).rendersOnSeparateLines(false);}

    public static Element footer() {return element("footer");}

    public static Element h1() {return element("h1");}

    public static Element h1(String text) {return h1().content(text);}

    public static Element h2() {return element("h2");}

    public static Element h2(String text) {return h2().content(text);}

    public static Element h3() {return element("h3");}

    public static Element h3(String text) {return h3().content(text);}

    public static Element h4() {return element("h4");}

    public static Element h4(String text) {return h4().content(text);}

    public static Element h5() {return element("h5");}

    public static Element h5(String text) {return h5().content(text);}

    public static Element h6() {return element("h6");}

    public static Element h6(String text) {return h6().content(text);}

    public static Element header() {return element("header");}

    public static Element hr() {return element("hr").close(false);}

    public static Element i() {return element("i").rendersOnSeparateLines(false);}

    public static Element i(String content) {return i().content(content);}

    public static Element li() {return element("li");}

    public static Element li(String text) {return li().content(text);}

    public static Element li(Renderable content) {return li().content(content);}

    public static Element nav() {return element("nav");}

    public static Renderable nbsp() {return unsafeString("&nbsp;");}

    public static Element ol() {return element("ol");}

    public static Element ol(Modifier modifier) {return ol().is(modifier);}

    public static Element p() {return element("p");}

    public static Element p(String text) {return p().content(text);}

    public static Element span() {return element("span");}

    public static Element span(String text) {return span().content(text);}

    public static Element small() {return element("small").rendersOnSeparateLines(false);}

    public static Element small(String text) {return small().content(text);}

    public static Element strong() {return element("strong").rendersOnSeparateLines(false);}

    public static Element strong(String text) {return strong().content(text);}

    public static Element time(Temporal datetime) {return element("time").attr("datetime", datetime.toString());}

    public static Element ul() {return element("ul");}
}
