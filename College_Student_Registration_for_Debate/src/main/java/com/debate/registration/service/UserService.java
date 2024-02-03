package com.debate.registration.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.debate.registration.dto.SignUpDto;
import com.debate.registration.entity.User;

public interface UserService extends UserDetailsService {
	public User save(SignUpDto signUpDto);
}
