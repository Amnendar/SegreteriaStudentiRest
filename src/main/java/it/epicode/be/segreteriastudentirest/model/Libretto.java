package it.epicode.be.segreteriastudentirest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Libretto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codiceLibretto;
	@OneToOne
	@JoinColumn(name = "studente_id", referencedColumnName = "id")
	private Studente studente;
	
}
