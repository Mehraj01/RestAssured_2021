package ShortVideos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartansTest_GetRequest_1_2 {

    String spartanBaseUrl="http://3.89.251.52:8000";

    // Send the simple GET REQUEST
    @Test
    public void viewSpartanTest1(){

        // Response is special data type that storing API response
        Response response =  RestAssured.given().auth().basic("admin", "admin").get(spartanBaseUrl + "/api/spartans");
        // press alt and hit enter to get the variable

        // statusCode(); will return status code of that response
        System.out.println("response.statusCode() = " + response.statusCode());

        // print body
        System.out.println("response.body().asString() = " + response.body().asString());

        //prettyPrint(); will return body like in Postman
        System.out.println("response.body().asString() = " + response.body().prettyPrint());

    }

    /** (Gerkin language)
     * When user send GET Request to /api/spartans en point
     * Then status code must be 200
     * And body should contains SomeName
     */
    @Test
    public void viewSpartanTest2(){
        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");

        // Verify status code is 200
        Assertions.assertEquals(response.statusCode(),200);

        // Verify body contains Gonul
        Assertions.assertTrue(response.body().asString().contains("Gonul"));

    }

    /**
     * Given accepts type is Json
     * When user sends a GET request to spartanAllURL
     * Then response status code is 200
     * And response body should be json format
     */
    @Test
    public void viewSpartanTest3(){

        // Response stores request and responses.
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanBaseUrl + "/api/spartans");

        // verify status code
        Assertions.assertEquals(response.statusCode(),200);

        // verify response body is JSON
        Assertions.assertEquals(response.contentType(), "application/json;charset=UTF-8");
        // contentType(); -> will return as String.
        // contentType(); ->  is located inside the response header
    }
    



}
