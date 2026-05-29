package com.payLoad.core;

import com.playLoad.utils.PropertiesLoader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;


public class TestBase {

    @BeforeEach
    public void init() {
        RestAssured.baseURI = "https://khamidov.dev/playloud";
        RestAssured.basePath ="api";
    }
    public static final String NAME = PropertiesLoader.loadProperty("user.name");
    public static final String EMAIL = PropertiesLoader.loadProperty("user.email");
    public static final String PASSWORD = PropertiesLoader.loadProperty("user.password");

    public static final String USERS_PATH = PropertiesLoader.loadProperty("users.path");
    public static final String LOGIN_PATH = PropertiesLoader.loadProperty("login.path");
    public static final String PRODUCTS_PATH = PropertiesLoader.loadProperty("products.path");
}
