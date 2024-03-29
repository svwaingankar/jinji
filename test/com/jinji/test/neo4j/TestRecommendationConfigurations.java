package com.jinji.test.neo4j;

import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.recommender.*;
import com.jinji.recommender.algorithm.ItemBasedCollaborativeAlgo;
import com.jinji.recommender.algorithm.SimpleContentBasedAlgo;
import com.jinji.recommender.algorithm.UserBasedCollaborativeAlgo;
import com.jinji.recommender.datamodel.ContentBasedGraphDataModel;
import com.jinji.recommender.datamodel.SimpleGraphDataModel;
import org.junit.Test;
import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;

import java.util.List;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 21/3/13
 * Time: 10:49 PM
 */
public class TestRecommendationConfigurations extends BaseTest{

    /**
     * Simply use the user/items nodes and the primary relationship between them to do Item based Collaborative filtering based Recommendations
     */
    @Test
    public void testItemBasedCollaborative() throws Exception {

        //Currently support only Neo4j
        //the Neo4jGraphDb implementation by default looks for neo4jgraph.properties
        Neo4jGraphDb db = new Neo4jGraphDb("D:\\shantaram\\jinji-graph");

        //Each datasource impl knows hos to handle the datamodel.
        //for Neo4jGraphDb these identifiers for nodes refer to index names.

        SimpleGraphDataModel simpleDataModel = new SimpleGraphDataModel();
        simpleDataModel.setUser("customers");
        simpleDataModel.setItem("movies");
        simpleDataModel.setPrimaryRelation("rated");
        simpleDataModel.setPrimaryRelationProperty(null);
        simpleDataModel.setDatasource(db);


        JinjiRecommenderEngine engine = new JinjiRecommenderEngine(db);

        engine.setMaxRecommendations(10);
        engine.addAlgorithm(new ItemBasedCollaborativeAlgo(simpleDataModel), 10);

        engine.processRecommmendations();

        //This just returns item id's
        List<String> recommendations = engine.getRecommmendations("u00002", 10);
        System.out.println("Recommended items for user u00002 : ");
        for(String item:recommendations){
            System.out.println(item);
        }

    }

    /**
     * Simply use the user/items nodes and the primary relationship between them to do Item based Collaborative filtering based Recommendations
     */
    @Test
    public void testUserBasedCollaborative() throws Exception {

        //Currently support only Neo4j
        //the Neo4jGraphDb implementation by default looks for neo4jgraph.properties
        Neo4jGraphDb db = new Neo4jGraphDb("D:\\shantaram\\jinji-graph");

        //Each datasource impl knows hos to handle the datamodel.
        //for Neo4jGraphDb these identifiers for nodes refer to index names.

        SimpleGraphDataModel simpleDataModel = new SimpleGraphDataModel();
        simpleDataModel.setUser("customers");
        simpleDataModel.setItem("movies");
        simpleDataModel.setPrimaryRelation("rated");
        simpleDataModel.setPrimaryRelationProperty(null);
        simpleDataModel.setDatasource(db);


        JinjiRecommenderEngine engine = new JinjiRecommenderEngine(db);

        engine.setMaxRecommendations(10);
        engine.addAlgorithm(new UserBasedCollaborativeAlgo(simpleDataModel), 10);

        engine.processRecommmendations();

        //This just returns item id's
        List<String> recommendations = engine.getRecommmendations("u00002", 10);
        System.out.println("Recommended items for user u00002 : ");
        for(String item:recommendations){
            System.out.println(item);
        }

    }

