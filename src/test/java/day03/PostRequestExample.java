package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PostRequestExample {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://52.91.154.247";
        RestAssured.port=8000;
        RestAssured.basePath="/api";

    }

    @DisplayName("Testing POST / api/spartans")
    @Test
    public void testAddSpartan(){

        String myBodyData="{\n" +
                "  \"name\": \"B18_Postman\",\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"phone\": 1231257897\n" +
                "}";
        System.out.println("myBodyData = " + myBodyData);

        given()
                .contentType(ContentType.JSON)
                .body(myBodyData)
                .log().all().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name",is("B18_Postman"))

        ;


    }


    // Create another test
    // make a post request, store the response Object
    // save the id unto int variable
    // save the name into String
    // print them out
    // as a homework, save the spartan data field into map
         // so your map will contain id, name, gender, phone
    //print them out

    @DisplayName("Practice extracting data")
    @Test
    public void postRequestExtractingData(){

        String myBodyData="{\n" +
                "  \"name\": \"Seda\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 1231257897\n" +
                "}";

        Response response=given()
                .contentType(ContentType.JSON)
                .body(myBodyData)
                .log().all().
         when()
                .post("/spartans")
                .prettyPeek()
                ;

        System.out.println("The id is "+response.path("data.id"));
        System.out.println("The name is "+response.path("data.name"));

        JsonPath jp=response.jsonPath();
        System.out.println("ID using jsonPath " + jp.getInt("data.id"));
        System.out.println("Name using jsonPath " + jp.getString("data.name"));




    }

}
