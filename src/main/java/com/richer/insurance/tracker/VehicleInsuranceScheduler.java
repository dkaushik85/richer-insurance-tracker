package com.richer.insurance.tracker;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.richer.insurance.dto.VehicleDetailDTO;
import com.richer.insurance.service.VehicleService;
import com.richer.insurance.upload.VehicleInsuranceUploader;

@Component
public class VehicleInsuranceScheduler {

	public VehicleInsuranceScheduler() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private VehicleInsuranceUploader vehicleInsuranceUploader;

	@Scheduled(cron = "${richer.job.cron}")
	public void getAllVehicleDetails() {
		System.out.println("Job Started !! ");
		List<VehicleDetailDTO> vehicleDetailDTOs = vehicleService.getAllVehicleDetails();
		writeToCsv(vehicleDetailDTOs);
		System.out.println("Job Ended !! ");
	}

	public void writeToCsv(List<VehicleDetailDTO> vehicleDetailDTOs) {

		try {
			Random random= new Random();
			Path path = Paths.get("./vehicle-insurace-"+random.nextLong()+".csv");
			Writer writer = Files.newBufferedWriter(path);

			StatefulBeanToCsv<VehicleDetailDTO> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			beanToCsv.write(vehicleDetailDTOs);
			writer.close();

			FileSystemResource resource = new FileSystemResource(path);
			
			vehicleInsuranceUploader.uploadCsvToService(resource);
			
			
		} catch (Exception e) {
			System.err.println("Error while csv " + e.getMessage());
		}

	}

}
