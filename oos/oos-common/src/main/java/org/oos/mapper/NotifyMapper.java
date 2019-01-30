package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.oos.domain.NotifyVO;

public interface NotifyMapper {
	
	public List<NotifyVO> getList(Map<String, Object> map);
	
	public NotifyVO get(Long sbno);
	
	public int delete(Long sbno);
	
	public int modify(NotifyVO vo);
	
	public int insert(NotifyVO vo);
	
	public int sidCount(Map<String, Object> map);
	
	public int popupSwitch(Map<String, Object> map);
	
	public List<NotifyVO> popupList(Map<String,Object> map);

	public NotifyVO getPopup(Long sbno);
	
	public int popupCount(Map<String, Object> map);
} 
