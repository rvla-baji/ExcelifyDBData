package io.dataexport.dataexport.service;

import java.util.List;

import io.dataexport.dataexport.entity.Organization;

public interface OrganizationService {

	List<Organization> convertTableDatatoJsonList();
}
