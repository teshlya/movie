package com.example.misha.movies.data;

public class QueryStringPage {

    private String queryString;
    private int page;

    public QueryStringPage(String queryString, int page) {
        this.queryString = queryString;
        this.page = page;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
