package com.jinji.graph.neo4j.algorithms;

import com.jinji.recommender.algorithm.JinjiRecommendationAlgorithm;
import com.jinji.recommender.datamodel.ContentBasedGraphDataModel;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 12:08 AM
 */
public class SimpleContentBasedAlgoNeo4jImpl implements JinjiRecommendationAlgorithm {


    ContentBasedGraphDataModel model;
    int multiplyingFactor;

    public void setMultiplyingFactor(int multiplyingFactor) {
        this.multiplyingFactor = multiplyingFactor;
    }

    public int getMultiplyingFactor() {
        return multiplyingFactor;
    }

    public SimpleContentBasedAlgoNeo4jImpl(ContentBasedGraphDataModel model) {
        this.model=model;
    }

    public ContentBasedGraphDataModel getModel() {
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

    public void setModel(ContentBasedGraphDataModel model) {
        this.model = model;
    }
}
