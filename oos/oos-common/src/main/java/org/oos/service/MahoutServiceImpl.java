package org.oos.service;

import java.util.ArrayList;
import java.util.List;

import org.oos.domain.MahoutVO;
import org.oos.domain.Mahout_MemberVO;
import org.oos.domain.ReplyVO;
import org.oos.mapper.MahoutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;

@Service
@lombok.extern.java.Log
public class MahoutServiceImpl implements MahoutService{

	@Setter(onMethod_=@Autowired)
	private MahoutMapper mapper;	
	
	@Override
	public void setTable() {
		mapper.delete();
		List<Mahout_MemberVO> memList = mapper.getOrderList();
		List<MahoutVO> userList = new ArrayList<>();
		
		
		memList.forEach(vo -> {
			
			vo.getCartList().forEach(cart -> {
				MahoutVO user = new MahoutVO();
				user.setUser_id(vo.getMno());
				user.setItem_id(cart.getPno());
				user.setValue(3.5);
				
				userList.add(user);
			});
			 
			vo.getOrderList().forEach(order -> {
				int index = -1;
				MahoutVO user = new MahoutVO();
				user.setUser_id(vo.getMno());
				user.setItem_id(order.getPno());
				user.setValue(4);
				for(int i=0; i<userList.size(); i++) {
					if(userList.get(i).getItem_id().equals(order.getPno())
							&& (userList.get(i).getUser_id() == vo.getMno()) ) {
						userList.remove(i);
						break;
					}
				}
				userList.add(user);
			});
			
			
			 mapper.getScoreList(vo.getMid()).forEach(rep -> {
				int index = -1;
				MahoutVO user = new MahoutVO();
				user.setUser_id(vo.getMno());
				user.setItem_id(rep.getPno());
				user.setValue(rep.getScore());
				for(int i=0; i<userList.size(); i++) {
					if(userList.get(i).getItem_id().equals(rep.getPno())
							&& (userList.get(i).getUser_id() == vo.getMno()) ) {
						userList.remove(i);
						break;
					}
				}
				userList.add(user);
			});
		});
		
		userList.forEach(vo -> {
			log.info(vo+"");
			mapper.insert(vo);
		});
		
	}

	@Override
	public List<Mahout_MemberVO> getRecList(String mid) {
		return mapper.getRecList(mid);
	}

}
