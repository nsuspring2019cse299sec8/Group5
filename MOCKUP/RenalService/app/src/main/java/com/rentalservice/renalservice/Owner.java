package com.rentalservice.renalservice;

public class Owner {

    private String name;
    private String house_address;
    private String phone;


    public Owner(String name, String house_address, String phone) {
        this.name = name;
        this.house_address = house_address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouse_address() {
        return house_address;
    }

    public void setHouse_address(String house_address) {
        this.house_address = house_address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
