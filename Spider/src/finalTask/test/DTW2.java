package finalTask.test;

public class DTW2 {
	private int num_of_parameters;
	private int num_of_frames1;
	private int num_of_frames2;
	private double[][] sound1;
	private double[][] sound2;
	private double[][] distance;  //¿€º∆∆•≈‰æ‡¿Î
	private double D1,D2,D3;
	
	public DTW2(double[][] sound1, double[][] sound2, int num_of_parameters) {
		this.sound1 = sound1;
		this.sound2 = sound2;
		this.num_of_parameters = num_of_parameters;
		initial();
	}
	public void initial() {
		num_of_frames1 = sound1.length;
		num_of_frames2 = sound2.length;
		
		distance = new double[num_of_frames1][num_of_frames2];
		for (int i=0;i<num_of_frames1;i++)
			for(int j=0;j<num_of_frames2;j++) {
				distance[i][j]= Double.MAX_VALUE;
			}
		distance[0][0]= cal_distance_frame(0,0);
	}
	
	private double cal_distance_frame (int num1,int num2) {
		double sum = 0;
		for (int k=0;k<num_of_parameters;k++) {
			 sum += Math.pow(sound1[num1][k]-sound2[num2][k], 2D);
//			 sum += Math.sqrt(sound1[num1][k]-sound2[num2][k]);
		}
		return Math.pow(Math.sqrt(sum), 2D);
	}
	
	public double cal_big_distance() {
		for(int i=1;i<num_of_frames1;i++) {
			for(int j=0;j<num_of_frames2;j++) {
				D1 = distance[i-1][j];
				if (j>0) {
					D2 = distance[i-1][j-1];
				}else {
					D2 = Double.MAX_VALUE;
				}
				if (j>1) {
					D3 = distance[i-1][j-2];
				}else {
					D3 = Double.MAX_VALUE;
				}
				
				distance[i][j] = cal_distance_frame(i,j)+ min(D1,D2,D3);
			}
		}
		double distance_result = distance[num_of_frames1-1][num_of_frames2-1];
		return distance_result;
	}
	
	private double min(double x,double y,double z) {
		double a = (x<=y)? x:y;
		return (a<=z)? a:z;
	}
}
