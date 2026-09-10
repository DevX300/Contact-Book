package com.washeed.Util;

import java.io.Serializable;

public class Contact implements Serializable {
//    private static final long serialVersionUID = 1L;
    private String name;
    private String number;

    public Contact(String name, String number){
        this.name=name;
        this.number=number;
    }

    public void setName(String name) {this.name = name;}
    public void setNumber(String number) {this.number = number;}

    public String getName() {return name;}
    public String getNumber() {return number;}

    public String toString() {return name + " - " + number;}
}
