package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.Element;

import static com.github.t1.bulmajava.basic.Basic.element;

public class Title {
    public static Element subtitle(int level) {return element("h" + level).classes("subtitle");}

    public static Element subtitle(String text) {return subtitle(2, text);}

    public static Element subtitle(int level, String text) {return subtitle(level).content(text);}

    public static Element title(int level) {return element("h" + level).classes("title");}

    public static Element title(String text) {return title(1, text);}

    public static Element title(int level, String text) {return title(level).content(text);}

    public static Element titleP(String text) {return element("p").classes("title").content(text);}

    public static Element subtitleP(String text) {return element("p").classes("subtitle").content(text);}
}
