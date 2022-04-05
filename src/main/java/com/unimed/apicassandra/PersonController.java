package com.unimed.apicassandra;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final PersonRepository repository;
	public PersonController (PersonRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public Iterable<Person> getAll() {
		Iterable<Person> result = repository.findAll();
		return result;
	}
	
}
