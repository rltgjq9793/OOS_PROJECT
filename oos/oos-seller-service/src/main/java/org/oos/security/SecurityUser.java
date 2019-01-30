package org.oos.security;

import java.util.stream.Collectors;

import org.oos.domain.SellerVO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class SecurityUser extends User{

	private SellerVO seller;
	

	
	public SecurityUser(SellerVO seller) {
		
		super(seller.getSid(), seller.getSpw(),
				seller.getAuthList().stream()
			.map(auth -> new SimpleGrantedAuthority("ROLE_"+auth.getAuth()))
			// 다른 타입으로 바꿔줌 : mapping
			.collect(Collectors.toList())
			//각각의 auth가 SimpleGrantedAuthority로 바뀜
		);
		
		this.seller = seller;
	}	

}
