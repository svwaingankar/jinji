package com.jinji.graph.neo4j.algorithms;

import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.graph.neo4j.Neo4jGraphUtil;
import com.jinji.recommender.algorithm.JinjiRecommendationAlgorithm;
import com.jinji.recommender.datamodel.GraphDataModel;
import com.jinji.recommender.datamodel.SimpleGraphDataModel;
import org.neo4j.graphdb.Transaction;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 11:17 AM
 */
public class UserBasedCollaborativeAlgoNeo4jImpl implements JinjiRecommendationAlgorithm {


    SimpleGraphDataModel model;
    Neo4jGraphDb db;
    int multiplyingFactor;

    public UserBasedCollaborativeAlgoNeo4jImpl(GraphDataModel simpleDataModel) {
        this.model = (SimpleGraphDataModel) simpleDataModel;
        this.db = (Neo4jGraphDb) this.model.getDatasource();
    }

    public GraphDataModel getModel() {
        return model;
    }

    @Override
    public void processRecommendations() throws Exception {
        calculateUserToUser();
        calculateUserToItemReco();

    }

    private void calculateUserToItemReco() throws Exception {

        Transaction tx= db.getDB().beginTx();
        try {

            String query = userToItemForItemBased();

            Map<String, Object> props = new HashMap<String, Object>();
            db.executeQuery(query,props);

            Iterator<Map<String, Object>> resultIt = db.executeQuery(query, props);
            int count=100;
            if (resultIt.hasNext()) {
                Map<String, Object> row = resultIt.next();

                props = new HashMap<String, Object>();
                props.put("user",row.get("user"));
                props.put("item",row.get("item"));
                props.put("value",count--);

                new Neo4jGraphUtil(db).createUniqueRelationship((String)row.get("user"),model.getUser(),(String)row.get("item"),model.getItem(),"u2i_"+getId(),(count--)*multiplyingFactor+"");

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

    private String userToItemForItemBased() {

        StringBuilder query = new StringBuilder();

        query.append("START u1=node:customers('*:*') ");
        query.append("MATCH u1-[r1:rated]->i1-[r2:i2i]-i2 ");
        query.append("RETURN u1.id as user ,i2.id as item order by r1.VALUE desc,r2.value ");

        return query.toString();

    }

    private void calculateUserToUser() throws Exception {
        Transaction tx= db.getDB().beginTx();
        try {

            String query = simpleUserBasedWithProperty();

            Map<String, Object> props = new HashMap<String, Object>();
            db.executeQuery(query,props);

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

    @Override
    public String getId() {
        return "ica";
    }

    public int getMultiplyingFactor() {
        return multiplyingFactor;
    }

    public void setMultiplyingFactor(int multiplyingFactor) {
        this.multiplyingFactor = multiplyingFactor;
    }

    private String simpleUserBasedWithProperty() {

        StringBuilder query = new StringBuilder();


        query.append("start n1=node:"+model.getItem()+"('*:*') match (n1)<-[r1:"+model.getPrimaryRelation()+"]-(x)-[r2:"+model.getPrimaryRelation()+"]->(n2) where n1<>n2 AND r1.VALUE = r2.VALUE ");
        query.append("with n1,n2,count(*) as c create unique n1-[rr:i2i]->n2 SET rr.value=c ");

        return query.toString();
    }



}
