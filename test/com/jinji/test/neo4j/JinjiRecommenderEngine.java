package com.jinji.test.neo4j;

import com.jinji.recommender.*;

import java.util.List;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 22/3/13
 * Time: 8:32 AM
 */
public class JinjiRecommenderEngine {

    private RecommendationAlgorithm collaborativeFiltering;
    private SimpleGraphDataModel dataModel;


    public void addUserSimilarityCriteria(SimilarityFactor userSim, int w) {
        userSim.setWeight(w);
        userSim.setId("j_u_"+userSimilarity.size());
        userSim.setModel(this);
        userSim.setStartIndex(user);
        userSim.setEndIndex(user);

        userSimilarity.add(userSim);

    }

    public void addItemSimilarityCriteria(SimilarityFactor itemSim, int w) {
        itemSim.setWeight(w);
        itemSim.setId("j_i_"+userSimilarity.size());
        itemSim.setModel(this);
        itemSim.setStartIndex(item);
        itemSim.setEndIndex(item);

        userSimilarity.add(itemSim);
    }

    public void addUserItemSimilarityCriteria(SimilarityFactor userItemSim, int w) {
        userItemSim.setWeight(w);
        userItemSim.setId("j_i_"+userSimilarity.size());
        userItemSim.setModel(this);
        userItemSim.setStartIndex(user);
        userItemSim.setEndIndex(item);
        userSimilarity.add(userItemSim);
    }

    public void getRecommendation(String user) throws Exception {

        for(SimilarityFactor p:userSimilarity){
            p.calculate();
        }
        for(SimilarityFactor p:itemSimilarity){
            p.calculate();
        }
        for(SimilarityFactor p:userItemSimilarity){
            p.calculate();
        }
    }


    public void setCollaborativeFiltering(RecommendationAlgorithm collaborativeFiltering) {
        this.collaborativeFiltering = collaborativeFiltering;
    }

    public RecommendationAlgorithm getCollaborativeFiltering() {
        return collaborativeFiltering;
    }


    public List<String> getRecommmendations(String id1002, int i, int i1) {
    }

    public void addAlgorithm(JinjiRecommendationAlgorithm algo) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setDataModel(SimpleGraphDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public SimpleGraphDataModel getDataModel() {
        return dataModel;
    }

    public RecommendationResult getRecommmendationsWithTrace(String id1002, int i, int i1) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
