package it.epicode.be.segreteriastudentirest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.epicode.be.segreteriastudentirest.model.CorsoDiLaurea;
import it.epicode.be.segreteriastudentirest.model.Studente;

@SpringBootTest
@AutoConfigureMockMvc
class CorsoDiLaureaControllerTest {

	
	@Autowired
	MockMvc mockMvc;
	
	CorsoDiLaurea corso1;
	CorsoDiLaurea corso2;
	
	
	@BeforeEach
	public void contextInit() {

		corso1 = new CorsoDiLaurea();
		corso2 = new CorsoDiLaurea();

		corso1.setCodice("A12");
		corso1.setIndirizzo("Via Nazionale 15");
		corso1.setNome("Ingegneria gestionale");
		corso1.setNumeroEsami(12);
		
		corso2.setCodice("B22");
		corso2.setIndirizzo("Via Nazionale 15");
		corso2.setNome("Economia Aziendale");
		corso2.setNumeroEsami(10);
		
		

		
	}
	
	@Test
	@WithAnonymousUser
	public void getAllCorsi() throws Exception {
		this.mockMvc.perform(get("/api/corso")).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = "m.rossi", password = "test", roles = "ADMIN")
	public void addNewCorso() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(corso1);

		MvcResult result = mockMvc.perform(post("/api/corso").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated()).andExpect(content().json("{'codice':'A12'}"))
				.andExpect(content().json("{'nome':'Ingegneria gestionale'}")).andReturn();

	}
	
	@Test
	@WithMockUser(username = "m.rossi", password = "test", roles = "ADMIN")
	public void updateCorso() throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(corso2);
        this.mockMvc.perform(
                put("/api/corso/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isOk()).andExpect(content().json("{'codice':'B22'}"));
	}
	
	@Test
	@WithMockUser(username = "m.rossi", password = "test", roles = "ADMIN")
	public void getCorsoById() throws Exception{
		this.mockMvc.perform(get("/api/corso/1")).andExpect(status().isOk())
		.andExpect(status().isOk()).andExpect(content().json("{'codice':'A12'}"));
	}
	

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void listaCorsiWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/corso")).andExpect(status().isOk());
	}
	
}
