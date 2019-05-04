package com.rentalservice.renalservice.model;

public class HouseModel {
    private String name;
    private String road;
    private String flat;
    private String block;
    private String area;
    private String houseRent;

    public HouseModel(String name, String road, String flat, String block, String area,String houseRent) {
        this.name = name;
        this.road = road;
        this.flat = flat;
        this.block = block;
        this.area = area;
        this.houseRent =houseRent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
