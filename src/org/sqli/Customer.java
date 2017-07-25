package org.sqli;

import java.util.HashSet;
import java.util.Set;

public class Customer {

    private final Set<Order> orders = new HashSet<>();

    public boolean addProduct(String productCode, int quantity, String promotionCode){
        Order order = new Order(productCode, quantity, promotionCode);
        return orders.add(order);
    }

    public boolean canUseDiscount(){
        if (orders.size() < 5){
            return false;
        }
        for (Order order : orders){
            if (order.hasDiscount()){
                return false;
            }
        }
        return true;
    }

    public void print(Printer printer){
        for (Order order : orders){
            order.printUsing(printer);
        }
    }
}
