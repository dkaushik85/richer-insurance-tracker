package com.richer.insurance.service;

import java.util.List;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.richer.insurance.dto.VehicleDetailDTO;
import com.richer.insurance.model.Vehicle;
import com.richer.insurance.model.VehicleInsurance;
import com.richer.insurance.repository.VehicleInsuranceRepository;
import com.richer.insurance.repository.VehicleRepository;

@Service
public class VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private VehicleInsuranceRepository vehicleInsuranceRepository;

	public List<Vehicle> getAllVehicles() {
		List<Vehicle> vehicleRecords = new ArrayList<>();
		vehicleRepository.findAll().forEach(vehicleRecords::add);
		return vehicleRecords;
	}

	public List<VehicleDetailDTO> getAllVehicleDetails() {
		List<VehicleDetailDTO> vehicleDetailDTOs = vehicleRepository.getAllVehicleDetails();
		return vehicleDetailDTOs;
	}

	public List<VehicleInsurance> getAllVehiclesInsurance() {
		List<VehicleInsurance> vehicleRecords = new ArrayList<>();
		vehicleInsuranceRepository.findAll().forEach(vehicleRecords::add);
		return vehicleRecords;
	}

	public void addVehicle(Vehicle vehicleRecord) {
		vehicleRepository.save(vehicleRecord);
	}

	public void addVehicleInsurance(VehicleInsurance vehicleRecord) {
		vehicleInsuranceRepository.save(vehicleRecord);
	}

	public void writeToCsv(List<VehicleDetailDTO> vehicleDetailDTOs) {

		try {
			Writer writer = Files.newBufferedWriter(Paths.get("./vehicle-insurace.csv"));

			StatefulBeanToCsv<VehicleDetailDTO> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			beanToCsv.write(vehicleDetailDTOs);
			
			// closing the writer object 
            writer.close();
		} catch (Exception e) {
			System.err.println("Error while csv " + e.getMessage());
		}

	}
}