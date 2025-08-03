package com.github.t1.bulmajava.basic;

public record SizeModifier(int size) implements IsModifier {
    public static SizeModifier size(int size) {return new SizeModifier(size);}

    @Override public String name() {
        return Integer.toString(size);
    }
}
