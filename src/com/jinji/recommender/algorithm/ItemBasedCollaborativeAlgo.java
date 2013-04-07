package com.jinji.recommender.algorithm;

import com.jinji.graph.neo4j.algorithms.ItemBasedCollaborativeAlgoNeo4jImpl;
import com.jinji.recommender.datamodel.GraphDataModel;
import com.jinji.recommender.datamodel.SimpleGraphDataModel;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 12:04 AM
 */
public class ItemBasedCollaborativeAlgo implements JinjiRecommendationAlgorithm {

    JinjiRecommendationAlgorithm impl;

    @Override
    public void setMultiplyingFactor(int multiplyingFactor) {
        impl.setMultiplyingFactor(multiplyingFactor);
    }
    @Override
    public int getMultiplyingFactor() {
        return impl.getMultiplyingFactor();
    }

    public ItemBasedCollaborativeAlgo(SimpleGraphDataModel simpleDataModel) {

        if(simpleDataModel.getDatasource().getDataSourceId().equals("Neo4j"))
            impl = new ItemBasedCollaborativeAlgoNeo4jImpl(simpleDataModel);

    }
    @Override
    public GraphDataModel getModel() {
        return impl.getModel();
    }

    @Override
    public void processRecommendations() throws Exception {
        impl.processRecommendations();
    }

    @Override
    public String getId() {
        return impl.getId();
    }
}
