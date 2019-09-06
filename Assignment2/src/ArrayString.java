import java.io.*;
import java.util.Arrays;

public class ArrayString {
	
	public static boolean uniqueChar(String input) {
		boolean[] flags = new boolean[128];
		for (int i = 0; i < input.length(); i++) {
			int asciiVal = (int) input.charAt(i);
			if (flags[asciiVal]) {
				return false;
			}
			else {
				flags[asciiVal] = true;
			}
		}
		return true;
	}
	
	public static boolean permutation(String one, String two) {
		char[] charArrayOne = one.toCharArray();
		char[] charArrayTwo = two.toCharArray();
		Arrays.sort(charArrayOne);
		Arrays.sort(charArrayTwo);
		if (Arrays.equals(charArrayOne, charArrayTwo)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean palindromePermutation(String one) {
		int[] asciiArray = new int[128];
		for (int i = 0; i < one.length(); i++) {
			asciiArray[(int) one.charAt(i)] += 1;
		}
		if(one.length()%2 == 0) {
			for(int i = 0; i < asciiArray.length; i++) {
				if (asciiArray[i]%2 != 0) {
					return false;
				}
			}
		}
		else {
			boolean oddNumber = false;
			for(int i = 0; i < asciiArray.length; i++) {
				if (asciiArray[i]%2 != 0) {
					if(oddNumber) {return false;}
					else {oddNumber = true;}
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		//Testing first problem, should return false then true
		System.out.println("String is free of duplicates: " + uniqueChar("LotsOfDuplicates"));
		System.out.println("String is free of duplicates: " + uniqueChar("NoDuplicates"));
		
		//Testing second problem, should return true then false
		System.out.println("Strings are permutations: " + permutation("god","dog"));
		System.out.println("Strings are permutations: " + permutation("Not","Perm"));
		
		//Testing third problem. First two test even, second test odd
		System.out.println("Odd string is a permutation of a palindrome: " + palindromePermutation("opooo"));
		System.out.println("Odd string is a permutation of a palindrome: " + palindromePermutation("oppoo"));
		System.out.println("Even string is a permutation of a palindrome: " + palindromePermutation("aadd"));
		System.out.println("Even string is a permutation of a palindrome: " + palindromePermutation("aada"));
	}

}
