package Practice;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BearerToken {

    /**
     * To get the access token we need to check API doc. In our case we have API end point and username and password which
     * we enter them in Param to generate the token.
     * After we generated our token we copy and past the token in the new request header with Authorization
     */


    String accessToken="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";

    @BeforeAll
    public static void setUp(){

        baseURI="http://cybertek-reservation-api-qa3.herokuapp.com";

    }

    @Test
    public void test(){

       Response response=given().header("Authorization",accessToken).get("/api/campuses");


        Assertions.assertEquals(response.statusCode(),200);
        response.prettyPrint();

    }
}
