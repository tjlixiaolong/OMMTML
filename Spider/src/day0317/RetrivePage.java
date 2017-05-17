package day0317;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class RetrivePage {
	private static HttpClient httpClient = new HttpClient();
	public static boolean downloadPage(String path) throws HttpException, IOException{
		InputStream input = null;
		OutputStream output = null;
		GetMethod getMethod = new GetMethod(path);
		int statusCode = httpClient.executeMethod(getMethod);
		if(statusCode == HttpStatus.SC_OK){
			input = getMethod.getResponseBodyAsStream();
			String fileName = path.substring(path.lastIndexOf('/')+1);
			output = new FileOutputStream(fileName);
			int tempByte = -1;
			while((tempByte=input.read())>0){
				output.write(tempByte);
			}
			if(input!=null){
				input.close();
			}
			if(output!=null){
				output.close();
			}
			return true;
		}
		return false;
	}
	public static void main(String[] args){
		
//		String path = "http://www.163.com";
//		String fileName = path.substring(path.lastIndexOf('/')+1);
//		System.out.println(fileName);
		try {
			RetrivePage.downloadPage("http://freecode.com/projects/apache");
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
