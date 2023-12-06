package io.dataexport.dataexport.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dataexport.dataexport.entity.Organization;
import io.dataexport.dataexport.repository.OrganizationRepository;
import io.dataexport.dataexport.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public List<Organization> convertTableDatatoJsonList() {

		List<Organization> fetchedDataList = organizationRepository.findAll();

		logger.info("*** Fetched data list is **** {}", fetchedDataList);

		return fetchedDataList;
	}

}
