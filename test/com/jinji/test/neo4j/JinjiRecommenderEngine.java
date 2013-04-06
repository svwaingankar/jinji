package com.jinji.test.neo4j;

import com.jinji.graph.GraphDb;
import com.jinji.recommender.*;
import com.jinji.recommender.algorithm.JinjiRecommendationAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 22/3/13
 * Time: 8:32 AM
 */
public class JinjiRecommenderEngine {

    private List<JinjiRecommendationAlgorithm> algorithms = new ArrayList<JinjiRecommendationAlgorithm>();
    private GraphDb datasource;
    private int maxRecommendations;

    public JinjiRecommenderEngine(GraphDb db) {
        this.datasource = db;
    }

    public List<JinjiRecommendationAlgorithm> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(List<JinjiRecommendationAlgorithm> algorithms) {
        this.algorithms = algorithms;
    }

    public void setDatasource(GraphDb datasource) {
        this.datasource = datasource;
    }

    public GraphDb getDatasource() {
        return datasource;
    }

    public void processRecommmendations() throws Exception {

        for (JinjiRecommendationAlgorithm algo: algorithms){

            algo.getModel().processSimilarityFactors();
            algo.processRecommendations();

        }
    }

    public List<String> getRecommmendations(String id1002, int count) {

        List<String> algoIds = new ArrayList<String>();
        for (JinjiRecommendationAlgorithm algo: algorithms){

            algoIds.add(algo.getId());
        }

        return null;
    }

    /**
     * Add an algorthm and its multiplying fator to the recommendation engine.
     * @param algorithm
     * @param weight
     */
    public void addAlgorithm(JinjiRecommendationAlgorithm algorithm, int weight) {
        algorithm.setMultiplyingFactor(weight);
        algorithms.add(algorithm);
    }

    public RecommendationResult getRecommmendationsWithTrace(String id1002, int count) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setMaxRecommendations(int maxRecommendations) {
        this.maxRecommendations = maxRecommendations;
    }

    public int getMaxRecommendations() {
        return maxRecommendations;
    }
}
