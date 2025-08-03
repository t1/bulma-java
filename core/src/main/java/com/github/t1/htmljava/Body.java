package com.github.t1.htmljava;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.htmljava.HtmlBasics.element;
import static com.github.t1.htmljava.Renderable.Indented.indented;
import static com.github.t1.htmljava.Renderable.UnsafeString.unsafeString;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Body extends AbstractElement<Body> {
    public static Element scriptSrc(String src, String type) {return scriptSrc(src).attr("type", type);}

    public static Element scriptSrc(String src) {return element("script").attr("src", src);}

    public static Element javaScriptCode(String code) {
        return element("script").attr("type", "application/javascript")
                .content(indented(unsafeString(code)));
    }

    public static Body body() {return new Body();}

    public Body() {super("body");}
}
