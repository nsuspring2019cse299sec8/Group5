package com.rentalservice.renalservice;

public class Tenant {
    private String name;
    private String phone;
    private String prev_address;

    private String owner_id;
    private  boolean status;



    public Tenant() {

    }

    public Tenant(String name, String phone, String prev_address,boolean status) {
        this.name = name;
        this.phone = phone;
        this.prev_address = prev_address;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
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
