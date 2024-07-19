package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.elements.Delete;
import com.github.t1.htmljava.AbstractElement;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Size.sizes;
import static com.github.t1.htmljava.Basic.div;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Message extends AbstractElement<Message> {
    public static Message message() {return new Message();}

    private Message() {super("article", "message");}


    public Message header(Renderable... content) {
        getOrCreate("message-header").content(content);
        return this;
    }

    public Message delete() {
        var delete = Delete.delete();
        sizes()
                .filter(this::has)
                .findFirst()
                .ifPresent(delete::is);
        return header(delete);
    }

    public Message body(Renderable... content) {return super.content(div().classes("message-body").content(content));}
}
