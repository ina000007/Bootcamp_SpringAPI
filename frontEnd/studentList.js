var baseUrl="http://localhost:8083";
var myData=[];
function getData(){
	var url = baseUrl+"/student/allstudent";
	$.getJSON( url, {
		crossDomain: true,
		format: "json"
	})
	.done(function( data ) {
		myData = data;
		printData(myData);
		console.log("here "+myData);
		
	});
}
function printData(myData){
			console.log("json object : "+JSON.stringify(myData, null, 2));
			console.log("here "+myData.length);
			for(var index=0;index<myData.length;index++){
			console.log("data "+myData[index].registrationNumber);
			crtRow(myData[index].uniqId,myData[index].name,myData[index].age,myData[index].registrationNumber);
			console.log("break");
		}
}
function crtRow(id,name,age,rgsNm){
	console.log(id+" "+name);
	var row = document.createElement("tr");
	var nameTd = document.createElement("td");
	var ageTd = document.createElement("td");
	var rgsNmTd = document.createElement("td");

	row.id="row_"+id;
	nameTd.id = "name_"+id;
	ageTd.id = "age_"+id;
	rgsNmTd.id = "rgsNm_"+id;
	console.log("name: "+name);
	nameTd.innerText = name;
	ageTd.innerText = age;
	rgsNmTd.innerText = rgsNm;

	// newTskCat.onclick = fltrData;
	document.getElementById("tableBody").appendChild(row);
	document.getElementById("row_"+id).appendChild(nameTd);
	document.getElementById("row_"+id).appendChild(ageTd);
	document.getElementById("row_"+id).appendChild(rgsNmTd);
	// document.getElementById("tableBody").appendChild(row);
	var editbtn = document.createElement("button");
	var deletebtn = document.createElement("button");
	editbtn.className = "btn edit";
	deletebtn.className = "btn del";
	deletebtn.id = "delete_"+id; 
	editbtn.id = "edit_"+id;
	deletebtn.onclick = deleteRow;
	editbtn.onclick = editTsk;
	// document.getElementById("row_"+id).appendChild(editbtn);
	document.getElementById("row_"+id).appendChild(deletebtn);

}
function deleteRow(){
	
	var rowId="row_"+this.id.split("_")[1];
	document.getElementById(rowId).style.display ="none";
	delData(rowId);
}

function delData(rowId){
	var id=rowId.split("_")[1];
	$.ajax({
		url: baseUrl+"/delete/student/"+id,
		type: 'DELETE',
		success: function(result) {
		}
	});
}

function editTsk(){
	var id=this.id.split("_")[1];
	window.location.href="editStudent.";
	alert(id);
}