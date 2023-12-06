package io.dataexport.dataexport.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dataexport.dataexport.entity.Organization;
import io.dataexport.dataexport.model.OrganizationDTO;
import io.dataexport.dataexport.repository.OrganizationRepository;
import io.dataexport.dataexport.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public List<OrganizationDTO> convertTableDatatoJsonList() {

		List<OrganizationDTO> orgDtoList = new ArrayList<OrganizationDTO>();

		List<Organization> fetchedDataList = organizationRepository.findAll();

		logger.info("*** Fetched data list is **** {}", fetchedDataList);

		fetchedDataList.forEach(organization -> {
			OrganizationDTO organizationDTO = new OrganizationDTO();
			organizationDTO.setEmpId(organization.getEmpId());
			organizationDTO.setEmpLocation(organization.getEmpLocation());
			organizationDTO.setEmpName(organization.getEmpName());
			organizationDTO.setEmpTechnology(organization.getEmpTechnology());
			orgDtoList.add(organizationDTO);
		});

		return orgDtoList;
	}

}
