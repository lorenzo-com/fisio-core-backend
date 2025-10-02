package com.example.physiocore.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final ClientService clientService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return clientService.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + " no encontrado"));
	}

	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		return clientService.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException(id + " no encontrado"));
	}
}