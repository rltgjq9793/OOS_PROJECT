package org.oos.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrderDetailVO {

	private Long odno;
	private Long ono;
	private Long pno;
	private Long qty;
	private Long opno;
	private String pnum;
	private String name;
	private String address;
	private String addressDetail;
	private String del;
	private String mid;
	private String detail_state;
	private Long sno;
	private String dno;
	private Date regdate;
	
	private ProductVO product;
	private ProductOptionVO option;
	private ProductImgVO img;
	private OrderVO order;
}
