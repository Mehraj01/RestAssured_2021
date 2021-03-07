package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SpartanSearchTest_QueryParam {


    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI="http://52.91.154.247:8000";
        RestAssured.basePath="/api";

    }


    // http://52.91.154.247:8000/api/spartans/search?gender=Male&nameContains=ea
    @DisplayName("Testing / spartans/search Endpoint")
    @Test
    public void testSearch(){

        given()
                .log().all()
                .queryParam("gender", "Male")
                .queryParam("nameContains", "ea").
        when()
                .get("/spartans/search").
        then()
                .log().all()
                .statusCode(200)
                // assert number of elements according to your result here
                .body("numberOfElements",is(4))
        ;



    }
}
