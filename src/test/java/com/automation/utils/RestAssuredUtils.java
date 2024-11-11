package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {

    static RequestSpecification requestSpecification = RestAssured.given();
    static Response response;
    static String endpoint;

    public static void setEndpoint(String endpoint) {
        clearEndpoint();
        RestAssuredUtils.endpoint = endpoint;
    }

    public static void setHeader(String key, String value) {
        requestSpecification.header(key, value);
    }

    public static void setBody(String filename) throws FileNotFoundException {
        requestSpecification.body(getBodyFromJson(filename));
    }

    public static void setBody(Object pojo) {
        requestSpecification.body(pojo);
    }

    public static void get() {
        response = requestSpecification.get(endpoint);
    }

    public static void post() {
        requestSpecification.log().all();
        response = requestSpecification.post(endpoint);
        response.then().log().all();
    }

    public static void put() {
        requestSpecification.log().all();
        response = requestSpecification.put(endpoint);
        response.then().log().all();
    }

    public static void delete() {
        response = requestSpecification.delete(endpoint);
        response.then().log().all();
    }

    public static String getBodyFromJson(String filename) throws FileNotFoundException {
        String path = "src/test/resources/data/";
        Scanner sc = new Scanner(new FileInputStream(path + filename));
        String body = sc.useDelimiter("\\Z").next();
        return body;
    }

    public static int getStatusCode() {
        return response.getStatusCode();
    }

    public static Response getResponse() {
        return response;
    }

    public static String getValueOfJsonField(String jsonpath) {
        return response.jsonPath().getString(jsonpath);
    }

    public static void clearEndpoint() {
        endpoint = null;
    }
}
