package com.klimek.demo.restApi;

import com.klimek.demo.restApi.repositories.VehicleRepository;
import com.klimek.demo.restApi.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@SpringBootApplication
public class SpringBootStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterApplication.class, args);
	}

}

@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public void run(String... args) throws Exception {

		Vehicle audi = new Vehicle();
		audi.setVehicleIdentityNumber("Reg#1234");
		audi.setMark("Audi");
		audi.setModel("Q5");
		audi.setRented(false);

		vehicleRepository.save(audi);

		Vehicle tesla = new Vehicle();
		tesla.setVehicleIdentityNumber("Reg#6789");
		tesla.setMark("Tesla");
		tesla.setModel("Model S");
		tesla.setRented(false);

		vehicleRepository.save(tesla);
	}
}

