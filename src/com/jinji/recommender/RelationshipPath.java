package com.jinji.recommender;

import java.util.ArrayList;
import java.util.List;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 25/3/13
 * Time: 10:52 PM
 */
public class RelationshipPath {

    private List<Relation> relationships = new ArrayList<Relation>();
    private List<Node> nodes = new ArrayList<Node>();

    public RelationshipPath relationship(String relationship, Direction dir) {

        relationships.add(new Relation(relationship,dir));
        return this;
    }

    public List<Relation> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relation> relationships) {
        this.relationships = relationships;
    }


    public RelationshipPath node() {
        nodes.add(null);
        return this;
    }

    public RelationshipPath addStartNode(Node node){

        nodes.add(0,node);
        return this;
    }
    public RelationshipPath addEndNode(Node node){

        nodes.add(node);
        return this;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
