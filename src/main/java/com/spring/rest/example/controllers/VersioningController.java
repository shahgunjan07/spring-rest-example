package com.spring.rest.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.example.domain.PersonV1;
import com.spring.rest.example.domain.PersonV2;

/**
 * Controllers to segregate the requests bases on versioning  
 * @author shahg
 *
 */
@RestController
public class VersioningController {

	@GetMapping(value="/versioning/request",params="version=1")
	public PersonV1 personV1WithRequestVersioning() {
		PersonV1 person = new PersonV1("Gunjan Shah");
		return person;
	}
	
	@GetMapping(value="/versioning/request",params="version=2")
	public PersonV2 personV2WithRequestVersioning() {
		PersonV2 person = new PersonV2("Gunjan", "Shah");
		return person;
	}
	
	@GetMapping(value="/versioning/request",headers="X-API-VERSION=1")
	public PersonV1 personV1WithHeaderVersioning() {
		PersonV1 person = new PersonV1("Shreya Shah");
		return person;
	}
	
	@GetMapping(value="/versioning/request",headers="X-API-VERSION=2")
	public PersonV2 personV2WithHeaderVersioning() {
		PersonV2 person = new PersonV2("Shreya", "Shah");
		return person;
	}
	
	@GetMapping(value="/versioning/request",produces="application/vnd.company.app-v1+json")
	public PersonV1 personV1WithAcceptHeaderVersioning() {
		PersonV1 person = new PersonV1("Daxa Shah");
		return person;
	}
	
	@GetMapping(value="/versioning/request",produces="application/vnd.company.app-v2+json")
	public PersonV2 personV2WithAcceptHeaderVersioning() {
		PersonV2 person = new PersonV2("Daxa", "Shah");
		return person;
	}
}
