package com.rentalservice.renalservice;

public class Owner {

    private String name;
    private String email;
    private String area;
    private String phone;
    private String total_house;



    public Owner() {

    }

    public Owner(String name, String email, String phone, String house_address ) {
        this.name = name;
        this.area = house_address;
        this.phone = phone;
        this.email = email;

    }

    public Owner(String name, String email, String area, String phone, String total_house) {
        this.name = name;
        this.email = email;
        this.area = area;
        this.phone = phone;
        this.total_house = total_house;
    }

    public String getTotal_house() {
        return total_house;
    }

    public void setTotal_house(String total_house) {
        this.total_house = total_house;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
