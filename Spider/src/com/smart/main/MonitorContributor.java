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

public class MonitorContributor {
	public static void main(String[] args) {
		//所有contributor的url
		String url = "http://10.60.45.67:7474/db/data/label/Contributor/nodes";
		System.out.println("111111111111111111111111");
		
		//一个contributor有哪些项目的url(type="contribute")
		//String url = "http://10.60.45.67:7474/db/data/node/31167/relationships/out";
		
		//一个项目具体的repository的url
		//String url = "http://10.60.45.67:7474/db/data/node/31169";
        HttpClient httpClient = new HttpClient();
        String encoding = new BASE64Encoder().encode("neo4j:123456".getBytes());
        GetMethod getMethod = new GetMethod(url);
        System.out.println("222222222222");
        //getMethod.setDoAuthentication(true);
        getMethod.setRequestHeader("Authorization","Basic "+encoding);
        getMethod.setRequestHeader("Accept","application/json;charset=UTF-8");
        getMethod.setRequestHeader("Content-Type","application/json");
        
        try {
			int statusCode = httpClient.executeMethod(getMethod);
			System.out.println("3333333333333333333333");
//	        System.out.println("response=" + getMethod.getResponseBodyAsString());

	        JSONArray jsonArray = new JSONArray(getMethod.getResponseBodyAsString());
	        System.out.println("444444444444444444444");
	        int projects_number = 0;
	        Map<Integer,Integer> projects_statistics = new HashMap<Integer,Integer>();
	        System.out.println(jsonArray.length());
	        
	        for(int i=0;i<jsonArray.length();i++){
	        	String relationships_url = jsonArray.getJSONObject(i).getString("outgoing_relationships");
	            HttpClient httpClient1 = new HttpClient();
	            GetMethod getMethod1 = new GetMethod(relationships_url);
	            //getMethod.setDoAuthentication(true);
	            getMethod1.setRequestHeader("Authorization","Basic "+encoding);
	            getMethod1.setRequestHeader("Accept","application/json;charset=UTF-8");
	            getMethod1.setRequestHeader("Content-Type","application/json");
	            httpClient1.executeMethod(getMethod1);
	            
	            JSONArray jsonArray1 = new JSONArray(getMethod1.getResponseBodyAsString());
	            int projects_length = jsonArray1.length();
	            
	        	if(projects_statistics.containsKey(projects_length)){
	        		projects_number = projects_statistics.get(projects_length);
	        		projects_number++; 
	        		projects_statistics.put(projects_length, projects_number);
	        	}else{
	        		projects_number = 1;
	        		projects_statistics.put(projects_length, projects_number);
	        	}
	        	getMethod1.releaseConnection();
	        	System.out.println(projects_length + ": "+ projects_number);
	        }
	        
	        Iterator<Integer> iter = projects_statistics.keySet().iterator();
	        while(iter.hasNext()){
	        	int key = iter.next();
//	        	System.out.println(key);
	        	int value = projects_statistics.get(key);
	        	System.out.println("There are "+ value + " contributor(s),which own "+ key + " project(s).");
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
