package com.klimek.demo.restApi.services;

import com.klimek.demo.restApi.dto.VehicleQueryDTO;
import com.klimek.demo.restApi.entities.Vehicle;
import com.klimek.demo.restApi.exception.ProductNotRentException;
import com.klimek.demo.restApi.exception.ProductNotfoundException;
import com.klimek.demo.restApi.exception.ProductRentedException;
import com.klimek.demo.restApi.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleQueryServiceImpl implements VehicleQueryService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public VehicleQueryDTO getVehicle(Long id) {
        if (vehicleRepository.findById(id).isPresent()){
            Vehicle fetchedVehicle = vehicleRepository.findById(id).get();
            return new VehicleQueryDTO(fetchedVehicle.getId(), fetchedVehicle.getVehicleIdentityNumber(), fetchedVehicle.getMark(), fetchedVehicle.getModel(), fetchedVehicle.getRented());
        }else{
            return null;
        }
    }

    @Override
    public List<VehicleQueryDTO> listAllVehicles() {
        List<VehicleQueryDTO> vehicleList = new ArrayList<>();

        vehicleRepository.findAll().forEach(vehicle -> {
            vehicleList.add(new VehicleQueryDTO(vehicle.getId(), vehicle.getVehicleIdentityNumber(), vehicle.getMark(), vehicle.getModel(),vehicle.getRented()));
        });

        return vehicleList;
    }

    @Override
    public VehicleQueryDTO addVehicle(String vehicleIdentityNumber, String mark, String model, Boolean rented) {
        Vehicle addVehicle = new Vehicle();
        addVehicle.setVehicleIdentityNumber(vehicleIdentityNumber);
        addVehicle.setMark(mark);
        addVehicle.setModel(model);
        addVehicle.setRented(rented);
        Vehicle addedVehicle = vehicleRepository.save(addVehicle);
        return new VehicleQueryDTO(addedVehicle.getId(),addedVehicle.getVehicleIdentityNumber(),addedVehicle.getMark(),addedVehicle.getModel(),addedVehicle.getRented());
    }

    @Override
    public VehicleQueryDTO deleteVehicle(Long id) {
        Vehicle vehicleToDelete = vehicleRepository.findById(id).get();
        if (vehicleRepository.findById(id).isPresent()){
            vehicleRepository.deleteById(id);
            return new VehicleQueryDTO(vehicleToDelete.getId(),vehicleToDelete.getVehicleIdentityNumber(),vehicleToDelete.getMark(),vehicleToDelete.getModel(),vehicleToDelete.getRented());
        }else{
            throw new ProductNotfoundException();
        }
    }

    @Override
    public VehicleQueryDTO updateVehicle(Long id, String vehicleIdentityNumber, String mark, String model) {

        if (vehicleRepository.findById(id).isPresent()){
            Vehicle fetchedVehicle = vehicleRepository.findById(id).get();
            fetchedVehicle.setVehicleIdentityNumber(vehicleIdentityNumber);
            fetchedVehicle.setMark(mark);
            fetchedVehicle.setModel(model);
            vehicleRepository.save(fetchedVehicle);
            return new VehicleQueryDTO(fetchedVehicle.getId(), fetchedVehicle.getVehicleIdentityNumber(), fetchedVehicle.getMark(), fetchedVehicle.getModel(), fetchedVehicle.getRented());
        }else{
            throw new ProductNotfoundException();
        }
    }

    @Override
    public VehicleQueryDTO rentVehicle(Long id) {
        if (vehicleRepository.findById(id).isPresent()){
            Vehicle fetchedVehicle = vehicleRepository.findById(id).get();
            if(fetchedVehicle.getRented()==false) {
                fetchedVehicle.setRented(true);
                vehicleRepository.save(fetchedVehicle);
                return new VehicleQueryDTO(fetchedVehicle.getId(), fetchedVehicle.getVehicleIdentityNumber(), fetchedVehicle.getMark(), fetchedVehicle.getModel(), fetchedVehicle.getRented());
            } else {
                throw new ProductRentedException();
            }
        }else{
            return null;
        }
    }

    @Override
    public VehicleQueryDTO returnVehicle(Long id) {
        if (vehicleRepository.findById(id).isPresent()){
            Vehicle fetchedVehicle = vehicleRepository.findById(id).get();
            if(fetchedVehicle.getRented()==true) {
                fetchedVehicle.setRented(false);

                vehicleRepository.save(fetchedVehicle);
                return new VehicleQueryDTO(fetchedVehicle.getId(), fetchedVehicle.getVehicleIdentityNumber(), fetchedVehicle.getMark(), fetchedVehicle.getModel(), fetchedVehicle.getRented());
            } else {
                throw new ProductNotRentException();
            }
            }else{
            return null;
        }
    }

}
