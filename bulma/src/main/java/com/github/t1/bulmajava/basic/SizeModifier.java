package com.github.t1.bulmajava.basic;

/// Numeric size modifier producing `is-1` through `is-12` (e.g., for column widths).
/// Use `size(4)` instead of this for a modifier that generates `is-4`.
public record SizeModifier(int size) implements IsModifier {
    public static SizeModifier size(int size) {return new SizeModifier(size);}

    @Override public String name() {
        return Integer.toString(size);
    }
}
