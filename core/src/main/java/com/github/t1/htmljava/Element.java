package com.github.t1.htmljava;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Element extends AbstractElement<Element> {
    Element(@NonNull String name) {super(name);}
}
