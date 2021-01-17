package com.klimek.demo.restApi.services;

import com.klimek.demo.restApi.dto.VehicleQueryDTO;

import java.util.List;
import java.util.UUID;

public interface VehicleQueryService {

    public VehicleQueryDTO getVehicle(Long id);
    public VehicleQueryDTO deleteVehicle(Long id);
    public VehicleQueryDTO addVehicle(String vehicleIdentityNumber, String mark, String model, Boolean rented);
    public VehicleQueryDTO updateVehicle(Long id, String vehicleIdentityNumber, String mark, String model);
    public List<VehicleQueryDTO> listAllVehicles();
    public VehicleQueryDTO rentVehicle(Long id);
    public VehicleQueryDTO returnVehicle(Long id);
}
