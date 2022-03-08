package it.epicode.be.segreteriastudentirest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.segreteriastudentirest.model.CorsoDiLaurea;
import it.epicode.be.segreteriastudentirest.service.CorsoDiLaureaService;

@RestController
@RequestMapping("/api")
public class CorsoDiLaureaController {

	@Autowired
	CorsoDiLaureaService corsoService;

	@GetMapping("/corso")
	public ResponseEntity<Page<CorsoDiLaurea>> findAll(Pageable pageable) {
		Page<CorsoDiLaurea> findAll = corsoService.findAll(pageable);
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/corso/{id}")
	public ResponseEntity<CorsoDiLaurea> findById(@PathVariable Long id) {
		Optional<CorsoDiLaurea> findById = corsoService.findById(id);
		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/corso")
	public ResponseEntity<CorsoDiLaurea> save(@RequestBody CorsoDiLaurea corso) {
		CorsoDiLaurea s = corsoService.save(corso);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}

	@DeleteMapping("/corso/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		corsoService.delete(id);
		return new ResponseEntity<String>("Corso eliminato", HttpStatus.OK);
	}

	@PutMapping("/corso/{id}")
	public ResponseEntity<CorsoDiLaurea> update(@RequestBody CorsoDiLaurea corso, @PathVariable Long id) {
		CorsoDiLaurea s = corsoService.update(corso, id);
		return new ResponseEntity<>(s, HttpStatus.OK);

	}

}
