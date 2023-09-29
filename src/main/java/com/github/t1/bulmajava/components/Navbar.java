package com.github.t1.bulmajava.components;

import com.github.t1.bulmajava.basic.*;
import com.github.t1.bulmajava.elements.MenuActivationType;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Optional;
import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Attribute.StringAttribute.stringAttribute;
import static com.github.t1.bulmajava.basic.Basic.div;
import static com.github.t1.bulmajava.basic.Basic.span;

@EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
public class Navbar extends AbstractElement<Navbar> {
    public static Navbar navbar(String menuId) {return new Navbar(menuId);}

    private String menuId;

    private Navbar(String menuId) {
        super("nav", Attributes.of(
                Classes.of("navbar"),
                stringAttribute("role", "navigation"),
                stringAttribute("aria-label", "navigation")));
        this.menuId = menuId;
    }

    private static Renderable item(Renderable renderable) {
        if (renderable instanceof AbstractElement<?> e && !e.hasClass("navbar-burger"))
            return e.hasName("hr") ? e.classes("navbar-divider") : e.classes("navbar-item");
        return renderable;
    }

    public static Element navbarStart() {return div().classes("navbar-start").map(Navbar::item);}

    public Navbar brand(Renderable... renderables) {
        return this.content(div().classes("navbar-brand").map(Navbar::item).content(renderables));
    }

    public Navbar burger() {
        findElement("navbar-brand").orElseThrow().content(navbarBurger(menuId));
        return this;
    }

    private static Anchor navbarBurger(String targetId) {
        var burger = a()
                .classes("navbar-burger")
                .attr("role", "button")
                .ariaLabel("menu")
                .attr("aria-expanded", "false")
                .content(
                        span().ariaHidden(true),
                        span().ariaHidden(true),
                        span().ariaHidden(true));
        if (targetId != null) burger = burger.attr("data-target", targetId);
        return burger;
    }

    /** Use {@link #start(Renderable...)}/{@link #end(Renderable...)} instead! */
    @Deprecated
    @Override public Navbar content(String content) {return super.content(content);}

    /** Use {@link #start(Renderable...)}/{@link #end(Renderable...)} instead! */
    @Deprecated
    @Override public Navbar content(Stream<Renderable> content) {return super.content(content);}

    /** Use {@link #start(Renderable...)}/{@link #end(Renderable...)} instead! */
    @Deprecated
    @Override public Navbar content(Renderable... content) {return super.content(content);}

    /** Use {@link #start(Renderable...)}/{@link #end(Renderable...)} instead! */
    @Deprecated
    @Override public Navbar content(Renderable content) {return super.content(content);}

    public Navbar isFixedTop() {return classes("is-fixed-top");}

    public Navbar isFixedBottom() {return classes("is-fixed-bottom");}

    public Navbar isTransparent() {return classes("is-transparent");}

    public Navbar start(Renderable... newStartContent) {
        var menu = findElement("navbar-menu").orElseGet(this::navbarMenu);
        return content(menu.content(navbarStart().content(newStartContent)));
    }

    public Navbar end(Renderable... newEndContent) {
        var menu = getOrCreate("navbar-menu", this::navbarMenu);
        var end = menu.getOrCreate("navbar-end", () -> div().map(Navbar::item));
        end.content(newEndContent);
        return this;
    }

    private Element navbarMenu() {return div().classes("navbar-menu").id(menuId);}

    @EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
    public static class NavbarDropdown extends AbstractElement<NavbarDropdown> {
        public static NavbarDropdown navbarDropdown(String dropdownName, MenuActivationType activationType) {
            return new NavbarDropdown().is(activationType).content(
                    a(dropdownName).classes("navbar-link"),
                    div().classes("navbar-dropdown").map(Navbar::item));
        }

        private NavbarDropdown() {super("div", "has-dropdown");}

        public NavbarDropdown content(Renderable content) {
            var dropdown = findDropdown();
            if (dropdown.isPresent()) {
                dropdown.get().content(item(content));
                return this;
            } else return super.content(content);
        }

        private Optional<AbstractElement<?>> findDropdown() {return findElement("navbar-dropdown");}

        private Optional<AbstractElement<?>> findLink() {return findElement("navbar-link");}

        @Override public NavbarDropdown is(Modifier... modifiers) {
            for (var modifier : modifiers) {
                if (modifier instanceof MenuActivationType) classes(modifier.className());
                else findDropdown().orElseThrow().classes(modifier.className());
            }
            return this;
        }

        public NavbarDropdown arrowless() {
            var link = findLink().orElseThrow();
            link.classes("is-arrowless");
            return this;
        }
    }
}
