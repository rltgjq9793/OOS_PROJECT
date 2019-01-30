package org.oos.domain;

import java.util.Date;

import lombok.Data;

@Data
public class NotifyVO {

	private Long sbno;
	private String sid;
	private String del;
	private String title,content;
	private Date updatedate, regdate;
	private String state;
	
	private String sname;
	private Long sno;
	
}
