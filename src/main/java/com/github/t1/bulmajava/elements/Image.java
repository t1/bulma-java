package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.AbstractElement;
import com.github.t1.bulmajava.basic.Basic;
import com.github.t1.bulmajava.basic.Modifier;

import static com.github.t1.bulmajava.basic.Attribute.noValueAttribute;
import static com.github.t1.bulmajava.basic.Basic.p;

public class Image {
    public static AbstractElement<?> iframe(String src, Modifier... modifiers) {
        return Basic.element("iframe").is(modifiers).attr("src", src);
    }

    public static AbstractElement<?> image(ImageDimension dimension, String src, String alt, Modifier... modifiers) {
        return image(dimension).contains(img(src, alt, modifiers));
    }

    public static AbstractElement<?> image(ImageDimension dimension) {
        return figure().classes("image").classes(dimension.className());
    }

    public static AbstractElement<?> imageP(ImageDimension dimension) {
        return p().classes("image").classes(dimension.className());
    }

    public static AbstractElement<?> img(String src, String alt, String width, String height, Modifier... modifiers) {
        return img(src, alt, modifiers)
                .attr("width", width)
                .attr("height", height);
    }

    public static AbstractElement<?> img(String src, String alt, Modifier... modifiers) {
        var img = Basic.element("img").close(false)
                .is(modifiers)
                .attr("src", src);
        if (alt != null) img = img.attr("alt", alt);
        return img;
    }

    public static AbstractElement<?> movie(ImageRatio ratio, String src, String width, String height, Modifier... modifiers) {
        return image(ratio).contains(
                iframe(src, modifiers)
                        .classes("has-ratio")
                        .attr("width", width).attr("height", height)
                        .attr(noValueAttribute("allowfullscreen")));
    }

    public static AbstractElement<?> figure() {return Basic.element("figure");}
}
