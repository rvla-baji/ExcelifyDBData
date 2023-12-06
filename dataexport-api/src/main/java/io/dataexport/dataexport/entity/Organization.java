package io.dataexport.dataexport.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "organization")
@Getter
@Setter
@ToString
public class Organization {

	@Id
	@Column(name = "emp_id")
	private Integer empId;

	@Column(name = "emp_name")
	private String empName;

	@Column(name = "emp_technology")
	private String empTechnology;

	@Column(name = "emp_location")
	private String empLocation;
}