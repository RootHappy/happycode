package com.wang.leetcode;

import java.util.HashMap;

public class ValidSudoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] input ={".87654321","2........","3........","4........","5........","6........","7........","8........","9........"};
		char[][] board = new char[9][9];
		for(int i = 0; i < input.length; i++) {
			board[i] = input[i].toCharArray();
		}
		System.out.println(isValidSudoku(board));
	}

	public static boolean isValidSudoku(final char[][] board) {
			HashMap<Character,Integer> maps = new HashMap<Character, Integer>();
			char[] numArray = new char[9];
			for(int i = 0; i < board.length;i++) {
				for(int k = 0; k < 9; k++) {
					numArray[k] = board[i][k];
				}
				maps.clear();
				for(int j = 0; j < numArray.length; j++) {
					if(maps.get(numArray[j]) == null) {
						maps.put(numArray[j], j);
					}else if(numArray[j] !=  '.' ){
						return false;
					}
				}
			}
			for(int i = 0; i < board[0].length;i++) {
				for(int k = 0; k < board.length;k++) {
					numArray[k] = board[k][i];
				}
				maps.clear();
				for(int j = 0; j < numArray.length; j++) {
					if(maps.get(numArray[j]) == null) {
						maps.put(numArray[j], j);
					}else if(numArray[j] !=  '.' ){
						return false;
					}
				}
			}
			for(int i = 0; i < board.length / 3; i++)  {
				for(int j = 0; j < board[0].length / 3; j++) {
					int index = 0;
					for(int m =i*3;m < i*3 + 3; m++) {
						for(int n =j*3; n < j*3 + 3;n++) {
							numArray[index++] = board[m][n];
						}
					}
					maps.clear();
					for(int k = 0; k < numArray.length; k++) {
						if(maps.get(numArray[k]) == null) {
							maps.put(numArray[k], k);
						}else if(numArray[k] !=  '.' ){
							return false;
						}
					}
				}
			}
			return true;
	}

}
