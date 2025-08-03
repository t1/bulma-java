package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.BulmaElement;
import com.github.t1.bulmajava.elements.Delete;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Renderable;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static com.github.t1.bulmajava.basic.Size.sizes;
import static com.github.t1.htmljava.HtmlBasics.div;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Message extends BulmaElement<Message> {
    public static Message message() {return new Message();}

    /// Sometimes, you can't call the #message method, because you have to build the body separately
    public static Element messageBody() {return div().classes("message-body");}

    private Message() {super("article", "message");}


    public Message header(Renderable... content) {
        getOrCreate("message-header").content(content);
        return this;
    }

    public Message delete() {
        var delete = Delete.delete();
        sizes()
                .filter(this::hasModifier)
                .findFirst()
                .ifPresent(delete::is);
        return header(delete);
    }

    public Message body(Renderable... content) {return super.content(messageBody().content(content));}
}
