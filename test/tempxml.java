import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.test.neo4j.JinjiRecommenderEngine;
import org.pojoxml.core.PojoXml;
import org.pojoxml.core.PojoXmlFactory;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 1/4/13
 * Time: 6:07 PM
 */
public class tempxml {
    public static void main(String args[]){

        PojoXml pojoXml = PojoXmlFactory.createPojoXml();
        pojoXml.saveXml(new JinjiRecommenderEngine(new Neo4jGraphDb("D:\\shantaram\\jinji-graph")),"D:\\obj.xml");

    }
}
