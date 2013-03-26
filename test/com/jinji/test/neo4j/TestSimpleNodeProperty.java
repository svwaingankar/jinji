package com.jinji.test.neo4j;

import com.jinji.graph.GraphDb;
import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.graph.neo4j.Neo4jGraphUtil;
import com.jinji.recommender.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 21/3/13
 * Time: 10:49 PM
 */
public class TestSimpleNodeProperty extends BaseTest{




    @Test
    public void testSimpleNodeCalculation() throws Exception {

        Neo4jGraphDb db = new Neo4jGraphDb();

        JinjiRecommenderEngine engine = new JinjiRecommenderEngine();
        engine.setDatasource(db);
        SimpleNodeProperty itemSim = new SimpleNodeProperty("gener");
        itemSim.setWeight(10);
        itemSim.setId("j_i_");
        itemSim.setEngine(engine);
        itemSim.setStartIndex("movies");
        itemSim.setEndIndex("movies");

        itemSim.calculate();

    }

}
