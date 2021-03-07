package day03;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Practice1 {

    @BeforeAll
    // in JUnit 5 @BeforeAll @AfterAll is static
    // if we dont not make it static it does not work because that's how it's defined in the doc
    public static void setUp(){
       // example of setting the port separately from baseURI
        RestAssured.baseURI="http://52.91.154.247";
        RestAssured.port=8000;
        RestAssured.basePath="/api";

    }

    @DisplayName("simple test")
    @Test
    public void test1(){

        given()
                .log().all()
                .queryParam("gender", "Female").
         when()
                .get("spartans/search" )
         .then().statusCode(200)   ;

    }
}
