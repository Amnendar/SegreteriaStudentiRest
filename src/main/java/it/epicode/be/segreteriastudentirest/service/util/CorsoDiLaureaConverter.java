package it.epicode.be.segreteriastudentirest.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.epicode.be.segreteriastudentirest.model.CorsoDiLaurea;
import it.epicode.be.segreteriastudentirest.service.CorsoDiLaureaService;


//classe che converte una stringa in un corso di laurea a partire dal codice

@Component
public class CorsoDiLaureaConverter implements Converter<Long, CorsoDiLaurea>{

	@Autowired
	CorsoDiLaureaService corsoservice;
	
	
	//questo metodo prende un codice e restituisce il corso corrispondente
	@Override
	public CorsoDiLaurea convert(Long codice) {
		return  corsoservice.findById(codice).get();
		
	}
	
	
}
