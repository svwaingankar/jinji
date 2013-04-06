package com.jinji.graph.neo4j;

import com.jinji.graph.GraphDb;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.UniqueFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 22/3/13
 * Time: 7:59 PM
 */
public class Neo4jGraphUtil {

    Neo4jGraphDb graph;

    public Neo4jGraphUtil(Neo4jGraphDb graph) {
        this.graph = graph;
    }

    public Neo4jGraphUtil(GraphDb graph) {
        this.graph = (Neo4jGraphDb)graph;
    }

    public void createUniqueNode(String id, String index, Map<String,Object> props) throws Exception {

        Transaction tx= graph.getDB().beginTx();
        try {

            UniqueFactory<Node> countryFactory = new UniqueFactory.UniqueNodeFactory(graph.getDB().index().forNodes(index)) {
                @Override
                protected void initialize(Node node, Map<String, Object> properties) {
                    node.setProperty("id", properties.get("id"));
                }
            };
            Node node = countryFactory.getOrCreate("id", id);
            for(Map.Entry prop:props.entrySet()){
                node.setProperty((String) prop.getKey(),prop.getValue());
            }
            tx.success();
        }
        catch(Exception e) {
            tx.failure();
            throw e;
        }
        finally {
            tx.finish();
        }

    }

    public void createUniqueRelationship(String startId, String startIndex, String endId, String endIndex, String rel, String relVal) throws Exception {

        Transaction tx= graph.getDB().beginTx();
        try {

            String query = createRelationship(startId, startIndex,  endId,  endIndex,  rel);

            Map<String, Object> props = new HashMap<String, Object>();
            graph.executeQuery(query,props);

            if(relVal!=null){
                query = createRelationshipProperty(startId, startIndex,  endId,  endIndex,  rel, relVal);

                graph.executeQuery(query,props);
            }
            tx.success();
        }
        catch(Exception e) {
            tx.failure();
            throw e;
        }
        finally {
            tx.finish();
        }



    }

    private static String createRelationship(String startId, String startIndex, String endId, String endIndex, String rel){
        StringBuilder query = new StringBuilder();
        query.append("START a=node:")
                .append(startIndex + "(id='" + startId + "'), b = node:")
                .append(endIndex+"(id='"+endId+"')")
                .append(" create unique (a)-[r:" + rel + "]->(b)");

        return query.toString();
    }

    private static String createRelationshipProperty(String startId, String startIndex, String endId, String endIndex, String rel,String relVal){
        StringBuilder query = new StringBuilder();
        query.append("START a=node:")
                .append(startIndex + "(id='" + startId + "'), b = node:")
                .append(endIndex+"(id='"+endId+"')")
                .append(" MATCH a-[r:"+rel+"]-b SET r.VALUE="+relVal);

        return query.toString();
    }

}
