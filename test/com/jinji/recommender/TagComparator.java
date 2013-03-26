package com.jinji.recommender;

import java.util.HashMap;
import java.util.Map;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 23/3/13
 * Time: 11:19 AM
 */
public class TagComparator extends SimilarityFactor {

    public TagComparator(String tags, TYPE type) {

    }

    public TagComparator(String language, TYPE in, int dir) {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public void calculate() {

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("user",getEngine().getUser());


    }

    public enum TYPE {
        IN,
        MATCH;
    }


}
