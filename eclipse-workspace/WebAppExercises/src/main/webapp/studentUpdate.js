

var url = "";
var form = document.forms["formStudent"];
let updateButton = document.querySelector("#update");
let cancelButton = document.querySelector("#cancel");





updateButton.onclick = (event) => {
	
	event.preventDefault();

	var url = "http://localhost:8080/WebAppExercises/updateStudent";
	var requestParameters =
		"id=" + form["id"].value +
		"&firstname=" + form["firstname"].value +
		"&lastname=" + form["lastname"].value +
		"&streetaddress=" + form["streetaddress"].value +
		"&postcode=" + form["postcode"].value +
		"&postoffice=" + form["postoffice"].value;

	console.log("Url requestParameters");
	console.log(url);
	console.log(requestParameters);

	postDataToServer(url, requestParameters, processUpdateResponse);


};
cancelButton.onclick = () => {

	location.replace("/WebAppExercises/studentList.html");


};



function postDataToServer(url, requestParameters, processUpdateResponse) {
	try {

		fetch(url, {
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

				} else {
					throw "HTTP status code is " + response.status;
				}

			})
			.then((data) => {
				console.log(data);
				processUpdateResponse(data.errorCode);
			});
	}

	catch (errorText) { alert("postDataToServer failed: " + errorText); }
	finally {
		console.log("Just before redirecting!");
		location.href = 'http://localhost:8080/WebAppExercises/studentList.html';
	};
}

function processUpdateResponse(result) {
	if (result === 1) {
		alert("Student data updated!");
	}
	else {
		alert("Cannot update the student!");
	}
}

function init() {
	window.onload = () => {
		var url = "http://localhost:8080/WebAppExercises/students";
		var urlParams = new URLSearchParams(location.search);
		var id = urlParams.get('id');
		console.log(url);
		console.log(id);
		getDataFromServer(url, id, getStudent);
		function getDataFromServer(url, id, getStudent) {
			fetch(url)
				.then((response) => {
					// 1.
					if (response.ok) {
						console.log(response);
						// 2.
						return response.json(); // 3.
					} else {
						throw "HTTP status code is " + response.status;
					}
				})
				.then((students) => {

					for (var student of students) {
						if (student.id == id) {
							console.log(students);
							getStudent(student)
						}
						// 5.
					}


				})

				.catch((errorText) => console.error("Fetching Student failed: " + errorText)); // 6.
		}

		function getStudent(student) {

			form["id"].disabled = true;
			form["id"].value = student.id;
			form["firstname"].value = student.firstname;
			form["lastname"].value = student.lastname;
			form["streetaddress"].value = student.streetaddress;
			form["postcode"].value = student.postcode;
			form["postoffice"].value = student.postoffice;

		}

	};

}

init();


