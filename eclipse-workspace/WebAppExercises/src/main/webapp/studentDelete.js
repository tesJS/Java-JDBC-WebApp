
var button = document.querySelector("#submit");

function postDataToServer(url, requestParameters, processDeleteResponse) {
	try {
		  fetch(  url,    {
		      method: "POST",
		      headers: {
		        "Content-Type": "application/x-www-form-urlencoded",
		        'Cache-Control': 'no-cache'
		      },
		      body: requestParameters,
		    })
		    .then((response) => {
		      // 1.
		      if (response.ok) {
		        return response.json();
		       
		      } else {
		        throw "HTTP status code is " + response.status;
		      }
		    }) 
		      .then((data) => {
				console.log(data);
		        processDeleteResponse(data.errorCode);
		      })
      }
  	  catch (errorText) { alert("postDataToServer failed: " + errorText)}
  	  finally {
		console.log("Just before redirecting!");
		location.href = 'http://localhost:8080/WebAppExercises/studentList.html';
	};

 
}


function processDeleteResponse(result) {
  if (result === 1) {
    alert("Student data deleted!");
  } else {
    alert("Cannot delete the student!");
  }
}

button.onclick = () => {
	var url = "http://localhost:8080/WebAppExercises/deleteStudent";
	
	var form = document.forms["formStudent"];
	var val=form["id"].value;
	var requestParameters = "id=" +val ;
  postDataToServer(url,requestParameters, processDeleteResponse);
};
