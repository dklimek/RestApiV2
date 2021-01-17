package com.klimek.demo.restApi.dto;

import java.util.UUID;

public class VehicleQueryDTO {

    private Long id;

    private String vehicleIdentityNumber;

    private String mark;

    private String model;

    private Boolean rented;

    public VehicleQueryDTO() {
    }

    public VehicleQueryDTO(Long id, String vehicleIdentityNumber, String mark, String model, Boolean rented) {
        this.id = id;
        this.vehicleIdentityNumber = vehicleIdentityNumber;
        this.mark = mark;
        this.model = model;
        this.rented = rented;
    }

    public Long getId() {
        return id;
    }

    public String getVehicleIdentityNumber() {
        return vehicleIdentityNumber;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public Boolean getRented() {
        return rented;
    }

    @Override
    public String toString() {
        return "VehicleQueryDTO{" +
                "id=" + id +
                ", vehicleIdentityNumber='" + vehicleIdentityNumber + '\'' +
                ", make='" + mark + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
