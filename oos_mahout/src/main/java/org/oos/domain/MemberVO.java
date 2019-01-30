package org.oos.domain;

import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	private String mid;
	private int mno;
	private int ano;
	private List<CartVO> cartList;
	private List<OrderDetailVO> orderList;
}
