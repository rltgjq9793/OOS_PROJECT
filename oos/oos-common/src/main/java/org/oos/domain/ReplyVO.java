package org.oos.domain;

import java.util.Date;


import lombok.Data;

@Data
public class ReplyVO {

	private Long rno;
	private Long pno;
	private String mid;
	private String sid;
	private String title;
	private String content;
	private String state;
	
	private Date regdate;
	private Date updatedate;
	private char kind;
	private Long parent;
	private int depth;
	private double score;
	
	private StoreVO store;
	private ProductVO product;
}
