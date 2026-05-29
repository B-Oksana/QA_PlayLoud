package com.payLoad.tests;

import com.payLoad.core.TestBase;
import com.playLoad.dto.login.LoginRequestDto;
import com.playLoad.dto.login.LoginResponseDto;
import com.playLoad.dto.users.ErrorDto;
import com.playLoad.dto.users.ErrorListDto;
import com.playLoad.dto.users.UsersRequestDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginTests extends TestBase {

    LoginRequestDto requestDto = LoginRequestDto.of(EMAIL, PASSWORD);

    @Test
    public void loginSuccessTest() {
        LoginResponseDto loginResponseDto = given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .post(LOGIN_PATH)
                .then()
                .statusCode(201)
                .extract().response().as(LoginResponseDto.class);
        System.out.println(loginResponseDto.accessToken());
    }

    @Test
    public void loginWithIncorrectPassword() {
        given()
                .contentType(ContentType.JSON)
                .body(LoginRequestDto.of(EMAIL, "Olga123456"))
                .when()
                .post(LOGIN_PATH)
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("error", equalTo("Unauthorized"));
    }

    @Test
    public void loginWithInvalidPassword() {
        ErrorListDto errorListDto = given()
                .contentType(ContentType.JSON)
                .body(LoginRequestDto.of(EMAIL, "111"))
                .when()
                .post(USERS_PATH)
                .then()
                .statusCode(400)
                .extract().response().as(ErrorListDto.class);

        System.out.println(errorListDto.message());
    }

    @Test
    public  void loginWithIncorrectEmail() {
        ErrorListDto errorListDto = given()
                .contentType(ContentType.JSON)
                .body(LoginRequestDto.of("olga@gmailcom", PASSWORD))
                .when()
                .post(LOGIN_PATH)
                .then()
                .statusCode(400)
                .assertThat().body("message", hasItem("email must be an email"))
                .extract().response().as(ErrorListDto.class);

        System.out.println(errorListDto.message());
    }


}
