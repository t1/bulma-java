package com.github.t1.ui;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import static com.github.t1.bulmajava.elements.Content.content_;
import static com.github.t1.htmljava.HtmlBasics.p;

@Path("/")
public class HomePage {
    @Inject Page page;

    @GET public String homePage() {
        return page.title("Overview").content(content_().content(
                        p("Welcome to the Forms Test Application!"),
                        p("Use the navigation bar to access the different forms and features demoed.")))
                .render();
    }
}
