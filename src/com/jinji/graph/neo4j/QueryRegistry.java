package com.jinji.graph.neo4j;

import com.jinji.recommender.Direction;
import com.jinji.recommender.Node;
import com.jinji.recommender.Relation;
import com.jinji.recommender.RelationshipPath;

import java.util.Map;

public class QueryRegistry {



    public static String simplePathPresenceSimilarityCalc(RelationshipPath path) {

                /*
        START node1=node:movies('*:*'),node2=node:movies('*:*')
        where node1<>node2 AND node1.genre<>node2.genre AND
        node1-[:language]->()<-[:language]-node2
        CREATE UNIQUE node1-[r:j_sim]-node2
        set r.genre=10
        RETURN node1.id,node2,node2.id,r.genre
         */
        StringBuilder query = new StringBuilder();
        query.append("START node1=node:movies('*:*'),node2=node:movies('*:*') ")
                .append("where node1<>node2 AND node1.genre<>node2.genre ")
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
