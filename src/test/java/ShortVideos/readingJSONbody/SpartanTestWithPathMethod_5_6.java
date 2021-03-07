package ShortVideos.readingJSONbody;

import io.restassured.http.ContentType;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.*;

public class SpartanTestWithPathMethod_5_6 {

    /**
     * Reading JSON body/payload with ==> Path(); -> Method
     *
     * If it is single id -> response.body().path("name").toString();
     *                        // -> response.body().path("name").toString();
     * If it is all Spartans ->  int firstId=response.path("id[0]");
     * we can save info inside List as wel -> List<String>names=response.path("name");
     *                                       //   -> List<Objects>phone=response.path("phone");
     */


    @BeforeAll
    public static void setUpClass(){ // This method has to be static
        baseURI="http://3.89.251.52:8000";

    }

    /**
     * Given accepts type is json
     * And path param id is 11
     * When user sends a GET request to /spartans/{id}
     * Then status code is 200
     * And content-type is application/json
     * And response payload values match the following
     *      id is 11
     *      name is "Alice"
     *      gender is "Female"
     *      phone is 9906873020
     */
    @Test
    public void pathMethod1(){

        Response response = given().accept(ContentType.JSON)
                // only spartan index 11
                .pathParam("id", 11).auth().basic("admin", "admin")
                .when().get("/api/spartans/{id}");
        
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        // path method returns the value. Return type is T because of that we use toString method 
        System.out.println("id value: " + response.body().path("id").toString());
        System.out.println("gender value: " + response.body().path("name").toString());
        System.out.println("name value: " + response.body().path("gender").toString());
        System.out.println("phone value: " + response.body().path("phone").toString());

        int id=response.path("id");
        String name=response.body().path("name");
        String gender=response.body().path("gender");
        long phone =response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(id,11);
        assertEquals(name, "Alice");
        assertEquals(gender, "Female");
        assertEquals(phone, 9906873020L);


    }

    @Test
    public void pathMethod2(){

        Response response = given().auth().basic("admin", "admin").get("/api/spartans");

        // extract first id
        int firstId=response.path("id[0]"); // we using index number to call specific info from database
        // if we use "id" without index it will return us all the IDs. Because our response contains all spartans IDs
        System.out.println("firstId = " + firstId);

        // extract the name from index 0
        String firstIndexName=response.path("name[0]");
        System.out.println("firstIndexName = " + firstIndexName);

        // extract the name from last index
        String lastIndexName=response.path("name[-1]");
        System.out.println("lastIndexName = " + lastIndexName);
        
        // extract all Names and print them. We store them in the LIST
        List<String>names=response.path("name");
        System.out.println("names = " + names);

        // print all the phone Numbers
        List<Objects>phone=response.path("phone");
        for (int i = 0; i <phone.size() ; i++) {
            System.out.println(phone.get(i));
        }

    }

}
