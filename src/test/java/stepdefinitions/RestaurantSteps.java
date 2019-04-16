package stepdefinitions;

import com.zomato.qa.common.api.ApiClient;
import com.zomato.qa.common.util.ReadYAML;
import com.zomato.qa.model.*;
import com.zomato.qa.zomatoapis.ZomatoAPIMethods;
import com.zomato.qa.zomatoapis.ZomatoContext;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class RestaurantSteps {
    private ZomatoContext context;
    private ApiClient apiClient;
    Response response;
    private HashMap<String, String> headerCollect = new HashMap<>();
    Locations locations;

    public  RestaurantSteps(ZomatoContext context) throws Throwable{
        this.context = context;
        this.apiClient = ZomatoContext.getApiClient();
    }

    @When("^I set mandatory fields for restaurant search in (.*?),(.*?)$")
    public void iSetMandatoryFieldsForRestaurantSearch(String city,String country) throws Throwable {
        this.response = apiClient.getResponse().getRestResponse();
        List<Locations> locations =  response.getBody().jsonPath().getList("location_suggestions",Locations.class);
        this.context.clearParams();
        for(Locations location : locations){
            if(location.getCity_name().equals(city) & location.getCountry_name().equals(country)){
                context.setParams("entity_id",location.getEntity_id());
                context.setParams("entity_type",location.getEntity_type());
                break;
            }
        }
        this.context.setParams("count","5");
        this.apiClient.setEndpoint(new ZomatoAPIMethods().GET_SEARCH);
        this.apiClient.setRequest(context.getParams());
    }

    @And("^I should be able to (.*?) the list of restaurant details$")
    public void iShouldHaveAListOfRestaurantDetails(String verify) throws Throwable {
        this.response = apiClient.getResponse().getRestResponse();
        Search search = response.as(Search.class, ObjectMapperType.GSON);
        HashMap dataMap;
        if(context.getParams().containsKey("category")){
            dataMap = ReadYAML.readYAMLValuesAsHashMap("/Search_restaurants_categories.yaml");
        }else{
            dataMap = ReadYAML.readYAMLValuesAsHashMap("/Search_restaurants_city.yaml");
        }
        int randNo = new Random().nextInt(dataMap.size()-1)+1;
        List<Restaurants> resList = search.getRestaurants();
        boolean validate = false;
        LinkedHashMap<String,Object> tempMap = new LinkedHashMap<>();
        for(int i=0;i<resList.size();i++){
            tempMap.put("res_id",resList.get(i).getRestaurant().getR().getResId());
            tempMap.put("name",resList.get(i).getRestaurant().getName());
            tempMap.put("cuisines",resList.get(i).getRestaurant().getCuisines());
            tempMap.put("aggregate_rating",resList.get(i).getRestaurant().getUserRating().getAggregateRating());
            if(dataMap.get(randNo).toString().equals(tempMap.toString())){
                validate = true;break;
            }
            tempMap.clear();
        }
        Assert.assertEquals(String.valueOf(validate),verify,"Restaurant data validation failed");
    }

    @When("^I set mandatory fields for (.*?) restaurants search$")
    public void iSetMandatoryFieldsForCuisineRestaurantsSearch(String cuisine) throws Throwable {
        this.response = apiClient.getResponse().getRestResponse();
        Cuisines cuisines = response.as(Cuisines.class, ObjectMapperType.GSON);
        List<Cuisine> cuisineList = cuisines.getCuisines();
        int cusID = 0;
        for(Cuisine cus : cuisineList){
            if(cus.getCuisine().getCuisineName().toLowerCase().equals(cuisine.toLowerCase())){
                cusID = cus.getCuisine().getCuisineId();break;
            }
        }
        String enity_id = context.getParams().get("city_id");
        this.context.clearParams();
        this.context.setParams("entity_id",enity_id);
        this.context.setParams("entity_type","city");
        this.context.setParams("cuisines",String.valueOf(cusID));
        this.context.setParams("count","5");
        this.apiClient.setEndpoint(new ZomatoAPIMethods().GET_SEARCH);
        this.apiClient.setRequest(context.getParams());
    }

    @And("^I should be able to (.*?) the list of (.*?) restaurants$")
    public void iShouldBeAbleToVerifyTheListOfCuisineRestaurants(String verify, String cuisine) throws Throwable {
        this.response = apiClient.getResponse().getRestResponse();
        Search search = response.as(Search.class, ObjectMapperType.GSON);
        HashMap dataMap = ReadYAML.readYAMLValuesAsHashMap("/Search_restaurants_cuisine.yaml");
        int randNo = new Random().nextInt(dataMap.size()-1)+1;
        List<Restaurants> resList = search.getRestaurants();
        boolean validate = false;
        LinkedHashMap<String,Object> tempMap = new LinkedHashMap<>();
        for(int i=0;i<resList.size();i++){
            tempMap.put("res_id",resList.get(i).getRestaurant().getR().getResId());
            tempMap.put("name",resList.get(i).getRestaurant().getName());
            tempMap.put("cuisines",resList.get(i).getRestaurant().getCuisines());
            tempMap.put("aggregate_rating",resList.get(i).getRestaurant().getUserRating().getAggregateRating());
            if(dataMap.get(randNo).toString().equals(tempMap.toString())){
                validate = true;break;
            }
        }
        Assert.assertEquals(String.valueOf(validate),verify,"Restaurant data validation failed");
    }

    @When("^I set mandatory fields for restaurants search by (.*?)$")
    public void iSetMandatoryFieldsForRestaurantsSearchByCategories(String category) throws Throwable {
        this.response = apiClient.getResponse().getRestResponse();
        Categories categories = response.as(Categories.class, ObjectMapperType.GSON);
        List<Category> categoryList = categories.getCategories();
        int catID = 0;
        for(Category cat : categoryList){
            if(cat.getCategories().getName().toLowerCase().equals(category.toLowerCase())){
                catID = cat.getCategories().getId();break;
            }
        }
        String enity_id = context.getParams().get("city_id");
        this.context.clearParams();
        this.context.setParams("entity_id",enity_id);
        this.context.setParams("entity_type","city");
        this.context.setParams("category",String.valueOf(catID));
        this.context.setParams("count","5");
        this.apiClient.setEndpoint(new ZomatoAPIMethods().GET_SEARCH);
        this.apiClient.setRequest(context.getParams());
    }


    @Given("^I want to find the details of the restaurant with (.*?)$")
    public void iWantToFindTheDetailsOfTheRestaurantWithResID(String resID) throws Throwable {
        this.context.clearParams();
        this.context.setParams("res_id",resID);
    }

    @When("^I set mandatory fields for restaurant details$")
    public void iSetMandatoryFieldsForRestaurantDetails() throws Throwable {
        this.apiClient.setEndpoint(new ZomatoAPIMethods().GET_RESTAURANTS);
        this.apiClient.setRequest(context.getParams());
    }

    @And("^I should be able to view details of the restaurant$")
    public void iShouldBeAbleToViewDetailsOfTheRestaurant() throws Throwable {
        this.response = apiClient.getResponse().getRestResponse();
        Restaurant restaurant = response.as(Restaurant.class, ObjectMapperType.GSON);
        HashMap dataMap = ReadYAML.readYAMLValuesAsHashMap("/Search_restaurants_details.yaml");
        LinkedHashMap<String,Object> tempMap = new LinkedHashMap<>();
        tempMap.put("name",restaurant.getName());
        tempMap.put("cuisines",restaurant.getCuisines());
        tempMap.put("aggregate_rating",restaurant.getUserRating().getAggregateRating());
        Assert.assertEquals(dataMap.get(Integer.parseInt(context.getParams().get("res_id"))).toString().equals(tempMap.toString()),
                true,"Restaurant details validation failed");
    }
}
