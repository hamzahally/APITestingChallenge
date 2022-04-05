package stepdefs;

import static org.assertj.core.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@CucumberContextConfiguration
public class Steps {

    public String setGetEndpoint(String instrumentName, String timeFrame){
        String baseurl = "https://uat-api.3ona.co/v2/public/get-candlestick";
        return String.format(baseurl+"?instrument_name=%s&timeframe=%s",instrumentName,timeFrame);
    }

    @Given("^the service is available$")
    public void theServiceIsAvailable() {
        Response response = RestAssured.get(setGetEndpoint("BTC_USDT","5m"));
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @When("^the instrument is set to ([^\"]*) and timeframe is ([^\"]*)$")
    public void setInstrumentNameAndTimeFrame(String instrumentName, String timeFrame) {
        Response response = RestAssured.get(setGetEndpoint(instrumentName,timeFrame));
        assertThat(response.getStatusCode()).isEqualTo(200);
    }


}
