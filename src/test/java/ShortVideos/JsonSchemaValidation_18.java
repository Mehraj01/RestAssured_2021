package ShortVideos;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

// we imported this because it is to long we we call it on the method
import static io.restassured.module.jsv.JsonSchemaValidator.*;
public class JsonSchemaValidation_18 {

    @BeforeAll
    public static void setUpClass(){ // This method has to be static
        baseURI="http://3.89.251.52:8000";
    }

    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .pathParam("id", 90).auth().basic("admin", "admin")
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

    }


}
