package Practice;
import ShortVideos.SpartanPOJO;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



public class Test3 {

    /**
     * SINCE WE DID THE STATIC IMPORT
     * we can directly call the get method
     * after we send the request
     * we can save the result in to a type called Response
     * need this  import io.restassured.response.Response;
     * Response response = get("http://3.89.251.52:8000/api/hello") ;
     *
     */

    @BeforeAll
    public static void setUp(){
        baseURI="http://3.89.251.52:8000";
    }

    /** (Gerkin language)
     * When user send GET Request to /api/spartans en point
     * Then status code must be 200
     * And body should contains SomeName
     */
    @Test
    public void test1(){
        Response response=given().auth().basic("admin", "admin").get("/api/spartans");
        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertTrue(response.body().asString().contains("Nona"));

    }


    /**
     * Given accepts type is json
     * And path param id is 11
     * When user sends a GET request to /spartans/{id}
     * Then status code is 200
     * And content-type is application/json
     * And response payload values match the following
     *      id is 11
     *      name is "Alice"
     *      gender is "Female"
     *      phone is 9906873020
     */

    @Test
    public void hamcrestMatchers(){ // -> Hamcrest Matchers

        given().auth().basic("admin", "admin")
                .accept(ContentType.JSON).and().pathParam("id",11)

                .when().get("api/spartans/{id}")

                .then().assertThat().statusCode(is(200)).and()
                .assertThat().contentType("application/json;charset=UTF-8").and()
                .assertThat().body("id",equalTo(11), "name",equalTo("Nona"),
                        "gender",equalTo("Female"), "phone", equalTo(7959094216l));



    }



    /**
     * Given accepts type is json
     * And path param id is 11
     * When user sends a GET request to /spartans/{id}
     * Then status code is 200
     * And content-type is application/json
     * And response payload values match the following
     *      id is 11
     *      name is "Alice"
     *      gender is "Female"
     *      phone is 9906873020
     */
    @Test
    public void pathMethod_SingleId(){

        Response response=given().auth().basic("admin", "admin").accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("api/spartans/{id}");
        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertEquals(response.contentType(),"application/json;charset=UTF-8");
        System.out.println(response.body().path("id").toString());
        System.out.println(response.body().path("name").toString());
        System.out.println(response.body().path("gender").toString());
        System.out.println(response.body().path("phone").toString());
        int id=response.path("id");
        String name=response.path("name");
        String gender =response.path("gender");
        long phone=response.path("phone");
        Assertions.assertEquals(id ,11);
        Assertions.assertEquals(name, "Nona");
        Assertions.assertEquals(gender, "Female");
        Assertions.assertEquals(phone,7959094216l);

    }


    @Test
    public void pathMethod_AllSpartans_WithIndex(){

        Response response=given().auth().basic("admin", "admin").accept(ContentType.JSON)
                .when().get("api/spartans");
        int id =response.path("id[0]");
        String name=response.path("name[0]");
        String gender=response.path("gender[0]");
        long phone =response.path("phone[0]");
        System.out.println(id);
        System.out.println(name);
        System.out.println(gender);
        System.out.println(phone);
        int idLast=response.path("id[-1]");
        System.out.println(idLast);

        List<Objects>phoneNumbers=response.path("phone");
        for (int i = 0; i <phoneNumbers.size() ; i++) {
            System.out.println(phoneNumbers.get(i));
        }


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

        Response response=given().auth().basic("admin", "admin").accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");


        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertEquals(response.contentType(),"application/json;charset=UTF-8");

        JsonPath jsonPath=response.jsonPath();

        int id =jsonPath.getInt("id");
        String name=jsonPath.getString("name");
        System.out.println(id);
        System.out.println(name);


    }

    @Test
    public void DeSerialization_Collection(){

        Response response=given().auth().basic("admin", "admin").accept(ContentType.JSON)
                .when().get("/api/spartans");

        List<Map<String, Object>>list=response.body().as(List.class);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
        // Will print only the values
        Map<String, Object>map=list.get(10); // keySet(): returns all the keys from the map as Set
        for (String each:map.keySet()) {    // get(key); method returns the value of the given key
            System.out.println(map.get(each));
        }
       // Will print keys and values
        for (Map.Entry<String, Object> each:map.entrySet()) {
            System.out.println(each.getKey()+":"+each.getValue());

        }

    }

    @Test
    public void DeSerialization_POJO(){

        Response response=given().auth().basic("admin", "admin").accept(ContentType.JSON).and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        SpartanPOJO spartanPOJO=response.body().as(SpartanPOJO.class);
        System.out.println(spartanPOJO.toString());
        int id=spartanPOJO.getId();
        System.out.println(id);


        System.out.println("============================="); // GSON to POJO

        Gson gson=new Gson();
        String newInfo="{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Margaretta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 2792789141\n" +
                "}";

     SpartanPOJO spartan=gson.fromJson(newInfo, SpartanPOJO.class);
        System.out.println(spartan.getGender());
        System.out.println(spartan.toString());


    }

    @Test
    public void serialization_pojoTOjson(){

        Gson gson=new Gson();
        SpartanPOJO SpartanPOJO= new SpartanPOJO(103,"Misha", "Male", 1234567890l);
        String gsonBody=gson.toJson(SpartanPOJO);
        System.out.println(gsonBody);
    }


}
