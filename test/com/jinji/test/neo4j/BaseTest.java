package com.jinji.test.neo4j;

import com.jinji.graph.neo4j.Neo4jGraphDb;
import com.jinji.graph.neo4j.Neo4jGraphUtil;
import org.junit.BeforeClass;

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
 * Date: 26/3/13
 * Time: 8:25 PM
 */
public class BaseTest {

    @BeforeClass
    public static void createGraph(){

        try{


            Neo4jGraphUtil util = new Neo4jGraphUtil(new Neo4jGraphDb());

            FileInputStream fstream = new FileInputStream("D:\\shantaram\\jinji\\trunk\\config\\users.txt");

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null)   {
                Map<String, Object> props = readprops(strLine);
                util.createUniqueNode((String) props.get("id"),"customers", props);

                readpropNodes(strLine,util,(String)props.get("id"),"customers");

            }
            in.close();

            fstream = new FileInputStream("D:\\shantaram\\jinji\\trunk\\config\\items.txt");

            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while ((strLine = br.readLine()) != null)   {
                Map<String, Object> props = readprops(strLine);
                util.createUniqueNode((String) props.get("id"), "movies", props);

                readpropNodes(strLine,util,(String)props.get("id"),"movies");

            }
            in.close();

            fstream = new FileInputStream("D:\\shantaram\\jinji\\trunk\\config\\user-item.txt");

            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while ((strLine = br.readLine()) != null)   {
                String[] str = strLine.trim().split("-");
                util.createUniqueRelationship(str[0], "customers", str[1], "movies", "bought" );
            }
            in.close();
        }catch (Exception e){//Catch exception if any
            e.printStackTrace();
        }

    }

    private static Map<String, Object> readprops(String strLine) {
        String[] str = strLine.trim().split(",");
        Map<String, Object> props = new HashMap<String, Object>();
        for(String s:str){
            if(!s.startsWith("n:")){
                String[] keyval = s.split("=");
                props.put(keyval[0],keyval[1]);
            }
        }
        return props;
    }

    private static Map<String, Object> readpropNodes(String strLine, Neo4jGraphUtil util,String mainNodeId,String mainNodeIndex) throws Exception {

        String[] str = strLine.trim().split(",");
        Map<String, Object> props = new HashMap<String, Object>();
        for(String s:str){
            if(!s.startsWith("n:"))
                continue;
            String[] keyval = s.replace("n:","").trim().split("=");
            String[] vals = keyval[1].split(" ");
            for(String val:vals){
                String[] nodes = ((String)val).split("-");
                util.createUniqueNode(nodes[1],nodes[0], Collections.EMPTY_MAP);
                util.createUniqueRelationship(mainNodeId,mainNodeIndex,nodes[1],nodes[0],keyval[0]);
            }
        }

        return props;
    }
}