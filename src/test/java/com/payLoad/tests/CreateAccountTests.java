package com.payLoad.tests;

import com.payLoad.core.TestBase;
import com.playLoad.dto.users.ErrorDto;
import com.playLoad.dto.users.UsersRequestDto;
import com.playLoad.dto.users.UsersResponseDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class CreateAccountTests extends TestBase {

   UsersRequestDto requestDto = UsersRequestDto.of(NAME, EMAIL, PASSWORD);
    // UsersRequestDto requestDto = UsersRequestDto.of(NAME,"ole"+System.currentTimeMillis()+"@gmail.com",PASSWORD);

    @Test
    public void createAccountSuccessTest() {
        UsersResponseDto responseDto = given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .post(USERS_PATH)
                .then()
                .assertThat().statusCode(201)
                .extract().response().as(UsersResponseDto.class);
        System.out.println(responseDto.id() + " *** " + responseDto.name());
    }

    @Test
    public void createExistedEmailErrorTest(){
        ErrorDto errorDto = given()
                .contentType(ContentType.JSON)
                .body(UsersRequestDto.of(NAME, EMAIL, PASSWORD))
                .when()
                .post(USERS_PATH)
                .then()
                .assertThat().statusCode(409)
                .assertThat().body("message", containsString("email already exists"))
                .extract().response().as(ErrorDto.class);

        System.out.println(errorDto.message());
        // A record with this email already exists.
    }

    @Test
    public void createAccountWithInvalidPassword() {
        given()
                .contentType(ContentType.JSON)
                .body(UsersRequestDto.of(NAME, "test" + System.currentTimeMillis() + "@gm.com",
                        "123"))
                .when()
                .post(USERS_PATH)
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("error", equalTo("Bad Request"));
    }


}
