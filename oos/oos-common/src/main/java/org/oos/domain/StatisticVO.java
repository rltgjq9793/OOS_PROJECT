package org.oos.domain;

import java.util.Date;

import lombok.Data;

@Data
public class StatisticVO {

	private int stno;
	private int income;
	private int visits;
	private Date regdate;
}
