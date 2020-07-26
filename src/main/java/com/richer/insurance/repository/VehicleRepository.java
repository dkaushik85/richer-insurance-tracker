package com.richer.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.richer.insurance.dto.VehicleDetailDTO;
import com.richer.insurance.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {
	
	@Query("Select new com.richer.insurance.dto.VehicleDetailDTO(v.registrationNumber,vh.policyNumber,vh.startDate,vh.endDate) from Vehicle v join v.vehicleInsurances vh where vh.endDate> CURRENT_DATE")
	public List<VehicleDetailDTO> getAllVehicleDetails();
}
