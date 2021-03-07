package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SpartanTest_Parameters {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI="http://52.91.154.247:8000";
        RestAssured.basePath="/api";

    }


    @DisplayName("Testing /spartans/{id}")
    @Test
    public void test(){

        given()
                .log().all()
                .pathParam("id",20).
         when()
                .get("/spartans/{id}");




    }



    @DisplayName("Testing2 /spartans/{id}")
    @Test
    public void test2(){

        given()
                .log().all().
        when()
                .get("spartans/{id}", 20).
        then()
                .statusCode(is(200));

    }

    @DisplayName("Testing /spartans/{id} Body")
    @Test
    public void testSingleSpartanBody(){

        given()
                .log().all()
                .pathParam("id", 20).
         when()
                .get("/spartans/{id}").
         then()
                .log().all()
                .statusCode(is(200))
                //.body("JSON PATH", is("THE VALUE"))
                 .body("id",is(20))
                 .body("name", is("Lothario"))
                 .body("gender", is("Male"))
                 .body("phone", is(7551551687L))
        ;
    }
}
