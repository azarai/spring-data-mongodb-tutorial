package de.codeboje.springboot.tutorials.datamongodb;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

@Document
public class User {

	@Id
	private String id;
	
	@Indexed(unique = true)
	private String username;
	
	@TextIndexed
	private String firstname;
	
	@TextIndexed
	private String lastname;
	
	@TextScore
	private Float textScore;
	
	private Address homeAddress;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Float getTextScore() {
		return textScore;
	}

	public void setTextScore(Float textScore) {
		this.textScore = textScore;
	}
}
