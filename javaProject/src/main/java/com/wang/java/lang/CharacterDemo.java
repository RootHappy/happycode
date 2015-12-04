package com.wang.java.lang;



public class CharacterDemo {

	public static void main(String[] args) {
		Character character = new Character('1');
		Character secondValue = Character.valueOf('Z');

		System.out.println("Character.charValue() : " + character.charValue());
		System.out.println("Character.charValue() : " + secondValue.charValue());
		System.out.println("Character.hashCode() : " + character.hashCode());
		System.out.println("Character.hashCode() : " + secondValue.hashCode());
		System.out.println("Character.toString() : " + secondValue.toString());
		System.out.println("Character.equals() : " + character.equals(secondValue));

		System.out.println("Character.charCount() : " +Character.charCount(0x10000));
		System.out.println("==============Character Static method=========================");
		System.out.println("Character.isUpperCase() : " +Character.isUpperCase('A'));
		System.out.println("Character.isUpperCase() : " +Character.isUpperCase(65));
		System.out.println("Character.isLowerCase() : " +Character.isLowerCase('Z'));
		System.out.println("Character.isLowerCase() : " +Character.isLowerCase(97));
		System.out.println("Character.isTitleCase() : " +Character.isTitleCase('A'));
		System.out.println("Character.isTitleCase() : " +Character.isTitleCase('a'));
		System.out.println("Character.isDigit() : " +Character.isDigit('1'));
		System.out.println("Character.isDigit() : " +Character.isDigit('-'));
		System.out.println("Character.isLetter(): " + Character.isLetter('a'));
		System.out.println("Character.isLetter(): " + Character.isLetter('9'));
		System.out.println("Character.isLetterOrDigit(): " + Character.isLetterOrDigit('a'));
		System.out.println("Character.isLetterOrDigit(): " + Character.isLetterOrDigit('9'));
		System.out.println("Character.isAlphabetic(): " + Character.isAlphabetic('9'));
		System.out.println("Character.isAlphabetic(): " + Character.isAlphabetic('a'));
		System.out.println("Character.isIdeographic(): " + Character.isIdeographic(12345));
		System.out.println("Character.isSpaceChar(): " + Character.isSpaceChar(' '));
		System.out.println("Character.isWhitespace(): " + Character.isWhitespace('\n'));

		System.out.println("Character.toUpperCase() : " + Character.toUpperCase('a'));
		System.out.println("Character.toLowerCase() : " + Character.toLowerCase('A'));
		System.out.println("Character.toTitleCase() : " + Character.toTitleCase('A'));
		System.out.println("Character.digit() : " + Character.digit('1',2));
		System.out.println("Character.digit() : " + Character.digit(49,2));
		System.out.println("Character.getNumericValue() : " + Character.getNumericValue('\u216C'));

		System.out.println(Character.getType('a'));

	}
}
