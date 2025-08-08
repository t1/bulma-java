package com.github.t1.orders;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.t1.customers.Customers.JANE;
import static com.github.t1.customers.Customers.JOE;
import static com.github.t1.products.Products.CHAIR;
import static com.github.t1.products.Products.CUPBOARD;
import static com.github.t1.products.Products.TABLE;
import static java.util.Comparator.comparing;

@Path("/orders")
public class Orders {
    private static final Map<Long, Order> ORDERS = new ConcurrentHashMap<>();

    static {
        add(Order.builder()
                .id(1L)
                .orderDate(LocalDate.of(2020, 1, 2))
                .customer(JANE)
                .orderLine(OrderLine.builder()
                        .id(1L).product(TABLE).quantity(1)
                        .id(2L).product(CHAIR).quantity(6)
                        .id(3L).product(CUPBOARD).quantity(2)
                        .build())
                .build());
        add(Order.builder()
                .id(2L)
                .orderDate(LocalDate.of(2030, 12, 23))
                .customer(JOE)
                .orderLine(OrderLine.builder()
                        .id(3L).product(TABLE).quantity(1)
                        .build())
                .build());
    }

    private static void add(Order order) {
        ORDERS.put(order.getId(), order);
    }

    @GET
    @Path("/{orderId}")
    public Order order(@PathParam("orderId") long orderId) {
        return Optional.ofNullable(ORDERS.get(orderId))
                .orElseThrow(NotFoundException::new);
    }

    @GET public List<Order> all() {
        return ORDERS.values().stream().sorted(comparing(Order::getId)).toList();
    }
}
