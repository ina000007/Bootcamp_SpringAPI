var baseUrl="http://localhost:8083";

function regstrSt(){
	console.log("called");
	var name = $("#name").val();
	console.log(name);
	var age  = $('#age').val();
	var rgsNm = $('#rgsNm').val();
	
	console.log(age);
	console.log(rgsNm);
	postData(name,age,rgsNm);
}

function postData(name,age,rgsNm){
	console.log("here");
	
		jQuery.ajax({
		url: baseUrl+"/register/student",
		type: "POST",
		data: {"name": name, "age":age,"registrationNumber":rgsNm },
		dataType: "json",
		
		success: function(result) {
			console.log("post done: "+ result.registrationNumber);
			console.log(result);
		//	aFunc(); 
			//createNwDiv(result.descpt,"task",result.todo_id);
		},
		error:function(err){console.log(err);}
	}); 
}
