package com.example.afinal;

public class OrdersContent {
    String Name, Quantity, Price;

    public OrdersContent(String name, String quantity, String price) {
        Name = name;
        Quantity = quantity;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
