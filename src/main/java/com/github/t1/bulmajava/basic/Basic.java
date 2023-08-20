package com.github.t1.bulmajava.basic;

import java.time.temporal.Temporal;

public class Basic {
    public static Element element(String name) {return Element.element_(name);}

    public static Element abbr(String title, String abbr) {
        return element("abbr").attr("title", title).contains(abbr);
    }

    public static Element article() {return element("article");}

    public static Element br() {return element("br").close(false);}

    public static Element code(String text) {return element("code").contains(text).rendersOnSeparateLines(false);}

    public static Renderable comment(String content) {
        return renderer -> renderer.append("<!-- ").safeAppend(content).append(" -->");
    }

    public static Element control() {return div().classes("control");}

    public static Element div() {return element("div");}

    public static Element em(String text) {return element("em").contains(text).rendersOnSeparateLines(false);}

    public static Element footer() {return element("footer");}

    public static Element group() {return div().classes("field", "is-grouped");}

    public static Element h1() {return element("h1");}

    public static Element h1(String text) {return h1().contains(text);}

    public static Element h2() {return element("h2");}

    public static Element h2(String text) {return h2().contains(text);}

    public static Element h3() {return element("h3");}

    public static Element h3(String text) {return h3().contains(text);}

    public static Element h4() {return element("h4");}

    public static Element h4(String text) {return h4().contains(text);}

    public static Element h5() {return element("h5");}

    public static Element h5(String text) {return h5().contains(text);}

    public static Element h6() {return element("h6");}

    public static Element h6(String text) {return h6().contains(text);}

    public static Element header() {return element("header");}

    public static Element hr() {return element("hr").close(false);}

    public static Element i() {return element("i").rendersOnSeparateLines(false);}

    public static Element label() {return element("label").classes("label");}

    public static Element label(String text) {return label().contains(text);}

    public static Element li() {return element("li");}

    public static Element li(String text) {return li().contains(text);}

    public static Element multilineGroup() {
        return group().classes("is-grouped-multiline").map(e -> control().contains(e));
    }

    public static Element nav() {return element("nav");}

    public static Element ol() {return element("ol");}

    public static Element ol(ListType type) {return ol().attr("type", type.code());}

    public static Element p() {return element("p");}

    public static Element p(String text) {return p().contains(text);}

    public static Element span() {return element("span");}

    public static Element span(String text) {return span().contains(text);}

    public static Element small() {return element("small").rendersOnSeparateLines(false);}

    public static Element small(String text) {return small().contains(text);}

    public static Element strong() {return element("strong").rendersOnSeparateLines(false);}

    public static Element strong(String text) {return strong().contains(text);}

    public static Element time(Temporal datetime) {return element("time").attr("datetime", datetime.toString());}

    public static Element ul() {return element("ul");}
}
