package org.oos.security;

import java.util.stream.Collectors;

import org.oos.domain.MemberVO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class SecurityUser extends User{

	private MemberVO member;
	public SecurityUser(MemberVO member) {
		
		super(member.getMid(), member.getMpw(),
			member.getAuthList().stream()
			.map(auth -> new SimpleGrantedAuthority("ROLE_"+auth.getAuth()))
			// 다른 타입으로 바꿔줌 : mapping
			.collect(Collectors.toList())
			//각각의 auth가 SimpleGrantedAuthority로 바뀜
		);
		
		this.member = member;
	}	

}
