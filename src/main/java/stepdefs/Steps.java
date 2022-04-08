package stepdefs;

import static org.assertj.core.api.Assertions.*;

import io.cucumber.gherkin.internal.com.eclipsesource.json.JsonValue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.TestContext;

public class Steps {

    private final TestContext testContext;

    public Steps(TestContext testContext) {
        this.testContext = testContext;
    }

    public String setGetEndpoint(String instrumentName, String timeFrame) {
        String baseurl = "https://uat-api.3ona.co/v2/public/get-candlestick";
        return String.format(baseurl + "?instrument_name=%s&timeframe=%s", instrumentName, timeFrame);
    }

    @Given("^the service is available$")
    public void theServiceIsAvailable() {
        Response response = RestAssured.get(setGetEndpoint("BTC_USDT", "5m"));
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @When("^no instrument or timeframe is set$")
    public void noInstrumentOrTimeframeSet() {
        RestAssured.get("https://uat-api.3ona.co/v2/public/get-candlestick");
    }

    @When("^the instrument is set to ([^\"]*)$")
    public void setInstrumentName(String instrumentName) {
        if(instrumentName.equals("empty")){
            testContext.setInstrument("");
        } else {
            testContext.setInstrument(instrumentName);
        }
    }

    @When("^timeframe is ([^\"]*)$")
    public void setTimeFrame(String timeFrame) {
        testContext.setTimeFrame(timeFrame);
    }

    @When("^the request is sent$")
    public void sendRequest() {
        testContext.setResponse(RestAssured.get(setGetEndpoint(testContext.getInstrument(), testContext.getTimeFrame())));
    }

    @Then("^the response should return a (\\d+) response$")
    public void checkResponseCode(int responseCode) {
        assertThat(testContext.getResponse().getStatusCode()).isEqualTo(responseCode);
    }


    @Then("^response with instrument name of ([^\"]*)$")
    public void checkInstrumentName(String instrumentName) {
        assertThat(testContext.getResponse().getBody().path("result.instrument_name").toString()).isEqualTo(instrumentName);

    }

    @Then("^response with time frame of ([^\"]*)$")
    public void checkTimeFrame(String timeFrame) {
        assertThat(testContext.getResponse().getBody().path("result.interval").toString()).isEqualTo(timeFrame);

    }

    @Then("^the data should be null$")
    public void dataResponseIsNull() {
        assertThat(testContext.getResponse().getBody().jsonPath().getString("result.data")).isEqualTo(null);

    }

    @Then("^response has error ([^\"]*)$")
    public void errorReponse(String error) {
        assertThat(testContext.getResponse().getBody().path("error").toString()).isEqualTo(error);

    }

    @Then("^response message is ([^\"]*)$")
    public void invalidInputResponse(String message) {
        assertThat(testContext.getResponse().getBody().path("message").toString()).isEqualTo(message);

    }

    @Then("^the response should contain the correct data tags")
    public void invalidInputResponse() {
        String[] dataTags = {"t","o","h","l","c","v"};
        assertThat(testContext.getResponse().getBody().path("result.data[0]").toString()).contains(dataTags);

    }


}
