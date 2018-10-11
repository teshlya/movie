package com.example.misha.movies.data;

public class QueryParameters extends QueryStringPage {

    private String apiKeyValue;
    private String languageValue;
    private boolean includeAdult;

    public QueryParameters(String queryString, int page) {
        super(queryString, page);
    }

    public QueryParameters(String queryString, int page, String apiKeyValue, String languageValue, boolean includeAdult) {
        super(queryString, page);
        this.apiKeyValue = apiKeyValue;
        this.languageValue = languageValue;
        this.includeAdult = includeAdult;
    }

    public QueryParameters(QueryStringPage queryStringPage, String apiKeyValue, String languageValue, boolean includeAdult) {
        super(queryStringPage.getQueryString(), queryStringPage.getPage());
        this.apiKeyValue = apiKeyValue;
        this.languageValue = languageValue;
        this.includeAdult = includeAdult;
    }

    public String getApiKeyValue() {
        return apiKeyValue;
    }

    public void setApiKeyValue(String apiKeyValue) {
        this.apiKeyValue = apiKeyValue;
    }

    public String getLanguageValue() {
        return languageValue;
    }

    public void setLanguageValue(String languageValue) {
        this.languageValue = languageValue;
    }

    public boolean isIncludeAdult() {
        return includeAdult;
    }

    public void setIncludeAdult(boolean includeAdult) {
        this.includeAdult = includeAdult;
    }
}
