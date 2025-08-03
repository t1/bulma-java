package com.github.t1.bulmajava.basic;

import com.github.t1.htmljava.PrefixedClassModifier;

public interface IsModifier extends PrefixedClassModifier {
    default String prefix() {return "is";}
}
