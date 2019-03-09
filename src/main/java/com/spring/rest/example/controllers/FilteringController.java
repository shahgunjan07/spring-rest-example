package com.spring.rest.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.spring.rest.example.domain.FilterResponse;



@RestController
public class FilteringController {

	/**
	 * Find all property without filter
	 * @return
	 */
	@GetMapping(value="/filtering/all")
	public MappingJacksonValue findAll() {
		List<FilterResponse> list = new ArrayList<>();
		list.add(new FilterResponse("field1", "field2", "field3"));
		list.add(new FilterResponse("field11", "field22", "field33"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2","field3");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("MyFilter", filter);
		
		MappingJacksonValue jacksonMapping = new MappingJacksonValue(list);
		jacksonMapping.setFilters(filterProvider);
		
		return jacksonMapping;
	}
	
	/**
	 * Example of dynamic filter.
	 * @return
	 */
	@GetMapping(value="/filtering/filtered")
	public MappingJacksonValue findFilteredResponse() {
		List<FilterResponse> list = new ArrayList<>();
		list.add(new FilterResponse("field1", "field2", "field3"));
		list.add(new FilterResponse("field11", "field22", "field33"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("MyFilter", filter);
		
		MappingJacksonValue jacksonMapping = new MappingJacksonValue(list);
		jacksonMapping.setFilters(filterProvider);
		
		return jacksonMapping;
	}
	
}
