package com.github.t1.bulmajava.elements;

public enum ImageRatio implements ImageDimension {
    square {
        @Override public String key() {return "square";}
    },
    _1by1, _5by4, _4by3, _3by2, _5by3, _16by9, _2by1,
    _3by1, _4by5, _3by4, _2by3, _3by5, _9by16, _1by2, _1by3;


    @Override public String key() {return name().substring(1);}
}
