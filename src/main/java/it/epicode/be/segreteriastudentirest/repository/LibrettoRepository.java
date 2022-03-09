package it.epicode.be.segreteriastudentirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.segreteriastudentirest.model.Libretto;

public interface LibrettoRepository extends JpaRepository<Libretto, Long> {

}
