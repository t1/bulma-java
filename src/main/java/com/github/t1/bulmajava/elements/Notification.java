package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.AbstractElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Notification extends AbstractElement<Notification> {
    public static AbstractElement<?> notification() {return new Notification();}

    public Notification() {super("div", "notification");}
}
