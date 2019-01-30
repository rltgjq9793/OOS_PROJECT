package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.NotifyVO;
import org.oos.mapper.NotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class NotifyServiceImpl implements NotifyService{
	
	@Setter(onMethod_= @Autowired)
	private NotifyMapper mapper;
	
	
	@Override
	public List<NotifyVO> getList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.getList(map);
	}

	@Override
	public NotifyVO get(Long sbno) {
		// TODO Auto-generated method stub
		return mapper.get(sbno);
	}

	@Override
	public int delete(Long sbno) {
		// TODO Auto-generated method stub
		return mapper.delete(sbno);
	}

	@Override
	public int modify(NotifyVO vo) {
		// TODO Auto-generated method stub
		return mapper.modify(vo);
	}

	@Override
	public int insert(NotifyVO vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int sidCount(Map<String, Object> map) {
		
		return mapper.sidCount(map);
	}

	@Override
	public int popupSwitch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.popupSwitch(map);
	}



	@Override
	public List<NotifyVO> popupList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.popupList(map);
	}

	@Override
	public NotifyVO getPopup(Long sbno) {
		// TODO Auto-generated method stub
		return mapper.getPopup(sbno);
	}

	@Override
	public int popupCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.popupCount(map);
	}

	
}
