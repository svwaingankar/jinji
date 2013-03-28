package com.jinji.test.neo4j;

import com.jinji.graph.GraphDb;
import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.recommender.*;
import org.junit.Test;

import java.util.List;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 21/3/13
 * Time: 10:49 PM
 */
public class TestRecommendationConfigurations extends BaseTest{


    @Test
    public void testDefaultConfig(){

        //This simply used the user/items nodes and the primary relationship between them to do Item based Collaborative filtering based Recommendations


        //Currently support only Neo4j
        //the Neo4jGraphDb implementation by default looks for neo4jgraph.properties
        GraphDb db = new Neo4jGraphDb();

        //Each datasource impl knows hos to handle the datamodel.
        //for Neo4jGraphDb these identifiers for nodes refer to index names.

        SimpleGraphDataModel simpleDataModel = new SimpleGraphDataModel(db);
        simpleDataModel.setUser("users");
        simpleDataModel.setItem("items");
        simpleDataModel.setPrimaryRelation("bought");


        JinjiRecommenderEngine engine = new JinjiRecommenderEngine();

        engine.addAlgorithm(new ItemBasedCollaborativeAlgo(simpleDataModel));

        //This just returns item id's
        List<String> recommendations = engine.getRecommmendations("id1002", 10, 100);

    }

    @Test
    public void testWithUserBasedCollaborativeReco(){

        //This simply used the user/items nodes and the primary relationship between them to do Collaboarative filtering based Recommendations


        //Currently support only Neo4j
        //the Neo4jGraphDb implementation by default looks for neo4jgraph.properties
        GraphDb db = new Neo4jGraphDb();

        //Each datasource impl knows hos to handle the datamodel.
        //for Neo4jGraphDb these identifiers for nodes refer to index names.

        SimpleGraphDataModel simpleDataModel = new SimpleGraphDataModel(db);
        simpleDataModel.setUser("users");
        simpleDataModel.setItem("items");
        simpleDataModel.setPrimaryRelation("bought");


        JinjiRecommenderEngine engine = new JinjiRecommenderEngine();
        engine.setDataModel(simpleDataModel);
        engine.addAlgorithm(new ItemBasedCollaborativeAlgo());

        //This just returns item id's
        List<String> recommendations = engine.getRecommmendations("id1002", 10, 100);

    }

    @Test
    public void testWithContentBasedRecoAlgorithm() throws Exception {

        GraphDb db = new Neo4jGraphDb();
        JinjiRecommenderEngine engine = new JinjiRecommenderEngine();

        ContentBasedGraphDataModel model = new ContentBasedGraphDataModel(db);

        model.setUser("customers");
        model.setItem("movies");


        // NumericNodeProperty assigns weight based on difference between the property, eg users with same age have weight 100%, user with 1 years difference have 90%
        model.addUserSimilarityCriteria(new NumericNodeProperty("age"),10);
        // SimpleNodeProperty, assigns weight or 0 based on whether property matches or not
        model.addItemSimilarityCriteria(new SimpleNodeProperty("gener"), 10);

        RelationshipPath path = new RelationshipPath();
        path.relationship("language", Direction.INCOMING)
                .relationship("language", Direction.OUTGOING);

        model.addItemSimilarityCriteria(new SimplePathPresence(path), 10);

        // TagComparator with MATCH, assigns weight based on what percentage of tags match (eucledian distance)
        //engine.addItemSimilarityCriteria(new TagComparator("tags", TagComparator.TYPE.MATCH), 10);


        // TagComparator with IN and 1 direction, assigns weight based on whether the property in item is contained IN the list of propery values on user. eg- whether the movie language is on the languages user understands.
        //engine.addUserItemSimilarityCriteria(new TagComparator("language", TagComparator.TYPE.IN, 1), 10);


        engine.addAlgorithm(new SimpleContentBasedAlgo(model));
        engine.getRecommendation("user0001");


        //This contains item id's with info on their weights for each criteria
        RecommendationResult recommendations = engine.getRecommmendationsWithTrace("id1002", 10, 100);

    }

}
