package org.oos.controller;


import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oos.domain.UserVO;
import org.oos.mapper.MahMapper;
import org.oos.recommend.UserRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TodoControllerTests {
	
	@Setter(onMethod_=@Autowired)
	private MahMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private UserRecommend userRec;

	@Test
	public void getMemberList() {
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
