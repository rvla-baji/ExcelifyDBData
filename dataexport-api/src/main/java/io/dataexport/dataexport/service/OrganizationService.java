package io.dataexport.dataexport.service;

import java.util.List;

import io.dataexport.dataexport.model.OrganizationDTO;

public interface OrganizationService {

	List<OrganizationDTO> convertTableDatatoJsonList();
}
