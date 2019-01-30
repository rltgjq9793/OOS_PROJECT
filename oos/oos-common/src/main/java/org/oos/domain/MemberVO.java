package org.oos.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="tbl_member")
public class MemberVO {
	
	@Id
	private String mid;
	private String mpw;
	private String mname;
	private String pnum;
	private String birth;
	private String email;
	private String address;
	private String addressdetail;
	private Date regdate = new Date();
	
	
	private String sns = "null";
	private char permit = 'Y';
	private char del = 'N';
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="mid")
	private List<MemberAuth> authList;
}
