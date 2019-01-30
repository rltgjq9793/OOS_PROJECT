package org.oos.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.java.Log;

// 시큐리티에 대한 설정을 담당한다

@EnableGlobalMethodSecurity(prePostEnabled = true)
//메소드의 pre post 잡아줌(이거 안넣으면 컨트롤러 @PreAuthorize 활성화못시킴)
@Log
@EnableWebSecurity // SecurityConfig를 인식되게 한다
public class SecurityConfig extends WebSecurityConfigurerAdapter{
									// 설정 담당 클래스
	@Autowired
	DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	//웹과 관련된 보안 설정
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/m/**","/css/**", "/js/**","/img/**", "/font/**", "**/favicon.ico", "/index").permitAll()
				.antMatchers("/user/list").permitAll()
				.antMatchers("/order/**", "/user/**","/user/mypage/**").hasRole("USER");
		
		http.formLogin().loginPage("/oos/login")
	      .defaultSuccessUrl("/main");
		// access denied 걸리면  로그인페이지 갈거라고 선언
		
		http.rememberMe().key("oos")
		
			.userDetailsService(userDetailsService())
			.tokenRepository(getJDBCReopsitory())
			.tokenValiditySeconds(60*60*24*15);
	
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).invalidateHttpSession(true)
		.logoutSuccessUrl("/main");
		
	}	
	
	private PersistentTokenRepository getJDBCReopsitory() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		// Oosserservice를 빈으로 설정
		return new OosUserService();
	}
	
	
}
