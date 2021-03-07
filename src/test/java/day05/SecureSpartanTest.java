package day05;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class SecureSpartanTest {

      // This instance are created from the image: Cybertek_Latest_BasicAuth
     // so it require username and password to access the application
     //54.160.106.84
     //100.24.235.129
     //23.23.75.140

    // yours is created from Cybertek_Latest_NoAuth
    // it has the Spartan App version that does not require username and password


    // add @BeforeAll and use the spartanApp ID with basic auth
    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://54.160.106.84"; // username, password -> admin, admin
        RestAssured.port=8000;
        RestAssured.basePath="/api";

    }

    // add a test
    // make a simple get request /spartans/{id}
    @DisplayName("Test GET /spartans/{id} Endpoint with No Credentials")
    @Test
    public void testGetSingleSpartanSecured(){

        given()
                .log().all()
                .pathParam("id",45).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                . statusCode(401)
        ;
    }

    @DisplayName("Test GET  /spartans/{id} Endpoint with Credentials")
    @Test
    public void testGettingSpartanWithCredentials(){

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .pathParam("id",45).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(200)    ;


    }

    @Test
    public static int createRandomSpartan(){

        // use faker to get random first name , gender, and 10 digit number for phone
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender = faker.demographic().sex();
        long phone = faker.number().numberBetween(1000000000L,9999999999L);
        // this what we are going pass for post body
        Spartan sp = new Spartan(name, gender, phone) ;
        // make a post request with random data
        Response response = given()
                .log().ifValidationFails() // only log if anything goes wrong
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(sp).
                        when()
                .post("/spartans")
                .prettyPeek();
        // use jsonPath to get the id from the response and return it out from the method
        return response.jsonPath().getInt("data.id") ;

    }

}
