package com.jinji.graph.neo4j;

import com.jinji.graph.GraphDb;
import com.jinji.graph.QueryRegistry;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 22/3/13
 * Time: 8:15 AM
 */
public class Neo4jGraphDb implements GraphDb {

    private static GraphDatabaseService graphService;
    private static ExecutionEngine engine;


    private static void initialize(){

        if(graphService==null){
            graphService= new GraphDatabaseFactory().newEmbeddedDatabase("D:\\shantaram\\jinji-graph");
            engine = new ExecutionEngine(graphService);
            registerShutdownHook(graphService);
        }
    }

    public static GraphDatabaseService getDB(){
        initialize();
        return graphService;
    }

    public static ExecutionEngine getEngine(){
        initialize();
        return engine;
    }


    public Iterator<Map<String,Object>> executeQuery(String query, Map<String, Object> params) throws Exception {
        long start, end;
        try {
            start= System.currentTimeMillis();
            ExecutionResult result = getEngine().execute(query,params);
            end= System.currentTimeMillis();
            return result.iterator();
        }
        catch(Exception e) {
            throw new Exception("Error executing query",e);
        }
    }


    private static void registerShutdownHook(final GraphDatabaseService graphDb) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }


}
