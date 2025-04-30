package controller;

import dto.User;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static constants.CommonConstants.BASE_URI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class UserController {

    RequestSpecification requestSpecification;
    private static final String USER_ENDPOINT = "user";

    public UserController(){
        this.requestSpecification = given()
                .filter(new AllureRestAssured())

                .accept(JSON)
                .contentType(JSON)
                .baseUri(BASE_URI);
    }

    @Step("Create a user")
    public Response createUser(User user) {
        return given(this.requestSpecification)
                .body(user)
                .when()
                .log().all()
                .post(USER_ENDPOINT)
                .andReturn();
    }

    public Response updateUser(User user) {
        return given(this.requestSpecification)
                .body(user)
                .when()
                .post(USER_ENDPOINT + "/" + user.getUsername())
                .andReturn();
    }

    public Response getUserByUsername(String username) {
        return given(this.requestSpecification)
                .when()
                .get(USER_ENDPOINT + "/" + username)
                .andReturn();
    }


    public Response deleteUserByUsername(String username) {
        return given(this.requestSpecification)
                .when()
                .delete(USER_ENDPOINT + "/" + username)
                .andReturn();
    }


}
