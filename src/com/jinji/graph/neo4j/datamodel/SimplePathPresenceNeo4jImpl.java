package com.jinji.graph.neo4j.datamodel;

import com.jinji.graph.GraphDb;
import com.jinji.graph.neo4j.QueryRegistry;
import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.recommender.Node;
import com.jinji.recommender.Relation;
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

            String query = simplePathPresenceSimilarityCalc();
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

    public String simplePathPresenceSimilarityCalc() {


        StringBuilder query = new StringBuilder();
        query.append("START node1=node:"+getStartIndex()+"('*:*'),node2=node:"+getEndIndex()+"('*:*') ")
                .append("where node1<>node2 ")
                .append("AND node1");

        if(path.getRelationships().size()>path.getNodes().size()){

            int count=0;

            for(int i=0;i<path.getNodes().size();i++){

                Relation relation = path.getRelationships().get(i);
                Node node = path.getNodes().get(i);

                query.append(relation.getString());
                if(node==null)
                    query.append("()");

            }
            Relation relation = path.getRelationships().get(path.getRelationships().size()-1);
            query.append(relation.getString());


        }
        query.append("node2 ")
                .append("CREATE UNIQUE node1-[r:"+getId()+"]-node2 ")
                .append("set r.value="+getWeight());

        return query.toString();


    }
}
