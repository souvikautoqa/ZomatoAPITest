package stepdefinitions;

import com.zomato.qa.common.api.ApiClient;
import com.zomato.qa.common.api.ApiConfiguration;
import com.zomato.qa.zomatoapis.ZomatoContext;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import it.unimi.dsi.fastutil.Hash;
import org.testng.Assert;

import java.util.HashMap;

public class CommonSteps {
    private ZomatoContext context;
    private ApiClient apiClient;

    public  CommonSteps(ZomatoContext context) throws Throwable{
        this.context = context;
        this.apiClient = ZomatoContext.getApiClient();
    }

    @And("^I send the request$")
    public void iSendTheRequest() throws Throwable {
       HashMap<String,String> apiAuth = new HashMap<String,String>();
       apiAuth.put(apiClient.getAPIConfiguration().getProperties().getStringValue("ZOMATO_APIKEY_PARAMETER"),
               apiClient.getAPIConfiguration().getProperties().getStringValue("ZOMATO_APIKEY_VALUE"));
       apiClient.setHeaders(apiAuth);
       apiClient.sendRequest();
    }

    @Then("^I should receive response code (\\d+)$")
    public void iShouldReceiveResponseCode(int respCode) throws Throwable {
        int statusCode = apiClient.getResponse().getStatusCode();
        Assert.assertEquals(statusCode,respCode,"Expected : "+respCode+" -  Actual : "+statusCode);
    }
}
