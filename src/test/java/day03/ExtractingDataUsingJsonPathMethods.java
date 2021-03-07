package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ExtractingDataUsingJsonPathMethods {

    @DisplayName("Getting Movie info")
    @Test
    public void test1(){

        Response response=given()
                .log().all()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey","26aa5b74")
                .queryParam("t","Boss Baby").
        when()
                .get();

        // the JsonPath is a class that have a lot of methods
        // to get the body data in different format or data types
        // we get this object by calling the method of Response object called .jsonPath()
        JsonPath jp = response.jsonPath();
        // get the title as String
        String title = jp.getString("Title");
        int year = jp.getInt("Year");
        System.out.println("title = " + title);
        System.out.println("year = " + year);
        
        String director=jp.getString("Director");
        System.out.println("director = " + director);

        // get the first rating source as we did in previous class
        String rating1Src=jp.getString("Ratings[0].Source");
        System.out.println("rating1Src = " + rating1Src);

        // store the entire response as Map<String , Object>
        // Since our result is a Json Object with key value pair
        // we can directly call getMap method and provide the path
        // store the whole thing into a Map object
        // " " as jsonPath means the root path
        // we can also use dollar sign "$" to specify rootPath
        Map<String, Object>responseMap = jp.getMap("");
        System.out.println("responseMap = " + responseMap);

        //print out the Awards jey from above Map
        System.out.println("Awards are "+ responseMap.get("Awards"));

        // one more example of Map
        // store first Rating Json Object into map
        /***
         *       {
         *           "Source": "Internet Movie Database",
         *           "Value": "6.3/10"
         *
         *        }
         */

        Map<String, Object>firstRatingMap=jp.getMap("Ratings[0]");
        System.out.println("firstRatingMap = " + firstRatingMap);
//
//        // above is doing below when getMap method is being called
//        Map<String, Object>manualMap=new HashMap<>();
//        manualMap.put("Source", "Internet Movie Database");
//        manualMap.put("Value", "6.3/10");

        // I want to store all the source of rating into the list of string
        // your result should be ["Internet Movie Database", "Rotten Tomatoes", "Metacritic"]
        // JsonPath getList method will store items in json jsonArray into list
        
        // get me the list of Source field of the Ratings jsonArray from response
         List<List>sourceList=jp.getList("Ratings.Source");
        System.out.println("sourceList = " + sourceList);




       
        


    }

}
