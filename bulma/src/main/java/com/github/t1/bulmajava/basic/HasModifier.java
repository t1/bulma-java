package com.github.t1.bulmajava.basic;

import com.github.t1.htmljava.PrefixedClassModifier;

public interface HasModifier extends PrefixedClassModifier {
    default String prefix() {return "has";}
}
