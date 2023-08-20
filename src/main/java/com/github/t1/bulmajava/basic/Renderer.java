package com.github.t1.bulmajava.basic;

@SuppressWarnings("UnusedReturnValue")
public class Renderer {
    private final StringBuilder string = new StringBuilder();
    private int indent = 0;

    public Renderer in() {
        indent++;
        return this;
    }

    public Renderer out() {
        indent--;
        return this;
    }

    public Renderer appendIndent() {return append("    ".repeat(indent));}

    public Renderer nl() {
        if (lastChar() != '\n') append("\n");
        return this;
    }

    private char lastChar() {
        return string.charAt(string.length() - 1);
    }

    public Renderer append(String string) {
        this.string.append(string);
        return this;
    }

    public Renderer safeAppend(String string) {
        string.chars().forEach(this::safeAppend);
        return this;
    }

    private void safeAppend(int c) {
        switch (c) {
            case '&' -> string.append("&amp;");
            case '<' -> string.append("&lt;");
            case '>' -> string.append("&gt;");
            case '\"' -> string.append("&quot;");
            case '\'' -> string.append("&#x27;");
            default -> string.append((char) c);
        }
    }

    public String render() {return string.toString();}
}
