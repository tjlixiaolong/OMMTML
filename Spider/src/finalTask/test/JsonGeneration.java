package finalTask.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import finalTask.util.DataPrepared2;

public class JsonGeneration {
	public static void main(String[] args) throws Exception {
		
		File directory = new File("E:\\projects");
		String[] projectList = directory.list();
		for(int i=0;i<projectList.length;i++){
			System.out.println(projectList[i]);
			if(new File("E:\\projects\\"+projectList[i]).isDirectory()) 
				continue;
			String commitRow1 = DataPrepared2.readTxtFile("E:\\projects\\"+projectList[i],4);
			String[][] arr = DataPrepared2.getTxtRow(commitRow1);
			String s = "";
			for(int j=0;j<arr.length;j++){
				s = s + "[\""+arr[j][0]+"\","+arr[j][1]+"],";
			}
			String jsonFileName = projectList[i].substring(0,projectList[i].lastIndexOf("."));
			if(jsonFileName.contains(".")){
				jsonFileName = jsonFileName.substring(0,jsonFileName.indexOf("."))+"_"+jsonFileName.substring(jsonFileName.indexOf(".")+1);
			}
	        FileWriter fw = new FileWriter("E:\\projects\\json\\comment_"+jsonFileName+".json",true);
	        BufferedWriter bufw = new BufferedWriter(fw);
	        bufw.write("test=["+s.substring(0,s.lastIndexOf(","))+"]");
	        bufw.flush();
	        fw.close();
		}
	}
}
