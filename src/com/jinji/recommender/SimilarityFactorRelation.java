package com.jinji.recommender;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 12:09 PM
 */
public class SimilarityFactorRelation {

    String id;
    String property;

    public SimilarityFactorRelation(String relationshipname, String prop) {
        this.id = relationshipname;
        this.property = prop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
