package com.github.t1.bulmajava.form;

import static java.util.Locale.ROOT;

public enum InputType {
    TEXT, PASSWORD, EMAIL, TEL, FILE,
    CHECKBOX, RADIO,
    SUBMIT, RESET;

    public String key() {return name().toLowerCase(ROOT);}
}
