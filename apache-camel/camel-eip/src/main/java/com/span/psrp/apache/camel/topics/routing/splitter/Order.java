package com.span.psrp.apache.camel.topics.routing.splitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukesh.k on 9/29/2015.
 */
public class Order {

    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    private int number;

    public Order(int number) {
        this.number = number;
    }

    public void addItem(String itemName, int quantity, int price) {
        this.orderItems.add(new OrderItem(this,itemName, quantity, price));
    }
    
    public int getNumber() {
        return number;
    }

    public List<OrderItem> getItems() {
        return this.orderItems;
    }
    
    public String toString() {
        return "Order: " + number + ", Items: " + orderItems;
    }
}
