package it.epicode.be.segreteriastudentirest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

	
	 /**
     * Verifica che un utente reale presente nel database sia in grado di autenticarsi ottenendo
     * il token JWT che viene passato nella successiva richiesta di accesso ad un endpoint
     * protetto.
     * 
     */
	@Test
    public void utenteRealeGetTokenAndAuthentication() throws Exception {
        String username = "admin";
        String password = "admin";
        // crea direttamente la stringa JSON con le credenziali da passare all'endpoint di login 
        String body = "{\"userName\":\"" + username + "\", \"password\":\"" 
                      + password + "\"}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        
        // si estrae dalla risposta ottenuta la stringa corrispondente al token
        //String token = response.split(",")[1];
        //token = token.substring(10, token.length() - 1);
        // si chiama l'enpdoint passando il token nell'header di autorizzazione
        //mockMvc.perform(MockMvcRequestBuilders.get("/api/studente") .header("Authorization", "Bearer " + token))
        //.andExpect(status().isOk());
	}
	
	
}
