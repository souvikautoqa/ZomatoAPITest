package com.zomato.qa.common.api;

import com.zomato.qa.common.util.PropertiesConfiguration;

import java.net.URI;

//
public class ApiConfiguration {
    private URI hostName;
    private String apiUsername;
    private String apiPassword;
    private int port = 80;
    private PropertiesConfiguration properties;

    public ApiConfiguration() throws Exception{
        configureWith("/ZomatoConfiguration.properties");
        try {
            this.hostName = URI.create(this.getProperties().getStringValue("ZOMATO_APIKEY_HOST"));
            this.apiUsername = this.getProperties().getStringValue("ZOMATO_APIAUTH_USERNAME");
            this.apiPassword = this.getProperties().getStringValue("ZOMATO_APIAUTH_PASSWORD");
            this.port = Integer.parseInt(this.getProperties().getStringValue("ZOMATO_APIKEY_PORT"));
        } catch (Exception e) {
            throw new Exception("Incorrect configuration found :" + e.getMessage());
        }
    }

    public void configureWith(String propertiesPath) throws Exception {
        this.properties = new PropertiesConfiguration(propertiesPath) {
        };
    }

    public URI getHostName() {
        return this.hostName;
    }

    public void setHostName(URI hostName) {
        this.hostName = hostName;
    }

    public String getApiUsername() {
        return this.apiUsername;
    }

    public void setApiUsername(String apiUsername) {
        this.apiUsername = apiUsername;
    }

    public String getApiPassword() {
        return this.apiPassword;
    }

    public void setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public PropertiesConfiguration getProperties() {
        return this.properties;
    }

    public void setProperties(PropertiesConfiguration properties) {
        this.properties = properties;
    }
}
