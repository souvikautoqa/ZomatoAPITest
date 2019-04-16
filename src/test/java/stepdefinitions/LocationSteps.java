package stepdefinitions;

import com.zomato.qa.common.api.ApiClient;
import com.zomato.qa.common.api.ApiConfiguration;
import com.zomato.qa.model.Locations;
import com.zomato.qa.zomatoapis.ZomatoAPIMethods;
import com.zomato.qa.zomatoapis.ZomatoContext;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.HashMap;

public class LocationSteps {
    private ZomatoContext context;
    private ApiClient apiClient;
    private HashMap<String, String> headerCollect = new HashMap<>();

    public  LocationSteps(ZomatoContext context) throws Throwable{
        this.context = context;
        this.apiClient = ZomatoContext.getApiClient();
    }

    @Given("^I am in my (.*?)$")
    public void iAmInCityCountry(String city) throws Throwable {
        context.clearParams();
        context.setParams("query",city);
        context.setParams("count","10");
    }

    @When("^I set mandatory fields for city search$")
    public void iSetMandatoryFieldsForCitySearch() throws Throwable {
       this.apiClient.setEndpoint(new ZomatoAPIMethods().GET_LOCATIONS);
       this.apiClient.setRequest(context.getParams());
    }
}
