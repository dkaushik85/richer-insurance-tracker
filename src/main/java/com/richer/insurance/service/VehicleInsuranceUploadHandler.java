package com.richer.insurance.service;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.richer.insurance.exception.VehicleInsuranceException;

@Component
public class VehicleInsuranceUploadHandler {

	private static final Logger logger = LoggerFactory.getLogger(VehicleInsuranceUploadHandler.class);
	@Value("${richer.upload.service.uri}")
	String uploadFileUri;
	@Value("${richer.upload.service.endpoint}")
	String uploadFileEndpoint;

	public HttpStatus postCsvToService(Path filePath) throws VehicleInsuranceException {
		logger.info("Job started post csv task");
		HttpStatus uploadStatus=null;
		try {
			FileSystemResource resource = new FileSystemResource(filePath);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			MultiValueMap<String, Object> fileContent = new LinkedMultiValueMap<>();
			fileContent.add("file", resource);
			HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(
					fileContent, headers);
			RestTemplate restTempplate = new RestTemplate();
			logger.info("post csv to URL : {}", uploadFileUri+uploadFileEndpoint);
			ResponseEntity<String> response = restTempplate.postForEntity(uploadFileUri+uploadFileEndpoint, request, String.class);
			uploadStatus=response.getStatusCode();
			logger.info("Status Code : {}", uploadStatus);
			logger.info("Job complated upload csv task");
		} catch (Exception e) {
			logger.error("Error while post csv : {}", e.getMessage());
			throw new VehicleInsuranceException("FileUploadError-101", e.getMessage());
		}
		
		return uploadStatus;
	}

}
