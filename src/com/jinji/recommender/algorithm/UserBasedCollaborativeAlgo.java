package com.jinji.recommender.algorithm;

import com.jinji.recommender.datamodel.SimpleGraphDataModel;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 11:17 AM
 */
public class UserBasedCollaborativeAlgo implements JinjiRecommendationAlgorithm {

    SimpleGraphDataModel model;

    int multiplyingFactor;

    public void setMultiplyingFactor(int multiplyingFactor) {
        this.multiplyingFactor = multiplyingFactor;
    }

    public int getMultiplyingFactor() {
        return multiplyingFactor;
    }

    public UserBasedCollaborativeAlgo(SimpleGraphDataModel simpleDataModel) {
        this.model =simpleDataModel;
    }

    public SimpleGraphDataModel getModel() {
        return model;
    }

    @Override
    public void processRecommendations() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setModel(SimpleGraphDataModel model) {
        this.model = model;
    }
}
