package com.viajala.dictionary;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Dictionary {
	
	private final String DICTIONARY_FILE = "/home/julian/Emcali-Workspace/ScrabbleHelper/src/resources/words_en.txt";
	private List<String> loadedWords;
	
	public Dictionary() {
		loadDictionary();
	}
	
	/**
	 * Method that loads the Dictionary txt file into an array to be processed.
	 * 
	 */
	
	private void loadDictionary() {
		File file = new File(DICTIONARY_FILE);
		try {
			loadedWords = FileUtils.readLines(file, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that look for a specific word in the Dictionary.
	 * 
	 * @param word: word to be verified.
	 * @param original: Set of letters enterd by the user.
	 * @return true if the word exists in the Dictionary.
	 * 
	 */
	
	public boolean hasWord(String word) {
		if(Collections.binarySearch(loadedWords, word) > 0) {
			return true;
		} else {
			return false;
		}
	}

}
