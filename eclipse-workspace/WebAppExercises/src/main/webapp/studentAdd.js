

let button = document.querySelector("#submit");

function processAddResponse(result){
	if(result===1){
		alert("Student data added!");
	}
	else {
		alert("Cannot add the student!");
	}
}

button.onclick = () => {
	var url = "http://localhost:8080/WebAppExercises/addStudent";
	
	var form = document.forms["formStudent"];
	
	var requestParameters = 
	 "id=" + form["id"].value +
	 "&firstname=" + form["firstname"].value +
	 "&lastname=" + form["lastname"].value +
	 "&streetaddress=" + form["streetaddress"].value+
	 "&postcode=" + form["postcode"].value+
	 "&postoffice=" + form["postoffice"].value;
	 
  	postDataToServer(url,requestParameters, processAddResponse);
  	
  	
};






