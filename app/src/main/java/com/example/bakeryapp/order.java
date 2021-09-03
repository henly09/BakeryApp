package com.example.bakeryapp;

public class order {

    private int ID;
    private String fullname;
    private String ordered;
    private int price;

    public order(int ID, String fullname, String ordered, int price) {
        this.ID = ID;
        this.fullname = fullname;
        this.ordered = ordered;
        this.price = price;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getOrdered() {
        return ordered;
    }

    public void setOrdered(String ordered) {
        this.ordered = ordered;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
