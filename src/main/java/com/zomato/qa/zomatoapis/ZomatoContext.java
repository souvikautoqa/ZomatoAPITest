package com.zomato.qa.zomatoapis;

import com.zomato.qa.common.api.ApiClient;
import com.zomato.qa.common.api.ApiConfiguration;

import java.util.HashMap;

public class ZomatoContext {
    private static ThreadLocal<ApiClient> apiClients = new ThreadLocal();
    private Object requestObject;

    public ZomatoContext() throws Exception {
        apiClients.set(new ApiClient(new ApiConfiguration()));
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(String key, String value) {
        this.params.put(key,value);
    }

    public void clearParams(){
        if(!this.params.isEmpty())
            this.params.clear();
    }

    private HashMap<String,String> params = new HashMap<>();

    public static ApiClient getApiClient() {
        return (ApiClient)apiClients.get();
    }

    public static void setApiClient(ApiClient aApiClient) {
        apiClients.set(aApiClient);
    }

    public void setRequestObject(Object request) {
        this.requestObject = request;
    }

    public Object getRequestObject() {
        return this.requestObject;
    }



}
