package com.jinji.recommender.algorithm;

import com.jinji.recommender.datamodel.GraphDataModel;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 12:09 AM
 */
public interface JinjiRecommendationAlgorithm {

    public void setMultiplyingFactor(int multiplyingFactor);
    public int getMultiplyingFactor();
    public abstract GraphDataModel getModel();
    public void processRecommendations() throws Exception;
    public String getId();

}
