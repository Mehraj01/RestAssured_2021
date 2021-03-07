package ShortVideos;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanPOSTRequest_13_14 {

    @BeforeAll
    public static void setUpClass(){ // This method has to be static
        baseURI="http://54.174.216.245:8000";
    }

    /**
     * Given accepts type and Content-type is JSON
     * And request JSON body is :
     * {
     *   "gender": "Male",
     *   "name": "Mike",
     *   "phone": 1234567890
     * }
     * When user sends POST request to '/spartans/'
     * Then status code 201
     * And content-type should be application/json
     * And Json payload/response should contain:
     * "A Spartan is Born!" message
     * and same data what is posted
     */

    @Test
    public void PostWithSting(){
        // sending json body as String
        Response post = given().accept(ContentType.JSON)// we asking API to send JSON Response body
                .and().contentType(ContentType.JSON)// We Letting API know we are sending JSON body
                .body("{\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"name\": \"Mike\",\n" +
                        "  \"phone\": 1234567890\n" +
                        "}")
                .when().post("/api/spartans/");

        post.prettyPrint();

        //validation
        // verify status code is 201
        assertEquals(post.statusCode(),201);
        assertEquals(post.contentType(), "application/json");

        // verify success message
        assertEquals(post.path("success"), "A Spartan is Born!");

        // verify request body
        JsonPath jsonPath=post.jsonPath();
        assertEquals(jsonPath.getString("data.name"), "Mike");
        assertEquals(jsonPath.getString("data.gender"), "Male");
        assertEquals(jsonPath.getLong("data.phone"), 1234567890l);

    }


    @Test
    public void PostMethodWithMap(){
        // create a Map to be used as body for the POST Request
        Map<String, Object>requestMap=new HashMap<>();
        requestMap.put("name", "MikeMap");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 1234567890l);


        Response post = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/api/spartans/");

        assertEquals(post.statusCode(), 201);
        post.prettyPrint();
    }


    // POST Request Serialization
    @Test
    public void PostWithPOJO(){
        // create SpartanPOJO object and use ad a body for POST request
        SpartanPOJO spartanPOJO=new SpartanPOJO();
        spartanPOJO.setName("MikePOJO");
        spartanPOJO.setGender("Male");
        spartanPOJO.setPhone(1234567890l);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartanPOJO)
                .when().post("/api/spartans/");

        assertEquals(response.statusCode(), 201);
        assertEquals(response.contentType(),"application/json");

        response.prettyPrint();

        // ======================= GET REQUEST ===================

        // De-serialization
        Response response1 = given().accept(ContentType.JSON)
                .pathParam("id", 571)
                .and().when().get("/api/spartans/{id}");

        SpartanPOJO spartanResponse=response1.body().as(SpartanPOJO.class);
        System.out.println(spartanResponse.toString());

    }
}
