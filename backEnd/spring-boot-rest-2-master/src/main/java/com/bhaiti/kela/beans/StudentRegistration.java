package com.bhaiti.kela.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.util.ReadWriteJSON;

public class StudentRegistration {
	
	private List<Student> studentRecords;
	
	private static StudentRegistration stdregd = null;
	
	private StudentRegistration(){
		studentRecords = new ArrayList<Student>();
		ReadWriteJSON.readJSON();
		JSONArray arr =ReadWriteJSON.compData;
		for(int index=0;index<arr.size();index++) {
			JSONObject obj = (JSONObject)arr.get(index);
			String key = new TreeSet<String>(obj.keySet()).last();
			JSONObject jObj = (JSONObject)obj.get(key);
			Student st = new Student();
			st.setAge((Long)jObj.get("age"));
			st.setName((String)jObj.get("name"));
			st.setRegistrationNumber((String)jObj.get("registrationNumber"));
			st.setUniqId(key);
			studentRecords.add(st);
		}
	}
	
	public static StudentRegistration getInstance() {
		stdregd = new StudentRegistration();
		
		return stdregd;
//		if(stdregd == null) {
//			stdregd = new StudentRegistration();
//			return stdregd;
//		}
//		else {
//			return stdregd;
//		}
	}
	
	public void add(Student std) throws Exception {
//		std.setUniqId(""+( ReadWriteJSON.rollNmSet.last()+1));
//		ReadWriteJSON.addNewStudent(std);
		studentRecords.add(std);
	}
	
	public String upDateStudent(Student std) {
		
		for(int i=0; i<studentRecords.size(); i++)
        {
            Student stdn = studentRecords.get(i);
            System.out.println(stdn.getRegistrationNumber());
            System.out.println(std.getRegistrationNumber());
            if(stdn.getRegistrationNumber().equals(std.getRegistrationNumber())) {
            	studentRecords.set(i, std);//update the new record
            	return "Update successful";
            }
        }
		
		return "Update un-successful";
		
	}
	
	public String deleteStudent(String registrationNumber) {
		
		for(int i=0; i<studentRecords.size(); i++)
        {
            Student stdn = studentRecords.get(i);
            if(stdn.getRegistrationNumber().equals(registrationNumber)) {
            	studentRecords.remove(i);//update the new record
            	return "Delete successful";
            }
        }
		
		return "Delete un-successful";
		
	}

	public List<Student> getStudentRecords() {
		return studentRecords;
	}

}
