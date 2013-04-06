package com.jinji.graph.neo4j;

import com.jinji.graph.GraphDb;
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
    private static String dbpath;

    public Neo4jGraphDb(String dbpath) {
        this.dbpath = dbpath;
        if(graphService==null){
            graphService= new GraphDatabaseFactory().newEmbeddedDatabase(dbpath);
            engine = new ExecutionEngine(graphService);
            registerShutdownHook(graphService);
        }
    }

    public static GraphDatabaseService getGraphService() {
        return graphService;
    }

    public static void setGraphService(GraphDatabaseService graphService) {
        Neo4jGraphDb.graphService = graphService;
    }

    public static String getDbpath() {
        return dbpath;
    }

    public static void setDbpath(String dbpath) {
        Neo4jGraphDb.dbpath = dbpath;
    }

    public GraphDatabaseService getDB(){
        return graphService;
    }

    public ExecutionEngine getEngine(){
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


    @Override
    public String getDataSourceId() {
        return "Neo4j";
    }
}
