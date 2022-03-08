package it.epicode.be.segreteriastudentirest.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.epicode.be.segreteriastudentirest.model.CorsoDiLaurea;
import it.epicode.be.segreteriastudentirest.model.Role;
import it.epicode.be.segreteriastudentirest.model.Roles;
import it.epicode.be.segreteriastudentirest.model.Studente;
import it.epicode.be.segreteriastudentirest.model.User;
import it.epicode.be.segreteriastudentirest.repository.CorsoDiLaureaRepository;
import it.epicode.be.segreteriastudentirest.repository.RoleRepository;
import it.epicode.be.segreteriastudentirest.repository.StudenteRepository;
import it.epicode.be.segreteriastudentirest.repository.UserRepository;

@Component
public class AddUserSpringRunner implements CommandLineRunner {
	
	@Autowired
	StudenteRepository studenteRepository;
	
	@Autowired
	CorsoDiLaureaRepository corsoRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

		Role role = new Role();
		role.setRoleName(Roles.ROLE_ADMIN);
		User user = new User();
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setUserName("admin");
		user.setPassword(bCrypt.encode("admin"));
		user.setEmail("admin@domain.com");
		user.setRoles(roles);
		user.setActive(true);

		roleRepository.save(role);
		userRepository.save(user);
		
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
		
		studenteRepository.save(studente);
		

	}

}
