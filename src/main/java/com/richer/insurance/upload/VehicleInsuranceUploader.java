package com.richer.insurance.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class VehicleInsuranceUploader {

	private static final Logger logger = LoggerFactory.getLogger(VehicleInsuranceUploader.class);
	@Value("${richer.upload.service.uri}")
	String uploadFileUri;
	@Value("${richer.upload.service.endpoint}")
	String uploadFileEndpoint;

	public void uploadCsvToService(FileSystemResource resource) {
		logger.info("Job started upload csv task");
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			MultiValueMap<String, Object> fileContent = new LinkedMultiValueMap<>();
			fileContent.add("file", resource);
			HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(
					fileContent, headers);
			RestTemplate restTempplate = new RestTemplate();
			logger.info("Upload csv to URL : {}", uploadFileUri+uploadFileEndpoint);
			ResponseEntity<String> response = restTempplate.postForEntity(uploadFileUri+uploadFileEndpoint, request, String.class);

			logger.info("Status Code : {}", response.getStatusCode());
			logger.info("Job complated upload csv task");
		} catch (Exception e) {
			logger.error("Error while uploading csv : {}", e.getMessage());
		}
		

	}

}
