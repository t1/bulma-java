package com.github.t1.bulmajava.form;

import static java.util.Locale.ROOT;

public enum InputType {
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/checkbox">spec</a> */
    CHECKBOX,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/color">spec</a> */
    COLOR,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/date">spec</a> */
    DATE,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/datetime-local">spec</a> */
    DATETIME_LOCAL,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/email">spec</a> */
    EMAIL,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file">spec</a> */
    FILE,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/hidden">spec</a> */
    HIDDEN,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/image">spec</a> */
    IMAGE,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/month">spec</a> */
    MONTH,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/number">spec</a> */
    NUMBER,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/password">spec</a> */
    PASSWORD,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/radio">spec</a> */
    RADIO,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/range">spec</a> */
    RANGE,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/reset">spec</a> */
    RESET,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/search">spec</a> */
    SEARCH,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/submit">spec</a> */
    SUBMIT,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/tel">spec</a> */
    TEL,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/text">spec</a> */
    TEXT,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/time">spec</a> */
    TIME,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/url">spec</a> */
    URL,
    /** @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/week">spec</a> */
    WEEK;

    public String key() {return name().toLowerCase(ROOT).replace('_', '-');}
}
