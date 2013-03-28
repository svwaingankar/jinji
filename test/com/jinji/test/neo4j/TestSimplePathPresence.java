package com.jinji.test.neo4j;

import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.recommender.Direction;
import com.jinji.recommender.RelationshipPath;
import com.jinji.recommender.SimplePathPresence;
import org.junit.Test;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 21/3/13
 * Time: 10:49 PM
 */
public class TestSimplePathPresence extends BaseTest{


    @Test
    public void testSingleRelationshipPath() throws Exception {

        Neo4jGraphDb db = new Neo4jGraphDb();

        JinjiRecommenderEngine engine = new JinjiRecommenderEngine();
        engine.setDatasource(db);

        RelationshipPath path = new RelationshipPath();
        path.relationship("language",Direction.OUTGOING)
               .node()
                .relationship("language",Direction.INCOMING);

        SimplePathPresence itemSim = new SimplePathPresence(path);
        itemSim.setWeight(10);
        itemSim.setId("j_i_");
        itemSim.setModel(engine);
        itemSim.setStartIndex("movies");
        itemSim.setEndIndex("movies");

        itemSim.calculate();

    }

}
