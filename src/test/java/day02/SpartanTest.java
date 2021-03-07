package day02;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SpartanTest {




    @DisplayName("Get All Spartans")
    @Test
    public void testBreakingBad(){

    String spartanUrl="http://52.91.154.247:8000/api/spartans";
    // how to set base URL, part, base path so I can reuse them

        RestAssured.baseURI="http://52.91.154.247:8000";
        RestAssured.basePath="/api";

        // it will create the request URL as is
        // baseURI +basePath +what  is after get("This part")

        // i want to explicitly specify I want JSON response from this result
        given()
                .header("Accepts", "application/json").
        when()
                .get("/spartans").
        then()
                .statusCode(is(200));

    }


    @DisplayName("Get All Spartans 2")
    @Test
    public void testAllSpartans2(){

        // send the same request specifying the accept header is application/json
        // use baseURI basePATH, check status code 200, contentType header is json

        given()
                .baseUri("http://52.91.154.247:8000")
                //.basePath("/api")
                .accept("application/json").accept(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .statusCode(is(200))
                //.contentType("application/json;charset=UTF-8")
                //.contentType(is("application/json;charset=UTF-8"))
                .contentType(ContentType.JSON);
    }
}

