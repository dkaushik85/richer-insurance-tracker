package com.richer.insurance.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Component
public class VehicleInsuranceUploader {

	

	public void uploadCsvToService(FileSystemResource resource) {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			MultiValueMap<String, Object> fileContent = new LinkedMultiValueMap<>();
			fileContent.add("file", resource);

			HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(
					fileContent, headers);

			String uploadFileToURL = "http://localhost:8080/upload-vehicle-insurance";

			RestTemplate restTempplate = new RestTemplate();
			ResponseEntity<String> response = restTempplate.postForEntity(uploadFileToURL, request, String.class);

			System.out.println("Status Code : " + response.getStatusCode());

		} catch (Exception e) {
			System.err.println("Error while csv " + e.getMessage());
		}

	}

}
