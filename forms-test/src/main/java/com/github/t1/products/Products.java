package com.github.t1.products;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Comparator.comparing;

@Path("/products")
public class Products {
    public static final Product TABLE = Product.builder().id(1L).name("Table").description(loremIpsum()).price(500).build();
    public static final Product CHAIR = Product.builder().id(2L).name("Chair").description(loremIpsum()).price(100).build();
    public static final Product CUPBOARD = Product.builder().id(3L).name("Cupboard").description(loremIpsum()).price(400).build();

    private static String loremIpsum() {
        return "Lorem ipsum dolor sit amet, consectetur adipisici elit, " +
               "sed eiusmod tempor incidunt ut labore et dolore magna aliqua. " +
               "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
               "ut aliquid ex ea commodi consequat. Quis aute iure reprehenderit " +
               "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
               "Excepteur sint obcaecat cupiditat non proident, sunt in culpa " +
               "qui officia deserunt mollit anim id est laborum.";
    }

    private static final Map<Long, Product> PRODUCTS = new ConcurrentHashMap<>();

    static {add(TABLE); add(CHAIR); add(CUPBOARD);}

    private static void add(Product product) {PRODUCTS.put(product.getId(), product);}


    @GET @Path("/{productId}") public Product one(@PathParam("productId") long productId) {
        return Optional.ofNullable(PRODUCTS.get(productId))
                .orElseThrow(NotFoundException::new);
    }

    @GET public List<Product> all() {
        return PRODUCTS.values().stream().sorted(comparing(Product::getId)).toList();
    }
}
