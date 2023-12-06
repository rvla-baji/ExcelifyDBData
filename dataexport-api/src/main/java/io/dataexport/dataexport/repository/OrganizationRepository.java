package io.dataexport.dataexport.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.dataexport.dataexport.entity.Organization;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Integer> {
	
	List<Organization> findAll();
}