package com.viajala.scrabble;

import java.util.ArrayList;
import java.util.List;

import com.viajala.dictionary.Dictionary;



public class ScrabbleHelper {
	
	public List<String> foundWords;
	public String originals;
	private Dictionary dictionary;

	/**
	 * Method that validates input letters
	 * 
	 * @param letters: List of letters separated by ','.
	 * @return true if all input letters are valid.
	 */
	
	public boolean validateInput(String letters) {
		String[] array = letters.split(",");

		for (String s : array) {
			if (!s.matches("[a-zA-z]{1}")) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * Method that starts the process to find possible words.
	 * 
	 * @param list: List of letters separated by ','.
	 * @return true if all input letters are valid.
	 */

	public void findWords(String list) {
		String original = list.replaceAll(",", "");
		this.setOriginals(original);
		StringBuilder comb = new StringBuilder();
		dictionary = new Dictionary();
		foundWords = new ArrayList<>();

		combination(0, original, comb);
	}

	/**
	 * Method that obtains all the possible unique subsets of letters.
	 * For example from a,b,c -> {ab}{ac}{bc}{abc}
	 * 
	 * @param init: Index used to select each letter.
	 * @param original: Set of letters enter by the user.
	 * @param comb: Used to build the possible subsets of letters.
	 * 
	 */

	private void combination(int init, String original, StringBuilder comb) {
		for (int i = init; i < original.length(); i++) {
			comb.append(original.charAt(i));
			if (comb.length() >= 2) {
				permutation("", comb.toString());
			}
			if (i < original.length()) {
				combination(i + 1, original, comb);
			}

			comb.setLength(comb.length() - 1);
		}
	}

	/**
	 * Method that obtains all the possible permutations of a set of letters.
	 * For example from {ab} -> {ab}{ba}
	 * 
	 * @param p: Prefix used to generate different permutations  
	 * @param letters: group of letters used to generate permutations.
	 * 
	 */
	private void permutation(String p, String letters) {
		int length = letters.length();

		if (length == 0) {
			if(dictionary.hasWord(p.toLowerCase())) {
				foundWords.add(p);
			}
		} else {
			for (int i = 0; i < length; i++) {
				permutation(p + letters.charAt(i), letters.substring(0, i)
						+ letters.substring(i + 1, length));
			}
		}
	}

	public List<String> getFoundWords() {
		return foundWords;
	}

	public String getOriginals() {
		return originals;
	}

	public void setOriginals(String originals) {
		this.originals = originals;
	}
}
