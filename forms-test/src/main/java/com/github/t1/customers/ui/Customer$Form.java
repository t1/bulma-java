package com.github.t1.customers.ui;

import com.github.t1.customers.Customer;
import com.github.t1.htmljava.Renderable;
import jakarta.enterprise.context.Dependent;

import static com.github.t1.bulmajava.basic.Size.NORMAL;
import static com.github.t1.bulmajava.form.Field.EXPANDED;
import static com.github.t1.bulmajava.form.Field.field;
import static com.github.t1.bulmajava.form.Field.fieldset;
import static com.github.t1.bulmajava.form.Input.input;
import static com.github.t1.bulmajava.form.InputType.EMAIL;
import static com.github.t1.bulmajava.form.InputType.TEXT;

// TODO generate this class from the Customer class with a annotation processor
@Dependent
public class Customer$Form {
    public Renderable content(Customer customer) {
        return fieldset().content(
                field().horizontal().label("Customer Number", NORMAL)
                        .control(field().control(input(TEXT).readonly().placeholder("12345").value(customer.getId().toString()), EXPANDED)),
                field().horizontal().label("Name", NORMAL)
                        .control(field().control(input(TEXT).readonly().placeholder("Name").value(customer.getName()), EXPANDED)),
                field().horizontal()
                        .label("Email", NORMAL)
                        .control(field().control(input(EMAIL).readonly().placeholder("Email").value(customer.getEmail()), EXPANDED)));
    }
}
