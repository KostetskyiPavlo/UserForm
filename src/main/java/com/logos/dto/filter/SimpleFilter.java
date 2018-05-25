package com.logos.dto.filter;

import lombok.Data;

@Data
public class SimpleFilter {

	private String search;
	
	private String minSalary;
	
	private String maxSalary;
	
	private int pageSize = 10;
	
}
