package com.tsi.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {
    int id;
    String name;
    String title;
    Map<String, String> indexMap = new HashMap<>();
    String content;
    List<Comment> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getIndexMap() {
        return indexMap;
    }

    public void setIndexMap(Map<String, String> indexMap) {
        this.indexMap = indexMap;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
