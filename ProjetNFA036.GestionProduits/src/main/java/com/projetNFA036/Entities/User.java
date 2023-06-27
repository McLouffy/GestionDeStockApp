package com.projetNFA036.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUtilisateur;

	@NotBlank(message = "Vous devez saisir un login")
	@Column(name = "username")
	private String username;

	@NotBlank(message = "Vous devez saisir un mot de passe")
	@Column(name = "password")
	private String password;

	@NotBlank(message = "Vous devez saisir un nom")
	@Column(name = "nom")
	private String nom;

	@NotBlank(message = "Vous devez saisir un prénom")
	@Column(name = "prenom")
	private String prenom;

	private Boolean enabled;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_utilisateur"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	@NotNull(message = "Vous devez choisir un role")
	private List<Roles> role;

}