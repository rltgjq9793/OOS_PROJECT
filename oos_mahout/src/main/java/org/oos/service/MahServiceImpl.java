package org.oos.service;

import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.jfree.util.Log;
import org.oos.domain.UserVO;
import org.oos.mapper.MahMapper;
import org.oos.recommend.UserRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.Setter;

@Service
@lombok.extern.java.Log
public class MahServiceImpl implements MahService{

	@Setter(onMethod_=@Autowired)
	private MahMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private UserRecommend userRec;

	@Override
	@Scheduled(cron="0 1 0 * * *")
	public void getMemberList() {
		log.info("start!!!!!!!!!!!!!!!!!!!!!!!!");
		mapper.delete();
		mapper.getMemberList().forEach(member -> {
			List<RecommendedItem> userRecommendations = userRec.userRec(member.getAno());
			if(userRecommendations.size() > 0) {
				userRecommendations.forEach(rec -> {
					UserVO vo = new UserVO();
					vo.setUser_id(member.getAno());
					vo.setItem_id(rec.getItemID());
					
					mapper.insert_user_order(vo);
				});
			}
			
		});
	}
	

}
