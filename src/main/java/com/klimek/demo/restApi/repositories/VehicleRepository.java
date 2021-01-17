package com.klimek.demo.restApi.repositories;

import com.klimek.demo.restApi.entities.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "cars", path = "cars")
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    List<Vehicle> findByMark(@Param("mark") String mark);
    Vehicle findFirstByOrderByIdDesc();
}
