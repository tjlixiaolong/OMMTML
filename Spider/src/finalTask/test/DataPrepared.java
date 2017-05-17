package finalTask.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import sun.misc.BASE64Encoder;

public class DataPrepared {
	public static void main(String[] args) throws IOException {
		System.out.println(getForksInfo("apache/spark"));
	}
	
	public static String getForksInfo(String projectName) throws IOException {
		Map<String,String> idMap = new HashMap<String,String>();
		
        for(int i=1;i<1000;i++){
        	String url = "https://api.github.com/repos/"+projectName+"/forks?page="+String.valueOf(i);

            HttpClient httpClient = new HttpClient();
//            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
//            httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
            //httpClient.getParams().setAuthenticationPreemptive(true);
            String encoding = new BASE64Encoder().encode("bruceli2011@163.com:625113abc".getBytes());
            GetMethod getMethod = new GetMethod(url);
            //getMethod.setDoAuthentication(true);
            getMethod.setRequestHeader("Authorization","Basic "+encoding);
            int statusCode = httpClient.executeMethod(getMethod);

//            System.out.println("response=" + getMethod.getResponseBodyAsString());
            if(getMethod.getResponseBodyAsString()==null||
            		getMethod.getResponseBodyAsString().substring(1, getMethod.getResponseBodyAsString().lastIndexOf("]")).trim().equals("")) break;
            JSONArray jsonArray = new JSONArray(getMethod.getResponseBodyAsString());

            for(int j=0;j<jsonArray.length();j++){
            	idMap.put(String.valueOf(jsonArray.getJSONObject(j).get("id")), 
            			String.valueOf(jsonArray.getJSONObject(j).get("created_at")));
            }
            getMethod.releaseConnection();
            System.out.println("length is :"+idMap.keySet().size());
            if(idMap.size()==0){
                return "";
            }
        }
        return testMapVoid(idMap);
    }
	
	public static String testMapVoid (Map map) {
		   String mapStr = "";
	       List listKey = new ArrayList();  
	       List listValue = new ArrayList();  
	       Iterator it = map.keySet().iterator();  
	       while (it.hasNext()) {  
	           String key = it.next().toString();
	           listKey.add(key);
	           listValue.add(map.get(key));  
	       }
	       
	       for(int i =0 ;i<listKey.size();i++){
	    	   mapStr = mapStr+"<"+listKey.get(i)+","+listValue.get(i)+">"+",";
	       }
	       return mapStr.substring(0, mapStr.lastIndexOf(","));
	   }  
}
