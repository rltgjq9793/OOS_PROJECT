package org.oos.domain;

import java.util.Date;

import lombok.Data;

@Data
public class AdminNotifyVO {

	private Long bno;
	private String sid;
	private String del;
	private String title,content;
	private Date updatedate, regdate;
}
