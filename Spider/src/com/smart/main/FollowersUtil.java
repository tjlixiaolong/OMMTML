package com.smart.main;

import net.sf.json.JSONArray;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-05-20.
 */
public class FollowersUtil { 
	public static void main(String[] args) {
		try {
			System.out.println(getFollowersId("arashm"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static String getFollowersId(String login) throws IOException {
        String url = "https://api.github.com/users/"+login+"/followers";

        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        //httpClient.getParams().setAuthenticationPreemptive(true);
        String encoding = new BASE64Encoder().encode("bruceli2011@163.com:625113abc".getBytes());
        GetMethod getMethod = new GetMethod(url);
        //getMethod.setDoAuthentication(true);
        getMethod.setRequestHeader("Authorization","Basic "+encoding);
        int statusCode = httpClient.executeMethod(getMethod);

        //System.out.println("response=" + getMethod.getResponseBodyAsString());

        JSONArray jsonArray = new JSONArray(getMethod.getResponseBodyAsString());

        List<String> idList = new ArrayList<String>();
        for(int i=0;i<jsonArray.length();i++){
            idList.add(String.valueOf(jsonArray.getJSONObject(i).get("id")));
        }
        getMethod.releaseConnection();
        if(idList.size()==0){
            return "";
        }
        return StringUtils.join(idList,",");
    }

    public static String getFollowingsId(String login) throws IOException {
        String url = "https://api.github.com/users/"+login+"/following";
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        String encoding = new BASE64Encoder().encode("bruceli2011@163.com:625113abc".getBytes());
        //httpClient.getParams().setAuthenticationPreemptive(true);
        GetMethod getMethod = new GetMethod(url);
        //getMethod.setDoAuthentication(true);
        getMethod.setRequestHeader("Authorization","Basic "+encoding);
        int statusCode = httpClient.executeMethod(getMethod);

        //System.out.println("response=" + getMethod.getResponseBodyAsString());

        JSONArray jsonArray = new JSONArray(getMethod.getResponseBodyAsString());

        List<String> idList = new ArrayList<String>();
        for(int i=0;i<jsonArray.length();i++){
            idList.add(String.valueOf(jsonArray.getJSONObject(i).get("id")));
        }
        getMethod.releaseConnection();
        if(idList.size()==0){
            return "";
        }
        return StringUtils.join(idList,",");
    }
    public static String getSubscriptionsId(String login) throws IOException {
        String url = "https://api.github.com/users/"+login+"/subscriptions";
        String encoding = new BASE64Encoder().encode("bruceli2011@163.com:625113abc".getBytes());
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        //httpClient.getParams().setAuthenticationPreemptive(true);
        GetMethod getMethod = new GetMethod(url);
        //getMethod.setDoAuthentication(true);
        getMethod.setRequestHeader("Authorization","Basic "+encoding);
        int statusCode = httpClient.executeMethod(getMethod);

        //System.out.println("response=" + getMethod.getResponseBodyAsString());

        JSONArray jsonArray = new JSONArray(getMethod.getResponseBodyAsString());

        List<String> idList = new ArrayList<String>();
        for(int i=0;i<jsonArray.length();i++){
            idList.add(String.valueOf(jsonArray.getJSONObject(i).get("id")));
        }
        getMethod.releaseConnection();
        if(idList.size()==0){
            return "";
        }
        return StringUtils.join(idList,",");
    }
}
