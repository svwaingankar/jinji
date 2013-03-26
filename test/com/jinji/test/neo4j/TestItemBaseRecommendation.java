package com.jinji.test.neo4j;

import com.jinji.graph.GraphDb;
import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.graph.neo4j.Neo4jGraphUtil;
import com.jinji.recommender.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 21/3/13
 * Time: 10:49 PM
 */
public class TestItemBaseRecommendation extends BaseTest{



    @Test
    public void testItemBasedRecommendation() throws Exception {

        GraphDb db = new Neo4jGraphDb();
        JinjiRecommenderEngine engine = new JinjiRecommenderEngine();
        engine.setDatasource(db);

        // tell engine which index contains user nodes
        engine.setUser("customers");
        // NumericNodeProperty assigns weight based on difference between the property, eg users with same age have weight 100%, user with 1 years difference have 90%
        engine.addUserSimilarityCriteria(new NumericNodeProperty("age"),10);

        // tell engine which index contains item nodes
        engine.setItem("movies");
        // SimpleNodeProperty, assigns weight or 0 based on whether property matches or not
        engine.addItemSimilarityCriteria(new SimpleNodeProperty("gener"), 10);

        RelationshipPath path = new RelationshipPath();
        path.relationship("language", Direction.INCOMING)
        .relationship("language", Direction.OUTGOING);


        engine.addItemSimilarityCriteria(new SimplePathPresence(path), 10);

        // TagComparator with MATCH, assigns weight based on what percentage of tags match (eucledian distance)
        //engine.addItemSimilarityCriteria(new TagComparator("tags", TagComparator.TYPE.MATCH), 10);


        // TagComparator with IN and 1 direction, assigns weight based on whether the property in item is contained IN the list of propery values on user. eg- whether the movie language is on the languages user understands.
        //engine.addUserItemSimilarityCriteria(new TagComparator("language", TagComparator.TYPE.IN, 1), 10);

        engine.getRecommendation("user0001");


    }

}
