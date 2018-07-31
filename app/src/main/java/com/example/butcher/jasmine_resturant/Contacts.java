package com.example.butcher.jasmine_resturant;

/**
 * Created by devhanan on 7/20/2017.
 */
public class Contacts {

    //private variables
    int id;
    String title;
    String desc;
    String quantity;
    String price;
    String total;


    // Empty constructor
    public Contacts() {

    }

    // constructor
    public Contacts(int id, String title, String desc, String quantity, String price, String total) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    // constructor

    // getting ID
    public int getID() {
        return this.id;
    }

    // setting id
    public void setID(int id) {
        this.id = id;
    }

    // getting name
    public String getDesc() {return this.desc;}

    // setting name
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getQuantity() {
        return this.quantity;
    }

    // setting name
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getPrice() {
        return this.price;
    }

    // setting name
    public void setPrice(String price) {
        this.price = price;
    }
    public String getTotal() {return this.total;
    }

    // setting name
    public void setTotal(String total) {
        this.total = total;
    }

    public String getTitle() {
        return this.title;
    }

    // setting name
    public void setTitle(String title) {
        this.title = title;
    }



    // getting phone number

}


