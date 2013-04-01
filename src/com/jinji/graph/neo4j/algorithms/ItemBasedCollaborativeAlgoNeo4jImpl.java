package com.jinji.graph.neo4j.algorithms;

import com.jinji.recommender.algorithm.JinjiRecommendationAlgorithm;
import com.jinji.recommender.datamodel.GraphDataModel;
import com.jinji.recommender.datamodel.SimpleGraphDataModel;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 31/3/13
 * Time: 9:53 PM
 */
public class ItemBasedCollaborativeAlgoNeo4jImpl implements JinjiRecommendationAlgorithm {

    GraphDataModel model;
    int multiplyingFactor;

    public ItemBasedCollaborativeAlgoNeo4jImpl(GraphDataModel simpleDataModel) {
        this.model = simpleDataModel;
    }

    public GraphDataModel getModel() {
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

    public int getMultiplyingFactor() {
        return multiplyingFactor;
    }

    public void setMultiplyingFactor(int multiplyingFactor) {
        this.multiplyingFactor = multiplyingFactor;
    }

}
