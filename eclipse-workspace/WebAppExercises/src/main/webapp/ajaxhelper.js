 var form = document.forms["formStudent"];
 
 var requestParameters = 
	 "id=" + form["id"].value +
	 "&firstname=" + form["firstname"].value +
	 "&lastname=" + form["lastname"].value +
	 "&streetaddress=" + form["streetaddress"].value+
	 "&postcode=" + form["postcode"].value;
	 "&postoffice=" + form["postoffice"].value;
	 



function postDataToServer(url, requestParameters, processAddResponse) {
	
	try{
		
		fetch(  url,    {
		      method: "POST",
		      headers: {
		        "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
		        'Cache-Control': 'no-cache'
		      },
		      body: requestParameters,
		    })
		    .then((response) => {
		      
		      if (response.ok) {
		        return response.json();
		       
		      }else {
		        throw "HTTP status code is " + response.status;
		      }
		      
		    }) 
		      .then((data) => {
				console.log(data);
		        processAddResponse(data.errorCode);
		      });
	}
		  
  	  catch(errorText) {alert("postDataToServer failed: " + errorText);}
  	  finally {
			console.log("Just before redirecting!");
			location.href = 'http://localhost:8080/WebAppExercises/studentList.html';
		};
}


