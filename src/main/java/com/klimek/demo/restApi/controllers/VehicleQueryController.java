package com.klimek.demo.restApi.controllers;

import com.klimek.demo.restApi.entities.VehicleConstants;
import com.klimek.demo.restApi.services.VehicleQueryService;
import com.klimek.demo.restApi.dto.VehicleQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@RestController
@RequestMapping(value = "/api/vehicles")
public class VehicleQueryController {

    @Autowired
    private VehicleQueryService vehicleQueryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VehicleQueryDTO>> listAllVehicles(){
        return new ResponseEntity<>(vehicleQueryService.listAllVehicles(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VehicleQueryDTO> getVehicle(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(vehicleQueryService.getVehicle(id), HttpStatus.OK);
    }

    @GetMapping(value = "rent/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VehicleQueryDTO> rentVehicle(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(vehicleQueryService.rentVehicle(id), HttpStatus.OK);
    }
    @GetMapping(value = "return/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VehicleQueryDTO> returnVehicle(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(vehicleQueryService.returnVehicle(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VehicleQueryDTO> addVehicle(@RequestBody String payload) {
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(payload);
            JSONObject jsonObject = (JSONObject) object;
            Long id = (Long) jsonObject.get(VehicleConstants.id.toString());
            String vehicleIdentityNumber = (String) jsonObject.get(VehicleConstants.vehicleIdentityNumber.toString());
            String mark = (String) jsonObject.get(VehicleConstants.mark.toString());
            String model = (String) jsonObject.get(VehicleConstants.model.toString());
            Boolean rented = (Boolean) jsonObject.get(VehicleConstants.rented.toString());
            if (id!=null) {
                return new ResponseEntity<>(vehicleQueryService.updateVehicle(id,vehicleIdentityNumber, mark, model), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(vehicleQueryService.addVehicle(vehicleIdentityNumber, mark, model, rented), HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VehicleQueryDTO> deleteVehicle(@PathVariable(value = "id") Long id) {
       return new ResponseEntity<>(vehicleQueryService.deleteVehicle(id), HttpStatus.OK);

    }

}
