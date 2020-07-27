package com.richer.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.richer.insurance.dto.VehicleDetailDTO;
import com.richer.insurance.model.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, String> {
	
	//
	// Inner Join Vehicle & Vehicle Insurance along with filter on policy end date greater than current date
	//
	@Query("Select new com.richer.insurance.dto.VehicleDetailDTO(v.registrationNumber,vh.policyNumber,vh.startDate,vh.endDate) from Vehicle v join v.vehicleInsurances vh where vh.endDate> CURRENT_DATE")
	public List<VehicleDetailDTO> getAllVehicleDetails();
}
