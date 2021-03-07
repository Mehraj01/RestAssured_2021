package day04;


import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class PutAndPatchRequestTest {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://52.91.154.247";
        RestAssured.port=8000;
        RestAssured.basePath="/api";

    }


    @DisplayName("Put request body as a Map")
    @Test
    public void testPutRequestWithMap(){

        // put request to update spartan with id 421
        // name: put with map, gender: Male, phone: 1234567890
        // status code 204
        //how to actually know its updated since it does not have body in request
        // we can make another get request tight after this and assert the body
        Map<String, Object>updatedBody=new LinkedHashMap<>();
        String randomName=new Faker().name().firstName();

        updatedBody.put("name",randomName );
        updatedBody.put("gender","Male" );
        updatedBody.put("phone", 1234567890L);


        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(updatedBody). // this is how we do it with th map

        when()
                .put("/spartans/{id}", 67  ).
        then()
                .log().all().statusCode(is(204)) ;

        // making another get request to make sure it worked!!!

        when()
                .get("/spartans/{id}",67).
        then().statusCode(200)
                .body("name", is(randomName));

    }


    @DisplayName("Put request body as a POJO")
    @Test
    public void testPutRequestWithPOJO(){

        // put request to update spartan with id 421
        // name: put with map, gender: Male, phone: 1234567890
        // status code 204
        //how to actually know its updated since it does not have body in request
        // we can make another get request tight after this and assert the body
        // getting random name


        String randomName=new Faker().name().firstName();
        // this how we can provide POJO instead
        Spartan sp1=new Spartan(randomName,"Female", 1234567890L);


        given()
                .log().all()
                .contentType(ContentType.JSON)
                //.body(updatedBody). // this is how we do it with th map
                .body(sp1).// this is pojo
         when()
                .put("/spartans/{id}", 421  ).
        then()
                .log().all().statusCode(is(204)) ;

    }

    // difference between put and patch
    // in this app
    // put will completely renew entire json that location with data
    // patch will partially update the data

    @DisplayName("Patch request")
    @Test
    public void testPathPartialUpdate(){

        // only update the name with faker
        String randomName=new Faker().name().firstName();
       // String patchedBody="\"name\": \""+randomName+"\"";

        Map<String, Object>patchedBodyMap=new HashMap<>();
        patchedBodyMap.put("name", randomName);


        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(patchedBodyMap).
        when()
                .patch("/spartans/{id}", 67).
        then()
                .log().all()
                .statusCode(204);


        // make another get request here to make sure it worked


    }

    // create a method that post a random spartan to the server
    // and return the ID of that spartan, so you can always use a data that exist




}
