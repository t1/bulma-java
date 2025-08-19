package com.github.t1.customers;

import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.t1.customers.Customer.Level.bronze;
import static com.github.t1.customers.Customer.Level.gold;
import static com.github.t1.customers.Customer.Level.platinum;
import static jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static java.util.Comparator.comparing;

@Path("/customers")
public class Customers {
    public static final Customer JANE = Customer.builder().name("Jane Doe").email("jane.doe@example.com").supportLevel(gold).build();
    public static final Customer JOE = Customer.builder().name("Joe Doe").email("joe.doe@example.com").supportLevel(bronze).build();
    public static final Customer JOANE = Customer.builder().name("Joane Doe").email("joane.doe@example.com").supportLevel(platinum).build();
    private static long nextId = 1;

    private static final Map<Long, Customer> CUSTOMERS = new ConcurrentHashMap<>();

    static {add(JANE); add(JOE); add(JOANE);}

    private static void add(Customer customer) {
        if (customer.getId() != null) throw new BadRequestException("Customer id must not be set on create");
        var id = nextId++;
        customer.setId(id);
        CUSTOMERS.put(id, customer);
    }


    @GET @Path("/{customerId}")
    public Customer customer(@PathParam("customerId") long customerId) {
        return Optional.ofNullable(CUSTOMERS.get(customerId))
                .orElseThrow(NotFoundException::new);
    }

    @GET
    public List<Customer> customers() {
        return CUSTOMERS.values().stream().sorted(comparing(Customer::getId)).toList();
    }

    @POST
    // I don't like to have to specify the media types here, but in Quarkus, this necessary ;-(
    @Consumes({APPLICATION_JSON, APPLICATION_FORM_URLENCODED})
    public Response create(@Valid Customer customer) {
        add(customer);
        return Response.seeOther(URI.create("/customers/" + customer.getId())).build();
    }

    @PUT  @Path("/{customerId}")
    public Response update(@PathParam("customerId") long customerId, Customer customer) {
        if (customer.getId() != null) throw new BadRequestException("Set the customer id in the path, not in the body");
        if (!CUSTOMERS.containsKey(customerId)) throw new NotFoundException();
        customer.setId(customerId);
        CUSTOMERS.put(customerId, customer);
        return Response.ok().header("HX-Redirect", "/customers/" + customerId).build();
    }

    @DELETE @Path("/{customerId}")
    public Response delete(@PathParam("customerId") long customerId) {
        var customer = CUSTOMERS.remove(customerId);
        if (customer == null) throw new NotFoundException();
        return Response.ok().header("HX-Redirect", "/customers").build();
    }
}
