package io.dataexport.dataexport.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonPropertyOrder({ "empId", "empName", "empTechnology", "empLocation" })
@ToString
public class OrganizationDTO {

	@JsonProperty("Employee Id")
	private Integer empId;

	@JsonProperty("Employee Name")
	private String empName;

	@JsonProperty("Employee Technology")
	private String empTechnology;

	@JsonProperty("Employee Location")
	private String empLocation;

}