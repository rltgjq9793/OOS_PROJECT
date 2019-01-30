package org.oos.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="tbl_seller_auth")
public class SellerAuthVO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ano;
	private String auth;
	
	
	public SellerAuthVO(String auth) {
		this.auth = auth;
	}

}
