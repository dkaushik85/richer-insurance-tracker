package com.richer.insurance.scheduler;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource; 
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.richer.insurance.dto.VehicleDetailDTO;
import com.richer.insurance.service.VehicleService;
import com.richer.insurance.upload.VehicleInsuranceUploader;

@Component
public class VehicleInsuranceScheduler {

	private static final Logger logger = LoggerFactory.getLogger(VehicleInsuranceScheduler.class);

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
		logger.info("Job Started !! ");
		try {
			List<VehicleDetailDTO> vehicleDetailDTOs = vehicleService.getAllVehicleDetails();
			if (vehicleDetailDTOs != null)
				writeToCsv(vehicleDetailDTOs);
		} catch (Exception e) {
			logger.error("Error : {} ", e.getMessage());
			e.printStackTrace();
		}
		logger.info("Job Ended !! ");
	}

	public void writeToCsv(List<VehicleDetailDTO> vehicleDetailDTOs) {
		logger.info("Job started writeToCsv task");
		try {
			Random random = new Random();
			Path path = Paths.get("./vehicle-insurace-" + random.nextLong() + ".csv");
			Writer writer = Files.newBufferedWriter(path);

			StatefulBeanToCsv<VehicleDetailDTO> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			beanToCsv.write(vehicleDetailDTOs);
			writer.close();

			logger.info("Job completed writeToCsv task");

			FileSystemResource resource = new FileSystemResource(path);

			vehicleInsuranceUploader.uploadCsvToService(resource);

		} catch (Exception e) {
			System.err.println("Error while csv " + e.getMessage());
		}

	}

}
