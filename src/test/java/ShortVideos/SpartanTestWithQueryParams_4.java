package ShortVideos;

// we added static key word to RestAssured import so we dont need to use RestAssured in the class
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
// We made Assertion import static as well
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanTestWithQueryParams_4 {

    @BeforeAll
    public static void setUpClass(){ // This method has to be static
      baseURI="http://3.89.251.52:8000";
    }


    /**
     * Given accepts type is Json
     * And query parameter values are:
     * gender\female
     * nameContains\e
     * When user sends GET request to /api/spartans/search
     * Then response content-type: application/json;charset=UTF-8
     * And "Female" should be in response payload (body)
     */
    @Test
    public void QueryParam1(){
// we added static key word to RestAssured import so we dont need to use RestAssured in the class
// because of that we are able to use given(); without restAssured
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "J")
                .when().get("/api/spartans/search");

        // verify status code
        assertEquals(response.statusCode(),200);
        // verify content type
        assertEquals(response.contentType(),"application/json;charset=UTF-8" );
        // verify Female
        assertTrue(response.body().asString().contains("Female"));

        // verify Male is not existc
        assertFalse(response.body().asString().contains("Male"));

        // verify Joesph
        assertTrue(response.body().asString().contains("Joesph"));

        response.prettyPrint();

    }

    @Test
    public void queryParams2(){
        // creating map for query Params. Because Map is key and value format as well

        Map<String,Object>paramsMap=new HashMap<>();
        paramsMap.put("gender", "Female");
        paramsMap.put("nameContains", "J");


        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("/api/spartans/search");


        // verify status code
        assertEquals(response.statusCode(),200);
        // verify content type
        assertEquals(response.contentType(),"application/json;charset=UTF-8" );
        // verify Female
        assertTrue(response.body().asString().contains("Female"));
        // verify Male is not existc
        assertFalse(response.body().asString().contains("Male"));
        // verify Joesph
        assertTrue(response.body().asString().contains("Joesph"));
        response.prettyPrint();


    }
}
