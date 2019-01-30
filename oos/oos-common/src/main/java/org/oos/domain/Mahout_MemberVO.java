package org.oos.domain;

import java.util.List;

import lombok.Data;

@Data
public class Mahout_MemberVO {
	private String mid;
	private int mno;
	private int pno;
	private List<CartVO> cartList;
	private List<OrderDetailVO> orderList;
}
