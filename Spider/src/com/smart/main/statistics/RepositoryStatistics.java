package com.smart.main.statistics;

import com.smart.main.testJFreeChart.BarChart1_forRepositoryStatistics;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import sun.misc.BASE64Encoder;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RepositoryStatistics {
	public static void main(String[] args) {
		String url = "http://10.60.45.67:7474/db/data/label/Repository/nodes";

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
	        System.out.println("The system has received "+jsonArray.length() + " projects in all.");

			JFrame frame=new JFrame("项目数据统计");
	        int count_notstamp = 0;
	        int count_stamp = 0;
	        Set<String> projectSet = new HashSet();
	        for(int i=0;i<jsonArray.length();i++){
	        	JSONObject data = jsonArray.getJSONObject(i).getJSONObject("data");
	        	String project_name = String.valueOf(data.get("ProjectName"));
	        	projectSet.add(project_name);
	        	if(data.has("timeStamp")){
	        		count_stamp ++;
	        	}else{
	        		count_notstamp++;
	        	}
	        }
	        
	        //System.out.println("The system has received notstamp "+count_notstamp + " projects.");
	        System.out.println("The system has received stamp "+count_stamp + " projects.");
	        System.out.println("The system has received "+projectSet.size() + " delete duplicate projects.");

			System.out.println("------------------");
	        
//	        
//	        int contributors_number = 0;
//	        Map<Integer,Integer> contributors_map = new HashMap<Integer,Integer>();
//	        for(int i=0;i<jsonArray.length();i++){
//	        	//String project_name = String.valueOf(jsonArray.getJSONObject(i).getJSONObject("data").get("ProjectName"));
//	        	String contributors = String.valueOf(jsonArray.getJSONObject(i).getJSONObject("data").get("contributors"));
//	        	int contributors_length = contributors.trim().split(",").length;
//	        	//System.out.println(contributors_length);
//	        	//System.out.println("Project "+project_name+" contributors is "+contributors+" "+contributors_length);
//	        	if(contributors_length==1&&contributors.equals("")){
//	        		contributors_length = 0;
//	        	}
//	        	if(contributors_map.containsKey(contributors_length)){
//	        		contributors_number = contributors_map.get(contributors_length)+1;
//	        	}else{
//	        		contributors_number = 1;
//	        	}
//	        	contributors_map.put(contributors_length, contributors_number);
//	        }
//	        
//	        Iterator<Integer> iter = contributors_map.keySet().iterator();
//	        while(iter.hasNext()){
//	        	int key = iter.next();
////	        	System.out.println(key);
//	        	int value = contributors_map.get(key);
//	        	System.out.println("There are "+ value + " project(s),which owns "+ key + " contributor(s)");
//	        }
	        System.out.println("------------------");
	        getMethod.releaseConnection();
			frame.add(new BarChart1_forRepositoryStatistics(count_stamp,projectSet.size()).getChartPanel());
			//frame.add(new TimeSeriesChart(contributors_map).getChartPanel());
			frame.setBounds(50, 50, 1000, 1000);
			frame.setVisible(true);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
