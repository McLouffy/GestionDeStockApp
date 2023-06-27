package com.projetNFA036.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Restaurants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRestaurant;
	// @Size(min = 4, max = 15)
	private String nomRestaurant;
	private String adresseRestaurant;
	private int nombreUser;

	// @JsonIgnore
	// @OneToMany(mappedBy = "restaurant")
	// private List<Utilisateurs> utilisateurs;

}
