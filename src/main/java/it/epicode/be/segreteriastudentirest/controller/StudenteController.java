package it.epicode.be.segreteriastudentirest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.segreteriastudentirest.model.Studente;
import it.epicode.be.segreteriastudentirest.service.StudenteService;

@RestController
@RequestMapping("/api")
public class StudenteController {

	@Autowired
	StudenteService studenteService;

	@GetMapping("/studente")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Page<Studente>> findAll(Pageable pageable) {
		Page<Studente> findAll = studenteService.findAll(pageable);
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/studente/{id}")
	public ResponseEntity<Studente> findById(@PathVariable Long id) {
		Optional<Studente> findById = studenteService.findById(id);
		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/studente")
	public ResponseEntity<Studente> save(@RequestBody Studente studente) {
		Studente s = studenteService.save(studente);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}

	@DeleteMapping("/studente/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		studenteService.delete(id);
		return new ResponseEntity<String>("Studente eliminato", HttpStatus.OK);
	}

	@PutMapping("/studente/{id}")
	public ResponseEntity<Studente> update(@RequestBody Studente studente, @PathVariable Long id) {
		Studente s = studenteService.update(studente, id);
		return new ResponseEntity<>(s, HttpStatus.OK);

	}
}
