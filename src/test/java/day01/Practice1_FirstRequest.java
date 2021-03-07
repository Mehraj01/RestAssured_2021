package day01;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Practice1_FirstRequest {
    // you may use your IP
    // we are using spartan app that does not require password
    //http://52.91.154.247:8000/api/hello

    @Test
    public void test1(){
        // make sure your request works on postman
        // if anything does not work manually it will not work in here either

        //RestAssured.get("URL Here");
        // since we did the static import
        // we can directly call the method
       // after we send the request
       // we can save the result in the a type called Response
       // need this ==> import io.restassured.response.Response;
        Response response = get("http://54.174.216.245:8000/api/hello");
        //response object store all the information about the response we got
        // like status, statusline, body, headers and so on..
        System.out.println("status code of this response "+response.statusCode()); //200
        // this is another way of getting status code starts with HTTP/1.1 200
        System.out.println("Getting status line of this response "+response.statusLine());

        // in restAssured there are usually 2 methods that does same action
        // one directly with the name like response.statusCode();
        // another starting with getXXX like response.getStatusCode();
        System.out.println("Getting status line of this response "+response.getStatusCode());//200

        // getting the header out of the response
        // we can use ==>  response.header("Header name goes here")
        // or we can use ==>  response.getHeader("Header name goes here")
        System.out.println("Getting the value of date header "+response.getHeader("Date"));
        System.out.println("Getting the value of date header "+response.header("Date"));

        // try to get content-Type header value and Content-Length header value
        System.out.println("Getting the value of Content-Type header "+response.header("Content-Type"));
        System.out.println("Getting the value of Content-Length header "+response.header("Content-Length"));

        // content-type is common in pretty much all request so there is a built support for this header
        // by directly calling a method
        System.out.println("Getting the value of Content-Type Header "+ response.contentType());
        System.out.println("Getting the value of Content-Type Header "+response.getContentType());

    }

    @DisplayName("Testing /hello endpoint")
    @Test
    public void testHello(){

        Response response = get("http://54.174.216.245:8000/api/hello");
        // testing the status code returned correctly
        assertEquals(200,response.statusCode());
        // testing the Content-Type header value is : text/plain;charset=UTF-8
        // 4 way: -> header("Content-Type"); getHeader("Content-Type"); ContentType();  getContentType();
        assertEquals("text/plain;charset=UTF-8", response.header("Content-Type"));
        assertEquals("text/plain;charset=UTF-8", response.getHeader("Content-Type"));
        assertEquals("text/plain;charset=UTF-8", response.contentType());
        assertEquals("text/plain;charset=UTF-8", response.getContentType());

        // testing the Content-Length header value is: 17
        assertEquals("17", response.header("Content-Length"));
        assertEquals("17", response.getHeader("Content-Length"));

    }

    @DisplayName("Testing /hello endpoint body")
    @Test
    public void testingHelloResponseBody(){

        // get the body and assert the body equal to Hello from Sparta
        Response response = get("http://54.174.216.245:8000/api/hello");

       // getting the body as String using asString method of Response object
        System.out.println(response.asString());
        // getting the body by calling body method
        // Do not use toString --> it does not give you the content
        // That's why asserting method exist
        System.out.println(response.body().asString());

        // assert the body is Hello from Sparta, length is 17
        String helloBody=response.asString();
        assertEquals("Hello from Sparta", helloBody);
        // asserting the length is 17
        assertEquals(17, helloBody.length());


    }

    @DisplayName("Printing the response body using method")
    @Test
    public void printingBody(){

        Response response = get("http://54.174.216.245:8000/api/hello");
        // prettyPrint() -> easy way to print response body and return at the same time
        response.prettyPrint();

        System.out.println("=========================================");
        // another way to see the body quick is prettyPeek
        // it print out the entire response
        // it will include all header, status code, body
        // Most importantly, it return same Response object rather that String like prettyPrint
        //it will enable you to call more method of response object after peeking
       // response.prettyPeek();

        System.out.println("=========================================");

        // I want to see entire response + save the status code into a variable in same statement

        int statusCode=response.prettyPeek().statusCode();
        System.out.println("Printing only status code "+statusCode);


    }


}
