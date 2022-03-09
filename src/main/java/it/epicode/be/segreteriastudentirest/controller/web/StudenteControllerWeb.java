package it.epicode.be.segreteriastudentirest.controller.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.segreteriastudentirest.model.Studente;
import it.epicode.be.segreteriastudentirest.service.CorsoDiLaureaService;
import it.epicode.be.segreteriastudentirest.service.StudenteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/web")
public class StudenteControllerWeb {

	@Autowired
	StudenteService studenteservice;
	
	@Autowired
	CorsoDiLaureaService corsoservice;
	
	
	@GetMapping("/mostrastudenti")
	public ModelAndView findAllStudenti() {
		log.info("*** Find all studenti ***");
		ModelAndView view = new ModelAndView("elencoStudenti");//dentro ci va l elenco studenti che stiamo richiamando
		view.addObject("studenti", studenteservice.findAll());//qui passiamo la lista, al primo posto il nome della variabile e al secondo cosa effettivamente e
		return view;
	}
	
	
	@GetMapping("/mostraformaggiungi")
	public String aggiungiStudente(Studente studente, Model model) {
		log.info("*** aggiungiStudenti ***");
		model.addAttribute("corsi", corsoservice.findAll());
		return "aggiungiStudente";
	}
	
	
	@PostMapping("/aggiungistudente")
	public String aggiungiStudente(@Valid Studente studente, BindingResult result, Model model) { //valid serve a verificare sui campi a livello di back end, BindingResult serve a vedere il risultato della post
		log.info("*** aggiunta studente in corso ***");
		if(result.hasErrors()) {
			model.addAttribute("corsi", corsoservice.findAll());
			return "aggiungiStudente";
		}
		studenteservice.save(studente); 
		log.info("*** studente aggiunto ***");
		return "redirect:/web/mostrastudenti";
		
	}
	
	
	@GetMapping("/mostraformaggiornastudente/{id}")
	public ModelAndView mostraFormAggiornaStudente(@PathVariable("id") Long id, Model model) {
		log.info("*** mostra form aggiornamento studente ***");
		Optional<Studente> tempStudente =studenteservice.findById(id);
		if (tempStudente.isPresent()){
			ModelAndView view = new ModelAndView("aggiornaStudente");
			view.addObject("studente", tempStudente.get());
			view.addObject("corsi", corsoservice.findAll());
			return view;
		}
		else {
			return new ModelAndView("error").addObject("message", "ID non trovato!");
		}
	}
	
	
}
