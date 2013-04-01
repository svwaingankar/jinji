package com.jinji.recommender;

import com.jinji.graph.GraphDb;
import com.jinji.recommender.datamodel.GraphDataModel;

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

    abstract public void calculate(GraphDb datasource) throws Exception;

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

    public enum Type {
        UserToUser,
        ItemToItem,
        ItemToUser,
        UserToItem;

    }
}
