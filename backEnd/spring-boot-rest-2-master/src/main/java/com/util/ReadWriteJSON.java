package com.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bhaiti.kela.beans.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ReadWriteJSON {
	public static TreeSet<Long> rollNmSet = new TreeSet<>();
    public static JSONArray compData = new JSONArray();
	static {readJSON();}
	public static void main(String[] args) throws Exception {
//		readJSON();
//		writeJSON("aaa", 12, 1233,"1");
//		Student st = new Student();
//		st.setName("nie");
//		st.setAge(123);
//		st.setRegistrationNumber("123");
//		addNewStudent(st);
//		st.setName("nishant");
//		editStudent(st,"124");
		delteStudent("11");
	}

	public static void writeJson(Student st,String id) throws Exception {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(st);
		JSONParser parser = new JSONParser(); 
		JSONObject obj = (JSONObject)parser.parse(json);
		JSONObject obj1 = new JSONObject();
		obj1.put(""+id, obj);
		compData.add(obj1);
		try (FileWriter file = new FileWriter("test.json")) {

			file.write(compData.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addNewStudent(Student st) throws Exception {
		Long newId = rollNmSet.last()+1;
		rollNmSet.add(rollNmSet.last()+1);
		writeJson(st, newId+"");
		System.out.println("called....");
	}

	public static void readJSON() {
		JSONParser parser = new JSONParser();
		try {

			Object arr = parser.parse(new FileReader("test.json"));
			compData = (JSONArray) arr;
			for (int indx = 0; indx < compData.size(); indx++) {
				JSONObject uiqObj = (JSONObject) compData.get(indx);
				String key =new TreeSet<String>(uiqObj.keySet()).last();
				rollNmSet.add(Long.parseLong(key));
				JSONObject jsonObject = (JSONObject)uiqObj.get(key);
				String name = (String) jsonObject.get("name");
				long age = (Long) jsonObject.get("age");
				String rgsNm = (String)jsonObject.get("registrationNumber");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public static void editStudent(Student st,String id) throws Exception {
		getObjById(id);
		writeJson(st,id);
	}
	public static String delteStudent(String id) throws Exception {
		getObjById(id);
		try (FileWriter file = new FileWriter("test.json")) {

			file.write(compData.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		compData=new JSONArray();
		readJSON();
		return "deleted";
	}
	public static JSONObject getObjById(String id) {
		JSONObject obj=null;
		JSONObject temp =null;
		for(int indx=0;indx<compData.size();indx++) {
			obj = (JSONObject)compData.get(indx);
			String uniId = new TreeSet<String>(obj.keySet()).last();
			if(uniId.equals(id)) {
				temp=obj;
				obj.remove(uniId);
				compData.remove(indx);
				return temp;
				}
		}
		return obj;
	}
}
