package org.oos.service;

import java.util.List;

import org.oos.domain.StatisticVO;
import org.oos.mapper.StatisticMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

public class StatisticServiceImpl implements StatisticService{

	
	@Setter(onMethod_= @Autowired)
	private StatisticMapper mapper;

	@Override
	public List<StatisticVO> Statistic() {
		// TODO Auto-generated method stub
		return mapper.Statistic();
	}
	
}
