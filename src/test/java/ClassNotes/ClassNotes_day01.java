package ClassNotes;
/*


54.174.216.245
100.25.192.231
54.158.178.13


-> What do I get from The instance i created from
	    - Cybertek_latest_no_auth AMI

	Simple Spartan App (does not require password)
	HR Databse pre-installed
	HR Databse Rest Api that we will practice (ORDS)
	Jenkins

	--- remember -- it does not have library app we saw on Friday
			--- everyone was accessing same server with same IP


The spartan app Request URLS from the documents
Hello endpoint
	http://52.71.242.164:8000/api/hello

Spartan endpoints
	http://52.71.242.164:8000/api/spartans


This URL took us to the UI to give us nice htmp page
http://52.71.242.164:8000/spartans

This URL returned pure data in a format called json
http://52.71.242.164:8000/api/spartans

this is our entry point for api request in this application
http://52.71.242.164:8000/api/


What is JSON :
	JavaScript Object Notation
	Its a key value pair
	its popular light-weight way of transfering data

	for example :
	Lets try to create a json for Person Object
			with name , age , isMarried , gender

		Its ket value pair
		the key is always String and need to be in quotation
		value can be :
			String
			Number
			Boolean
			null
			array
			another json object


		This is one json with 5 fields
		each key value pair should be separated by comma
		{
			"name" 		: "Anna",
			"age" 		: 18  ,
			"isMarried" : false ,
			"gender"	: "female",
			"company"	: null
		}

		Task 1 :
		Creata a Car Json object with these fields
		make , year , model
		{
			"make" : "Toyoya",
			"year" : 2020 ,
			"model": "Pirus"
		}

		// what about Array
		arrays are defined using [ ]

		my favorite numbers : [ 1, 4, 6, 8, 9]


		[1 , 2]

		[carObject1 , carObject2]

		how to create a array of 2 car
		This is Json Array that have 2 car json Object
		[
			{
				"make" : "Toyoya",
				"year" : 2020 ,
				"model": "Pirus"
			} ,

			{
				"make"   : "Honda",
				"year"   : 2018 ,
				"model"  : "CRV"
			}
		]


--->  Postman is a Client tool
      - to work with APIs to send requests get response
      - Organize the requests

--> Collection in Postman is a top level folder -> to store your requests so we can run any time

--> Header : exists in both request and response
	  - is metadata about the request
	  - It's a key value pair

--> few common header type is:
	  ->  Accept header:  to specify what format you want to get your result only if the api itself support.
	  ->  Content Type:  in request header -> to specify what kind of data we are sending to the server
      ->  Content Type: in response header : what was the contenttype we got as response



--> HTTP Methods :
	  - GET -> getting data in the server
	  - POST -> adding data to the server
			- we need to provide the data in the format
			- the document specify
			- and tell exactly what type if data we are sending
			- defined by Content-Type Header
			- Common status code for successful POST is
				201 Created -- you will have response body
	  - PUT
		 	Put is used to update the exsisting data from the server
		 	difference between PUT and POST
		 	 POST is adding new data
		 	 	so the URL will be /api/spartans
		 	 PUT is updating existing data
		 	 	so the URL will be /api/spartan/validIDNumberHere

		 	 provide updated data
		 	 provide the contenttype of the data you are sending to update

		 	 common status code for successful PUT request
		 	  is 204 NO-CONTENT , it has no response body
		 	  204 means it was updated successfully


		  DELETE

		  	Delete method is used to delete the data from the server
		  	Request URL will  /api/spartan/theValidID

		  	common status code for successful Delete request
		 	  is 204 NO-CONTENT , it has no response body
		 	  204 means it was deleted successfully


-> Response :

		Status code

			2xx :  SUCCESS
				200 OK : success
				201 CREATED : successfully added data
				204 NO-CONTENT successfully updated or deleted

			4xx :  CLIENT SIDE ERROR
				400 BAD REQUEST : bad data being sent
				404 NOT FOUND : the resource does not exists at that location
				405 METHOD NOT ALLOWED :
						DELETE /api/spartans -->> 405 error
				406 NOT ACCEPTABLE
				415 Unsupported Media type
					if you forget to specify the Content-Type
					of Post request body, it will see it as plain text
					and it will throw this error if it does not support

			5xx : Server side error
				500 internal service error
				If the server do not have any mapping of the url
				requested to handle the action -->> 500

		Response Header

		Body



==> https://httpstatuses.com/



































 */