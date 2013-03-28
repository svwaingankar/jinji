package com.jinji.recommender;

import java.util.ArrayList;
import java.util.List;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 12:08 AM
 */
public class SimpleContentBasedAlgo implements JinjiRecommendationAlgorithm {


    ContentBasedGraphDataModel model;

    public SimpleContentBasedAlgo(ContentBasedGraphDataModel model) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public ContentBasedGraphDataModel getModel() {
        return model;
    }

    public void setModel(ContentBasedGraphDataModel model) {
        this.model = model;
    }
}
