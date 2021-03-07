package ShortVideos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SpartanTestsWithPathParameters_3 {


    @BeforeAll
    public static void setUpClass(){ // This method has to be static

        RestAssured.baseURI="http://3.89.251.52:8000";

    }

    /**
     * Given accept type is JSON
     * And id parameter value is 18
     * When user sends GET Request to /api/spartans/{id}
     * Then response status code should be 200
     * And response content-Type: application/json;charset=UTF-8
     * And "Melda" should be request response payload
     */
    @Test
    public void pathTest1(){

        Response response = RestAssured.given().auth().basic("admin", "admin").accept(ContentType.JSON)
                .and().pathParam("id", 18)
                .when().get("/api/spartans/{id}");

        // Verify that status code
        Assertions.assertEquals(response.statusCode(),200);
        // verify content type
        Assertions.assertEquals(response.contentType(),"application/json;charset=UTF-8" );

        // Verify Melda exists
        Assertions.assertTrue(response.body().asString().contains("Melda"));

        response.body().prettyPeek();

    }



    /** Negative Scenario
     *
     * Given accepts type is Json
     * And id parameter value is 700
     * When user sends GET request to /api/spartans/{id}
     * Then response status code should be 404
     * And response content-type: application/json;charset=UTF-8
     * And "Spartan Not Found" message should be in response playload (body)
     */
    @Test
    public void negativePathParamTest(){

        Response response = RestAssured.given().auth().basic("admin", "admin").accept(ContentType.JSON).
                and().pathParam("id", 1000)
                .when().get("/api/spartans/{id}");

        // verify status code is 404
        Assertions.assertEquals(response.statusCode(),404);
        // verify content-type
        Assertions.assertEquals(response.contentType(),"application/json;charset=UTF-8");
        // verify Spartan Not Found
        Assertions.assertTrue(response.body().asString().contains("Spartan Not Found"));


        response.prettyPrint();
        System.out.println("=========================");
        response.prettyPeek();



    }
}
