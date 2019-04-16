package stepdefinitions;

import com.zomato.qa.common.api.ApiClient;
import com.zomato.qa.zomatoapis.ZomatoAPIMethods;
import com.zomato.qa.zomatoapis.ZomatoContext;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.HashMap;

public class CuisineSteps {
    private ZomatoContext context;
    private ApiClient apiClient;
    private HashMap<String, String> headerCollect = new HashMap<>();

    public  CuisineSteps(ZomatoContext context) throws Throwable{
        this.context = context;
        this.apiClient = ZomatoContext.getApiClient();
    }

    @Given("^I want to have (.*?) in my (.*?)$")
    public void iWantToHaveCuisineInMyCity_ID(String cuisine, String cityID) throws Throwable {
        context.setParams("cuisine",cuisine);
        context.setParams("city_id",cityID);
    }

    @When("^I set mandatory fields for cuisine search$")
    public void iSetMandatoryFieldsForCuisineSearch() throws Throwable {
        this.apiClient.setEndpoint(new ZomatoAPIMethods().GET_CUISINE);
        this.apiClient.setRequest(context.getParams());
    }
}
