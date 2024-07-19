package com.github.t1.bulmajava.elements;

import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Renderable;

import static com.github.t1.htmljava.Basic.element;
import static com.github.t1.htmljava.Renderable.RenderableString.string;

public class Title {
    public static Element subtitle(int level) {return element("h" + level).classes("subtitle").is(level);}

    public static Element subtitle(String text) {return subtitle(2, text);}

    public static Element subtitle(Renderable... content) {return subtitle(2, content);}

    public static Element subtitle(int level, String text) {return subtitle(level, string(text));}

    public static Element subtitle(int level, Renderable... content) {return subtitle(level).content(content).is(level);}

    public static Element title(int level) {return element("h" + level).classes("title").is(level);}

    public static Element title(String text) {return title(1, text);}

    public static Element title(Renderable... content) {return title(1, content);}

    public static Element title(int level, String text) {return title(level, string(text));}

    public static Element title(int level, Renderable... content) {return title(level).content(content);}

    public static Element titleP(String text) {return element("p").classes("title").content(text);}

    public static Element subtitleP(String text) {return element("p").classes("subtitle").content(text);}
}
