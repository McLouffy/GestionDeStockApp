package com.projetNFA036.Entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Historique {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idHistorique;

	//stock FK de Historique
	@ManyToOne
	private Stock stock;

	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateControle;

	private String action;

	public LocalDate getDateControle() {
		return dateControle;
	}

	public void setDateControle(LocalDate dateControle) {
		this.dateControle = dateControle;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getIdHistorique() {
		return idHistorique;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setIdHistorique(int idHistorique) {
		this.idHistorique = idHistorique;
	}

}
