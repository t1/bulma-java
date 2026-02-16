package com.github.t1.htmljava;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Body extends AbstractElement<Body> {
    public static Body body() {return new Body();}

    public Body() {super("body");}
}
