package com.jinji.recommender;

import com.jinji.graph.GraphDb;
import com.jinji.graph.neo4j.QueryRegistry;
import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.graph.neo4j.datamodel.SimpleNodePropertyNeo4jImpl;
import org.neo4j.graphdb.Transaction;

import java.util.HashMap;
import java.util.Map;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 23/3/13
 * Time: 11:21 AM
 */
public class SimpleNodeProperty extends SimilarityFactor {

    String property;

    public SimpleNodeProperty(String prop) {
        this.property = prop;
    }

    @Override
    public void calculate(GraphDb datasource) throws Exception {

        if(datasource.getDataSourceId().equals("Neo4j")){
            new SimpleNodePropertyNeo4jImpl(property).calculate(datasource);
        }

    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
