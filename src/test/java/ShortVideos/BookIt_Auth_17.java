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


public class BookIt_Auth_17 {

    String accessToken ="Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";

    @BeforeAll
    public static void setUpClass(){ // This method has to be static
        baseURI="http://cybertek-reservation-api-qa3.herokuapp.com";
    }

    @Test
    public void test(){

        Response response = given().header("Authorization", accessToken)
                .when().get("/api/campuses");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();


    }
}
