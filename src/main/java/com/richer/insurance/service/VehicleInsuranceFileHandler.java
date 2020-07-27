package com.richer.insurance.service;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.richer.insurance.dto.VehicleDetailDTO;
import com.richer.insurance.exception.VehicleInsuranceException;

@Service
public class VehicleInsuranceFileHandler {
	private static final Logger logger = LoggerFactory.getLogger(VehicleInsuranceFileHandler.class);

	@Value("${richer.writetocsv.filename}")
	String csvFileName;

	public Path writeToCsv(List<VehicleDetailDTO> vehicleDetailDTOs) throws VehicleInsuranceException {
		logger.info("Job started writeToCsv task");
		Path path = null;
		try {
			path = Paths.get("./" + csvFileName);
			Writer writer = Files.newBufferedWriter(path);
	
	        // suppress all quoting, map c
			StatefulBeanToCsv<VehicleDetailDTO> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			beanToCsv.write(vehicleDetailDTOs);
			writer.close();

			logger.info("Job completed writeToCsv task");
		} catch (Exception e) {
			logger.error("Error writetocsv " + e.getMessage());
			throw new VehicleInsuranceException("CSV file Error", e.getMessage());
		}
		return path;
	}

	public boolean cleanupFile(Path path) throws VehicleInsuranceException {
		
		/*
		 * Delete file code goes here
		 * 
		 */
		logger.info("Cleanup task started");
		logger.info("Cleanup task completed");
		return true;
	}
}
