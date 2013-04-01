package com.jinji.recommender;

import com.jinji.graph.GraphDb;
import com.jinji.graph.neo4j.QueryRegistry;
import com.jinji.graph.neo4j.Neo4jGraphDb;
import org.neo4j.graphdb.Transaction;

import java.util.HashMap;
import java.util.Map;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 23/3/13
 * Time: 11:21 AM
 */
public class SimpleNodeProperty extends SimilarityFactor {

    String property;

    public SimpleNodeProperty(String prop) {
        this.property = prop;
    }

    @Override
    public void calculate(GraphDb datasource) throws Exception {

        Neo4jGraphDb graph = (Neo4jGraphDb) datasource;
        Transaction tx= graph.getDB().beginTx();

        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("indexName",startIndex);
            params.put("property",property);
            graph.executeQuery(QueryRegistry.simpleNodePropertySimilarityCalc(), params);
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
}
