package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

// you may aslo add display name at class level like you did at test level
@DisplayName("Testing ZIp Code API")
public class ZipCodeTest {

    @BeforeAll
    public static void SetUp(){
        // The Url must start with Http od Https
        // Or rest Assured Can Not Decide It's A Valid Url or Not
        RestAssured.baseURI="http://api.zippopotam.us";
        RestAssured.basePath="/us";

    }

    @DisplayName("Zip to City Test")
    @Test
    public void testZipToCity(){

        given()
                .pathParam("zip", 11235) // if our zipCode starts with zero make it as string "012345"
                .log().all().
        when()
                .get("/{zip}").
        then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("country", is("United States"))
                 // get the state and check it is Virginia
                 .body("places[0].state", is("New York"))
                  //Fix For the space in the key
                  .body("'post code'", is("11235"))
                  .body("places[0].'place name'",is("Brooklyn"))
        ;
        // if a field/key you are trying to get has space
        // then add single quote '' for example " 'post code' "

    }


    @DisplayName("City to Zip")
    @Test
    public void testCityToZip(){

        http://api.zippopotam.us/us/NY/Brooklyn -> given gets these
        given()
                //.pathParam("state", "NY")
                //.pathParam("city", "Brooklyn")
                .log().all().
        when()
                //.get("/{state}/{city}").
                .get("/{state}/{city}", "NY", "Brooklyn").// another way to do it
        then()
                .log().all()
                .statusCode(is(200))
                .body("'country abbreviation'",is("US"))
                 // Test the latitude of the first place is "40.694"
                 .body("places[0].latitude",is("40.694"))
                 // jsonPath hack for getting last item from jsonArray
                 // -1 index indicate the last item, only works here not in postman
                .body("places[-1].latitude", is("40.6451"))
        ;
    }
}
