package com.google.expensemanagementapi.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.expensemanagementapi.entity.User;
import com.google.expensemanagementapi.exception.UserNotFoundException;
import com.google.expensemanagementapi.repository.UserRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username).orElseThrow(()->new UserNotFoundException("User Not Found"));
		Set<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
	}
}
