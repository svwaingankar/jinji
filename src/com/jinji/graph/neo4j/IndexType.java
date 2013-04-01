package com.jinji.graph.neo4j;

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


