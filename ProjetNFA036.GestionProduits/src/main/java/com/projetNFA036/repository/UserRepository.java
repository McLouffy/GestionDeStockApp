package com.projetNFA036.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetNFA036.Entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername (String username);

}
