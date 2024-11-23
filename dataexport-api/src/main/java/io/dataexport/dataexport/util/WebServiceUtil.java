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

		byte[] byteArray = arrayOutputStream.toByteArray();

		RestClient restClient = RestClient.create();
		ResponseEntity<String> responseEntity = restClient.post().uri(nimbusEndPoint)
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(byteArray).retrieve().toEntity(String.class);
		System.out.println(responseEntity);

	}
}