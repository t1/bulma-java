package com.github.t1.bulmajava.helpers;

import com.github.t1.bulmajava.basic.IsModifier;

public class ColorsHelper {
    public static IsModifier light(IsModifier color) {return () -> color.key() + "-light";}

    public static IsModifier dark(IsModifier color) {return () -> color.key() + "-dark";}
}
