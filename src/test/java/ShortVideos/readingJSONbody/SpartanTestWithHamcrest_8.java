package ShortVideos.readingJSONbody;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanTestWithHamcrest_8 {
   //  we import the matchers statically -> import static org.hamcrest.Matchers.*;
    // hamcrest.Matchers -> we can use all the assertions in same execution and with request and body


    @BeforeAll
    public static void setUpClass(){ // This method has to be static
        baseURI="http://54.174.216.245:8000";
    }

    /**
     * Given accepts type is json
     * And path param spartan id is 15
     * When user sends a get request to /spartans/{id}
     * Then status code is 200
     * And content type is Json
     * and json data has following
     *      "id": 15,
     *      "name": "Margaretta"
     *      "gender": "Female"
     *      "phone": 2792789141
     */

    @Test
    public void test1(){
                 // request
                 given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}")
                // response
                .then().statusCode(200).and() // status code verification
                .assertThat().contentType("application/json;charset=UTF-8");
    }

    @Test
    public void test2(){
       // request
        given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}")
                // assertions with hamcrest.Matchers
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("id", equalTo(15),
                "name",equalTo("Margaretta"), "gender", equalTo("Female"),
                "phone", Matchers.equalTo(2792789141l));
        // I did Matchers import static. Instead of Matchers.equalTo(); I use equalTo();

    }


}
