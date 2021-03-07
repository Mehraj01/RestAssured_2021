package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
public class PutRequestExample {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://52.91.154.247";
        RestAssured.port=8000;
        RestAssured.basePath="/api";

    }

    @DisplayName("Updating exciting Data")
    @Test
    public void updateSpartan(){


        String updatedBody="{\n" +
                "  \"name\": \"HelloWorld\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 1231257897\n" +
                "}";


        given()
                .contentType(ContentType.JSON)
                .body(updatedBody)
                .log().all().
        when()
                .put("/spartans/{id}",571).
         then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void testDelete(){

        when()
                .delete("/spartans/{id}",35).
        then()
                .statusCode(204);


    }

}
