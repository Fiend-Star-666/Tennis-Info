package com.intern.tennis.primary;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class Player {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(unique = true)
	private Integer rankOfPlayer;
	
	private Date dateOfBirth; 
	
	private Integer points;
}
//spring.jpa.properties.hibernate.globally_quoted_identifiers=true
//rank was a reserved keyword that is why rankOfPlayer is used