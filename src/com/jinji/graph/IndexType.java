package com.jinji.graph;

public enum IndexType {

    ITEM("movies"),
    USER("customers");

    private final String indexName;

    private IndexType(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexName() {
        return indexName;
    }
}


