package finalTask;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import finalTask.util.DataPrepared2;

public class JsonMerge {
	public static void main(String[] args) throws Exception{
		
		int rowNumber = 2;
		String feature = "commit";
		
		File directory = new File("E:\\projects");
		String[] projectArr = directory.list();
		List<String> projectList = new ArrayList<String>();
		for(int i=0;i<projectArr.length;i++){
			if(!new File("E:\\projects\\"+projectArr[i]).isDirectory()){
				projectList.add(projectArr[i]);
			}
		}
		
		//generate timeArray
		Set<String> timeSet = new HashSet<String>();
		for(int k=0;k<projectList.size();k++){
			String featureRow1 = DataPrepared2.readTxtFile("E:\\projects\\"+projectList.get(k),rowNumber);
			String[][] project_arr = DataPrepared2.getTxtRow(featureRow1);
			for(int i=0;i<project_arr.length;i++){
				timeSet.add(project_arr[i][0]);
			}
		}
		String[] timeArray = timeSet.toArray(new String[timeSet.size()]);
		Arrays.sort(timeArray);
			
		//start merge
		String[][] mergeArray = new String[timeSet.size()][projectList.size()+1];
		
		for(int k=0;k<projectList.size();k++){
			String featureRow1 = DataPrepared2.readTxtFile("E:\\projects\\"+projectList.get(k),rowNumber);
			String[][] project_arr = DataPrepared2.getTxtRow(featureRow1);
			Map<String,String> projectMap = arrayToMap(project_arr);
			for(int i=0;i<timeArray.length;i++){
				mergeArray[i][0] = timeArray[i];
				if(projectMap.containsKey(timeArray[i])){
					mergeArray[i][k+1] =  projectMap.get(timeArray[i]);
				}else{
					mergeArray[i][k+1] = "";
				}
			}
		}
		
		System.out.println("------------------------------");
		for(int i=0;i<mergeArray.length;i++){
			for(int j=0;j<mergeArray[i].length;j++){
				System.out.print(mergeArray[i][j]+",");
			}
			System.out.println();
		}
		System.out.println("-------------------------------");
		
		//generate json file
		String mergeStr = "";
		for(int j=0;j<mergeArray.length;j++){
			String s = "";
			for(int k=1;k<projectList.size()+1;k++){
				s = s + ","+ mergeArray[j][k];
			}
			mergeStr = mergeStr + "[\""+mergeArray[j][0]+"\"," + s.substring(1) + "],";
//			mergeStr = mergeStr + "[\""+mergeArray[j][0]+"\","+mergeArray[j][1]+"],";
		}
        FileWriter fw = new FileWriter("E:\\projects\\json\\merge\\"+feature+"_merge.json");
        BufferedWriter bufw = new BufferedWriter(fw);
        bufw.write("test=["+mergeStr.substring(0,mergeStr.lastIndexOf(","))+"]");
        bufw.flush();
        fw.close();
        
	}
	
	public static Map<String,String> arrayToMap(String[][] arr){
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0;i<arr.length;i++){
			map.put(arr[i][0], arr[i][1]);
		}
		return map;
	}
}
