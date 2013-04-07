package com.jinji.graph.neo4j.datamodel;

import com.jinji.graph.GraphDb;
import com.jinji.graph.neo4j.QueryRegistry;
import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.recommender.SimilarityFactor;
import org.neo4j.graphdb.Transaction;

import java.util.HashMap;
import java.util.Map;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 23/3/13
 * Time: 11:21 AM
 */
public class SimpleNodePropertyNeo4jImpl extends SimilarityFactor {

    String property;

    public SimpleNodePropertyNeo4jImpl(String prop) {
        this.property = prop;
    }

    @Override
    public void calculate(GraphDb datasource) throws Exception {

        Neo4jGraphDb graph = (Neo4jGraphDb) datasource;
        Transaction tx= graph.getDB().beginTx();

        try {

            String query = resetNodePropertySimilarityCalc();
            graph.executeQuery(query);

            query = simpleNodePropertySimilarityCalc();
            graph.executeQuery(query);
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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }


    public String simpleNodePropertySimilarityCalc() {

        StringBuilder query = new StringBuilder();
        query.append("START node1=node:"+getStartIndex()+"('*:*'),node2=node:"+getEndIndex()+"('*:*') ")
                .append("where node1<>node2 AND node1."+property+"=node2."+property+" ")
                .append("CREATE UNIQUE node1-[r:"+getId()+"]-node2 ")
                .append("set r.value="+getWeight());
        return query.toString();

    }

    public String resetNodePropertySimilarityCalc() {

        StringBuilder query = new StringBuilder();
        query.append("START node1=node:"+getStartIndex()+"('*:*'),node2=node:"+getEndIndex()+"('*:*') ")
                .append("where node1<>node2 AND node1."+property+"<>node2."+property+" ")
                .append("CREATE UNIQUE node1-[r:"+getId()+"]-node2 ")
                .append("set r.value=0");
        return query.toString();

    }

}
