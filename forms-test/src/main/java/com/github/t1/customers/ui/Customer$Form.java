package com.github.t1.customers.ui;

import com.github.t1.bulmajava.form.Field;
import com.github.t1.customers.Customer;
import jakarta.enterprise.context.Dependent;

import java.util.stream.Stream;

import static com.github.t1.bulmajava.basic.Style.STATIC;
import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.EMAIL;
import static com.github.t1.bulmajava.form.InputType.TEXT;

// TODO generate this class from the Customer class with an annotation processor
//  annotations: @Input(TEXT, EMAIL, ...), @Readonly (=>static); required and others from Bean Validation
@Dependent
public class Customer$Form {
    public Stream<Field> of(Customer customer) {
        return Stream.of(
                field("Customer Number").content(input(TEXT).is(STATIC).fieldName("customerNumber").value(customer.getId())),
                field("Name").content(input(TEXT).fieldName("name").required().autofocus().value(customer.getName())),
                field("Email").content(input(EMAIL).fieldName("email").value(customer.getEmail())));
    }
}
