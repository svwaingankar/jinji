package com.jinji.recommender;

import com.jinji.graph.GraphDb;
import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.graph.neo4j.Neo4jGraphUtil;
import com.jinji.recommender.algorithm.JinjiRecommendationAlgorithm;
import org.neo4j.graphdb.Transaction;

import java.util.*;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 7/4/13
 * Time: 8:29 AM
 */
public class Neo4jRecommendationProcessor implements DatasourceRecoProcessor{

    Neo4jGraphDb db;

    public Neo4jRecommendationProcessor(GraphDb db) {
        this.db = (Neo4jGraphDb) db;
    }


    @Override
    public void processFinalRecommendations(List<JinjiRecommendationAlgorithm> algorithms) throws Exception {


    }

    @Override
    public List<String> getRecommendations(String user, int count, List<JinjiRecommendationAlgorithm> algorithms) throws Exception {

        List<String> items = new ArrayList<String>();

        Transaction tx= db.getDB().beginTx();
        try {

            StringBuilder query = new StringBuilder();

            String algoIds="";
            for(int i=0;i<algorithms.size();i++){

                JinjiRecommendationAlgorithm algo = algorithms.get(i);
                algoIds+= "u2i_"+algo.getId();

                if(i+1<algorithms.size()){
                    algoIds+= "|";
                }
            }

            query.append("START u1=node:"+algorithms.get(0).getModel().getUser()+"(id='"+user+"') ");
            query.append("MATCH u1-[r:"+algoIds+"]-i1 ");
            query.append("RETURN u1.id as user,i1.id as item,sum(r.VALUE) order by sum(r.VALUE) ");

            Map<String, Object> props = new HashMap<String, Object>();

            Iterator<Map<String, Object>> resultIt = db.executeQuery(query.toString(), props);

            if (resultIt.hasNext()) {
                Map<String, Object> row = resultIt.next();

                items.add((String)row.get("item"));

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

        return items;
    }

}
