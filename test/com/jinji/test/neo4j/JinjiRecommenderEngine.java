package com.jinji.test.neo4j;

import com.jinji.graph.GraphDb;
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
    private GraphDb datasource;

    public void setDatasource(GraphDb datasource) {
        this.datasource = datasource;
    }

    public GraphDb getDatasource() {
        return datasource;
    }

    public void getRecommendation(String user) throws Exception {

/*        for(SimilarityFactor p:userSimilarity){
            p.calculate();
        }
        for(SimilarityFactor p:itemSimilarity){
            p.calculate();
        }
        for(SimilarityFactor p:userItemSimilarity){
            p.calculate();
        }*/
    }


    public void setCollaborativeFiltering(RecommendationAlgorithm collaborativeFiltering) {
        this.collaborativeFiltering = collaborativeFiltering;
    }

    public RecommendationAlgorithm getCollaborativeFiltering() {
        return collaborativeFiltering;
    }

    public List<String> getRecommmendations(String id1002, int i, int i1) {
        return null;
    }

    public void addAlgorithm(JinjiRecommendationAlgorithm algo, int i) {
        algo.
    }

    public RecommendationResult getRecommmendationsWithTrace(String id1002, int i, int i1) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
