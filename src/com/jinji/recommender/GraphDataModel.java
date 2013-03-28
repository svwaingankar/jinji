package com.jinji.recommender;

import com.jinji.graph.GraphDb;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 12:41 AM
 */
public interface GraphDataModel {

    public void setDatasource(GraphDb datasource);
    public GraphDb getDatasource();

}
