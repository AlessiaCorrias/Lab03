package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

	

List<String> dizionario = new ArrayList<>();
	
	public Dictionary() {
		
	}
	
	public void loadDictionary(String language) throws IOException{

		try {
			FileReader fr = new FileReader("C:\\Users\\aless\\git\\Lab03\\Lab03_SpellChecker\\src\\main\\resources\\Italian.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
			dizionario.add(word);
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}

	}
	
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> output = new ArrayList<>();
		for(String s : inputTextList) {
			if(dizionario.contains(s)) {
				output.add(new RichWord(s, true));	
			} else {
				output.add(new RichWord(s, false));
			}
		}
		return output;
	}

}



