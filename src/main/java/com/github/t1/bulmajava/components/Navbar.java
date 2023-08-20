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

    private static final Attributes NAVBAR_ATTRIBUTES = Attributes.of(
            Classes.of("navbar"),
            stringAttribute("role", "navigation"),
            stringAttribute("aria-label", "navigation"));

    private final String menuId;

    private Navbar(String menuId) {
        super("nav", NAVBAR_ATTRIBUTES);
        this.menuId = menuId;
    }

    private static Renderable item(Renderable renderable) {
        if (renderable instanceof AbstractElement<?> e && !e.hasClass("navbar-burger"))
            return e.hasName("hr") ? e.classes("navbar-divider") : e.classes("navbar-item");
        return renderable;
    }

    public static Element navbarStart() {return div().classes("navbar-start").map(Navbar::item);}

    public static Element navbarEnd() {return div().classes("navbar-end").map(Navbar::item);}

    public Navbar brand(Renderable... renderables) {
        return this.contains(div().classes("navbar-brand").map(Navbar::item).contains(renderables));
    }

    public Navbar burger() {
        var brand = findContent("navbar-brand").orElseThrow();
        return this.replace(brand, brand.contains(navbarBurger(menuId)));
    }

    private static Anchor navbarBurger(String targetId) {
        var burger = a()
                .classes("navbar-burger")
                .attr("role", "button")
                .ariaLabel("menu")
                .attr("aria-expanded", "false")
                .contains(
                        span().ariaHidden(),
                        span().ariaHidden(),
                        span().ariaHidden());
        if (targetId != null) burger = burger.attr("data-target", targetId);
        return burger;
    }

    @Deprecated // use containsStart/End instead
    @Override public Navbar contains(String content) {return super.contains(content);}

    @Deprecated // use containsStart/End instead
    @Override public Navbar contains(Stream<Renderable> content) {return super.contains(content);}

    @Deprecated // use containsStart/End instead
    @Override public Navbar contains(Renderable... content) {return super.contains(content);}

    @Deprecated // use containsStart/End instead
    @Override public Navbar contains(Renderable content) {return super.contains(content);}

    public Navbar isFixedTop() {return classes("is-fixed-top");}

    public Navbar isFixedBottom() {return classes("is-fixed-bottom");}

    public Navbar isTransparent() {return classes("is-transparent");}

    public Navbar containsStart(Renderable... newStartContent) {
        var menu = findContent("navbar-menu").orElseGet(() -> navbarMenu(menuId));
        return contains(menu.contains(navbarStart().contains(newStartContent)));
    }

    public Navbar containsEnd(Renderable... newEndContent) {
        Element menu;
        Renderable existingContent;
        var optionalMenu = findContent("navbar-menu");
        if (optionalMenu.isPresent()) {
            menu = (Element) optionalMenu.get();
            existingContent = content();
        } else {
            menu = navbarMenu(menuId);
            existingContent = ConcatenatedRenderable.concat(content(), menu);
        }
        var newMenu = menu.contains(navbarEnd().contains(newEndContent));
        var newContent = (existingContent.equals(menu)) ? newMenu : existingContent.replace(menu, newMenu);
        return content(newContent);
    }

    public static Element navbarMenu(String id) {return div().classes("navbar-menu").id(id);}

    @EqualsAndHashCode(callSuper = true) @SuperBuilder(toBuilder = true)
    public static class NavbarDropdown extends AbstractElement<NavbarDropdown> {
        public static NavbarDropdown navbarDropdown(String dropdownName, MenuActivationType activationType) {
            return new NavbarDropdown().is(activationType).contains(
                    a(dropdownName).classes("navbar-link"),
                    div().classes("navbar-dropdown").map(Navbar::item));
        }

        private NavbarDropdown() {super("div", "has-dropdown");}

        public NavbarDropdown contains(Renderable content) {
            var dropdown = findDropdown();
            if (dropdown.isPresent()) {
                return content(content().replace(dropdown.get(), dropdown.get().contains(item(content))));
            } else return super.contains(content);
        }

        private Optional<AbstractElement<?>> findDropdown() {return findContent("navbar-dropdown");}

        private Optional<AbstractElement<?>> findLink() {return findContent("navbar-link");}

        @Override public NavbarDropdown is(Modifier... modifiers) {
            var result = this;
            for (var modifier : modifiers) {
                if (modifier instanceof MenuActivationType) {
                    result = result.classes(modifier.className());
                } else {
                    var dropdown = findDropdown().orElseThrow();
                    result = result.content(content().replace(dropdown, dropdown.classes(modifier.className())));
                }
            }
            return result;
        }

        public NavbarDropdown isArrowless() {
            var link = findLink().orElseThrow();
            return content(content().replace(link, link.classes("is-arrowless")));
        }
    }
}
