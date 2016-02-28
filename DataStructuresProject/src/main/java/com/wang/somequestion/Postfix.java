package com.wang.somequestion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wang.datastructures.list.MyArrayStack;

/**
 * 后缀表达式的栈操作
 * @author wangyunlong
 *
 */
public class Postfix {
	
	/**
	 * 后缀表达式的计算
	 * 
	 * 思路：如果是操作数，直接入栈，如果是操作符则弹出两个数计算后结果入栈
	 * 
	 * @param expression
	 * @return
	 * @throws Exception
	 */
	public static int postfixCompute(String[] expression) throws Exception{
		MyArrayStack stack = new MyArrayStack();
		
		for(String str : expression){
			if(isNumeric(str)){
				stack.push(Integer.parseInt(str));
			}else{
				isOperator(str,stack);
			}
		}
		return stack.pop();
	}
	
	/**
	 * 给定字符是操作符，处理对应的操作符计算
	 * @param str
	 * @param stack
	 * @throws Exception
	 */
	public static void isOperator(String str, MyArrayStack stack) throws Exception{
		int operatorLeft,operatorRight;
		int result = 0;
		switch(str){
			case "+":
				operatorLeft = stack.pop();
				operatorRight = stack.pop();
				result = operatorLeft + operatorRight;
				stack.push(result);
				break;
			case "-":
				operatorLeft = stack.pop();
				operatorRight = stack.pop();
				result = operatorLeft - operatorRight;
				stack.push(result);
				break;
			case "*":
				operatorLeft = stack.pop();
				operatorRight = stack.pop();
				result = operatorLeft * operatorRight;
				stack.push(result);
				break;
			case "/":
				operatorLeft = stack.pop();
				operatorRight = stack.pop();
				result = operatorLeft / operatorRight;
				stack.push(result);
				break;
		}
	}
	
	/**
	 * 判断给给定字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher matcher = pattern.matcher(str);
		if( matcher.matches())
			return true;
		return false;
	}
	
	
	
	public static void main(String[] args)throws Exception{
		String postfixExpression = "6 5 2 3 + 8 * + 3 + *";
		int result = postfixCompute(postfixExpression.split(" "));
		System.out.println(result);
	}
}
