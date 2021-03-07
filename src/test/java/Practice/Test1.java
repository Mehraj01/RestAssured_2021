package Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*; // This is -> given, when, then and etc.
import static org.hamcrest.Matchers.*;


public class Test1 {

    @BeforeAll
    public static void init(){

        baseURI="http://54.174.216.245:8000";
    }

    @Test
    public void test1(){

       given().accept(ContentType.JSON).and().pathParam("id", 11)
                .when().get("/api/spartans/{id}")
               .then().statusCode(200).and().body("id", equalTo(11))
               .assertThat().contentType("application/json;charset=UTF-8");
    }

    @Test
    public void test2(){



    }



}
