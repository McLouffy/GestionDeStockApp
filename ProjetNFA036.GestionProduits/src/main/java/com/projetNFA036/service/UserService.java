package com.projetNFA036.service;

import org.springframework.data.domain.Page;

import com.projetNFA036.Entities.User;

public interface UserService {

	User getById(Long id);

	Page<User> getAllUserParPage(int page, int size);

}
