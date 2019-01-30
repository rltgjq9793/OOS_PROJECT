package org.oos.domain;

import java.util.Date;

import lombok.Data;

@Data
public class HashTagVO {

	private Long hno;
	private String tag;
	private int tagcnt;
	private Date regdate;
	private char del;
	
}
