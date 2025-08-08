package com.github.t1.customers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Comparator.comparing;

@Path("/customers")
public class Customers {
    public static final Customer JANE = Customer.builder().id(1L).name("Jane Doe").email("jane.doe@example.com").build();
    public static final Customer JOE = Customer.builder().id(2L).name("Joe Doe").email("joe.doe@example.com").build();
    public static final Customer JOANE = Customer.builder().id(3L).name("Joane Doe").email("joane.doe@example.com").build();

    private static final Map<Long, Customer> CUSTOMERS = new ConcurrentHashMap<>();

    static {add(JANE); add(JOE); add(JOANE);}

    private static void add(Customer customer) {CUSTOMERS.put(customer.getId(), customer);}


    @GET @Path("/{customerId}") public Customer one(@PathParam("customerId") long customerId) {
        return Optional.ofNullable(CUSTOMERS.get(customerId))
                .orElseThrow(NotFoundException::new);
    }

    @GET public List<Customer> all() {
        return CUSTOMERS.values().stream().sorted(comparing(Customer::getId)).toList();
    }
}
