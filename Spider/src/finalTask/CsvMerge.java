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
		//markMap.put("mysql-server"+"_projects", "成熟");
		//markMap.put("subversion"+"_projects", "成熟");
		//markMap.put("php-src"+"_projects", "成熟");
		//markMap.put("linux"+"_projects", "成熟");
		//markMap.put("firebug"+"_projects", "成熟");
		//markMap.put("git"+"_projects", "成熟");
		markMap.put("spark"+"_projects", "成熟");
		markMap.put("jquery"+"_projects", "成熟");
		markMap.put("angular"+"_projects", "成熟");
		markMap.put("FreeCodeCamp"+"_projects", "成熟");
		markMap.put("swift"+"_projects", "成熟");
		markMap.put("ember.js"+"_projects", "成熟");
		markMap.put("d3"+"_projects", "成熟");
		markMap.put("Chart.js"+"_projects", "成熟");
		markMap.put("matplotlib"+"_projects", "成熟");
		markMap.put("ipython"+"_projects", "成熟");
		markMap.put("sympy"+"_projects", "成熟");
		markMap.put("docker"+"_projects", "成熟");
		markMap.put("openstack"+"_projects", "成熟");
		markMap.put("rails"+"_projects", "成熟");
		markMap.put("spring-framework"+"_projects", "成熟");
		markMap.put("scikit-learn"+"_projects", "成熟");
		markMap.put("go"+"_projects", "成熟");
		markMap.put("rust"+"_projects", "成熟");
		markMap.put("ruby"+"_projects", "成熟");
		markMap.put("tensorflow"+"_projects", "成熟");
		markMap.put("scala"+"_projects", "成熟");
		markMap.put("groovy-core"+"_projects", "成熟");
		markMap.put("echarts"+"_projects", "成熟");
		markMap.put("django"+"_projects", "成熟");
		
		markMap.put("pw2mw"+"_projects", "不成熟");
		markMap.put("PiSpace"+"_projects", "不成熟");
		markMap.put("wijs"+"_projects", "不成熟");
		markMap.put("angelos"+"_projects", "不成熟");
		markMap.put("luv.js"+"_projects", "不成熟");
		markMap.put("pyqcy"+"_projects", "不成熟");
		markMap.put("pass.js"+"_projects", "不成熟");
		markMap.put("mfp"+"_projects", "不成熟");
		markMap.put("GenI"+"_projects", "不成熟");
		markMap.put("Canvassa"+"_projects", "不成熟");
		markMap.put("comp"+"_projects", "不成熟");
		markMap.put("Crisp"+"_projects", "不成熟");
		markMap.put("Unpapered"+"_projects", "不成熟");
		markMap.put("redstore"+"_projects", "不成熟");
		markMap.put("pdnsui"+"_projects", "不成熟");
		markMap.put("spiff"+"_projects", "不成熟");
		markMap.put("DOMinate"+"_projects", "不成熟");
		markMap.put("hgd"+"_projects", "不成熟");
		markMap.put("matR"+"_projects", "不成熟");
		markMap.put("ajhc"+"_projects", "不成熟");
		
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
