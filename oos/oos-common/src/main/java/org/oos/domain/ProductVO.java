package org.oos.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProductVO {

	private Long pno;
	private String pname;
	private Long price;
	private Long sno;
	
	private LocalDate regdate;
	
	private String sname; //목록에 상점이름띄울때
	private String mid; //유저맞춤추천
	
	private Date updatedate;
	private StoreVO store;
	private List<ProductImgVO> imgList;
	private List<ProductOptionVO> optList;
	private List<CategoryVO> cateList;
	private List<HashTagVO> tagList;
	private char del;	
	private  char state;	
	private int QuestionReplyCnt;
	private int ReviewReplyCnt;
	private String content;
}
