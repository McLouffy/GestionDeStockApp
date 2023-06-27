package com.projetNFA036.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetNFA036.Entities.Restaurants;

public interface RestaurantRepository extends JpaRepository<Restaurants, Integer> {

}
