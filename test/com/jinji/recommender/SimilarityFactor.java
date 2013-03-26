package com.jinji.recommender;

import com.jinji.test.neo4j.JinjiRecommenderEngine;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 23/3/13
 * Time: 11:21 AM
 */
abstract public class SimilarityFactor {

    String startIndex;
    String endIndex;
    int weight;
    String id;

    private JinjiRecommenderEngine engine;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //abstract public void calculate();

    abstract public void calculate() throws Exception;

    public void setEngine(JinjiRecommenderEngine engine) {
        this.engine = engine;
    }

    public JinjiRecommenderEngine getEngine() {
        return engine;
    }

    public String getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(String endIndex) {
        this.endIndex = endIndex;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }
}
