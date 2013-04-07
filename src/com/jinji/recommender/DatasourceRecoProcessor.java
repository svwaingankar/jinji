package com.jinji.recommender;

import com.jinji.recommender.algorithm.JinjiRecommendationAlgorithm;

import java.util.List;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 7/4/13
 * Time: 8:30 AM
 */
public interface DatasourceRecoProcessor {

    public void processFinalRecommendations(List<JinjiRecommendationAlgorithm> algorithms) throws Exception;
    public List<String> getRecommendations(String user, int count, List<JinjiRecommendationAlgorithm> algorithms) throws Exception;

}
