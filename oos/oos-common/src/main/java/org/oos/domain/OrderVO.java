package org.oos.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderVO {

	private Long ono;
	private Long dno;
	private Long total;
	private Long sno;
	
	private String mid;
	private String state;
	
	private Date regdate;
	private char del;
	
	private OrderDetailVO detail;
	
	private ProductVO product; // 대표상품 가져오기
}
