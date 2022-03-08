package it.epicode.be.segreteriastudentirest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


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
public class AuthControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	@WithAnonymousUser
	public void loginNoBody() throws Exception {
		this.mockMvc.perform(post("/auth/login")).andExpect(status().isBadRequest());
	}

	@Test
	@WithAnonymousUser
	public void getAllStudenti() throws Exception {
		this.mockMvc.perform(get("/api/studente")).andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void listaStudentiWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/studente")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "m.rossi", password = "test", roles = "ADMIN")
	public void addNewStudent() throws Exception {
		CorsoDiLaurea corso = new CorsoDiLaurea();
		Studente studente = new Studente();
		List<Studente> studenti = new ArrayList<Studente>();
		
		corso.setCodice("A12");
		corso.setIndirizzo("Via Nazionale 15");
		corso.setNome("Ingegneria gestionale");
		corso.setNumeroEsami(12);
		corso.setStudenti(studenti);
		
		studente.setCitta("Milano");
		studente.setCognome("Banfi");
		studente.setCorsoDiLaurea(corso);
		studente.setEmail("ciao@gmail.com");
		studente.setIndirizzo("Via Bologna 63");
		studente.setNome("Tiberio");
		
		ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(studente);
        
        MvcResult result = mockMvc.perform(
                post("/api/studente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'nome':'Tiberio'}"))
                .andExpect(content().json("{'cognome':'Banfi'}"))
                .andReturn();

	}
}
