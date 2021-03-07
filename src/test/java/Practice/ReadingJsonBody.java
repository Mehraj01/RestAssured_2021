package Practice;

import static io.restassured.RestAssured.*; // this import ->  baseURI
import static org.junit.jupiter.api.Assertions.*;


import ShortVideos.SpartanPOJO;
import com.google.gson.Gson;
import io.restassured.RestAssured; // this import ->  RestAssured.baseURI
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import static org.hamcrest.Matchers.*;


public class ReadingJsonBody {

    // - 1. Use the response with path() method method to save the data and do your assertions
    // - 2. Save response inside the jsonPath object and reach the data with jsonPath methods
    // - 3. Use asString ->  response.body().asString().contains("Female")
    // - 4. Use Hamcrest Matcher to assert the result -> body("id", equalTo(11)
    // - 5. Use the POJO (GSON) object to store response body and use POJO methods to reach the the data

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://54.174.216.245:8000";
    }


    /**
     * Given accepts type is json
     * And path param spartan id is 11
     * When user sends a get request to /spartans/{id}
     * Then status code is 200
     * And content type is Json
     * and "id": 11,
     *      "name": "Alice"
     *      "gender": "Female"
     *      "phone": 9906873020
     */

    @Test
    public void JsonPath(){

        Response response= (Response) given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        // Help of JsonPath class we store Json response inside this jsonPath and use this class methods to reach the data
        // getInt, getString, getLong, getByte and etc..
        JsonPath jsonPath=response.jsonPath();

        int id=jsonPath.getInt("id");
        long phone=jsonPath.getLong("phone");
        String name=jsonPath.getString("name");

        assertEquals(id,11);
        assertEquals(name,"Luna");
        assertEquals(phone, 7685940321l);



    }

// =====================================================================================================================

    @Test
    public void PathMethod(){

        Response response= (Response) given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");
        response.prettyPeek();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        // we use path method to read json body
        int id=response.path("id");
        String name=response.path("name");
        long phone=response.path("phone");

        assertEquals(id, 11);
        assertEquals(name,"Luna");
        assertEquals(phone,7685940321l );

    }

    @Test
    public void PathMethod_AllSpartans(){

        Response response=get("/api/spartans");
      response.prettyPeek();

        int IdIndexFirst=response.path("id[0]");
        int idIndexLast=response.path("id[-1]");
        String name=response.path("name[0]");
        assertEquals(idIndexLast, 898);

        List<String>names=response.path("name");
        for (int i = 0; i <names.size() ; i++) {
            System.out.println(names.get(i));
        }

        List<Object>ids=response.path("id");
        for (int i = 0; i <ids.size() ; i++) {
            System.out.println(ids.get(i));
        }


    }
// =====================================================================================================================
    /**
     * Given accepts type is Json
     * And query parameter values are:
     * gender\female
     * nameContains\e
     * When user sends GET request to /api/spartans/search
     * Then response content-type: application/json;charset=UTF-8
     * And "Female" should be in response payload (body)
     */

    @Test
    public void StringJsonBody_QueryParam(){

        Response response=given().accept(ContentType.JSON)
                .and().queryParam("gender", "female")
                .and().queryParam("nameContains","Ch")
                .when().get("/api/spartans/search");
        response.prettyPeek();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");


        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Chelsea"));
        assertFalse(response.body().asString().contains("HelloWorld"));

//..............................

        // creating map for query Params. Because Map is key and value format as well
        Map <String, Object> map=new LinkedHashMap<>();
        map.put("gender", "Female");
        map.put("nameContains","J");
        Response response1=given().accept(ContentType.JSON)
                .and().queryParams(map)
                .when().get("/api/spartans/search");


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8" );
        assertTrue(response.body().asString().contains("Female"));
        assertFalse(response.body().asString().contains("Male"));
        assertTrue(response.body().asString().contains("Joesph"));

    }

// =====================================================================================================================
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
    public void Hamcrest(){

        given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().assertThat().body(
                        "id", equalTo(11), "name",equalTo("Luna"),
                         "gender", equalTo("Female"),  "phone",equalTo(7685940321l)
                          );


    }
// =====================================================================================================================

    @Test
    // Deserialization
    public void Pojo_Deserialization(){


        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("/api/spartans/{id}");


      // We Store the Response body inside the POJO and we reach all the info with getters
        SpartanPOJO spartans=response.body().as(SpartanPOJO.class);
        assertEquals(spartans.getId(),11);
        assertEquals(spartans.getName(),"Luna");
        assertEquals(spartans.getGender(),"Female");
        assertEquals(spartans.getPhone(), 7685940321l);

    }


    @Test
    // Deserialization and Serialization
    public void GSON_POJO(){

        Gson gson=new Gson();
        String myJsonBody="{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Margaretta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 2792789141\n" +
                "}";

        // Deserialization -> Json to Pojo
        SpartanPOJO Deserialization=gson.fromJson(myJsonBody,SpartanPOJO.class);
        System.out.println(Deserialization.toString());

        // Serialization -> Pojo to Json
        SpartanPOJO spartans1=new SpartanPOJO(101,"Mike", "Male", 1234567890l);
        String serialization=gson.toJson(spartans1);

    }

    @Test
    public void Collection_Deserialization(){


        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .and().when().get("/api/spartans/{id}");

        Map<String,Object>map=response.body().as(Map.class);
        System.out.println(map.get("name"));
        assertEquals(map.get("name"),"Luna");

        // EntrySet works with JSON DATA perfectly
        for (Map.Entry<String,Object> each:map.entrySet()) {
            System.out.println(each.getKey()+" : "+each.getValue());
        }

    }

    // Getting all Spartans
    @Test
    public void ListOfMap_Deserialization(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        //List of MAP
        List<Map<String, Object>>listOfMaps=response.body().as(List.class);
        Map<String,Object>FirstIndexSpartan=listOfMaps.get(0);

        int index=0; // for counting indexes
        for (Map<String,Object>each:listOfMaps) {
            System.out.println(index+ " : "+each);
            index++;
        }
    }

}
