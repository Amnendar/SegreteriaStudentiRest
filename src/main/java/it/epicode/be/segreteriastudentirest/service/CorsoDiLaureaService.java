package it.epicode.be.segreteriastudentirest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.segreteriastudentirest.model.CorsoDiLaurea;
import it.epicode.be.segreteriastudentirest.repository.CorsoDiLaureaRepository;

@Service
public class CorsoDiLaureaService {
	
	@Autowired
	CorsoDiLaureaRepository corsoRepository;
	
	public Page<CorsoDiLaurea> findAll(Pageable pageable) {
		return corsoRepository.findAll(pageable);

	}

	public Optional<CorsoDiLaurea> findById(Long id) {
		return corsoRepository.findById(id);
	}

	public CorsoDiLaurea save(CorsoDiLaurea corso) {
		return corsoRepository.save(corso);
	}
	
	public CorsoDiLaurea update(CorsoDiLaurea corso, Long id) {
		Optional<CorsoDiLaurea> corsoResult = corsoRepository.findById(id);
		if (corsoResult.isPresent()) {
			CorsoDiLaurea corsoUpdate = corsoResult.get();
			corsoUpdate.setNome(corso.getNome());
			corsoUpdate.setCodice(corso.getCodice());
			corsoUpdate.setIndirizzo(corso.getIndirizzo());
			corsoUpdate.setNumeroEsami(corso.getNumeroEsami());
			corsoUpdate.setStudenti(corso.getStudenti());
			return corsoRepository.save(corsoUpdate);

		} else {
			return null; // TODO implementare expection
		}
	}
	public void delete(Long id) {
		corsoRepository.deleteById(id);
	}

}
