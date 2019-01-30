package org.oos.domain;

import lombok.Data;

@Data
public class AuthDTO {

	private String sns;
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String user_id;
	private String user_pw;
	private String del;
}
