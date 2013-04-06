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
        calculateItemToItemSimilarity();
        calculateUserToUserSimilarity();
        calculateUserToItemReco();
    }

    private void calculateUserToItemReco() {


    }

    private void calculateUserToUserSimilarity() {


    }

    private void calculateItemToItemSimilarity() {


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

    //start n=node(#{m.neo_id}) match (n)<-[r1:rated]-(x)-[r2:rated]->(y) where (r1.stars>3 and r2.stars>3) return y.title,count(*) order by count(*) desc limit 5

    public static String simpleItemBasedWithNoProperty() {

        StringBuilder query = new StringBuilder();
        query.append("start n=node(#{m.neo_id}) match (n)<-[r1:rated]-(x)-[r2:rated]->(y) where (r1.stars>3 and r2.stars>3) return y.title,count(*) order by count(*) desc limit 5 ");
        return query.toString();

    }


}
