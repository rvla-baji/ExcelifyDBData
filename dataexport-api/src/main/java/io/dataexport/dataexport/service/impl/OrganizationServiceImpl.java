package io.dataexport.dataexport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dataexport.dataexport.entity.Organization;
import io.dataexport.dataexport.repository.OrganizationRepository;
import io.dataexport.dataexport.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public List<Organization> convertTableDatatoJsonList() {

		return organizationRepository.findAll();
	}

}
