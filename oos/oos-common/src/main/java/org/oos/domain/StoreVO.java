package org.oos.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class StoreVO {
	private Long sno;
	private String sname;
	private String owner;
	private String saddress;
	private String saddressdetail;
	private String tel;
	private Date regdate;
	private Date updatedate;
	private char del;
	
	private int pdNum; //총 상품 개수
	private int likeNum; //찜 개수
	private String mid; // 찜목록 가져올때 쓰려고
	private int odNum; //총 주문개수
	
	private int visitcnt;
	
	private List<StoreImgVO> imgList;
	private List<StoreHashTagVO> hashList;

}
