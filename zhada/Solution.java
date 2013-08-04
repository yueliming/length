package com.zhada;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
public class Solution {	
	private static double MILE_METER;
	private static double YARD_METER;
	private static double INCH_METER;
	private static double FOOT_METER;
	private static double FATH_METER;
	private static double FURLONG_METER;
	private static double[] othersToMeter = new double[6];//不同单位和m的换算关系
	private static double[] computeResult = new double[10];//将它们转换成m，并对含有"+"和"-"运算符的表达式计算的结果
	
	
	//读取input.txt中的文件将表达式的运算结果存入othersToMeter1数组中
	public static void readFile(String filename) throws FileNotFoundException{
		Scanner sc = new Scanner(new FileReader(filename));
		for(int i=0;i<6;i++){
			String temp;
			temp = sc.nextLine();
			String[] content = temp.split(" ");
			othersToMeter[i] = Double.parseDouble(content[3]);
		}
		MILE_METER = othersToMeter[0];
		YARD_METER = othersToMeter[1];
		INCH_METER = othersToMeter[2];
		FOOT_METER = othersToMeter[3];
		FATH_METER = othersToMeter[4];
		FURLONG_METER = othersToMeter[5];
		
		sc.nextLine();		
		for(int i=0;i<10;i++){
			String b = sc.nextLine();
			String[] regulation = b.split(" ");			
			//读取每一行的内容并放入栈中
			Stack<String> stack = new Stack<String>();
			for(int j=0;j<regulation.length;j++){
				stack.push(regulation[j]);
			}			
			while(!stack.isEmpty()){
				double tempory;
				double To_Meter = findRelationWithMeter(stack.pop());
				tempory = Double.parseDouble(stack.pop())*To_Meter;
				if(stack.size()>=3){
					String operator = stack.pop();
					if(operator.equals("+")){
						computeResult[i] += tempory;
					}else{
						computeResult[i] += -tempory;
					}
				}else{
					computeResult[i] += tempory;
				}	
			}			
		}
		
		for(double var : computeResult){    //computeResult[]中存储的就是运算的结果
			System.out.println(var);
		}
	}
	
	/*
	 * 用来找出不同单位与m之间的换算关系,根据前两个字符就可知道，它和m之间的换算关系
	*/
	public static double findRelationWithMeter(String signature){		
		String sig = signature.substring(0, 2);		
		if(sig.equals("mi")){
			return MILE_METER;
		}else if(sig.equals("ya")){
			return YARD_METER;
		}else if(sig.equals("in")){
			return INCH_METER;
		}else if(sig.equals("fo")||sig.equals("fe")){
			return FOOT_METER;
		}else if(sig.equals("fa")){
			return FATH_METER;
		}else{
			return FURLONG_METER;
		}
	}

	public static void main(String[] args) throws IOException {
		String file_input = "E:/zhada/input.txt";
		String file_output = "E:/zhada/output.txt";
		
		readFile(file_input);
		Util.writeResult(file_output,computeResult);
	}
}
