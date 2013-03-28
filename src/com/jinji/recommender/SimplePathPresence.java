package com.jinji.recommender;

import com.jinji.graph.QueryRegistry;
import com.jinji.graph.neo4j.Neo4jGraphDb;
import org.neo4j.graphdb.Transaction;

import java.util.HashMap;
import java.util.Map;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 25/3/13
 * Time: 11:07 PM
 */
public class SimplePathPresence extends SimilarityFactor {

    RelationshipPath path;

    public SimplePathPresence(RelationshipPath path) {
        this.path = path;
    }



    @Override
    public void calculate() throws Exception {

        Neo4jGraphDb graph = getModel().getDatasource() ;
        Transaction tx= graph.getDB().beginTx();

        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("startIndex",startIndex);
            params.put("endIndex",endIndex);

            graph.executeQuery(QueryRegistry.simplePathPresenceSimilarityCalc(path), params);
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
}
