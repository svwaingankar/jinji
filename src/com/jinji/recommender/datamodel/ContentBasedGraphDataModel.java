package com.jinji.recommender.datamodel;

import com.jinji.graph.GraphDb;
import com.jinji.recommender.SimilarityFactor;
import com.jinji.recommender.SimilarityFactorModel;
import com.jinji.recommender.SimilarityFactorRelation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jinji - https://github.com/svwaingankar/jinji
 * User: shantaramw
 * Date: 28/3/13
 * Time: 12:16 AM
 */
public class ContentBasedGraphDataModel implements GraphDataModel {

    private GraphDb datasource;
    private String user;
    private String item;
    private String primaryRelation;

    private Map<SimilarityFactor.Type,List<SimilarityFactorModel>> similarityModels = new HashMap<SimilarityFactor.Type, List<SimilarityFactorModel>>();

    public void setDatasource(GraphDb datasource) {
        this.datasource = datasource;
    }

    public GraphDb getDatasource() {
        return datasource;
    }

    @Override
    public void processSimilarityFactors() throws Exception {

        for(Map.Entry<SimilarityFactor.Type,List<SimilarityFactorModel>> entry :similarityModels.entrySet()){

            SimilarityFactor.Type type = entry.getKey();

            for(SimilarityFactorModel factor:entry.getValue()){
                if(type ==SimilarityFactor.Type.ItemToItem)
                    factor.process(getDatasource(),"movies","movies");
                if(type ==SimilarityFactor.Type.UserToUser)
                    factor.process(getDatasource(),"customers","customers");
                if(type ==SimilarityFactor.Type.ItemToUser)
                    factor.process(getDatasource(),"movies","customers");
                if(type ==SimilarityFactor.Type.UserToItem)
                    factor.process(getDatasource(),"customers","movies");

            }

        }
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setPrimaryRelation(String primaryRelation) {
        this.primaryRelation = primaryRelation;
    }

    public String getPrimaryRelation() {
        return primaryRelation;
    }
                                                               /*
    public void addUserSimilarityCriteria(SimilarityFactor userSim, int w) {
        userSim.setWeight(w);
        userSim.setId("j_u_"+userSimilarity.size());
        userSim.setModel(this);
        userSim.setStartIndex(user);
        userSim.setEndIndex(user);

        userSimilarity.add(userSim);

    }

    public void addItemSimilarityCriteria(SimilarityFactor itemSim, int w) {
        itemSim.setWeight(w);
        itemSim.setId("j_i_"+itemSimilarity.size());
        itemSim.setModel(this);
        itemSim.setStartIndex(item);
        itemSim.setEndIndex(item);

        itemSimilarity.add(itemSim);
    }

    public void addUserItemSimilarityCriteria(SimilarityFactor userItemSim, int w) {
        userItemSim.setWeight(w);
        userItemSim.setId("j_i_"+userItemSimilarity.size());
        userItemSim.setModel(this);
        userItemSim.setStartIndex(user);
        userItemSim.setEndIndex(item);
        userItemSimilarity.add(userItemSim);
    }                                                        */

    public void registerSimilarityFactor(SimilarityFactor.Type type, int weight, SimilarityFactorRelation similarityFactorRelation) {
        List<SimilarityFactorModel> list = null;
        if(similarityModels.containsKey(type)){
            list = similarityModels.get(type);
        }else{
            list = new ArrayList<SimilarityFactorModel>();
        }

        list.add(new SimilarityFactorModel(similarityFactorRelation.getId(),weight,similarityFactorRelation));
        similarityModels.put(type,list);
    }

    public void createSimilarityFactor(SimilarityFactor.Type type, int weight, SimilarityFactor similarityFactor) {

        List<SimilarityFactorModel> list = null;
        if(similarityModels.containsKey(type)){
            list = similarityModels.get(type);
        }else{
            list = new ArrayList<SimilarityFactorModel>();
        }

        list.add(new SimilarityFactorModel(type.name()+list.size(),weight,similarityFactor));

        similarityModels.put(type,list);
    }
}
