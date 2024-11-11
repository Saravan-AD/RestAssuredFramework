package com.automation.steps;

import com.automation.pojo.CreateRequestPojo;
import com.automation.pojo.CreateResponsePojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class ResponseSteps {

    @Then("verify status code is {int}")
    public void verify_status_code_is(int value) {
        Assert.assertEquals(200, RestAssuredUtils.getStatusCode());
    }

    @Then("verify request body is matches response body")
    public void verify_request_body_is_matches_response_body() {
        Response response = RestAssuredUtils.getResponse();
        CreateResponsePojo responsePojo = response.as(CreateResponsePojo.class);
        CreateRequestPojo requestPojo = (CreateRequestPojo) ConfigReader.getObject("request.pojp");
        Assert.assertTrue(requestPojo.getData().equals(responsePojo.getData()));
    }

    @And("verify json value of {string} is {string}")
    public void verifyJsonValueOfIs(String jsonpath, String value) {
        Assert.assertEquals(value, RestAssuredUtils.getValueOfJsonField(jsonpath));
    }

    @And("verify response as {string}")
    public void verifyResponseAs(String message) {
        if (message.contains("@id")) {
            message = message.replace("@id", ConfigReader.getConfig("product.id"));
        }
        Assert.assertEquals(message, RestAssuredUtils.getValueOfJsonField("message"));
    }
}