    /**
     * Simply use the user/items nodes and the primary relationship between them to do Item based And User based Collaborative filtering based Recommendations
     */
    @Test
    public void testHybridItemAndUserBasedCollaborativeReco() throws Exception {

        Neo4jGraphDb db = new Neo4jGraphDb("D:\\shantaram\\jinji-graph");

        JinjiRecommenderEngine engine = new JinjiRecommenderEngine(db);
        engine.setMaxRecommendations(10);

        SimpleGraphDataModel simpleDataModel = new SimpleGraphDataModel();
        simpleDataModel.setUser("users");
        simpleDataModel.setItem("items");
        simpleDataModel.setPrimaryRelation("bought");
        simpleDataModel.setPrimaryRelationProperty(null);
        simpleDataModel.setDatasource(db);



        // multiple algorithms can be added with their corresponding datamodels. multiplying factor for each algo is 2nd parameter
        // multiplying factor is non mandatory. if not given then it is assumed to be 1
        engine.addAlgorithm(new ItemBasedCollaborativeAlgo(simpleDataModel),10);
        engine.addAlgorithm(new UserBasedCollaborativeAlgo(simpleDataModel),5);

        engine.processRecommmendations();

        //This takes usedid, min, max recommendations needed and returns item id's
        List<String> recommendations = engine.getRecommmendations("user002", 10);

    }

    @Test
    public void testWithContentBasedRecoAlgorithm() throws Exception {

        Neo4jGraphDb db = new Neo4jGraphDb("D:\\shantaram\\jinji-graph");
        JinjiRecommenderEngine engine = new JinjiRecommenderEngine(db);
        engine.setMaxRecommendations(10);

        ContentBasedGraphDataModel model = new ContentBasedGraphDataModel();

        model.setUser("customers");
        model.setItem("movies");
        model.setDatasource(db);

        //For Content based recommendations
        //Criteria to be calculated are specified for User-User, Item-Item and User-Item (Called Similarity Factors here onwards)
        //Each of these Similarity Factors is stored as a relationship
        //eg- compare languages of 2 movies to decide if they are similar (Item-Item)
        //    compare ages of 2 users to decide if they are similar (User-User)
        //    compare language of a movie and the languages understood by a user (User-Item)

        // These *Similarity Factors Relationships* are then used to genreate the final *Recommendation Relation* between a User and an Item.

        // Similarity Factors can either be given to Engine by the programmer or can be created and maintained by the Engine itself

        // Programmer specified Similarity Factors
        // These relationships will be used as they are. the programmer is responsible for updating these relationships as data in added/modified in the graph

            // params type,weight,relation name+property
            // this is an example of programmer calculation factor. programmer externally gets facebook data to find a users friends and give a weight.
            model.registerSimilarityFactor(SimilarityFactor.Type.UserToUser, 5, new SimilarityFactorRelation("FB_friend","count"));


        // Engine calculated Similarity Factors
        // These relationships will be calculated and stord based on info provided.   the engine is responsible for creating/updating these relationships

            // SimpleNodeProperty, assigns weight or 0 based on whether property matches . eg-compare genre of 2 movies
            // the engine will read the property genre on the Item nodes (since item to item type)
            model.createSimilarityFactor(SimilarityFactor.Type.ItemToItem, 10, new SimpleNodeProperty("genre"));

            // NumericNodeProperty assigns weight based on difference between the property, eg users with same age have weight 100%, user with 1 years difference have 90%
            model.createSimilarityFactor(SimilarityFactor.Type.UserToUser, 10, new NumericNodeProperty("age"));

            // SimplePathPresence assigns weight based on whether a path exists
            // eg - check if user understands the language of the movie Movie-[language]->(language)<-[speaks]-User

            RelationshipPath path = new RelationshipPath();
            path.relationship("language", Direction.OUTGOING)
                    .relationship("speaks", Direction.INCOMING);

            //popularity

            model.createSimilarityFactor(SimilarityFactor.Type.ItemToUser, 30, new SimplePathPresence(path));
            // TODO: remove this, This Factor can be used for target Pincode/City match criteria calculation

        model.createSimilarityFactor(SimilarityFactor.Type.ItemToUser, 30, new SimplePathPresence(path));
        // TODO: remove this, This Factor can be used for target Pincode/City match criteria calculation

        //A list of such Similarity factor processors will be provided and programmer can also write more.


        engine.addAlgorithm(new SimpleContentBasedAlgo(model), 10);
        //engine.processRecommmendations();

        //This contains item id's with info on their weights for each criteria, for helping programmer tweak weights
       // RecommendationResult recommendationsWithTrace = engine.getRecommmendationsWithTrace("id1002", 10);

        PojoXml pojoXml = PojoXmlFactory.createPojoXml();
        pojoXml.saveXml(path,"D:\\obj.xml");

    }

}
