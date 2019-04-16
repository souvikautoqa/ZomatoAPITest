package com.zomato.qa.zomatoapis;

import com.zomato.qa.common.api.HttpMethod;
import com.zomato.qa.common.api.ParametersType;

public class ZomatoAPIMethods {

    // List of Zomato APIs to test
    public ZomatoAPIMethod GET_LOCATIONS = new ZomatoAPIMethod("/locations", HttpMethod.GET, ParametersType.QUERY);
    public ZomatoAPIMethod GET_SEARCH = new ZomatoAPIMethod("/search", HttpMethod.GET, ParametersType.QUERY);
    public ZomatoAPIMethod GET_CUISINE = new ZomatoAPIMethod("/cuisines", HttpMethod.GET, ParametersType.QUERY);
    public ZomatoAPIMethod GET_CATEGORIES = new ZomatoAPIMethod("/categories", HttpMethod.GET, ParametersType.QUERY);
    public ZomatoAPIMethod GET_REVIEWS = new ZomatoAPIMethod("/reviews", HttpMethod.GET, ParametersType.QUERY);
    public ZomatoAPIMethod GET_RESTAURANTS = new ZomatoAPIMethod("/restaurant", HttpMethod.GET, ParametersType.QUERY);




}
