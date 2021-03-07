package day01;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredMethodChaining {

    @DisplayName("First Attempt for chaining")
    @Test
    public void chainingMethodsTest1(){


      // given() -> some more information you want to provide other than request url
                     // like header, query param, path variable, body
      // when() -> you send the request GET, POST, PUT, PATCH, DELETE

      // then() -> VALIDATE SOMETHING HERE

      // http://52.91.154.247:8000/api/hello
        when().
                get("http://3.89.251.52:8000/api/hello"). // sending a request to this url
        then(). // assertions starts here
                statusCode(is(200)). //asserting status code
                header("Content-Length", is(equalTo("17"))); // asserting header is "17"

    }
    @DisplayName("Using Hamcrest matcher for assertion")
    @Test
    public void testingWithMatcher(){

        // when -> response object ,  prettyPeek() -> printing the return same response object
        when().
                get("http://3.89.251.52:8000/api/hello"). // sending a request to this url
                prettyPeek().
        // then -> start validation
        then(). // assertions starts here
                statusCode(is(200)). //asserting status code
                header("Content-Length", is(equalTo("17"))).   // asserting header is "17"
                header("Content-Type",is("text/plain;charset=UTF-8")).
                body(is("Hello from Sparta"));

    }

    @DisplayName("Testing Get /api/Spartans endpoint")
    @Test
    public void testAllSpartans(){

        // given part -- RequestSpecification
            //- you can add information like header, query param, path var, body
            //- if this request need authentication , it also goes to give section
        // when part -- Send Request (GET, POST, PUT, DELETE)
            // - Get response
       // then part -- ValidationResponse
            // this is where assertion start
            // you can chain multiple assertion
            // if any assertion fail, whole test fail


        given().// adding all your request specific information like header, query param, path var, body
                header("Accept","application/xml" ).auth().basic("admin", "admin").
        when().
                get("http://3.89.251.52:8000/api/spartans").
                prettyPeek().
        then().// validation
                statusCode(is(200));
    }


}
