package com.jinji.test.neo4j;

import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.recommender.*;
import org.junit.Test;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 21/3/13
 * Time: 10:49 PM
 */
public class TestSimpleNodeProperty extends BaseTest{




    @Test
    public void testSimpleNodeCalculation() throws Exception {

        Neo4jGraphDb db = new Neo4jGraphDb("D:\\shantaram\\jinji-graph");


        SimpleNodeProperty itemSim = new SimpleNodeProperty("genre");
        itemSim.setWeight(10);
        itemSim.setId("i1");
        itemSim.setStartIndex("movies");
        itemSim.setEndIndex("movies");

        itemSim.calculate(db);

    }

}
