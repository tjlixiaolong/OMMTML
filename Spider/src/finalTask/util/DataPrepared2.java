package finalTask.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DataPrepared2 {
	public static void main(String[] args) throws Exception {
//		String[][] arr = getTxtRow("E:\\projects\\ember.js_projects.txt",2);
//		String s = "";
//		for(int i=0;i<arr.length;i++){
//			s = s + "[\""+arr[i][0]+"\","+arr[i][1]+"],";
//		}
//        FileWriter fw = new FileWriter("E:\\projects\\json\\commit_ember.js_projects.json",true);
//        BufferedWriter bufw = new BufferedWriter(fw);
//        bufw.write("["+s.substring(0,s.lastIndexOf(","))+"]");
//        bufw.flush();
//        fw.close();
		String featureRow1 = readTxtFile("E:\\projects\\d3_projects.txt",6);
		String[][] arr = getTxtRow(featureRow1);
		for(int j=0;j<arr.length;j++){
			System.out.println(arr[j][0]+","+arr[j][1]);
		}
	}
	
	public static String[][] getTxtRow(String featureRow) throws ParseException{

		DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);        
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");        
		
//		String s1 = "[ffa99a016354637e,Thu Feb 28 09:15:06 CST 2008],[dc1fb8b3a7e7ac1,Sun Dec 13 18:57:17 CST 2009],[dadadahjkhqweq,Thu Feb 28 09:15:06 CST 2008]";
		
//		String filePath = "E:\\ember.js_projects.txt";
//		String commitRow = readTxtFile(filePath,row);
//		if(commitRow.equals("")) return new String[0][0];
		String[] arr = featureRow.split("],");
		Map<String,Integer> featureMap = new HashMap<String,Integer>();
		int count = 0;
		for(int i=0;i<arr.length;i++){
			String[] arr1 = arr[i].split(",");
			if(arr1[0].contains("[")){
				arr1[0] = arr1[0].substring(1);
			}
			if(arr1[1].contains("]")){
				arr1[1] = arr1[1].substring(0, arr1[1].indexOf("]"));
			}
			arr1[1] = sdf.format(df.parse(arr1[1]));
			
			if(!featureMap.containsKey(arr1[1])){
				count = 0;
			}else{
				count = featureMap.get(arr1[1]);
			}
			count++;
			featureMap.put(arr1[1], count);
			
//			System.out.println(arr1[0]+","+sdf.format(df.parse(arr1[1])));
		}
		
		Object[] key_arr = featureMap.keySet().toArray();
		Arrays.sort(key_arr);
//		for(Object key:key_arr){
//			Object value = commitMap.get(key);
//			System.out.println(key+","+value);
//		}
		
		String[][] featureArr;
		featureArr = new String[key_arr.length][2];
		for(int i=0;i<key_arr.length;i++){
			featureArr[i][0] = String.valueOf(key_arr[i]);
			featureArr[i][1] = String.valueOf(featureMap.get(key_arr[i]));
		}
		return featureArr;
	}
	
	public static String readTxtFile(String filePath,int rowNumber){
        String lineTxt2 = null;
		try {
        	String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                for(int i=1;i<rowNumber;i++){
                	bufferedReader.readLine();
                }
                lineTxt2 =  bufferedReader.readLine();
//                System.out.println(lineTxt2);
                
                read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return lineTxt2;
    }
}
