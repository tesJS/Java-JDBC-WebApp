

let studentHtmlDetails = "";
let deleteUrl = "http://localhost:8080/WebAppExercises/deleteStudent";
let tablebody = document.querySelector("tbody");

function deleteStudent(id) {
	var requestParameter = "id=" + id;
	console.log(requestParameter);
	requestParameter = requestParameter.trim();
	if (window.confirm("Delete student " + id + "?")) {
		postDataToServer(deleteUrl, requestParameter, processDeleteResponse);
	}

	else return;


}


function addStudent() {
	location.replace("/WebAppExercises/studentAdd.html");

}

//not implemented yet
function updateStudent(id) {
	location.replace("/WebAppExercises/studentUpdate.html" + "?id=" + id);

}

function createUpdateDeleteLinks(studentId) {
	return "<span class='update-link' onclick='updateStudent(" + studentId + ");'>Update</span>" +
		"&nbsp;&nbsp;" +
		"<span class='delete-link' onclick='deleteStudent(" + studentId + ");'>Delete</span>";
}

function main() {
	fetch("http://localhost:8080/WebAppExercises/students")
		.then((response) => {
			// 1.
			if (response.ok) {

				// 2.
				return response.json(); // 3.
			} else {
				throw "HTTP status code is " + response.status;
			}
		})
		.then((students) => {

			printStudents(students) // 5.

			tablebody.innerHTML = studentHtmlDetails;

		})

		.catch((errorText) => console.error("Fetching Students failed: " + errorText)); // 6.
}
function printStudents(students) {

	for (var student of students) {

		studentHtmlDetails += `<tr>  
		    <td>${student.id} </td>
		    <td>${student.firstname}</td>
			<td>${student.lastname}</td>			
			<td>${student.streetaddress}</td>
			<td>${student.postcode}</td>
			<td>${student.postoffice}</td>
			<td>${createUpdateDeleteLinks(student.id)} </td>
		   </tr>`;

	}


}
main();


//To handlie the deletion of students
function postDataToServer(url, requestParameters, processDeleteResponse) {
	try {
		fetch(url, {
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
	catch (errorText) { alert("postDataToServer failed: " + errorText) }
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









