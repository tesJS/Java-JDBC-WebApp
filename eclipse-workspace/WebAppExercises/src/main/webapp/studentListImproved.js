
let tablebody = document.querySelector("tbody");



getDataFromServer("http://localhost:8080/WebAppExercises/students",showStudentList);



function showStudentList(students) {

	for (var student of students) {

		lastNames += "<tr>  <td>" + student.id + "</td>  <td> " + student.lastname + "</td> <td>" + student.firstname + "</td> <td>" + student.streetaddress + "</td>    <td>" + student.postcode + "</td>    <td>" + student.postoffice + "</td></tr > ";
		tablebody.insertAdjacentHTML("afterbegin",lastNames);
	}
}

