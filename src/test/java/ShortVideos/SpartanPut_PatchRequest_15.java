package ShortVideos;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class SpartanPut_PatchRequest_15 {

    @BeforeAll
    public static void setUpClass(){ // This method has to be static
        baseURI="http://3.89.251.52:8000";
    }

    @Test
    public void PutRequest(){
        /**
         * Different ways to send json body
         * 1. - String
         * 2. - Using Collections (Map)
         * 3. - POJO
         */

        Map<String,Object>putMap=new HashMap<>();
        putMap.put("name","MikePUT");
        putMap.put("gender", "Male");
        putMap.put("phone", 1234509876l);

        // we gonna send request body with updated value, and content-type header
        given().contentType(ContentType.JSON)
                .and().pathParam("id",571).and().auth().basic("admin","admin")
                .and().body(putMap)
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204).log().all();




    }


    @Test
    public void PatchRequest(){

        Map<String,Object>patchMap=new HashMap<>();
        patchMap.put("name","MikePATCHHHHHH");

        given().contentType(ContentType.JSON)
                .and().pathParam("id",571).and().auth().basic("admin","admin")
                .and().body(patchMap)
                .when().patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204).log().status();


    }

}
