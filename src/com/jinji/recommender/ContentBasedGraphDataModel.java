package com.jinji.recommender;

import com.jinji.graph.GraphDb;

import java.util.ArrayList;
import java.util.List;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 12:16 AM
 */
public class ContentBasedGraphDataModel implements GraphDataModel{

    private GraphDb datasource;
    private String user;
    private String item;
    private String primaryRelation;
    private List<SimilarityFactor> userSimilarity = new ArrayList<SimilarityFactor>();
    private List<SimilarityFactor> itemSimilarity = new ArrayList<SimilarityFactor>();
    private List<SimilarityFactor> userItemSimilarity = new ArrayList<SimilarityFactor>();



    public ContentBasedGraphDataModel(GraphDb db) {
        this.datasource =db;
    }


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
        itemSim.setId("j_i_"+itemSimilarity.size());
        itemSim.setModel(this);
        itemSim.setStartIndex(item);
        itemSim.setEndIndex(item);

        itemSimilarity.add(itemSim);
    }

    public void addUserItemSimilarityCriteria(SimilarityFactor userItemSim, int w) {
        userItemSim.setWeight(w);
        userItemSim.setId("j_i_"+userItemSimilarity.size());
        userItemSim.setModel(this);
        userItemSim.setStartIndex(user);
        userItemSim.setEndIndex(item);
        userItemSimilarity.add(userItemSim);
    }

}
