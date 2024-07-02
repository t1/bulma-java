package com.github.t1.bulmajava.basic;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Attribute.StringAttribute.stringAttribute;
import static com.github.t1.bulmajava.basic.Body.scriptSrc;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Html extends AbstractElement<Html> {
    public static final String APPLICATION_JAVASCRIPT = "application/javascript";

    /**
     * It generally makes sense to give the html head a title;
     * if you really don't want it, pass <code>null</code>.
     */
    public static Html html(String title) {return new Html(title);}

    protected Html(String title) {
        super("html", Attributes.of(stringAttribute("lang", "en")),
                Basic.element("head").content(
                        meta_("charset", "utf-8"),
                        meta_("http-equiv", "X-UA-Compatible", "IE=edge"),
                        meta_name("viewport", "width=device-width, initial-scale=1"),
                        (title == null) ? null : Basic.element("title").content(title)));
    }


    public Html meta(String name, String value) {return head(meta_(name, value));}

    public Html metaName(String name, String value) {return head(meta_name(name, value));}

    public Html meta(String name1, String value1, String name2, String value2) {
        return head(meta_(name1, value1, name2, value2));
    }

    private static Element meta_(String name, String value) {
        return Basic.element("meta").close(false).attr(name, value);
    }

    @SuppressWarnings("SameParameterValue")
    private static Element meta_(String name, String value, String content) {
        return meta_(name, value, "content", content);
    }

    @SuppressWarnings("SameParameterValue")
    private static Element meta_name(String value, String content) {
        return meta_("name", value, "content", content);
    }

    private static Element meta_(String name1, String value1, String name2, String value2) {
        return meta_(name1, value1).attr(name2, value2);
    }

    public Html title(String title) {return head(Basic.element("title").content(title));}

    public Html stylesheet(String href) {
        return head(Basic.element("link").attr("rel", "stylesheet").close(false).attr("href", href));
    }

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
    public Html javaScriptCode(String code) {return body(Body.javaScriptCode(code));}

    public Html head(Renderable content) {return head((AbstractElement<?> e) -> e.content(content));}

    public Html head(Function<AbstractElement<?>, AbstractElement<?>> function) {
        return content(e -> e.hasName("head"), function, () -> Basic.element("head"));
    }

    @Override
    public Html content(Renderable content) {
        return content instanceof AbstractElement<?> e && e.hasName("body")
                ? super.content(content) : body(content);
    }

    public Html body(Renderable content) {return body((AbstractElement<?> e) -> e.content(content));}

    public Html body(Function<AbstractElement<?>, AbstractElement<?>> function) {
        return content(e -> e.hasName("body"), function, Body::body);
    }


    @Override public void render(Renderer renderer) {
        renderer.unsafeAppend("<!DOCTYPE html>\n");
        super.render(renderer);
    }
}
