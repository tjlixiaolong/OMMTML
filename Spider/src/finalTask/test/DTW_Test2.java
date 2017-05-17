package finalTask.test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import finalTask.util.DataPrepared2;

public class DTW_Test2 {
	
//	public static final int DTW_NUM=10;
//	private static final int num_of_frames1=4;
//	private static final int num_of_frames2=5;
	
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		File directory = new File("E:\\projects");
		String[] projectList = directory.list();
		List<String> list = new ArrayList<String>();
		for(int i=0;i<projectList.length;i++){
			if(new File("E:\\projects\\"+projectList[i]).isDirectory()) 
				continue;
			String commitRow1 = DataPrepared2.readTxtFile("E:\\projects\\"+projectList[i],12);
			if(commitRow1==null||commitRow1.equals(""))
				continue;
			double[][] projectCommitArr = normalization(DataPrepared2.getTxtRow(commitRow1));
			double distanceCount = 0;
			for(int j=0;j<projectList.length;j++){
				if(new File("E:\\projects\\"+projectList[j]).isDirectory()) 
					continue;
				String commitRow2 = DataPrepared2.readTxtFile("E:\\projects\\"+projectList[j],12);
				if(commitRow2==null||commitRow2.equals(""))
					continue;
				double[][] CompareProjectCommitArr = normalization(DataPrepared2.getTxtRow(commitRow2));
				double distance = dtw(projectCommitArr,CompareProjectCommitArr,projectCommitArr.length,CompareProjectCommitArr.length);
				if(new Double(distance).isNaN()) continue;
				System.out.println(projectList[i]+","+projectList[j]+"两个closed_issue时间序列的最终DTW距离为："+ distance);
				distanceCount = distanceCount + distance;
//				list.add(projectList[i]+","+projectList[j]+"两个fork时间序列的最终DTW距离为："+ distance);
			}
			list.add("closed issue:"+projectList[i]+","+distanceCount);
		}
		
		System.out.println("------------------------------------------------------------");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
	public static double[][] normalization(String[][] arr) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		double[][] arr2 = new double[arr.length][2];
		for(int i=0;i<arr.length;i++){
			arr2[i][0] = daysBetween(sdf.parse(arr[i][0]),MinArrDate(arr))/daysBetween(MaxArrDate(arr),MinArrDate(arr));
			if(MaxArrValue(arr)==MinArrValue(arr)){
				arr2[i][1] = Double.valueOf(arr[i][1])-MinArrValue(arr);
			}else{
				arr2[i][1] = (Double.valueOf(arr[i][1])-MinArrValue(arr))/(MaxArrValue(arr)-MinArrValue(arr));
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
