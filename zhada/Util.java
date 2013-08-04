package com.zhada;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Util {
	//将结果写入output.txt中
	public static void writeResult(String filename ,double computeResult[]) throws IOException{
		File file = new File(filename);
		DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
		StringBuilder str;
		str = new StringBuilder("yueliming.2008@gmail.com");
		str.append("\n");
		str.append("\n");

		for(int i=0;i<computeResult.length;i++){
			String temp = String.format("%.2f",computeResult[i]);  //四舍五入，保留两位小数
			str.append(temp);
			str.append(" m");
			if(i<computeResult.length-1){
				str.append("\n");
			}
		}
		
		out.writeBytes(str.toString());
		out.flush();
		out.close();		
	}
}
