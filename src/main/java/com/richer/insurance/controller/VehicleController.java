package com.richer.insurance.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.richer.insurance.dto.VehicleDetailDTO;
import com.richer.insurance.model.Vehicle;
import com.richer.insurance.model.VehicleInsurance;
import com.richer.insurance.service.VehicleInsuranceService;

@RestController
public class VehicleController {
	@Autowired
	private VehicleInsuranceService vehicleService; 


	@RequestMapping("/")
	public List<Vehicle> getAllVehicle() {
		return vehicleService.getAllVehicles();
	}

	@RequestMapping("/get-vehicle-details")
	public List<VehicleDetailDTO> getAllVehicleDetails() {
		return vehicleService.getAllVehicleDetails();
	}

	@RequestMapping(value = "/add-vehicle", method = RequestMethod.POST)
	public void addVehicle(@RequestBody Vehicle vehicleRecord) {
		vehicleService.addVehicle(vehicleRecord);
	}

	@RequestMapping(value = "/add-vehicle-insurance", method = RequestMethod.POST)
	public void addVehicleInsurance(@RequestBody VehicleInsurance vehicleRecord) {
		vehicleService.addVehicleInsurance(vehicleRecord);
	}

	@RequestMapping(value = "/upload-vehicle-insurance", method = RequestMethod.POST)
	public String uploadVehicleRecords(@RequestParam("file") MultipartFile file) {
		System.out.println("Download file");
		if (file == null || file.isEmpty()) {
			return "No file uploaded";
		}

		try {

			byte[] bytes = file.getBytes();
			Random random= new Random(); 
			Path path = Paths.get("./uploaded-vehicle-insurance-"+random.nextLong()+".csv");
			Files.write(path, bytes);
			System.out.println(path.getFileName());

		} catch (Exception e) {
			System.err.println("Error "+e.getMessage());
			e.printStackTrace();
		}
		System.out.println("File downloaded file");
		
		return "Ok";

	}

}
