package com.bhaiti.kela.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import com.bhaiti.kela.beans.StudentRegistration;
import com.util.ReadWriteJSON;

@Controller
public class StudentDeleteController {
	@CrossOrigin(origins = "http://localhost:8000")
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/student/{regdNum}")

	@ResponseBody
	public String deleteStudentRecord(@PathVariable("regdNum") String regdNum) throws Exception {
		System.out.println("In deleteStudentRecord");
		return ReadWriteJSON.delteStudent(regdNum);
	}

}
