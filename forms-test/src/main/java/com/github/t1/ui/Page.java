package com.github.t1.ui;

import com.github.t1.bulmajava.components.Navbar;
import com.github.t1.bulmajava.elements.Title;
import com.github.t1.bulmajava.layout.Section;
import com.github.t1.htmljava.Element;
import com.github.t1.htmljava.Html;
import com.github.t1.htmljava.Renderable;
import com.github.t1.htmljava.Renderer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.BulmaElement.TextModifier.text;
import static com.github.t1.bulmajava.basic.Color.SUCCESS;
import static com.github.t1.bulmajava.basic.Size.SMALL;
import static com.github.t1.bulmajava.basic.State.ACTIVE;
import static com.github.t1.bulmajava.basic.Style.WHITE;
import static com.github.t1.bulmajava.components.Navbar.NAVBAR_FIXED_TOP;
import static com.github.t1.bulmajava.components.Tabs.tabs;
import static com.github.t1.bulmajava.elements.Icon.icon;
import static com.github.t1.bulmajava.helpers.ColorsHelper.dark;
import static com.github.t1.bulmajava.layout.Container.container;
import static com.github.t1.bulmajava.layout.Section.section;
import static com.github.t1.htmljava.Anchor.a;
import static com.github.t1.htmljava.Body.body;
import static com.github.t1.htmljava.Html.html;
import static com.github.t1.htmljava.HtmlBasics.element;
import static com.github.t1.htmljava.HtmlBasics.li;
import static com.github.t1.htmljava.HtmlBasics.span;
import static com.github.t1.htmljava.HtmlBasics.time;
import static com.github.t1.htmljava.Renderable.UnsafeString.unsafeString;
import static java.util.Locale.ROOT;

@RequestScoped
public class Page implements Renderable {
    //private final HttpSession session;
    private final HttpHeaders headers;
    private final UriInfo uriInfo;

    @ConfigProperty(name = "htmx.debug", defaultValue = "false") boolean debug;

    private Html html;
    private Section section;

    @Inject public Page(HttpHeaders headers, UriInfo uriInfo) {
        this.headers = headers;
        this.uriInfo = uriInfo;
    }

    public Page title(String title) {
        //noinspection CommaExpressionJS,JSUnresolvedReference
        this.html = html(title)
                .stylesheet("/webjars/fortawesome__fontawesome-free/css/all.css")
                .stylesheet("/webjars/bulma/css/bulma.css")
                .script("/webjars/htmx.org/dist/htmx.js")
                .script("/webjars/htmx-ext-json-enc/json-enc.js")
                //.script("/webjars/htmx-ext-ws/ws.js")
                .script("/webjars/htmx-ext-debug/debug.js")
                //.script("validation.js")
                .content(body().has(NAVBAR_FIXED_TOP).content(
                        container().content(
                                this.section = section().classes("mt-6")
                                        .attr("hx-ext", "ws,json-enc" + (debug ? ",debug" : ""))
                                        //.attr("ws-connect", "/connect/" + session.getId())
                                        .content(
                                                navbar(),
                                                Title.title(title)))));
        var body = this.html.findElement(e -> e.hasName("body")).orElseThrow();
        body.content(element("script").content(unsafeString("""
                document.body.addEventListener("reload-page", function(){
                    window.location.reload();
                })
                """)));
        return this;
    }

    private Navbar navbar() {
        return Navbar.navbar("the-navbar").classes("is-fixed-top", "px-5", "has-shadow")
                .hasBackground(dark(SUCCESS))
                .start(tabs().content(
                        tab("Home", "/", "home"),
                        tab("Products", "/products", "search"),
                        tab("Customers", "/customers", "wrench"),
                        tab("Orders", "/orders", "file-alt")));
    }

    private Element tab(String text, String href, String icon) {
        var a = a().has(text(WHITE));
        if (icon != null) a = a.content(icon(icon).is(SMALL).ariaHidden(true));
        var item = li().content(a.content(span(text)).href(href));
        if (href.equals(uriInfo.getPath())) item = item.is(ACTIVE);
        return item;
    }

    public Page content(Renderable... content) {
        this.section.content(content);
        return this;
    }

    public Page content(Stream<Renderable> content) {
        this.section.content(content);
        return this;
    }


    @Override public void render(Renderer renderer) {html.render(renderer);}

    public Renderable formatted(FormatStyle formatStyle, LocalDate date) {
        var formatter = DateTimeFormatter.ofLocalizedDate(formatStyle).withLocale(acceptableLocale());
        return time(date).content(date.format(formatter));
    }

    /// The first acceptable locale from the request headers, or ROOT if none is specified.
    public Locale acceptableLocale() {
        var acceptableLanguages = headers.getAcceptableLanguages();
        if (acceptableLanguages == null || acceptableLanguages.isEmpty()) return ROOT;
        return acceptableLanguages.getFirst();
    }
}
