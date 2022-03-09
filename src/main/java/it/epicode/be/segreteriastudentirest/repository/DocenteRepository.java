package it.epicode.be.segreteriastudentirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.segreteriastudentirest.model.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

}
