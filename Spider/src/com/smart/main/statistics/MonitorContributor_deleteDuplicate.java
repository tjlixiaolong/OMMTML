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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MonitorContributor_deleteDuplicate {
	public static void main(String[] args) {
		String url = "http://10.60.45.67:7474/db/data/label/Contributor/nodes";
		//String url = "http://10.60.45.67:7474/db/data/node/44278";

		//String url = "http://10.60.45.67:7474/db/data/node/31167/relationships/out";

		//String url = "http://10.60.45.67:7474/db/data/node/31169";
		HttpClient httpClient = new HttpClient();
		String encoding = new BASE64Encoder().encode("neo4j:123456".getBytes());
		GetMethod getMethod = new GetMethod(url);
		//getMethod.setDoAuthentication(true);
		getMethod.setRequestHeader("Authorization", "Basic " + encoding);
		getMethod.setRequestHeader("Accept", "application/json;charset=UTF-8");
		getMethod.setRequestHeader("Content-Type", "application/json");

		try {
			int statusCode = httpClient.executeMethod(getMethod);
			//System.out.println("response=" + getMethod.getResponseBodyAsString());

			JSONArray jsonArray = new JSONArray(getMethod.getResponseBodyAsString());
			//System.out.println(jsonArray);
			int projects_number = 0;
			Map<Integer, Integer> projects_statistics = new HashMap<Integer, Integer>();
			JFrame frame = new JFrame("寮?彂鑰呭弬涓庨」鐩暟鎹粺璁″浘");
			System.out.println(jsonArray.length());

			for (int i = 0; i < jsonArray.length(); i++) {
				String relationships_url = jsonArray.getJSONObject(i).getString("outgoing_relationships");
				HttpClient httpClient1 = new HttpClient();
				GetMethod getMethod1 = new GetMethod(relationships_url);
				//getMethod.setDoAuthentication(true);
				getMethod1.setRequestHeader("Authorization", "Basic " + encoding);
				getMethod1.setRequestHeader("Accept", "application/json;charset=UTF-8");
				getMethod1.setRequestHeader("Content-Type", "application/json");
				httpClient1.executeMethod(getMethod1);
				//System.out.println("response1=" + getMethod1.getResponseBodyAsString());
				JSONArray jsonArray1 = new JSONArray(getMethod1.getResponseBodyAsString());
				//System.out.println(jsonArray1);
				Map<String, JSONObject> deleteDuplicateMap = new HashMap<String, JSONObject>();

				for (int j = 0; j < jsonArray1.length(); j++) {
					//鑾峰緱endURL
					String end_url = jsonArray1.getJSONObject(j).getString("end");
					HttpClient httpClient_end = new HttpClient();
					GetMethod getMethod_end = new GetMethod(end_url);
					getMethod_end.setRequestHeader("Authorization", "Basic " + encoding);
					getMethod_end.setRequestHeader("Accept", "application/json;charset=UTF-8");
					getMethod_end.setRequestHeader("Content-Type", "application/json");
					httpClient_end.executeMethod(getMethod_end);
					//System.out.println("response2=" + getMethod_end.getResponseBodyAsString());
					JSONObject jsonArray_end = new JSONObject(getMethod_end.getResponseBodyAsString());
					//System.out.println(jsonArray_end);

					//椤圭洰鍘婚噸寮?

					//for (int k = 0; k < jsonArray_end.length(); k++) {
						//JSONObject json = jsonArray_end.getJSONObject(k);
//                        if(jsonArray_end.getJSONObject("data").get("ProjectName")==null){
//							continue;
//						}
					String projectName = null;
					JSONObject jsonEnd = jsonArray_end.getJSONObject("data");
					if(jsonEnd.has("ProjectName")){
						projectName = String.valueOf(jsonEnd.get("ProjectName"));
					}else{
						continue;
					}

						deleteDuplicateMap.put(projectName, jsonArray_end);
					getMethod_end.releaseConnection();
					}
					JSONArray jsonArray2 = new JSONArray();
					for (Map.Entry<String, JSONObject> me : deleteDuplicateMap.entrySet()) {
						JSONObject json = me.getValue();
						jsonArray2.put(json);
					}//椤圭洰鍘婚噸缁撴潫锛宩sonArray2鏄凡缁忓幓閲嶇殑

					int projects_length = jsonArray2.length();

					if (projects_statistics.containsKey(projects_length)) {
						projects_number = projects_statistics.get(projects_length);
						projects_number++;
						projects_statistics.put(projects_length, projects_number);
					} else {
						projects_number = 1;
						projects_statistics.put(projects_length, projects_number);
					}

					System.out.println(projects_length + ": " + projects_number);


				getMethod1.releaseConnection();
			}


			Iterator<Integer> iter = projects_statistics.keySet().iterator();
			int t = 0;
			while (iter.hasNext()) {
				int key = iter.next();
//	        	System.out.println(key);
				int value = projects_statistics.get(key);
				t = t + value;
				System.out.println("There are " + value + " contributor(s),which own " + key + " project(s).");
			}
			System.out.println(t);
			System.out.println("------------------");

			frame.add(new BarChart1(projects_statistics).getChartPanel());
			frame.setBounds(50, 50, 800, 600);
			frame.setVisible(true);
			getMethod.releaseConnection();

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
