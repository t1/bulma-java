package com.github.t1.bulmajava.helpers;

import com.github.t1.htmljava.Modifier;

public class ColorsHelper {
    public static Modifier light(Modifier color) {return () -> color.key() + "-light";}

    public static Modifier dark(Modifier color) {return () -> color.key() + "-dark";}
}
