package finalTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CsvMerge {
	public static void main(String[] args) throws Exception {
		
		Date now = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String nowDate = sdf.format(now);
		
		String date = "20170403";
		
		Map<String,String> map = new HashMap<String,String>();
		map = getFileMap("E:/projects/dtw_file/"+date+"/lixl_commit_"+date+".txt",map);
		map = getNewMap(getFileMap("E:/projects/dtw_file/"+date+"/lixl_commit1_"+date+".txt",map),2);
		map = getNewMap(getFileMap("E:/projects/dtw_file/"+date+"/lixl_commit2_"+date+".txt",map),3);
		map = getNewMap(getFileMap("E:/projects/dtw_file/"+date+"/lixl_comment_"+date+".txt",map),4);
		map = getNewMap(getFileMap("E:/projects/dtw_file/"+date+"/lixl_contributor_"+date+".txt",map),5);
		map = getNewMap(getFileMap("E:/projects/dtw_file/"+date+"/lixl_fork_"+date+".txt",map),6);
		map = getNewMap(getFileMap("E:/projects/dtw_file/"+date+"/lixl_open_issue_"+date+".txt",map),7);
		map = getNewMap(getFileMap("E:/projects/dtw_file/"+date+"/lixl_closed_issue_"+date+".txt",map),8);
		
		Iterator<String> it = map.keySet().iterator();  
//	       while (it.hasNext()) {  
//	           String key = it.next().toString();
//	           String value = map.get(key);
//	           System.out.println(key+","+value);
//	       }
	    Map<String,String> markMap = getMarkMap(map);
	    
	      
        FileWriter fw = new FileWriter("E:/projects/dtw_file/lixl_merge_"+date+".csv");
        BufferedWriter bufw = new BufferedWriter(fw);
    	bufw.write(",commit,commit1,commit2,comment,contributor,fork,open_issue,closed_issue");
    	bufw.newLine();
    	bufw.flush();
        while(it.hasNext()){
        	String key = it.next().toString();
        	String value = map.get(key);
        	bufw.write(key+","+value+","+markMap.get(key));
        	bufw.flush();
        	bufw.newLine();
        	bufw.flush();
        }
        bufw.flush();
        fw.close();
	}
	
	public static Map<String,String> getMarkMap(Map<String,String> keyMap){
		Map<String,String> markMap = new HashMap<String,String>();
		//markMap.put("mysql-server"+"_projects", "����");
		//markMap.put("subversion"+"_projects", "����");
		//markMap.put("php-src"+"_projects", "����");
		//markMap.put("linux"+"_projects", "����");
		//markMap.put("firebug"+"_projects", "����");
		//markMap.put("git"+"_projects", "����");
		markMap.put("spark"+"_projects", "����");
		markMap.put("jquery"+"_projects", "����");
		markMap.put("angular"+"_projects", "����");
		markMap.put("FreeCodeCamp"+"_projects", "����");
		markMap.put("swift"+"_projects", "����");
		markMap.put("ember.js"+"_projects", "����");
		markMap.put("d3"+"_projects", "����");
		markMap.put("Chart.js"+"_projects", "����");
		markMap.put("matplotlib"+"_projects", "����");
		markMap.put("ipython"+"_projects", "����");
		markMap.put("sympy"+"_projects", "����");
		markMap.put("docker"+"_projects", "����");
		markMap.put("openstack"+"_projects", "����");
		markMap.put("rails"+"_projects", "����");
		markMap.put("spring-framework"+"_projects", "����");
		markMap.put("scikit-learn"+"_projects", "����");
		markMap.put("go"+"_projects", "����");
		markMap.put("rust"+"_projects", "����");
		markMap.put("ruby"+"_projects", "����");
		markMap.put("tensorflow"+"_projects", "����");
		markMap.put("scala"+"_projects", "����");
		markMap.put("groovy-core"+"_projects", "����");
		markMap.put("echarts"+"_projects", "����");
		markMap.put("django"+"_projects", "����");
		
		markMap.put("pw2mw"+"_projects", "������");
		markMap.put("PiSpace"+"_projects", "������");
		markMap.put("wijs"+"_projects", "������");
		markMap.put("angelos"+"_projects", "������");
		markMap.put("luv.js"+"_projects", "������");
		markMap.put("pyqcy"+"_projects", "������");
		markMap.put("pass.js"+"_projects", "������");
		markMap.put("mfp"+"_projects", "������");
		markMap.put("GenI"+"_projects", "������");
		markMap.put("Canvassa"+"_projects", "������");
		markMap.put("comp"+"_projects", "������");
		markMap.put("Crisp"+"_projects", "������");
		markMap.put("Unpapered"+"_projects", "������");
		markMap.put("redstore"+"_projects", "������");
		markMap.put("pdnsui"+"_projects", "������");
		markMap.put("spiff"+"_projects", "������");
		markMap.put("DOMinate"+"_projects", "������");
		markMap.put("hgd"+"_projects", "������");
		markMap.put("matR"+"_projects", "������");
		markMap.put("ajhc"+"_projects", "������");
		
		Iterator<String> it = markMap.keySet().iterator();
		List<String> keyList = new ArrayList<String>();
	    	while (it.hasNext()) {  
	    		String key = it.next().toString();
	    		if(!keyMap.containsKey(key)){
	    			keyList.add(key);
	    		}
	    	}
		       
	    for(int i=0;i<keyList.size();i++){
	    	markMap.remove(keyList.get(i));
	    }
		       
		Iterator<String> iter = markMap.keySet().iterator();  
	       while (iter.hasNext()) {  
	           String key = iter.next().toString();
	           String value = markMap.get(key);
	           System.out.println(key+","+value);
	       }
	    return markMap;
	}
	
	public static Map<String,String> getNewMap(Map<String,String> map,int number){
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next().toString();
			String[] valueArr = map.get(key).split(",");
			if(valueArr.length!=number){
				map.put(key, map.get(key)+",");
			}
		}
		return map;
	}
	
	public static Map<String,String> getFileMap(String fileName,Map<String,String> map) throws Exception{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		while((line=br.readLine())!=null){
			String subLine =  line.substring(line.indexOf(":")+1);
//			System.out.println(line.substring(line.indexOf(":")+1));
			String[] lineArr = subLine.split(",");
			String projectName =  lineArr[0].substring(0,lineArr[0].lastIndexOf("."));
			if(map.containsKey(projectName)){
				map.put(projectName, map.get(projectName)+","+lineArr[1]);
			}else{
				map.put(projectName, lineArr[1]);
			}
		}
		return map;
	}
	
}
