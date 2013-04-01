package com.jinji.graph.neo4j.datamodel;

import com.jinji.graph.GraphDb;
import com.jinji.graph.neo4j.QueryRegistry;
import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.recommender.RelationshipPath;
import com.jinji.recommender.SimilarityFactor;
import org.neo4j.graphdb.Transaction;

import java.util.HashMap;
import java.util.Map;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 25/3/13
 * Time: 11:07 PM
 */
public class SimplePathPresenceNeo4jImpl extends SimilarityFactor {

    RelationshipPath path;

    public SimplePathPresenceNeo4jImpl(RelationshipPath path) {
        this.path = path;
    }



    @Override
    public void calculate(GraphDb datasource) throws Exception {

        Neo4jGraphDb graph = (Neo4jGraphDb) datasource;
        Transaction tx= graph.getDB().beginTx();

        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("startIndex",getStartIndex());
            params.put("endIndex",getEndIndex());

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
