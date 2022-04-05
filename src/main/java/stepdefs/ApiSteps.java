package stepdefs;

import static org.assertj.core.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ApiUtil;

@CucumberContextConfiguration
public class ApiSteps {

    @Given("^the service is available$")
    public void theServiceIsAvailable() {
        Response response = RestAssured.get("https://uat-api.3ona.co/v2/public/get-candlestick?instrument_name=BTC_USDT&timeframe=5m");
        assertThat(response.getStatusCode()).isEqualTo(200);

    }

}
