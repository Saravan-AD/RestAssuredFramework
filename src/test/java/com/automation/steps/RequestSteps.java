package com.automation.steps;

import com.automation.pojo.CreateRequestPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;

public class RequestSteps {

    @Given("user sets endpoint {string}")
    public void user_sets_endpoint(String endpoint) {
        if (endpoint.contains("@id")) {
            endpoint = endpoint.replace("@id", ConfigReader.getConfig("product.id"));
        }
        RestAssuredUtils.setEndpoint(endpoint);
    }

    @When("user sets request body from {string}")
    public void user_sets_request_body_from(String filename) throws FileNotFoundException {
        RestAssuredUtils.setBody(filename);

    }

    @When("performs post call")
    public void performs_post_call() {
        RestAssuredUtils.post();
        ConfigReader.setConfig("product.id", RestAssuredUtils.getValueOfJsonField("id"));
    }


    @When("user sets request body from {string} as pojo")
    public void userSetsRequestBodyFromAsPojo(String filename) throws Exception {
        String body = RestAssuredUtils.getBodyFromJson(filename);
        ObjectMapper objectMapper = new ObjectMapper();
        CreateRequestPojo requestPojo = objectMapper.readValue(body, CreateRequestPojo.class);
        RestAssuredUtils.setBody(requestPojo);
        ConfigReader.setObject("request.pojo", requestPojo);
    }

    @And("sets header {string} as {string}")
    public void setsHeaderAs(String key, String value) {
        RestAssuredUtils.setHeader(key, value);
    }

    @And("perform get call")
    public void performGetCall() {
        RestAssuredUtils.get();
    }

    @And("performs put call")
    public void performsPutCall() {
        RestAssuredUtils.put();
    }

    @And("perform delete call")
    public void performDeleteCall() {
        RestAssuredUtils.delete();
    }

    @When("user sets request body from {string} as pojo with {string} value {string}")
    public void userSetsRequestBodyFromAsPojoWithValue(String filename, String fieldname, String value) throws Exception {
        String body = RestAssuredUtils.getBodyFromJson(filename);
        ObjectMapper objectMapper = new ObjectMapper();
        CreateRequestPojo requestPojo = objectMapper.readValue(body, CreateRequestPojo.class);
        Field field=CreateRequestPojo.class.getDeclaredField(fieldname);
        field.setAccessible(true);
        field.set(requestPojo,value);
        RestAssuredUtils.setBody(requestPojo);
        ConfigReader.setObject("request.pojo", requestPojo);
    }
}
