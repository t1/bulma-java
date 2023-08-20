package test.components;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import test.RenderTestExtension;

import static com.github.t1.bulmajava.basic.Anchor.a;
import static com.github.t1.bulmajava.basic.Basic.li;
import static com.github.t1.bulmajava.basic.Basic.ul;
import static com.github.t1.bulmajava.components.Menu.*;
import static com.github.t1.bulmajava.basic.State.ACTIVE;
import static test.CustomAssertions.then;

@ExtendWith(RenderTestExtension.class)
class MenuTest {
    @Test void shouldRenderMenu() {
        var menu = menu().contains(
                menuLabel("General"),
                menuList().contains(
                        a("Dashboard"),
                        a("Customers")),
                menuLabel("Administration"),
                menuList().contains(
                        a("Team Settings"),
                        li().contains(
                                a("Manage Your Team").is(ACTIVE),
                                ul().contains(
                                        li().contains(a("Members")),
                                        li().contains(a("Plugins")),
                                        li().contains(a("Add a member")))),
                        a("Invitations"),
                        a("Cloud Storage Environment Settings"),
                        a("Authentication")),
                menuLabel("Transactions"),
                menuList().contains(
                        a("Payments"),
                        a("Transfers"),
                        a("Balance")));

        then(menu).rendersAs("""
                <aside class="menu">
                    <p class="menu-label">General</p>
                    <ul class="menu-list">
                        <li>
                            <a>Dashboard</a>
                        </li>
                        <li>
                            <a>Customers</a>
                        </li>
                    </ul>
                    <p class="menu-label">Administration</p>
                    <ul class="menu-list">
                        <li>
                            <a>Team Settings</a>
                        </li>
                        <li>
                            <a class="is-active">Manage Your Team</a>
                            <ul>
                                <li>
                                    <a>Members</a>
                                </li>
                                <li>
                                    <a>Plugins</a>
                                </li>
                                <li>
                                    <a>Add a member</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a>Invitations</a>
                        </li>
                        <li>
                            <a>Cloud Storage Environment Settings</a>
                        </li>
                        <li>
                            <a>Authentication</a>
                        </li>
                    </ul>
                    <p class="menu-label">Transactions</p>
                    <ul class="menu-list">
                        <li>
                            <a>Payments</a>
                        </li>
                        <li>
                            <a>Transfers</a>
                        </li>
                        <li>
                            <a>Balance</a>
                        </li>
                    </ul>
                </aside>
                """);
    }
}
