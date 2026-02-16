package com.github.t1.htmljava;

@SuppressWarnings("UnusedReturnValue")
public class Renderer {
    private final StringBuilder buffer = new StringBuilder();
    private String indentString = "    ";
    private int indent = 0;

    public Renderer indentString(String indentString) {this.indentString = indentString; return this;}

    public Renderer in() {indent++; return this;}

    public Renderer out() {indent--; return this;}

    public Renderer indent() {return unsafeAppend(indentString.repeat(indent));}

    public Renderer nl() {
        if (lastChar() != '\n') unsafeAppend("\n");
        return this;
    }

    private char lastChar() {
        return buffer.isEmpty() ? 0 : buffer.charAt(buffer.length() - 1);
    }

    public Renderer unsafeAppend(String string) {
        buffer.append(string);
        return this;
    }

    public Renderer safeAppend(String string) {
        if (string != null) string.chars().forEach(this::safeAppend);
        return this;
    }

    private void safeAppend(int c) {
        switch (c) {
            case '&' -> buffer.append("&amp;");
            case '<' -> buffer.append("&lt;");
            case '>' -> buffer.append("&gt;");
            case '\"' -> buffer.append("&quot;");
            case '\'' -> buffer.append("&#x27;");
            default -> buffer.append((char) c);
        }
    }

    public String render() {return buffer.toString();}
}
