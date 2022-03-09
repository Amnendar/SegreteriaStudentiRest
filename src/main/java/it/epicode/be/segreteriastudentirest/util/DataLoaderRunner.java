package it.epicode.be.segreteriastudentirest.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.be.segreteriastudentirest.model.CorsoDiLaurea;
import it.epicode.be.segreteriastudentirest.model.Docente;
import it.epicode.be.segreteriastudentirest.model.Libretto;
import it.epicode.be.segreteriastudentirest.model.Studente;
import it.epicode.be.segreteriastudentirest.repository.CorsoDiLaureaRepository;
import it.epicode.be.segreteriastudentirest.repository.DocenteRepository;
import it.epicode.be.segreteriastudentirest.repository.LibrettoRepository;
import it.epicode.be.segreteriastudentirest.repository.StudenteRepository;

@Component
public class DataLoaderRunner implements CommandLineRunner {
	
	@Autowired
	StudenteRepository studenteRepository;
	
	@Autowired
	CorsoDiLaureaRepository corsoRepository;

	@Autowired
	DocenteRepository docenterepo;
	
	@Autowired
	LibrettoRepository librettorepo;

	@Override
	public void run(String... args) throws Exception {
		
		Docente docente1 = new Docente();
		
		docente1.setNome("Renato");
		docente1.setCognome("Ferretti");
		
		docenterepo.save(docente1);
		
		List<Docente> docenti = new ArrayList<>();
		docenti.add(docente1);
		
		CorsoDiLaurea corso1 = new CorsoDiLaurea();
		CorsoDiLaurea corso2 = new CorsoDiLaurea();
		
	
		corso1.setCodice("A12");
		corso1.setIndirizzo("Via Nazionale 15");
		corso1.setNome("Ingegneria gestionale");
		corso1.setNumeroEsami(12);
		corso1.setDocenti(docenti);
		//corso1.setStudenti(studenti);
		
		corso2.setCodice("B33");
		corso2.setIndirizzo("Via Majorana 1");
		corso2.setNome("Scienze Politiche");
		corso2.setNumeroEsami(10);
		corso2.setDocenti(docenti);
		//corso2.setStudenti(studenti);
		
		corsoRepository.save(corso1);
		corsoRepository.save(corso2);
		
		Studente studente1 = new Studente();
		Studente studente2 = new Studente();
//		List<Studente> studenti = new ArrayList<>();
		
		studente1.setCitta("Milano");
		studente1.setCognome("Banfi");
		studente1.setCorsoDiLaurea(corso1);
		studente1.setEmail("ciao@gmail.com");
		studente1.setIndirizzo("Via Bologna 63");
		studente1.setNome("Tiberio");
		
		
		studente2.setCitta("Latina");
		studente2.setCognome("Jackson");
		studente2.setCorsoDiLaurea(corso2);
		studente2.setEmail("mjo@gmail.com");
		studente2.setIndirizzo("Via dei Proci 63");
		studente2.setNome("Micheal");
		
		
		
		
		studenteRepository.save(studente1);
		studenteRepository.save(studente2);
		
		Libretto libretto1 = new Libretto();
		Libretto libretto2 = new Libretto();
		
		libretto1.setCodiceLibretto("L2323");
		libretto1.setStudente(studente1);

		libretto2.setCodiceLibretto("L6655");
		libretto2.setStudente(studente2);
		
		librettorepo.save(libretto1);
		librettorepo.save(libretto2);
		
	}

	

}
