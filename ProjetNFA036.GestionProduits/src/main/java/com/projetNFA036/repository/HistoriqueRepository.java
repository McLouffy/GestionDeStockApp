package com.projetNFA036.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetNFA036.Entities.Historique;
import com.projetNFA036.Entities.Stock;

public interface HistoriqueRepository extends JpaRepository<Historique, Integer> {

	Historique save(Stock s);

}
