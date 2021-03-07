package Practice;

import static io.restassured.RestAssured.*; // this import ->  baseURI
import static org.junit.jupiter.api.Assertions.*;


import ShortVideos.SpartanPOJO;
import com.google.gson.Gson;
import io.restassured.RestAssured; // this import ->  RestAssured.baseURI
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
public class JsonSchemaValidation {


    @BeforeAll
    public static void setUpClass(){ // This method has to be static
        baseURI="http://54.174.216.245:8000";
    }

    @Test
    public void test1(){


        given().accept(ContentType.JSON)
                .pathParam("id", 18)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200).assertThat()
                .body(matchesJsonSchemaInClasspath("TestSchema.json"));
    }
}
