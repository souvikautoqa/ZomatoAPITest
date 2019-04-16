package stepdefinitions;

import com.zomato.qa.common.api.ApiClient;
import com.zomato.qa.zomatoapis.ZomatoAPIMethods;
import com.zomato.qa.zomatoapis.ZomatoContext;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.HashMap;

public class CategoriesSteps {
    private ZomatoContext context;
    private ApiClient apiClient;
    private HashMap<String, String> headerCollect = new HashMap<>();

    public  CategoriesSteps(ZomatoContext context) throws Throwable{
        this.context = context;
        this.apiClient = ZomatoContext.getApiClient();
    }

    @Given("^I want to find restaurants with specific (.*?) in my (.*?)$")
    public void iWantToFindRestaurantsWithSpecificCategoriesInMyCityID(String Categories,String CityID) throws Throwable {
        context.clearParams();
        context.setParams("city_id",CityID);
        context.setParams("categories",Categories);
    }

    @When("^I set mandatory fields for category search$")
    public void iSetMandatoryFieldsForCategorySearch() throws Throwable {
        this.apiClient.setEndpoint(new ZomatoAPIMethods().GET_CATEGORIES);
        this.apiClient.setRequest(context.getParams());
    }
}
