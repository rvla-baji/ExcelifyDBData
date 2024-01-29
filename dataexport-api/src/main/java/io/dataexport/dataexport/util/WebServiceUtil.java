package io.dataexport.dataexport.util;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WebServiceUtil {

	@Value("${nimbus.endpoint}")
	private String nimbusEndPoint;

	public void uploadToNimbusService(ByteArrayOutputStream arrayOutputStream) {

		RestClient restClient = RestClient.create();
		ResponseEntity<Void> response = restClient.post().uri(nimbusEndPoint)
				.contentType(
						MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(null).retrieve().toBodilessEntity();
		System.out.println(response);

	}
}