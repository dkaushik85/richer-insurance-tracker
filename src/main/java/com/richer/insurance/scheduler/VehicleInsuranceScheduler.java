package com.richer.insurance.scheduler;

import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.richer.insurance.dto.VehicleDetailDTO;
import com.richer.insurance.exception.VehicleInsuranceException;
import com.richer.insurance.service.NotificationHandler;
import com.richer.insurance.service.VehicleInsuranceFileHandler;
import com.richer.insurance.service.VehicleInsuranceService;
import com.richer.insurance.service.VehicleInsuranceUploadHandler;
/*
 * Scheduler for VehicleInsurance
 */
@Component
public class VehicleInsuranceScheduler {

	private static final Logger logger = LoggerFactory.getLogger(VehicleInsuranceScheduler.class);

	public VehicleInsuranceScheduler() {
		super(); 
	}

	@Autowired
	private VehicleInsuranceService vehicleService;

	@Autowired
	private VehicleInsuranceFileHandler fileHandler;

	@Autowired
	private VehicleInsuranceUploadHandler vehicleInsuranceUploader;

	@Autowired
	private NotificationHandler notificationHandler;

	@Value("${richer.writetocsv.cleanup}")
	boolean cleanupOnUpload;

	/*
	 * 1. Scheduled job executed based on configuration 2. Call to retrieve
	 * Vehicle Details 3. Call to wrtie Vehicle Details to csv file 4. Call to
	 * upload csv file to Webservice 5. Delete csv based on configuration.
	 */
	@Scheduled(cron = "${richer.job.cron}")
	public void getAllVehicleDetails() {
		logger.info("Job Started !! ");
		Path path = null;
		HttpStatus uploadStatus = null;
		try {
			//
			// Call VehicleServive that return the VehicleDetailDTOs
			//
			List<VehicleDetailDTO> vehicleDetailDTOs = vehicleService.getAllVehicleDetails();

			// check if VehicleDetailsDTOs has any records
			if (vehicleDetailDTOs != null && !vehicleDetailDTOs.isEmpty()) {

				// Call VehicleInsuranceFileHandler Service to write the
				// VehicleDetailDTOs records to a csv file
				// return a PATH where the local csv file written to
				path = fileHandler.writeToCsv(vehicleDetailDTOs);
				if (path != null) {

					// Call VehicleInsuranceUploader Service to upload the csv
					// file to WebService
					uploadStatus = vehicleInsuranceUploader.postCsvToService(path);

					// Check if csv file delete config
					// Delete the file
					if (uploadStatus.equals(HttpStatus.OK) && cleanupOnUpload) {
						// Call VehicleInsuranceFileHandler Service to delete the csv file
						fileHandler.cleanupFile(path);
					}
				}
			} else {
				logger.error("No VehicleInsurance to Upload");
				new VehicleInsuranceException("NODATAFOUND-101", "No VehicleInsurance to Upload");
			}
		} catch (VehicleInsuranceException e) {
			logger.error("VehicleInsuranceException code : {} errormessage : {} ", e.getErrorCode(), e);
			e.printStackTrace();
			notificationHandler.sendNotification();
		} catch (Exception e) {
			logger.error("Unexcpeted Error : {} ", e.getMessage());
			e.printStackTrace();
			notificationHandler.sendNotification();
		}
		logger.info("Job Ended !! ");
	}

}
