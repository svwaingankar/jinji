package com.jinji.recommender;

import com.jinji.graph.GraphDb;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 12:16 AM
 */
public class SimpleGraphDataModel implements GraphDataModel{

    private GraphDb datasource;
    private String user;
    private String item;
    private String primaryRelation;

    public void setDatasource(GraphDb datasource) {
        this.datasource = datasource;
    }

    public GraphDb getDatasource() {
        return datasource;
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

}
