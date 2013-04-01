package com.jinji.recommender;

import com.jinji.graph.GraphDb;
import com.jinji.graph.neo4j.QueryRegistry;
import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.graph.neo4j.datamodel.SimpleNodePropertyNeo4jImpl;
import com.jinji.graph.neo4j.datamodel.SimplePathPresenceNeo4jImpl;
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
    public void calculate(GraphDb datasource) throws Exception {

        if(datasource.getDataSourceId().equals("Neo4j")){
            new SimplePathPresenceNeo4jImpl(path).calculate(datasource);
        }
    }
}
