package com.github.t1.bulmajava.elements;

import com.github.t1.bulmajava.basic.BulmaElement;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class ProgressBar extends BulmaElement<ProgressBar> {
    public static ProgressBar progress(Integer value, int max) {
        var progress = new ProgressBar();
        if (value != null) progress = progress.attr("value", Integer.toString(value));
        return progress.attr("max", Integer.toString(max));
    }

    public ProgressBar() {super("progress", "progress");}
}
