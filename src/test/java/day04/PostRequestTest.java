package day04;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class PostRequestTest {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://52.91.154.247";
        RestAssured.port=8000;
        RestAssured.basePath="/api";

    }

    @DisplayName("Post request with String as body")
    @Test
    public void testPostWithStringBody(){

        // lets  try to get random names rather than same Adam each time
        Faker faker=new Faker();
        String randomName=faker.name().firstName();
        System.out.println("randomName = " + randomName);
        String bodyString= "{\n" +
                "  \"name\": \""+randomName+"\",\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"phone\": 1231257897\n" +
                "}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyString).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name", is(randomName));

    }

    @DisplayName("Posting with external json file")
    @Test
    public void testPostWithExternalFile(){

        // create a file object that point to spartan.json you just added
        // so we can use this as body in post request

        File file1 =new File("spartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file1).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name",is("From File"));

    }

    @DisplayName("Posting with Map object as Body")
    @Test
    public void testPostWithMapAsBody(){

        // add dependency jackson-databind

        //create a Map<String, Object> as hashMap
        //add name, gender, phone

        Map<String, Object>bodyMap=new HashMap<>();
        bodyMap.put("name", "Vincent");
        bodyMap.put("gender", "Male")  ;
        bodyMap.put("phone", 1234567890);
        System.out.println("bodyMap = " + bodyMap);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap). // jackson-data-bind turn your java map object to json here
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name",is("Vincent"))
        ;



    }

}
