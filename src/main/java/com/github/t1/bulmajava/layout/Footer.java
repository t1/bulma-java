package com.github.t1.bulmajava.layout;

import com.github.t1.bulmajava.basic.AbstractElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Footer extends AbstractElement<Footer> {
    public static Footer footer() {return new Footer();}

    public Footer() {super("footer", "footer");}
}
