package apiTest;

import controller.UserController;
import dto.User;
import dto.UserResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PetUserTests {
    @Test
    void createUser() {
        String baseUrl = "https://petstore.swagger.io/v2/";
        String userEndpoint = "user";

        String body = """
           {
             "id": 0,
             "username": "string",
             "firstName": "string",
             "lastName": "string",
             "email": "string",
             "password": "string",
             "phone": "string",
             "userStatus": 0
           }""";


        Response response = given()
                .baseUri(baseUrl)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body).
                when().post(userEndpoint).andReturn();
        response.body().prettyPrint();

        Assertions.assertEquals(200, response.statusCode());
    }


    @Test
    void createUserImprovedTest() {
        User user = new User(0, "string", "string", "string", "string",
                "string","string", 0);

        UserController userController = new UserController();
        Response response = userController.createUser(user);
        response.getBody().prettyPrint();

        Assertions.assertEquals(200, response.statusCode());
    }


    @Test
    void checkUserResponseBody() {
        String baseUrl = "https://petstore.swagger.io/v2/";
        String body = """
           {
             "id": 0,
             "username": "string",
             "firstName": "string",
             "lastName": "string",
             "email": "string",
             "password": "string",
             "phone": "string",
             "userStatus": 0
           }""";


        given()
                .baseUri(baseUrl)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body).
                when()
                .post("user")
                .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", notNullValue(String.class));
    }

    @Test
    void checkUserResponseBodyImprovedTest() {
        User user = new User(0, "string", "string", "string", "string",
                "string","string", 0);

        UserController userController = new UserController();
        Response response = userController.createUser(user);
        response.getBody().prettyPrint();
        UserResponse createdUser = response.as(UserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUser.getCode());
        Assertions.assertEquals("unknown", createdUser.getType());
        Assertions.assertNotNull(createdUser.getMessage());
        Assertions.assertTrue(Long.parseLong(createdUser.getMessage()) > 9223372036854762719L);
    }

}
