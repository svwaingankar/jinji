package com.jinji.recommender.datamodel;

import com.jinji.graph.GraphDb;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 12:16 AM
 */
public class SimpleGraphDataModel implements GraphDataModel {

    private GraphDb datasource;
    private String user;
    private String item;
    private String primaryRelation;
    private String primaryRelationProperty;

    public void setDatasource(GraphDb datasource) {
        this.datasource = datasource;
    }

    public GraphDb getDatasource() {
        return datasource;
    }

    @Override
    public void processSimilarityFactors() {
        //no similarity factors in simple data model
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setPrimaryRelation(String primaryRelation) {
        this.primaryRelation = primaryRelation;
    }

    public String getPrimaryRelation() {
        return primaryRelation;
    }

    public void setPrimaryRelationProperty(String primaryRelationProperty) {
        this.primaryRelationProperty = primaryRelationProperty;
    }

    public String getPrimaryRelationProperty() {
        return primaryRelationProperty;
    }
}
