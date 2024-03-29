package day05;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class PostRequestWithFormAsBody {

    // post in library app
    // body is not json, it's x-www-urlencoded=form-data

    // http://library2.cybertekschool.com/rest/v1/login
    // baseURI -> http://library2.cybertekschool.com
    // basePath -> /rest/v1
    // we are working on POST -> /login

    // Post body type -> x-www-urlencoded=form-data
    // librarian568@library
    // SjvLOgHC

    // in URLs if you do not see port, because it's omitted because it's sp common
    // http -> 80
    // https -> 443
    // anything other than above 2 ports need to be explicitly set in the URL
    // for example spartan app use port 8000 -> yourip:8000

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://library2.cybertekschool.com";
        RestAssured.basePath="/rest/v1";


    }
    @DisplayName("POST /login request test")
    @Test
    public void testLoginEndPoint(){

        given()
                .log().all()
                // explicitly saying the body content type is -> x-www-urlencoded=form-data
                .contentType(ContentType.URLENC)
                .formParam("email","librarian568@library" )
                .formParam("password", "SjvLOgHC").
        when()
                .post("/login").
        then()
                .log().all()
                .statusCode(200)
                // we cannot actually validate the token since it's dynamic
                // what we can validate though -- token field exists and it's not null
                .body("token",is(notNullValue()))

                ;
        // Create a method loginAndGetToken(String username, String password)
        // return: String as the token value


    }






    @DisplayName("Testing out the utility")
    @Test
    public void runningUtilityMethod(){

        System.out.println(loginAndGetToken("librarian568@library","SjvLOgHC" ));
    }






    /**
     * A static utility method to get the token by providing username and password
     * as Post request to /Login endpoint and capture the token field from response json
     * @param username
     * @param password
     * @return the token as String from the response json
     */
    @Test
    public String loginAndGetToken(String username, String password){

        String token="";

        Response response = given()
                .log().all()
                // explicitly saying the body content type is -> x-www-urlencoded=form-data
                .contentType(ContentType.URLENC)
                .formParam("email",username )
                .formParam("password", password).
                        when()
                .post("/login");


        // token=response.path("token"); // this is using path method
        token=response.jsonPath().getString("token");


        return token;
    }







}
