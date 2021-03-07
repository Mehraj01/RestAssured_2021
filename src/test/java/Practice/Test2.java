package Practice;

import static io.restassured.RestAssured.* ;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Test2 {

    /*
    @BeforeAll
    public static void setUp(){
        baseURI="http://3.89.251.52:8000";
    }
     */
// ======================================================

    String baseUrl="http://3.89.251.52:8000";

    @Test
    public void test1(){

        Response response= given().auth().basic("admin", "admin").get(baseUrl+"/api/spartans");
        System.out.println(response.statusCode());
    }



}
