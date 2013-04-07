package com.jinji.recommender;

import com.jinji.graph.GraphDb;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 29/3/13
 * Time: 1:09 AM
 */
public class SimilarityFactorModel {

    private String id;
    private SimilarityFactorRelation relation;
    private int multiplyingFactor;
    private SimilarityFactor similarityFactor;

    public SimilarityFactorModel(String id, int weight, SimilarityFactorRelation similarityFactorRelation) {
        this.id = id;
        this.multiplyingFactor = weight;
        this.relation = similarityFactorRelation;
        this.similarityFactor=null;
    }

    public SimilarityFactorModel(String id, int weight, SimilarityFactor similarityFactor) {
        this.id = id;
        this.multiplyingFactor = weight;
        this.relation = new SimilarityFactorRelation(id,"wt");
        this.similarityFactor = similarityFactor;
    }

    public SimilarityFactorRelation getRelation() {
        return relation;
    }

    public void setRelation(SimilarityFactorRelation relation) {
        this.relation = relation;
    }

    public int getMultiplyingFactor() {
        return multiplyingFactor;
    }

    public void setMultiplyingFactor(int multiplyingFactor) {
        this.multiplyingFactor = multiplyingFactor;
    }

    public void process(GraphDb datasource, String startIndexName, String endIndexName) throws Exception {

        if(similarityFactor!=null){
            similarityFactor.setEndIndex(endIndexName);
            similarityFactor.setStartIndex(startIndexName);
            similarityFactor.setWeight(multiplyingFactor);
            similarityFactor.calculate(datasource);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SimilarityFactor getSimilarityFactor() {
        return similarityFactor;
    }

    public void setSimilarityFactor(SimilarityFactor similarityFactor) {
        this.similarityFactor = similarityFactor;
    }
}