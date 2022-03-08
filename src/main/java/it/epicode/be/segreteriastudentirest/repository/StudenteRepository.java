package it.epicode.be.segreteriastudentirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.segreteriastudentirest.model.Studente;

public interface StudenteRepository extends JpaRepository<Studente, Long>{

}
