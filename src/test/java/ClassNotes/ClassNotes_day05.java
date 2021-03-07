package ClassNotes;
/*


Day 5 :

	quick review
	Authentication and Authorization

	RestAssured Library light start


	----------------------
	How do you handle dymanic data in your test
	for example data exists in one env and does not exist in another
	or data expire once you use it in one test (delete)

	 1,  get the data from database so we can work with latest data

	 2,  create your own data and use that data for the test

	 	in order to test Get One Spartan endpoint
	 		we need a valid ID , each env can have different IDs
	 		or that data might get deleted and test will fail because of data error

	 	what we did is , created our own data
	 			using Add 1 Spartan request -->>
	 			save the generated ID
	 			use that ID for the rest of the test
	 			and optionally clean up the data after the test


------ Authentication and Authorization

		Authentication :
				telling the system who you are
				by providing username and password.


		Authorization :
				things you can do according to who you are



 ------  few ways of Authorization:
 		Basic Auth --
 			 providing username and password for each and every request you make

 		Token based
 			generate a long token just one time
 			and use that for the rest of the request

 			similar to the visitor tag you get when you go to certain restricted areas


 			API KEY ---


 			Bearer Token --


 	Spartan app with basic auth IPs for class

 		http://54.160.106.84:8000/
		http://100.24.235.129:8000/
		http://23.23.75.140:8000/

 	Now we are about to see basic auth in action
 	create a new collection with a name you prefer
 	use this ID address for this collection  52.73.110.253

 	add a request for get one spartan
 	your endpoint will be 52.73.110.253:8000/api/spartan/1
 	and click send

 	we got 401 Unauthorized Response  as expected
 	after providing usename and password under Authorization tab
 	by selecting basic auth , we got successfull 200ok response


 	---- 3 type of users in this app with different authority

 	admin   (username password is :  admin admin)
 		:  Create , Read , Update , Delete

 	editor  (username password is :  editor editor)
 		:  Create , Read , Update

 	user    (username password is :  user user)
 		:  Read


    Add one more request :

    	POST 54.160.106.84:8000/api/spartans
    	{
    		"name":"Adam",
    		"gender":"Male",
    		"phone":1234567890
    	}

    first do not provide any username and password see the result   401
    then provide editor username password see the result 			201
    lastly provide user/user  as credentials and see the result 	403

    401 Unauthorized  VS 403 Forbidden (hot interview question)

    401 Unauthorized meaning
    	you did not provide a credential or correct credentials to send the request
    	you are encorouranged to try again with correct credentials

    403 Forbidden
    	even you did provide valid credentials
    		you are not authorized to take this action by who you are
    		because you do not have authority or permission
    	Do not bother to try again !


    rest api requests are stateless meating
     each and every request seen as new request for the server
     server does not save any information about the request
     just like when you order something from coffee shop ,
     	you have to tell the cashier what you want each and every time

    That's why we have been providing username and password for each and every
    request for spartan endpoints , unlike UI it does not remember the session.


    ---- Add a delete request to your collection
    	try out with different users
    	first send as is without basic auth   -->> 401
    	then send it with basic auth  editor  --->> 403
    	then send it as user  ---->> 403

    --- add one test for this request under test tab
    		to check status code 403 when providing user/user credentials

    Testing Role Bases Access Control
    	  testing user with correct authority can take certain actions

    As homework :


    Create a collection for this homework
    Create 3 folders  adminAction , editorAction , userActon

    admin --
    		test admin can add data , read data , update , delete data

    editor --
    		test editor can add data , read data , update
    		BUT CAN NOT DELETE DATA
    user  ---
    		test user can read data
    		BUT CAN NOT CREATE, UPDATE , DELETE DATA

    you may check the status code 403 for all




----   One example Public Api we are trying
 		base URL is    http://www.omdbapi.com
 		it require to provide api key in query paramter
 		you can request one from the website
 		or you can just this one for now 26aa5b74

 		it has few capanbility to search movies by providing key value pair
 		in query paramter as the document suggests

 		Here is the how one request to search for movie looks like
 		http://www.omdbapi.com/?apikey=26aa5b74&t=Boss Baby


 		few ways for api developers to specify what format user can get the data xml , json
 		in spartan we saw
 		1,  Accept header --  application json
 		in open movie db api
 		2,  query paramter  -- r = xml

 		3,  in the request URL itself  someurl.json (will see this later )


 		--- another API to try with API key  : https://newsapi.org/


 		Postman's Authorization tab support adding api key
 		from the dropdown select api key
 		provide your keyname according to the doc
 		in movie api , keyname is apikey and value is the one we got in email
 		IMPORTANTLY : Follow the document to add to header or query parameter



 	Library app use token to authorize the request
 	the token need to be added in the header section
 	the keyname is x-libraray-token ,
 	the value is what you generated by making post request to /login endpoint

 	The POST /login request has username and password as body
 	when you send the request it generate long token that has user's identity
 	and authority encoded ,
 	a token type known as JWT (Json Web Token) token

 	Each and every request we make need a header with token attached
 	or it will return 401 Unauthorized

 	Challenge :  add this header by using Authorization Tab rather than directly adding it


 	add 2 more requests under your collection

 	GET /get_book_categories

 	GET /get_all_users


 	add a folder called librarianAction
 	add all your requests other than /login



 	BookIT API for cybertek study room booking website

 	This API use Bearer token for authorization

	the endpoint to generate token
	it take username and password in query parameter
	as used in this request

	https://cybertek-reservation-api-qa.herokuapp.com/sign?email=jalabaster7f@drupal.org&password=terimapam

	The rest of the request use this token generated.

	Bearer token you provide under Postman will get added
	to the Authorization Header under header section
	the format is :

	Header name  : Authorization
	Value        : Bearer YourLongTokenGoesHere











https://cybertek-reservation-api-qa.herokuapp.com/?email=jalabaster7f@drupal.org&password=terimapam
https://cybertek-reservation-api-qa.herokuapp.com/sign?email=jalabaster7f@drupal.org&password=terimapam




 */