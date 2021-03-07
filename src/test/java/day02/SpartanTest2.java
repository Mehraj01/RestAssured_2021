package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class SpartanTest2 {

    @BeforeAll
    public static void setUp(){
     // how to set base URL, part, base path so I can reuse them
        RestAssured.baseURI="http://52.91.154.247:8000";
        RestAssured.basePath="/api";

    }
    @DisplayName("Get 1 Spartan test")
    @Test
    public void testSingleSpartan(){

        // I want to log the request I sent so I see what is the Url, methods and so on

        given()
                .log()
                .all().

        when()
                .get("/spartans/20")
                .prettyPeek().
        then()
                .log()
                //.ifValidationFails()
               // .body()
                .all()
                .statusCode(is(200));

    }



    // Task
    // add another test for hello endpoint by reusing the baseURI, baePath
    // specify you want to get a test result back
    // check status code is 200
    // contentType is Text
    // body is Hello from Sparta
    @DisplayName("Testing /Hello again")
    @Test
    public void HelloTest(){

        given()
                .accept(ContentType.TEXT).// specify you want to get a text result back
        when()
                .get("/hello")// sending request to baseURI+basePATH+ /hello
                .then()
                .statusCode(is(200))// checking status code 200
                .contentType(ContentType.TEXT)// checking content type is text
                .body(equalTo("Hello from Sparta"));// checking the body


    }


    @DisplayName("Get All Spartans")
    @Test
    public void testBreakingBad(){

   // String spartanUrl="http://52.91.154.247:8000/api/spartans";


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
                //.baseUri("http://52.91.154.247:8000")
                //.basePath("/api")
                //.accept("application/json")
                .accept(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .statusCode(is(200))
                //.contentType("application/json;charset=UTF-8")
                //.contentType(is("application/json;charset=UTF-8"))
                .contentType(ContentType.JSON);
    }
}

