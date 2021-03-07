package ShortVideos.readingJSONbody;

import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeAll;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanJsonToCollections_DeSerialization_9_10 {

     //- GSON -> Json parser, object mapper (Some other Json Parsers are: -> Jackson 1.x , Jackson.2.x)
      //- Map <String,Object> spartanMap= response.body().as(Map.class);
    //  as(Map.class); ->  convert Json Response to Java Collections (Map)


    // De-Serialization -> (Collections)
    // converting Json to Java Collection
    // Map <String,Object> spartanMap= response.body().as(Map.class);
    // -> System.out.println(spartanMap.get("name"));    (spartanMap.get("id"));

    // List Of Map -> I can store all spartans inside of list of map :
    // --> List <Map<String,Object>>listOfSpartans = response.body().as(List.class);
    //         -> Map<String,Object>firstIndexSpartan=listOfSpartans.get(0); -> It will hold index 0 spartan
    //


    @BeforeAll
    public static void setUpClass(){ // This method has to be static
        baseURI="http://3.89.251.52:8000";
    }

    /**
     * Given accepts type is json
     * And path param spartan id is 15
     * When user sends a get request to /spartans/{id}
     * Then status code is 200
     * And content type is Json
     * And   id": 15,
     *      "name": "Margaretta"
     *      "gender": "Female"
     *      "phone": 2792789141
     */
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).auth().basic("admin", "admin")
                .pathParam("id", 15)
                .and().when().get("/api/spartans/{id}");


        // convert Json Response to Java Collections (Map)
        Map <String,Object> spartanMap= response.body().as(Map.class);
        System.out.println(spartanMap);


        System.out.println(spartanMap.get("name"));
        System.out.println(spartanMap.get("id"));
        // verify the name
        assertEquals(spartanMap.get("name"),"Meta");


        for (Map.Entry<String, Object> each:spartanMap.entrySet()) {
            System.out.println(each.getKey()+": "+each.getValue());
        }

    }

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON).auth().basic("admin", "admin")
                .when().get("/api/spartans");

        response.prettyPrint();

        // convert full JSON body to List of Maps
        List <Map<String,Object>>listOfSpartans = response.body().as(List.class);

        // print all the of first spartan
        System.out.println(listOfSpartans.get(0));
        Map<String,Object>firstIndexSpartan=listOfSpartans.get(0);
        System.out.println(firstIndexSpartan.get("name"));

        int index=0;
        for (Map<String, Object>map:listOfSpartans) {
            System.out.println("index: "+index+" "+map);
            index++;
        }


    }
}
