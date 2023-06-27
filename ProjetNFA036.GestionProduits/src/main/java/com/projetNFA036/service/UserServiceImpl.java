package com.projetNFA036.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.projetNFA036.Entities.User;
import com.projetNFA036.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.getById(id);
	}

	@Override
	public Page<User> getAllUserParPage(int page, int size) {
		return userRepository.findAll(PageRequest.of(page, size));
	}


}
