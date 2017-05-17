package finalTask.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.text.ParseException;


public class Test {
	public static void main(String[] args) throws ParseException{
//		double[][]X = {{2,3},{3,4},{4,5},{5,6},{6,7}};
//		double[][]Y = {{1,1},{2,2},{3,3},{4,4},{5,5}};
//		DTW2 dtw = new DTW2(X,Y,2);
//		System.out.println(dtw.cal_big_distance());
////		System.out.println(Math.pow(Math.sqrt(3), 2D));
//		
//
////		String s = "Thu Feb 28 09:15:06 CST 2008";
//		DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);        
//		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");        
////		System.out.println(sdf.format(df.parse(s))); 
//		
//		String s1 = "[ffa99a016354637e,Thu Feb 28 09:15:06 CST 2008],[dc1fb8b3a7e7ac1,Sun Dec 13 18:57:17 CST 2009],[dadadahjkhqweq,Thu Feb 28 09:15:06 CST 2008]";
//		String[] arr = s1.split("],");
//		for(int i=0;i<arr.length;i++){
////			System.out.println(arr[i]);
//			String[] arr1 = arr[i].split(",");
//			if(arr1[0].contains("[")){
//				arr1[0] = arr1[0].substring(1);
//			}
//			if(arr1[1].contains("]")){
//				arr1[1] = arr1[1].substring(0, arr1[1].indexOf("]"));
//			}
//			System.out.println(arr1[0]+","+sdf.format(df.parse(arr1[1])));
//		}
		
//		HashMap map = new HashMap();
//		map.put("1", 1);
//		map.put("2", 2);
//		map.put("3", 3);
//		map.put("4", 4);
//		map.put("5", 5);
//		map.put("6", 6);
//		
//		Object[] key_arr = map.keySet().toArray();
//		Arrays.sort(key_arr);
//		for(Object key:key_arr){
//			Object value = map.get(key);
//			System.out.println(key+","+value);
//		}
		
//		int [][]arr = new int[3][2];
//		arr[0][0] = 1;
//		arr[0][1] = 2;
//		arr[1][0] = 3;
//		arr[1][1] = 4;
//		arr[2][0] = 5;
//		arr[2][1] = 6;
//		
////		int [][]arr = {{1,2},{3,4},{5,6}};
//		
//		for(int i=0;i<arr.length;i++){
//			for(int j=0;j<arr[i].length;j++){
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
//		 int i,min,max;
////		 int A[]={74,48,30,17,62};  // 声明整数数组A,并赋初值
//		 String[][]A = {{"2","3"},{"3","4"},{"4","5"},{"5","6"},{"6","7"}};
//		 
//		 for(i=0;i<A.length;i++){
//			 System.out.print(normalization(A)[i][0]+","+normalization(A)[i][1]);
//			 System.out.println();
//		 }
//		 String s = "abc.sj";
//		 System.out.println(s.indexOf("."));
//		 System.out.println(s.lastIndexOf("."));
//		 System.out.println(s.substring(0,s.indexOf("."))+"_"+s.substring(s.indexOf(".")+1));
		  
//		 min=max=Integer.valueOf(A[0][1]);
//		 System.out.print("数组A的元素包括：");
//		 for(i=0;i<A.length;i++){
//			 System.out.print(A[i][1]+" ");
//			 if(Integer.valueOf(A[i][1])>max)   // 判断最大值
//				 max=Integer.valueOf(A[i][1]);
//			 if(Integer.valueOf(A[i][1])<min)   // 判断最小值
//				 min=Integer.valueOf(A[i][1]);
//		 }
//		 System.out.println("\n数组的最大值是："+max); // 输出最大值
//		 System.out.println("数组的最小值是："+min); // 输出最小值
		
//		String date1 = "2010-10-03";
//		String date2 = "2010-10-06";
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
//        Date smdate=sdf.parse(date1);  
//        Date bdate=sdf.parse(date2);  
//        System.out.println(daysBetween(smdate,bdate));
		
//		Date now = new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
//		String date = sdf.format(now);
//		System.out.println(date);
		
		String s = "ember.js_projects.txt";
		System.out.println(Double.parseDouble("0.0")==0);
	}
	
    public static int daysBetween(Date smdate,Date bdate) throws ParseException{    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }  
	
	public static String[][] normalization(String[][] arr){
		String[][] arr2 = new String[arr.length][2];
		for(int i=0;i<arr.length;i++){
			arr2[i][0] = arr[i][0];
			arr2[i][1] = String.valueOf((Double.valueOf(arr[i][1])-MinArr(arr))/(MaxArr(arr)-MinArr(arr)));
		}
		return arr2;
	}
	
	public static double MinArr(String[][] A){
		 double min=Double.valueOf(A[0][1]);
		 for(int i=0;i<A.length;i++){
			 if(Double.valueOf(A[i][1])<min)   // 判断最小值
				 min=Double.valueOf(A[i][1]);
		 }
		 return min;
	}
	
	public static double MaxArr(String[][] A){
		 double max=Double.valueOf(A[0][1]);
		 for(int i=0;i<A.length;i++){
			 if(Double.valueOf(A[i][1])>max)   // 判断最大值
				 max=Double.valueOf(A[i][1]);
		 }
		 return max;
	}
}
