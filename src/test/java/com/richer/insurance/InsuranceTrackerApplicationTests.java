package com.richer.insurance;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.richer.insurance.model.Vehicle;
import com.richer.insurance.service.VehicleInsuranceService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={InsuranceTrackerApplication.class})
public class InsuranceTrackerApplicationTests {

	
	
	@Test
	void contextLoads() {
	}
	
	@Autowired
	VehicleInsuranceService service ;

	@Test
	public void testGetAllVehicles(){ 
		List<Vehicle> vehicles=service.getAllVehicles();
		
		
		for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle.getRegistrationNumber());
			
		}
	}
	
	

}
