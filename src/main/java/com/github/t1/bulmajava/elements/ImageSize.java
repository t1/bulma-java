package com.github.t1.bulmajava.elements;

public enum ImageSize implements ImageDimension {
    _16x16, _24x24, _32x32, _48x48, _64x64, _128x128;

    @Override public String key() {return name().substring(1);}
}
