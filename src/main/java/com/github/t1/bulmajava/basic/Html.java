package com.github.t1.bulmajava.basic;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

import static com.github.t1.bulmajava.basic.Attribute.StringAttribute.stringAttribute;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Html extends AbstractElement<Html> {
    /**
     * It generally makes sense to give the html head a title;
     * if you really don't want it, pass <code>null</code>.
     */
    public static Html html(String title) {return new Html(title);}

    protected Html(String title) {
        super("html", Attributes.of(stringAttribute("lang", "en")),
                Basic.element("head").contains(
                        meta_("charset", "utf-8"),
                        meta_("http-equiv", "X-UA-Compatible", "IE=edge"),
                        meta_name("viewport", "width=device-width, initial-scale=1"),
                        (title == null) ? null : Basic.element("title").contains(title)));
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

    public Html title(String title) {return head(Basic.element("title").contains(title));}

    public Html stylesheet(String href) {
        return head(Basic.element("link").attr("rel", "stylesheet").close(false).attr("href", href));
    }

    public Html script(String src) {return head(Basic.element("script").attr("src", src));}

    public Html head(Renderable content) {return head((AbstractElement<?> e) -> e.contains(content));}

    public Html head(Function<AbstractElement<?>, AbstractElement<?>> function) {
        return element(e -> e.hasName("head"), function, () -> Basic.element("head"));
    }

    @Override
    public Html contains(Renderable content) {
        return content instanceof AbstractElement<?> e && e.hasName("body")
                ? super.contains(content) : body(content);
    }

    public Html body(Renderable content) {return body((AbstractElement<?> e) -> e.contains(content));}

    public Html body(Function<AbstractElement<?>, AbstractElement<?>> function) {
        return element(e -> e.hasName("body"), function, () -> Basic.element("body"));
    }


    @Override public void render(Renderer renderer) {
        renderer.append("<!DOCTYPE html>\n");
        super.render(renderer);
    }
}
