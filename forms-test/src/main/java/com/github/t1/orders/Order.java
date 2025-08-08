package com.github.t1.orders;

import com.github.t1.customers.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Order {
    private Long id;
    private LocalDate orderDate;
    private Customer customer;
    @Singular private List<OrderLine> orderLines;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + orderDate +
                ", customer='" + customer + '\'' +
                ", orderLines=" + orderLines +
                '}';
    }
}
