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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class ReviewSteps {
    private ZomatoContext context;
    private ApiClient apiClient;
    private HashMap<String, String> headerCollect = new HashMap<>();
    Response response;

    public  ReviewSteps(ZomatoContext context) throws Throwable{
        this.context = context;
        this.apiClient = ZomatoContext.getApiClient();
    }

    @Given("^I want to find reviews of the restaurant with (.*?)$")
    public void iWantToFindReviewsOfTheRestaurantWithResID(String resID) throws Throwable {
        context.clearParams();
        context.setParams("res_id",resID);
    }

    @When("^I set mandatory fields for review search$")
    public void iSetMandatoryFieldsForReviewSearch() throws Throwable {
        this.apiClient.setEndpoint(new ZomatoAPIMethods().GET_REVIEWS);
        this.apiClient.setRequest(context.getParams());
    }

    @And("^I should be able to view review ratings for the restaurant$")
    public void iShouldBeAbleToViewReviewRatingsForTheRestaurant() throws Throwable {
        response = apiClient.getResponse().getRestResponse();
        Reviews reviews = response.as(Reviews.class, ObjectMapperType.GSON);

        HashMap dataMap = ReadYAML.readYAMLValuesAsHashMap("/Search_restaurants_reviews.yaml");
        int randNo = new Random().nextInt(dataMap.size()-1)+1;
        List<UserReview> reviewList = reviews.getUserReviews();
        boolean validate = false;
        for(int i=0;i<reviewList.size();i++){
            LinkedHashMap tempMap = new LinkedHashMap();
            tempMap.put("rating",reviewList.get(i).getReview().getRating());
            tempMap.put("rating_text",reviewList.get(i).getReview().getRatingText());
            tempMap.put("review_text",reviewList.get(i).getReview().getReviewText());
            if(dataMap.get(randNo).toString().equals(tempMap.toString())){
                validate = true;break;
            }
        }
        Assert.assertEquals(String.valueOf(validate),"true","Restaurant review data validation failed");


    }
}
