package finalTask.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import finalTask.util.DataPrepared2;
import finalTask.util.MarkMap;

public class DTW_Test0104 {
	
//	public static final int DTW_NUM=10;
//	private static final int num_of_frames1=4;
//	private static final int num_of_frames2=5;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		generateFile(2,"commit","20170103");
//		generateFile(4,"comment","20170103");
//		generateFile(6,"contributor","20170103");
//		generateFile(8,"fork","20170103");
//		generateFile(10,"open_issue","20170103");
//		generateFile(12,"closed_issue","20170103");
	}
	
	public static void generateFile(int row,String feature,String date)throws Exception{
		File directory = new File("E:\\projects");
		String[] projectList = directory.list();
		List<String> list = new ArrayList<String>();
		Map<String,String> mark1Map = MarkMap.getMark1Map();
		String projectList_i = "pw2mw_projects.txt";
		
//		for(int i=0;i<projectList.length;i++){
//			if(new File("\\projects\\"+projectList_i).isDirectory()) 
//				continue;
			String featureRow1 = DataPrepared2.readTxtFile("E:\\projects\\"+projectList_i,row);
//			if(featureRow1==null||featureRow1.equals(""))
//				continue;
			double[][] projectFeatureArr = normalization(DataPrepared2.getTxtRow(featureRow1),feature);
			double distanceCount1 = 0;
			double distanceCount2 = 0;
			for(int j=0;j<projectList.length;j++){
				if(new File("E:\\projects\\"+projectList[j]).isDirectory())
					continue;
				String featureRow2 = DataPrepared2.readTxtFile("E:\\projects\\"+projectList[j],row);
				if(featureRow2==null||featureRow2.equals(""))
					continue;
				double[][] CompareProjectFeatureArr = normalization(DataPrepared2.getTxtRow(featureRow2),feature);
				double distance = dtw(projectFeatureArr,CompareProjectFeatureArr,projectFeatureArr.length,CompareProjectFeatureArr.length);
				System.out.println(projectList_i+","+projectList[j]+"两个"+feature+"时间序列的最终DTW距离为："+ distance);
//				list.add(projectList[i]+","+projectList[j]+"两个"+feature+"时间序列的最终DTW距离为："+ distance);
				
				if(mark1Map.containsKey(projectList[j].substring(0,projectList[j].lastIndexOf(".")))){
					distanceCount1 = distanceCount1 + distance;
				}else{
					distanceCount2 = distanceCount2 + distance;
				}
//				distanceCount = distanceCount + distance;
			}
			list.add(feature+":"+projectList_i+","+distanceCount1/distanceCount2);
//		}
		System.out.println("------------------------------------------------------------");
		FileWriter fw = new FileWriter("E:\\projects\\dtw_file\\lixl_"+feature+"_"+date+".txt",true);
		for(int i=0;i<list.size();i++){
	        BufferedWriter bufw = new BufferedWriter(fw);
	        bufw.write(list.get(i));
	        bufw.newLine();
	        bufw.flush();
			System.out.println(list.get(i));
		}
		fw.close();
	}
	
	public static double[][] normalization(String[][] arr,String feature) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		double[][] arr2 = new double[arr.length][2];
		for(int i=0;i<arr.length;i++){
			if(MaxArrDate(arr).compareTo(MinArrDate(arr))==0){
				arr2[i][0] = daysBetween(MinArrDate(arr),sdf.parse(arr[i][0]));
			}else{
				arr2[i][0] = daysBetween(sdf.parse(arr[i][0]),MinArrDate(arr))/daysBetween(MaxArrDate(arr),MinArrDate(arr));
			}
			
			if(MaxArrValue(arr)==MinArrValue(arr)){
				arr2[i][1] = Double.valueOf(arr[i][1])-MinArrValue(arr);
			}else{
				arr2[i][1] = (Double.valueOf(arr[i][1])-MinArrValue(arr))/(MaxArrValue(arr)-MinArrValue(arr));
			}
			
			if(feature.equals("commit1")){
				arr2[i][1] = 1;
			}
			if(feature.equals("commit2")){
				if(Double.valueOf(arr[i][1])>(MaxArrValue(arr)/2)){
					arr2[i][1] = 1;
				}else{
					arr2[i][1] = 0;
				}
			}
		}
		return arr2;
	}
	
	public static double daysBetween(Date smdate,Date bdate) throws ParseException{    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Double.valueOf((between_days));           
    }
	
	public static Date MinArrDate(String[][] A) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date min = sdf.parse(A[0][0]);
		 for(int i=0;i<A.length;i++){
			 if(sdf.parse(A[i][0]).getTime() < min.getTime())   // 判断最小值
				 min = sdf.parse(A[i][0]);
		 }
		 return min;
	}
	
	public static Date MaxArrDate(String[][] A) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date max=sdf.parse(A[0][0]);
		 for(int i=0;i<A.length;i++){
			 if(sdf.parse(A[i][0]).getTime() > max.getTime())   // 判断最大值
				 max = sdf.parse(A[i][0]);
		 }
		 return max;
	}
	
	public static double MinArrValue(String[][] A){
		 double min=Double.valueOf(A[0][1]);
		 for(int i=0;i<A.length;i++){
			 if(Double.valueOf(A[i][1])<min)   // 判断最小值
				 min=Double.valueOf(A[i][1]);
		 }
		 return min;
	}
	
	public static double MaxArrValue(String[][] A){
		 double max=Double.valueOf(A[0][1]);
		 for(int i=0;i<A.length;i++){
			 if(Double.valueOf(A[i][1])>max)   // 判断最大值
				 max=Double.valueOf(A[i][1]);
		 }
		 return max;
	}
	
	public static double Min(double a1,double b1){
		return(a1<b1?a1:b1);
	}
	
	public static double dtw(double[][] a,double[][] b,int num_of_frames1,int num_of_frames2){
		int i,j;
		double[][] distance = new double[num_of_frames1][num_of_frames2];
		double[][] output = new double[num_of_frames1][num_of_frames2];
//		double[][] a = {{2,3},{3,4},{4,5}};
//		double[][] b = {{1,1},{2,2},{3,3}};
//		String[][] a = {{"2010-10-03","3"},{"2010-10-03","4"},{"2010-10-03","5"},{"2010-10-03","6"}};
//		String[][] b = {{"2010-10-03","1"},{"2010-10-03","2"},{"2010-10-03","3"},{"2010-10-03","4"},{"2010-10-03","5"}};
		
		for(i=0;i<num_of_frames1;i++){
			for(j=0;j<num_of_frames2;j++){
//				distance[i][j]=(b[j-1]-a[i-1])*(b[j-1])-a[i-1];
				distance[i][j]=Math.pow(b[j][0]-a[i][0], 2D)+Math.pow(b[j][1]-a[i][1], 2D);
			}
		}
		//输出整个矩阵的欧式距离
//		System.out.println("distance:");
		for(i=0;i<num_of_frames1;i++){
			for(j=0;j<num_of_frames2;j++){
//				System.out.print(distance[i][j]+" ");
			}
//			System.out.println("");
		}
		
		output[0][0] = distance[0][0];
		
		for(i=1;i<num_of_frames1;i++){
			output[i][0] = output[i-1][0] + distance[i][0];
		}
		
		for(j=1;j<num_of_frames2;j++){
			output[0][j] = output[0][j-1] + distance[0][j];
		}
			
		for(i=1;i<num_of_frames1;i++){
			for(j=1;j<num_of_frames2;j++){
				output[i][j]=Min(Min(output[i-1][j-1],output[i][j-1]),output[i-1][j])+distance[i][j];
			}
		}
		
		//DP过程，计算DTW距离
//		System.out.println("output:");
		for(i=0;i<num_of_frames1;i++){
			for(j=0;j<num_of_frames2;j++){
//				System.out.print(output[i][j]+" ");
			}
//			System.out.println("");
		}//输出最后的DTW距离矩阵，其中output[DTW_NUM][DTW_NUM-1]为最终的DTW距离和
//		System.out.println("两个时间序列的最终DTW距离和为："+output[num_of_frames1-1][num_of_frames2-1]);
		return output[num_of_frames1-1][num_of_frames2-1];
	}
}
