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

public class SpartanDeleteRequest_16 {

    @BeforeAll
    public static void setUpClass(){ // This method has to be static
        baseURI="http://3.89.251.52:8000";
    }

    @Test
    public void test1(){

        given().auth().basic("admin","admin").pathParam("id",578)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

        // verify part with the status code
        given().pathParam("id",578)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(404);

    }
}
