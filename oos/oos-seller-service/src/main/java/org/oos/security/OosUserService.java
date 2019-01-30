package org.oos.security;

import org.oos.persistence.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
public class OosUserService implements UserDetailsService{
	
	@Setter(onMethod_=@Autowired)
	private SellerRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findById(username)
				.filter(vo -> vo != null)
				.map(vo -> new SecurityUser(vo)).get();
	}

}
