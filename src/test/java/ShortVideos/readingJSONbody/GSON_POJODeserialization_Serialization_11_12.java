package ShortVideos.readingJSONbody;
import static org.junit.jupiter.api.Assertions.*;

import ShortVideos.SpartanPOJO;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class GSON_POJODeserialization_Serialization_11_12 {

    // // GSON -> de-serialization
    //        // how to convert JSON response to SpartanPOJO class OBJECT
    //        SpartanPOJO spartanPOJO=response.body().as(SpartanPOJO.class);

    @BeforeAll
    public static void setUpClass(){ // This method has to be static
        baseURI="http://54.174.216.245:8000";
    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();

        // GSON -> de-serialization
        // how to convert JSON response to SpartanPOJO class OBJECT
        SpartanPOJO spartanPOJO=response.body().as(SpartanPOJO.class);
        System.out.println(spartanPOJO.toString());


        // verify each key with spartan object
        assertEquals(spartanPOJO.getName(), "Margaretta");
        assertEquals(spartanPOJO.getId(), 15);
        assertEquals(spartanPOJO.getGender(),"Female");
        assertEquals(spartanPOJO.getPhone(),2792789141l);
    }

    @Test
    public void gsonExample(){

        Gson gson=new Gson();
        String myJsonBody="{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Margaretta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 2792789141\n" +
                "}";


        // using GSON method do de-serialization our json body
        SpartanPOJO spartanMeta=gson.fromJson(myJsonBody,SpartanPOJO.class);
        System.out.println(spartanMeta.toString());




        // serialization Java Object to -> Json Body
        SpartanPOJO spartanPOJO=new SpartanPOJO(101,"Mike", "Male", 1234567890l);
        // converting custom class to JSON (serialization)
        String jsonBody=gson.toJson(spartanPOJO);
        System.out.println(jsonBody);

    }




}
