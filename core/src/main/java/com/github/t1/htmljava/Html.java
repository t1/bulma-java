package com.github.t1.htmljava;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.net.URI;
import java.util.function.Function;

import static com.github.t1.htmljava.HtmlBasics.*;
import static com.github.t1.htmljava.Renderable.Indented.indented;
import static com.github.t1.htmljava.Renderable.UnsafeString.unsafeString;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Html extends AbstractElement<Html> {
    /**
     * It generally makes sense to give the html head a title;
     * if you really don't want it, pass <code>null</code>.
     */
    public static Html html(String title) {return new Html(title);}

    protected Html(String title) {
        super("html", Attributes.of(Attribute.of("lang", "en")),
                element("head").content(
                        metaElement("charset", "utf-8"),
                        metaElement("http-equiv", "X-UA-Compatible").attr("content", "IE=edge"),
                        metaElement("name", "viewport").attr("content", "width=device-width, initial-scale=1"),
                        (title == null) ? null : element("title").content(title)));
    }


    public Html meta(String name, String value) {return head(metaElement(name, value));}

    public Html metaName(String name, String value) {
        return head(metaElement("name", name).attr("content", value));}

    public Html meta(String name1, String value1, String name2, String value2) {
        return head(metaElement(name1, value1).attr(name2, value2));
    }

    private static Element metaElement(String name, String value) {
        return element("meta").close(false).attr(name, value);
    }

    public Html title(String title) {return head(element("title").content(title));}

    public Html stylesheet(URI href) {return stylesheet(href.toString());}

    public Html stylesheet(String href) {
        return head(element("link").attr("rel", "stylesheet").close(false).attr("href", href));
    }

    /// Add a `style` element to the `head` containing that CSS, indented to the current level.
    ///
    /// Note that the style is _unsafe_!
    public Html styleElement(String style) {
        return head(element(Styles.KEY).content(indented(unsafeString(style))));
    }

    public Html script(URI src) {return script(src.toString());}

    public Html script(String src) {return head(scriptSrc(src));}

    public Html scriptBody(String src) {return body(scriptSrc(src));}

    public Html script(String src, String type) {return head(scriptSrc(src, type));}

    public Html scriptBody(String src, String type) {return body(scriptSrc(src, type));}

    public Html javaScript(String src) {return script(src, APPLICATION_JAVASCRIPT);}

    public Html javaScriptBody(String src) {return body(scriptSrc(src, APPLICATION_JAVASCRIPT));}

    /**
     * Add a <code>script</code> element to the body with <code>type="application/javascript</code>,
     * and the script code you provide indented to the current level.
     * <br/>
     * Note that the code is <em>unsafe</em>!
     */
    public Html javaScriptCode(String code) {return body(HtmlBasics.javaScriptCode(code));}

    public Html head(Renderable content) {return head((AbstractElement<?> e) -> e.content(content));}

    public Html head(Function<AbstractElement<?>, AbstractElement<?>> function) {
        return content(e -> e.hasTagName("head"), function, () -> element("head"));
    }

    @Override public Html content(Renderable content, int index) {
        return content instanceof AbstractElement<?> e && e.hasTagName("body")
                ? super.content(content, index) : body(content, index);
    }

    public Html body(Renderable content) {return body(content, LAST);}

    public Html body(Renderable content, int index) {return body((AbstractElement<?> e) -> e.content(content, index));}

    public Html body(Function<AbstractElement<?>, AbstractElement<?>> function) {
        return content(e -> e.hasTagName("body"), function, Body::body);
    }


    @Override public void render(Renderer renderer) {
        renderer.unsafeAppend("<!DOCTYPE html>\n");
        super.render(renderer);
    }

    public AbstractElement<?> body() {return findElement(e -> e.hasTagName("body")).orElseThrow();}
}
