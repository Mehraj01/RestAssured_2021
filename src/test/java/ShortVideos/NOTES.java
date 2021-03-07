package ShortVideos;
/*

--> API (Application Programming Interface)
     - API is the acronym for Application Programming Interface, which is a software intermediary that allows two applications to talk to each other.

--> There is 2 type of API
   1. Soap (Simple Object Access Protocol)
   2. Rest (Representational State of Transfer)

- SOAP
     - is for communication between applications
     - is a format for sending messages
     - communicates via internet
     - is platform independent
     - is based on XML

- REST - is a lightweight option for developing web services using the HTTP protocol
     - it can structure data into XML or an other machine readable format, but usually JSON
     - Rest is very data driven, compared to SOAP, which is strongly function driven

--> SOPA vs REST
- Sopa is not popular anymore
- 80% of the market is using REST
- SOPA is XML Response, REST is XML and JSON
- REST is purely http protocol
      - We can hit the link on the browser and see the result


=======================================================================================

--> What is an API TESTING
- API testing is entirely different from GUI testing and mainly concentrates on the business logic layer of the software architecture. This testing wont concentrate on the look and feel of ac application.
- Instead of using standart user inputs(keyboard) and outputs, in API Testing, you use software to send calls to the API, get output, and note down the system's response.

--> What is an API Testing?
API Testing requires an application to interact with API. In order to test an API, you will need to:
    1. Use testing tool to drive the API
    2. Write your own code to test the API

--> Why API Testing is Important?
- Faster to test that GUI testing
- Without a UI, it can be tested earlier in the life cycle
- Become more popular in the industry
- Time Efficiency vs. functional and validation testing
- Cost Effective/ Reduce testing cost


=======================================================================================

--> How to send a request?
- When we send request, we need to know the API methods/endpoints that are available:
      - read documentation about api methods
      - Swagger tool, that has API methods and descriptions


--> Types of HTTP requests
- GET - Retrieves the data from a specified source
- POST - Sends new data to a specified source
- PUT - Updates info for a specified source
- DELETE - Removes data from a specified source

--> POSTMAN
- is a RestAPI client tool that helps to test REST API urls
- It is popular for both developers and testers
- We can use both manual and automation purposes

--> HTTP STATUS CODE

- 200 -> OK
- 400 -> Client's Error
- 500 -> Server/API's Error

--> Level 200 (Success)
200 -> ok
201 -> Created
203 -> Non-Authoritative information
204 -> No content

--> Level 400
400 -> Bad request
401 -> Unauthorized
403 -> Forbidden
404 -> Not Found
409 -> Conflict

--> Level 500
500 -> Internal Server Error
503 -> Service Unavailable
501 -> Not Implemented
504 -> Gateway Timeout
599 -> Network timeout
502 -> Bad Gateway


--> First Test Case
- When I send a Get request to:
- http://52.91.154.247:8000/api/spartan
       - 54.174.216.245:8000 -> Base URL
       - /api/spartan -> End point
- Then Response STATUD CODE must be 200
- And I should see all Spartans data in JSON format


=======================================================================================


--> HEADERS
- Additional information about request and response.
- It works like a map key & value structure
- What we send with the headers?
    - Content types (application/json, application/xml, ...)
    - Credentials, Authentication, (username, password, different types)


--> Request Headers
- Credintials, Authentication,(username, password, different types)
- Accepts, application/json -> hey api, please send me json response
- Accepts, application/xml -> hey api, please send me xml response


--> Response Headers
- Content-type, application/json -> hey client, I am sending you json body
- Content-type, application/xml -> hey client, I am sending you xml body


=======================================================================================

--> PARAMETERS
- Parameters are options you can pass with the endpoint to influence the response
- 2 Types of Parameters
     - Query Parameters
     - Path Parameters

--> Path Parameter
- is a part of URL and followed by the end of full resource url
- 54.174.216.245:8000/api/spartans/{id}
       - {id}  -> is path parameter

-> Query Parameters
- It is NOT part of url and passed in key + value format
- ? means end of URL  (After ? mark it is not url. It is key and value structures) ->  s?k=api
      - https://www.amazon.com/s?k=api
      - 54.174.216.245:8000/api/spartans/search?gender=Female
      - 54.174.216.245:8000/api/spartans/search?gender=Female&nameContains=ea

=======================================================================================

--> What are the parts of the GET REQUEST
- BaseURL + EndPoint (API method)
- Parameters
    1. Path Parameters: /{id}  -> it is part of the url
    2. Qeury Parameters: (key and value)  -> not part of the url
- Headers
    - Accepts, application/json (or xml) -> I want to get json response body
    - Credintials, Authentication,(username, password)

--> What are the parts of GET RESPONSE
- Statuc code:
    - 2XX -> Successful
    - 4XX -> client(our) error
    - 5xx -> server error
Headers
    -  Content-Type, application/json (Body: json, xml, html, text) -> I want to get json response body


--> We manually test API with Postman
--> We use swagger documentation to understand API.


=======================================================================================

--> Reading JSON body/payload

- String contains ("value") --> boolean verification
- path()
- JsonPath
- Hamcrest Matchers(changing)

-JSon response
{
    "id": 15,
    "name": "Margaretta",
    "gender": "Female",
    "phone": 2792789141
}


- GSON -> Json parser, object mapper (Some other Json Parsers are: -> Jackson 1.x , Jackson.2.x)
- Map <String,Object> spartanMap= response.body().as(Map.class);

--> POJO -> Plain Old Java Object

=======================================================================================

--> POST Method
- Request Part
    - BaseURL + EndPoint
    - Headers
         - Accepts, application/json -> we are asking API to send JSON response body
         -  Content-type, application/json -> we are letting API know that we are sending JSON BODY
    - Body
         - JSON
         - XML
         - ...
- Response Part
    - Status code
         - 201 Created -> this will be the status code if you successfully send POST request
    - Headers
    - Body
         - most of the time you will have some message like success
         - and also whatever you send as a request body

=======================================================================================

AUTHORIZATION IN REST API:
AUTHENTICATION AND AUTHORIZATION


--> AUTHENTICATION -> verifies if you are who you say you are.
 - Checks for valid Credentials

--> AUTHORIZATION -> verifies if you have access/permission to certain API/actions

BookIt API -> http://cybertek-reservation-api-docs.herokuapp.com/#introduction
email = emaynell8f@google.es
password = besslebond
 token = eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo



=======================================================================================

JSON Schema -> Developers created it
   - description of Json data

{
    "id": 90,
    "name": "Bedirxan",
    "gender": "Male",
    "phone": 74563453453
}

-> Structures of The Json
id -> required, integer, up to 6 digit maybe
name -> required, String, up to 25 character
gender -> optional, 2 option
phone -> required, numbers, 10 digits

Check Schema of JSON -> https://jsonschema.net/login
JSON Schema Validator -> https://www.jsonschemavalidator.net/

=======================================================================================

--> How do you test rest API?
- I verify if each REST API endpoint is working as expected
- I send POST, PUT, GET, DELETE type of request and verify response status code and response body, header.
- I also do positive and negative testing API.
      - When I do positive testing, I send valid request parameters, valid headers, valid request json body and verify that response status code
        is 200 successful and Json response body data is also matching the expected.
      - When I do negative testing, I send invalid request parameters, or invalid headers, or invalid request json body and verify that response status code
        is not 200 and json response body contains error message.


Documentation of API -> Swagger UI
Manual Testing of API -> POSTMAN, Sopa UI
Automated Testing of API -> Rest-Assured Library in Java

















 */