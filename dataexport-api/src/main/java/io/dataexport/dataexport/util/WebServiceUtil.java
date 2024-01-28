package io.dataexport.dataexport.util;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import io.dataexport.dataexport.constants.DataExportConstants;

@Component
public class WebServiceUtil {

	public String sendFileToNimbusService(MultipartFile multipartFile)

	{
		RestClient restClient = RestClient.create();

		if (restClient != null) {
			System.out.println("RestClient Obj Created");
		}

		return restClient.post().uri(DataExportConstants.NIMBUS_UPLOAD_END_POINT)
				.contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE)).body(multipartFile)
				.retrieve().toString();
	}
}