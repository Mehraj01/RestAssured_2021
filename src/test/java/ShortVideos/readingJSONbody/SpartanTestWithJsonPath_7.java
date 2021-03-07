package ShortVideos.readingJSONbody;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanTestWithJsonPath_7 {

    // Reading JSON body/payload ==> JsonPath jsonData= response.jsonPath();
    //  int id1 = jsonData.getInt("id");
    // String name=jsonData.getString("name");
    // String gender=jsonData.getString("gender");
    // long phone=jsonData.getLong("phone");

    @BeforeAll
    public static void setUpClass(){ // This method has to be static
        baseURI="http://54.174.216.245:8000";
    }

    /**
     * Given accepts type is json
     * And path param spartan id is 11
     * When user sends a get request to /spartans/{id}
     * Then status code is 200
     * And content type is Json
     * and "id": 11,
     *      "name": "Alice"
     *      "gender": "Female"
     *      "phone": 9906873020
     */
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");


        assertEquals(response.statusCode(), 200);
        // how to read value with path() method
        int id=response.path("id");
        System.out.println("id = " + id);

        // how read value with JsonPath?
        JsonPath jsonData= response.jsonPath(); // We save the data inside the JSON object
        // We can read the data with the methods below
        int id1 = jsonData.getInt("id");
        String name=jsonData.getString("name");
        String gender=jsonData.getString("gender");
        long phone=jsonData.getLong("phone");

        System.out.println("id1 = " + id1);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(id1,11);
        assertEquals(name, "Alice");
        assertEquals(gender, "Female");
        assertEquals(phone,9906873020l );



    }



}
