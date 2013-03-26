package com.jinji.test.neo4j;

import com.jinji.graph.GraphDb;
import com.jinji.recommender.SimilarityFactor;

import java.util.ArrayList;
import java.util.List;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 22/3/13
 * Time: 8:32 AM
 */
public class JinjiRecommenderEngine {

    private GraphDb datasource;
    private String user;
    private String item;
    private List<SimilarityFactor> userSimilarity = new ArrayList<SimilarityFactor>();
    private List<SimilarityFactor> itemSimilarity = new ArrayList<SimilarityFactor>();
    private List<SimilarityFactor> userItemSimilarity = new ArrayList<SimilarityFactor>();


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

    public void addUserSimilarityCriteria(SimilarityFactor userSim, int w) {
        userSim.setWeight(w);
        userSim.setId("j_u_"+userSimilarity.size());
        userSim.setEngine(this);
        userSim.setStartIndex(user);
        userSim.setEndIndex(user);

        userSimilarity.add(userSim);

    }

    public void addItemSimilarityCriteria(SimilarityFactor itemSim, int w) {
        itemSim.setWeight(w);
        itemSim.setId("j_i_"+userSimilarity.size());
        itemSim.setEngine(this);
        itemSim.setStartIndex(item);
        itemSim.setEndIndex(item);

        userSimilarity.add(itemSim);
    }

    public void addUserItemSimilarityCriteria(SimilarityFactor userItemSim, int w) {
        userItemSim.setWeight(w);
        userItemSim.setId("j_i_"+userSimilarity.size());
        userItemSim.setEngine(this);
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



}
