package com.github.t1.bulmajava.basic;

@SuppressWarnings("UnusedReturnValue")
public class Renderer {
    private final StringBuilder string = new StringBuilder();
    private String indentString = "    ";
    private int indent = 0;

    public Renderer indentString(String indentString) {
        this.indentString = indentString;
        return this;
    }

    public Renderer in() {
        indent++;
        return this;
    }

    public Renderer out() {
        indent--;
        return this;
    }

    public Renderer appendIndent() {return append(indentString.repeat(indent));}

    public Renderer nl() {
        if (lastChar() != '\n') append("\n");
        return this;
    }

    private char lastChar() {
        return string.isEmpty() ? 0 : string.charAt(string.length() - 1);
    }

    public Renderer append(String string) {
        this.string.append(string);
        return this;
    }

    public Renderer safeAppend(String string) {
        if (string != null) string.chars().forEach(this::safeAppend);
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
