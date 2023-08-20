package com.github.t1.bulmajava.basic;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Body extends AbstractElement<Body> {
    public static Body body() {return new Body();}

    public Body() {super("body");}


    public Body hasNavbarFixedTop() {return classes("has-navbar-fixed-top");}

    public Body hasNavbarFixedBottom() {return classes("has-navbar-fixed-bottom");}
}
