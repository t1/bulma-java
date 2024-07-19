package com.github.t1.bulmajava.elements;

import com.github.t1.htmljava.AbstractElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Block extends AbstractElement<Block> {
    public static AbstractElement<?> block() {return new Block("div");}

    public static AbstractElement<?> blockP() {return new Block("p");}

    private Block(String name) {super(name, "block");}
}
