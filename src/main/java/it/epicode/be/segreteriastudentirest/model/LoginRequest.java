package it.epicode.be.segreteriastudentirest.model;

import lombok.Data;

@Data
public class LoginRequest {

	private String userName;
	private String password;

}
