package com.jinji.graph.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Neo4jGraphService {
    GraphDatabaseService graphService;

    private Neo4jGraphService(){ }

    public GraphDatabaseService getDB(){
        if(graphService==null){
            graphService= new GraphDatabaseFactory().newEmbeddedDatabase("D:\\neo");
            registerShutdownHook(graphService);
        }
        return graphService;
    }

    private void registerShutdownHook(final GraphDatabaseService graphDb) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }
}
