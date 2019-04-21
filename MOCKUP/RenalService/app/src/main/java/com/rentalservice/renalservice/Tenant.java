package com.rentalservice.renalservice;

public class Tenant {
    private String name;
    private String phone;
    private String prev_address;


    public Tenant(String name, String phone, String prev_address) {
        this.name = name;
        this.phone = phone;
        this.prev_address = prev_address;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrev_address() {
        return prev_address;
    }

    public void setPrev_address(String prev_address) {
        this.prev_address = prev_address;
    }
}
