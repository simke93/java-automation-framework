package constants;

import dto.User;

public class CommonConstants {
    public static final String BASE_URI = "https://petstore.swagger.io/v2/";

//test data
    public static final User DEFAULT_USER = User.builder()
        .username("username")
        .firstName("firstName")
        .lastName("lastName")
        .email("email")
        .password("password")
        .phone("phone")
        .userStatus(0)
        .build();

    public static final User USER_1 = User.builder()
            .username("simici")
            .firstName("Ivan")
            .lastName("Simic")
            .email("email.com")
            .password("123qwerty")
            .phone("1234567")
            .userStatus(0)
            .build();

    public static final User USER_2 = User.builder()
            .username("simici2")
            .firstName("Ivan2")
            .lastName("Simic2")
            .email("email2.com")
            .password("123qwerty")
            .phone("12345675")
            .userStatus(0)
            .build();

    public static final User INVALID_USER = User.builder().build();
}
