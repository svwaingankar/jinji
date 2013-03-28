package com.jinji.graph;

import com.jinji.recommender.Direction;
import com.jinji.recommender.Node;
import com.jinji.recommender.Relation;
import com.jinji.recommender.RelationshipPath;

import java.util.Map;

public class QueryRegistry {


    public static String simpleNodePropertySimilarityCalc() {
        /*
        START node1=node:movies('*:*'),node2=node:movies('*:*')
        where node1<>node2 AND node1.gener<>node2.gener
        CREATE UNIQUE node1-[r:j_sim]-node2
        set r.gener=10
        RETURN node1.id,node2.id,r.gener
         */
        StringBuilder query = new StringBuilder();
        query.append("START node1=node:movies('*:*'),node2=node:movies('*:*') ")
                .append("where node1<>node2 AND node1.gener<>node2.gener ")
                .append("CREATE UNIQUE node1-[r:j_sim1]-node2 ")
                .append("set r.gener=10 ");
        return query.toString();

    }

    public static String simplePathPresenceSimilarityCalc(RelationshipPath path) {

                /*
        START node1=node:movies('*:*'),node2=node:movies('*:*')
        where node1<>node2 AND node1.gener<>node2.gener AND
        node1-[:language]->()<-[:language]-node2
        CREATE UNIQUE node1-[r:j_sim]-node2
        set r.gener=10
        RETURN node1.id,node2,node2.id,r.gener
         */
        StringBuilder query = new StringBuilder();
        query.append("START node1=node:movies('*:*'),node2=node:movies('*:*') ")
                .append("where node1<>node2 AND node1.gener<>node2.gener ")
                .append("AND node1");

        if(path.getRelationships().size()>path.getNodes().size()){

            int count=0;

            for(int i=0;i<path.getNodes().size();i++){

                Relation relation = path.getRelationships().get(i);
                Node node = path.getNodes().get(i);

                query.append(relation.getString());
                if(node==null)
                    query.append("()");

            }
            Relation relation = path.getRelationships().get(path.getRelationships().size()-1);
            query.append(relation.getString());


        }
                query.append("node2 ")
                .append("CREATE UNIQUE node1-[r:j_sim3]-node2 ")
                .append("set r.path=10 ");

        return query.toString();


    }


}
