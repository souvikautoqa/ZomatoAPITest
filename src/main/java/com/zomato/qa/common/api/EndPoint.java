package com.zomato.qa.common.api;

public interface EndPoint {
    String getRestMethodPath();
    HttpMethod getRestHttpMethodType();
    ParametersType getRestParametersType();

}
