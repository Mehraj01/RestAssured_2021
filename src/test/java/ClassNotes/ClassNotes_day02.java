package ClassNotes;
/*

DAY 2 :

--> Postman is the client tool we are using to send api request and get response

--> tools to send request and get response
	curl  --- command line client to send http request
	SoapUI --  desktop client for testing both soap and rest apis
	Katalon Studio --- can be used for desktop , mobile , api ..


--> PUT vs PATCH
	 compared to PUT , patch is mostly used for partial update


--> Query Parameter -> usually used to filter the result
		represented as  key value pair right after the ?
		https://www.google.com/search?q=cybertekchool


--> GET /api/spartans/search?gender=Male&nameContains=li
	if we have more than one query parameter
		& is used to connect them

--> Path Parameter|variable
	/api/spartans/{id}  /api/spartans/:id
	It's used to identify single resource amonth list of resources
	in above example
		the id is the path parameter to identify single spartan



--> Request
		 Request URL (ENDPOINT)
		 	http://54.158.178.13:8000/api/spartans

--> HTTP Methods|Verb  : GET POST PUT DELETE PATCH

--> HEADER  : key value pair
	 - Accept , Content-Type

--> Query Parameters : key value pair right after ?
		 	/api/spartans/search?gender=Male&nameContains=li
		 	mostly used to filter out the result according to
		 	the cirteria provided here

--> Path Parameters|Variable
		 	/api/spartans/{id}  /api/spartans/:id
		 	used to identify single resource among list of resources

--> Request Body | Payload
	the data you are sending to the server


--> Response
	- STATUS CODE
	- HEADER
    - RESPONSE BODY | Payload
				the data we received from the server

--> Swagger is one of many documenting tool for Rest API
	It provide options for try things out so you can directly make request and get response from the docs





------ Please open new collection called ZipCollection

		Here is the documentation : https://zippopotam.us/
		You have 2 ENDPOINTs
		 for getting city data according to country / zipcode
		 for getting zip code info according to country / city
		 example requests
		 api.zippopotam.us/us/90210
		 api.zippopotam.us/us/ma/belmont

		 Create 2 request (Zip to City and City to Zip)



---  TRY OUT GOT API
https://api.got.show/api/characters
https://api.got.show/api/general/characters/:name

---  TRY OUT BREAKING BAD API
https://breakingbadapi.com/documentation



--> Postman Variables :
-------
just like variables in Java
we can create variables in postman to reuse the value
with friendly name or make it more dynamic



--> There are different type of variables with different scope :

- global variables :
	accessible everywhere in same workspace

- Environment variables :
	accessible in selected environment

	postman allow you to create
		set of same variables grouped under certain name you specify
		knownn as environment

	use case of this in our situation
		we want to run same requests for Spartan Rest API
			in different IP addresses
		for example :
			  QA_IP_ADDRESS:8000/api/spartans
			  DEV_IP_ADDRESS:8000/api/spartans

		second half of the URL is the same for all environments
		the only thing change is the IP Address
		so we saved those URLs under different
			environments we created in Postman
		so we can run same request for different environments
			just by selecting different env from the list

	for example :
		QA
			base_url = QA_IP_ADDRESS:8000/api/spartans

		Dev
			base_url = QA_IP_ADDRESS:8000/api/spartans

	they must have same variables name so you can switch without an issue


	Task X :
		replace first part of all request URL with {{base_url}}



-------------
	 if you have a value that you want to store and it's the same value
	 no matter what environment you have , global variables are good option

	 if you have a value that change according to different environments
	 you are working on (like URL, username, passwords , or any env specific data)
	 then environment variable is good option


	 ---- add a global variable
	    called acceptHeader or myHeader
	    set the value as  application/xml

	 go to your Get All Spartans request
	 add a header Accept
	 for value use the global variable you just created





--> Collection variables
	accessible only within the collection
	the way we access is exactly the same as env , global variables
	we use {{ the variavble name here}} to access


	if you have values that you want to parametrize only for this collection
	rather than sharing among different collections in your workspace
	collection variable is good choice
	When exporting the collection to share with others
	all the collection variables will be exported along with it
	since it's the part of the entire collection

	environments and global variables will not be exported along with it
	it has to be a seprate process.



We can refer above 3 type of variables anywhere
using {{YOUR VARIABLES NAME HERE }}
and it will be replaced with the value of the variable



---- Postman provide range of built in global variables
--- to get random data with different category
	 like first name , phone numbe r, and a lot more



----- How do we run all the request in the the collection together

	---- COLLECTION RUNNER
		--- is used to run entire collection
		-- have option to select env
		-- provide external file for data driven test


How do I share my collection and env , global variables with others

export as file and import








Local variables
	accessible within the script its created













 */