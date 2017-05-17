package com.smart.main.statistics;

import com.smart.main.testJFreeChart.BarChart1;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import sun.misc.BASE64Encoder;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class MonitorRepository {

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
	        System.out.println("response=" + getMethod.getResponseBodyAsString());
	        
	        JSONArray jsonArray = new JSONArray(getMethod.getResponseBodyAsString());

			//数据去重�?��
			Map<String,JSONObject> map = new HashMap<String, JSONObject>();
			for(int j =0;j<jsonArray.length();j++){
				JSONObject json = jsonArray.getJSONObject(j);
				String projectname = String.valueOf(json.getJSONObject("data").get("ProjectName"));
				map.put(projectname,json);
			}
			JSONArray jsonArray2 = new JSONArray ();
			for(Map.Entry<String, JSONObject> me:map.entrySet()){
				JSONObject json = me.getValue();
				jsonArray2.put(json);
			}//数据去重结束

 	        System.out.println("The system has received "+jsonArray2.length() + " projects.");
	        
	        int contributors_number = 0;
	        Map<Integer,Integer> contributors_map = new HashMap<Integer,Integer>();
			JFrame frame=new JFrame("项目拥有开发者数据统计图");
            Set<String> contributorList = new HashSet();//库里�?��的人�?
	        for(int i=0;i<jsonArray2.length();i++){
	        	//String project_name = String.valueOf(jsonArray.getJSONObject(i).getJSONObject("data").get("ProjectName"));
	        	String contributors = String.valueOf(jsonArray2.getJSONObject(i).getJSONObject("data").get("contributors"));
				String[] contributorP = contributors.trim().split(",");
				for(int k = 0;k<contributorP.length;k++){
					if(contributorP[k].equals("")){
						continue;
					}
					contributorList.add(contributorP[k]);
				}

	        	int contributors_length = contributors.trim().split(",").length;
	        	//System.out.println(contributors_length);
	        	//System.out.println("Project "+project_name+" contributors is "+contributors+" "+contributors_length);
	        	if(contributors_length==1&&contributors.equals("")){
	        		contributors_length = 0;
	        	}
	        	if(contributors_map.containsKey(contributors_length)){
	        		contributors_number = contributors_map.get(contributors_length)+1;
	        	}else{
	        		contributors_number = 1;
	        	}
	        	contributors_map.put(contributors_length, contributors_number);
	        }
			System.out.println(contributorList);
			System.out.println(contributorList.size());
			Map<String,Integer> contributorsCountMap = new HashMap<String, Integer>();
			for(String i :contributorList){

				contributorsCountMap.put(i, Collections.frequency(contributorList,i));
			}
			System.out.println(contributorsCountMap);
			Map<Integer,Integer> projectStatistic = new HashMap<Integer, Integer>();

			int statistic_number =0;

			for(Map.Entry<String, Integer> cc:contributorsCountMap.entrySet()){
				if(projectStatistic.containsKey(cc.getValue())){
					statistic_number = projectStatistic.get(cc.getValue())+1;
				}else{
					statistic_number = 1;
				}
				projectStatistic.put(cc.getValue(),statistic_number);

			}
			System.out.println(projectStatistic);
			//1=4089,代表有4089人贡献1个项目


	        Iterator<Integer> iter = contributors_map.keySet().iterator();
	        while(iter.hasNext()){
	        	int key = iter.next();
//	        	System.out.println(key);
	        	int value = contributors_map.get(key);
	        	System.out.println("There are "+ value + " project(s),which owns "+ key + " contributor(s)");
	        }
	        System.out.println("------------------");
	        getMethod.releaseConnection();
			frame.add(new BarChart1(contributors_map).getChartPanel());
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
