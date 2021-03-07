package ClassNotes;

/*
Day 4 :
--> local variable -- the var you created inside test section


--> Test Tab of postman where we write the code in javascript
	- either by generating or custom logic :

--> This is where we do automation in postman with some built in assertions
	- Basic of javascript :

	 printing :
	 	 console.log("stuff you want to print here") ;
	 creating variable :
	 	  you can literally use var as data type for everything
	 	  var name = " your name " ;
	 	  var age = 18 ;
	 	  var isReady = true ;

	 console.log("the value of the name is " + name) ;

	 --------->  accessing the Postman collections , environment , global variables in scirpt

	 	pm.globals.get("your global variable name here") -->> return the value
	 	pm.environment.get("your evn variable name ")

	 collections variable there are two ways to access them
	 	pm.collectionVariables.get("your variable name");
		pm.variables.get("your variable name");


		------> updating or setting the variable values

		pm.globals.set("your variable name", "new value you wanna set");
		pm.environment.set("your variable name", "new value you wanna set");
		pm.collectionVariables.set("your variable name", "new value you wanna set");


----->  How to get the entire result as json

{
    "post code": "22031",
    "country": "United States",
    "country abbreviation": "US",
    "places": [
        {
            "place name": "Fairfax",
            "longitude": "-77.2649",
            "state": "Virginia",
            "state abbreviation": "VA",
            "latitude": "38.8604"
        }
    ]
}

--> var responseJson = pm.response.json();
		- console.log("country is  " + responseJson.country) ;
		- console.log("state is " + responseJson.places[0].state) ;

but what about the post code ??? responseJson.post code ?? oh wait it did not make sense with sapce ?  so what do I do ? do below
    //  responseJson["post code"]
		console.log("post code is " + responseJson["post code"]  ) ;

		// How do we do Data driven test in Postman
		-- Use collection runner and select external file csv , json

		How does Postman know which column is used for which data in csv file ?

		postman will look for variable with same name as column name
		and set the value of the variable for that column name

		for example :
			if you have collection variable called zip
			and you have column name for your csv file as zip
			it will get the value from the zip column of csv file


		Passing one data from one request to next request

		var responseJson = pm.response.json();
		save what you want to get from response into env variable
		for example :
		{
			"token":"long token value here",
			"redirect_uri" : "long url here"
		}
		// create a variable called token in Library2 environment
		// and set the value
		pm.environment.set("token" , responseJson.token) ;

		in next request refer the variable value {{token}}


	Library2 App information
	UI Login URL : 		http://library2.cybertekschool.com/login.html
	Rest API Base_url : http://library2.cybertekschool.com/rest/v1

	environment Library2 variables :
		library_url  =  http://library2.cybertekschool.com/rest/v1
		token =

	-----  Library2 environment creadentials
	student11@library    tScBPCUr
	student12@library    UC0LC9Hj
	student13@library    zcVbvUWH
	student14@library    6SW236ia
	student15@library    Tt6UFxnY
	student16@library    VNKw282v

	EndPoints we tried

	POST {{library_url}}/login
	Headers :
		accept:application/json
		Content-Type:application/x-www-form-urlencoded
	Body : type  x-www-form-urlencoded
		email:student11@library
		password:tScBPCUr

	Response we got 200OK status
		{
			"token":"long token value here",
			"redirect_uri" : "long url here"
		}

















 */