package org.oos.recommend;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CachingUserSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;

import com.zaxxer.hikari.HikariDataSource;

@Service
public class UserRecommend {

	@Inject
	@Resource(name="dataSource")
	private HikariDataSource ds;
	
	public List<RecommendedItem> userRec(int mno) {

		RandomUtils.useTestSeed();	
		JDBCDataModel dataModel = new MySQLJDBCDataModel(ds, "recommend", "user_id", "item_id", "value", null);
		
		try {
			
			UserSimilarity sim = new PearsonCorrelationSimilarity(dataModel);

			UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, sim, dataModel);

			UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, sim);

			List<RecommendedItem> recommendations = recommender.recommend(mno, 5); // 5개 추천

			return recommendations;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}
