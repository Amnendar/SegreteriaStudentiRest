package it.epicode.be.segreteriastudentirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import it.epicode.be.segreteriastudentirest.model.Libretto;
import it.epicode.be.segreteriastudentirest.repository.LibrettoRepository;

@Service
public class LibrettoService {

	@Autowired
	LibrettoRepository librettoRepo;
	
	
	public Page<Libretto> findAllByCodice(Pageable pageable, String codice){
		return librettoRepo.findAllByCodice(pageable, codice);
	}
	
}
