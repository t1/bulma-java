package com.github.t1.htmljava;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.net.URI;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Anchor extends AbstractElement<Anchor> {
    public static Anchor a() {return new Anchor();}

    public static Anchor a(String content) {return a().content(content);}

    private Anchor() {super("a");}


    public Anchor href(URI href) {return attr("href", (href == null) ? null : href.toString());}

    public Anchor href(String href) {return attr("href", href);}

    public Anchor title(String title) {return attr("title", title);}

    public Anchor button() {return classes("button");}

    /** Marks this <code>a</code> as <code>"aria-current"="page"</code> */
    public Anchor active() {return attr("aria-current", "page");}
}
