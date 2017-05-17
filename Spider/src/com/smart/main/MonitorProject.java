package com.smart.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import sun.misc.BASE64Encoder;

public class MonitorProject {
	public static void main(String[] args) {
		String url = "http://10.60.45.67:7474/db/data/label/Project/nodes";

        HttpClient httpClient = new HttpClient();
        String encoding = new BASE64Encoder().encode("neo4j:123456".getBytes());
        GetMethod getMethod = new GetMethod(url);
        //getMethod.setDoAuthentication(true);
        getMethod.setRequestHeader("Authorization","Basic "+encoding);
        getMethod.setRequestHeader("Accept","application/json;charset=UTF-8");
        getMethod.setRequestHeader("Content-Type","application/json");
        
        try {
			int statusCode = httpClient.executeMethod(getMethod);
	        //System.out.println("response=" + getMethod.getResponseBodyAsString());

	        JSONArray jsonArray = new JSONArray(getMethod.getResponseBodyAsString());
	        
	        int similar_number = 0;
	        Map<Integer,Integer> similar_statistics = new HashMap<Integer,Integer>();
	        for(int i=0;i<jsonArray.length();i++){
	        	//String project_name = String.valueOf(jsonArray.getJSONObject(i).getJSONObject("data").get("project_name"));
	        	String similar_project = String.valueOf(jsonArray.getJSONObject(i).getJSONObject("data").get("similar_projects"));
	        	int similar_length = similar_project.trim().split(" ").length;
	        	if(similar_length==1&&similar_project.equals("")){
	        		similar_length = 0;
	        		//System.out.println("Project "+project_name+" similar project is "+similar_project+" "+similar_length);
	        	}
	        	if(similar_statistics.containsKey(similar_length)){
	        		similar_number = similar_statistics.get(similar_length);
	        		similar_number++; 
	        		similar_statistics.put(similar_length, similar_number);
	        	}else{
	        		similar_number = 1;
	        		similar_statistics.put(similar_length, similar_number);
	        	}	        	
	        }
	        
	        Iterator<Integer> iter = similar_statistics.keySet().iterator();
	        while(iter.hasNext()){
	        	int key = iter.next();
//	        	System.out.println(key);
	        	int value = similar_statistics.get(key);
	        	System.out.println("There are "+ value + " project(s),which owns "+ key + " similar project(s)");
	        }
	        System.out.println("------------------");
	        getMethod.releaseConnection();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
