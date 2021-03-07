package ClassNotes;
/*

Day 3 :


	Quick review on component of request and response

	Quick review on different type of variables available in postman



	Test section of Postman to make assertion to our test

		programatically access our variables
		programatically change our variables values

		Build simple workflow :

			Add 1 Data  -->> generate new ID
				  save that ID into the variable to use for next request
			get that data just generated
			update that data you got
			delete that data to clean up

		Eventually run as one test suite using collection runner
		Data drive the entire test suite using external data (csv or json file)



API Request Response

			Request :
					Request URL | Endpoint
					HTTP Methods | Verb  : GET POST PUT PATCH DELETE

					Headers : Accept , Content-Type and so on
					Query Parameters :
						 a key value pair comes right after ? in url
						 used to filter the the result
						 /api/spartans/search?gender=Male&nameContains=ea
						 /api/characters?limit=10&offset=10

					Path variables|Parameters
						identify single resource among multiple resource
						/api/spartans/{id}
						/users/{username}
						/characters/:name

					Body | Payload :
						POST,PUT,PATCH requests have body
						the data we are sending to the server for adding/updating
						Make sure to tell the server what kind of data you are sending
						Content-Type header must be provided to indicate what kind of data

			Response

				 Status code
				 			2xx -->>  success
				 			4xx -->>  client side error
				 			5xx -->>  server side error
				 Header
				 		meta data about your response
				 		like Content-Type, Date and some other info

				 Body | Payload
				 		The response we got from the server
				 		and this where we do lots of validation

------------------------------------------------

			Postman Variables :  {{your_variable_name}}

			Collection Variables
				belong to the collection itself and only accessible within the collection

			Environmengt variables
				accessible ONLY IF that Environment is active

			Global Variables
				accessible everywhere within the same workspace




			Add 3 collection variables and give values :
				name
				gender
				phone


---------- Add another request for post ------
			--- add url as

			{{base_url}}/api/spartans

			make sure to select the type JSON in postman

			add below body
			---- DO NOT ADD ANYTHING EXTRA , THERE IS NO COMMENT IN JSON

			{
			  "name"  : "{{name}}",
			  "gender": "{{gender}}",
			  "phone": {{phone}}
			}



----   Car Json object

 myCar
 			get the
 				myCar.name
 				myCar.year
 				myCar.color
 				get the second color
 					myCar.color[1]

 				get the owner
 					myCar.owner
 				get the owner's name
 					myCar.owner.name
 					age
 					myCar.owner.age

 				passengers

 					myCar.passengers  json array is returned
 					myCar.passengers[0]  first item in the array
 					myCar.passengers[0].gender   Male

 					myCar.passengers[1].name -->> Asiya



		{
			"make" : "Toyota",
			"model" : "camry",
			"year" : 2020,
			"color" : ["white","black","brown"] ,
			"owner" : {
						 "name" : "Nahide",
						 "age"  : 18
					  } ,

		    "passengers" : [
		    					{
		    						"name" : "Muhtar",
		    						"gender" : "Male"
		    					},
		    					{
		    						"name" : "Asiya",
		    						"gender" : "Female"
		    					}

		    				]

		}



// how do you pass data between different requests in postman collection
     use Postmans variable to store the result and use it for the next request

     //  run one request  get the response
     //   use jsonPath to get the value of the field you want
     //   use that value to set the postman variable value



 How to do Data driven test in postman :
 	 Using Collection runner select the files
 	 postman support 2 type of file : csv , json file

 	it will read the file header to match the variable in collection

 	the collection variables : name , gender , phone
 	the csv file must have header as  name , gender , phone

 	That's how Postman match the value for each row




==> https://jsonpathfinder.com/

http://library2.cybertekschool.com/rest/v1

student11@library
tScBPCUr
student12@library
UC0LC9Hj
student13@library
zcVbvUWH
student14@library
6SW236ia
student15@library
Tt6UFxnY
student16@library
VNKw282v
@channel few students username password for library app

http://library2.cybertekschool.com/login.html












 */