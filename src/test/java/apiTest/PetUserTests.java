package apiTest;

import controller.UserController;
import dto.User;
import dto.UserResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static constants.CommonConstants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PetUserTests {
    //dependency injection or invertion of controlol -> Spring or Guice Google Lib
    UserController userController = new UserController();


    @Test
    void createUser() {
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
                .baseUri(BASE_URI)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body).
                when().post(userEndpoint).andReturn();
        response.body().prettyPrint();

        Assertions.assertEquals(200, response.statusCode());
    }


    @Test
    void createUserImprovedTest() {
        /*User user = new User(0, "string", "string", "string", "string",
                "string","string", 0);*/
        Response response = userController.createUser(DEFAULT_USER);
        response.getBody().prettyPrint();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void createUserImprovedTest2() {
        /*User user = new User(0, "string", "string", "string", "string",
                "string","string", 0);*/
        Response response = userController.createUser(USER_1);
        response.getBody().prettyPrint();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void createUserImprovedTest3() {
        /*User user = new User(0, "string", "string", "string", "string",
                "string","string", 0);*/
        Response response = userController.createUser(USER_2);
        response.getBody().prettyPrint();

        Assertions.assertEquals(200, response.statusCode());
    }


    static Stream<User> users(){
        return Stream.of(DEFAULT_USER, USER_1, USER_2);
    }

    @ParameterizedTest
    @MethodSource("users")
    void createUserParametrizedTest(User user) {
        Response response = userController.createUser(user);
        UserResponse createdUserResponse = response.as(UserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }



    @Test
    void checkUserResponseBody() {
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
                .baseUri(BASE_URI)
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
       /* User user = new User(0, "string", "string", "string", "string",
                "string","string", 0);*/

        Response response = userController.createUser(DEFAULT_USER);
        response.getBody().prettyPrint();
        UserResponse createdUser = response.as(UserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUser.getCode());
        Assertions.assertEquals("unknown", createdUser.getType());
        Assertions.assertNotNull(createdUser.getMessage());
        Assertions.assertTrue(Long.parseLong(createdUser.getMessage()) > 9223372036854762719L);
    }

}
