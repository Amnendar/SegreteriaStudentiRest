package it.epicode.be.segreteriastudentirest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class StudenteControllerTest {

	@Autowired
	MockMvc mockMvc;

	CorsoDiLaurea corso1;
	Studente studente1;
	Studente studente2;
	
	@BeforeEach
	public void contextInit() {
		
		log.info("*** STARTUP ***");
		corso1 = new CorsoDiLaurea();
		studente1 = new Studente();
		studente2 = new Studente();
		List<Studente> studenti = new ArrayList<Studente>();

		corso1.setCodice("A12");
		corso1.setIndirizzo("Via Nazionale 15");
		corso1.setNome("Ingegneria gestionale");
		corso1.setNumeroEsami(12);
		corso1.setStudenti(studenti);

		studente1.setCitta("Milano");
		studente1.setCognome("Banfi");
		studente1.setCorsoDiLaurea(corso1);
		studente1.setEmail("ciao@gmail.com");
		studente1.setIndirizzo("Via Bologna 63");
		studente1.setNome("Tiberio");
		
		studente2.setCitta("Lamezia Terme");
		studente2.setCognome("Torcasio");
		studente2.setCorsoDiLaurea(corso1);
		studente2.setEmail("terron@libero.cal");
		studente2.setIndirizzo("Via Dei Bizantini 63");
		studente2.setNome("Pasquale");
	}

	@Test
	@WithMockUser(username = "m.rossi", password = "test", roles = "ADMIN")
	public void addNewStudent() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(studente1);

		MvcResult result = mockMvc.perform(post("/api/studente").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated()).andExpect(content().json("{'nome':'Tiberio'}"))
				.andExpect(content().json("{'cognome':'Banfi'}")).andReturn();

	}
	
	@Test
	@WithMockUser(username = "m.rossi", password = "test", roles = "ADMIN")
	public void updateStudent() throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(studente2);
        this.mockMvc.perform(
                put("/api/studente/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isOk()).andExpect(content().json("{'nome':'Pasquale'}"));
	}
	
//	@Test
//    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
//    public void deleteStudent() throws Exception {
//        this.mockMvc.perform(delete("/api/studente/1")).andExpect(status().isOk());
//    }  
	
	
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
	

	

}
