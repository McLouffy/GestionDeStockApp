package com.projetNFA036.Entities;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_stock")
	private int idStock;

	// clé étrangère de la table stock
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produit", referencedColumnName = "id_produit")
	@NotNull(message = "Vous devez choisir un produit")
	private Produits produit;

	// @Temporal(TemporalType.DATE)
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOuverture;
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateExpiration;
	private String tempsRestant;
	private String status;
	private String userCreation;

	@ManyToOne
	private User user;

	// @OneToMany( mappedBy = "stock")
	// private List<Produits> produits;

	public void setDateExpiration(LocalDate dateExp) {
		this.dateExpiration = dateExp;
	}

	public void setDateOuverture(LocalDate dateOuv) {
		dateOuverture = dateOuv;

	}

	public LocalDate getDateExpiration() {
		return dateExpiration;
	}

	public String getTempsRestant() {
		long jour = ChronoUnit.DAYS.between(LocalDate.now(), getDateExpiration());
		tempsRestant = jour + " j ";

		return tempsRestant;
	}

}
