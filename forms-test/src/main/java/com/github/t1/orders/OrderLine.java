package com.github.t1.orders;

import com.github.t1.products.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class OrderLine {
    private Long id;
    private Product product;
    private int quantity;

    @Override public String toString() {return id + ":" + quantity + " x " + product;}
}
