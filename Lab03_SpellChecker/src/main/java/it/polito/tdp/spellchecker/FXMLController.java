/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	private ObservableList<String> list = FXCollections.observableArrayList();
	Dictionary dictionary;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	private ChoiceBox<String> choice;

	@FXML
	private TextArea txtinput;

	@FXML
	private Button btnSpellCheck;

	@FXML
	private TextArea txtoutput;

	@FXML
	private Label lblerrori;

	@FXML
	private Button btnClearText;

	@FXML
	private Label lbltempo;

	@FXML
	void doClearText(ActionEvent event) {
		txtinput.clear();
		txtoutput.clear();

	}

	@FXML
	void doSpellCheck(ActionEvent event) {
		long start = System.nanoTime();
		txtoutput.clear();
		String input = txtinput.getText().toLowerCase();
		input.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", ""); // non funziona
		if(input == null || input.equals("")|| input.equals(" ")) {
			txtoutput.clear();
			this.txtoutput.appendText("Devi inserire almeno una parola !");
			return;
		}
		
		String language = choice.getValue();
		String[] array = input.split(" ");
		List<String> inputTextList = new ArrayList<>();
		List<RichWord> outputTextList = new ArrayList<>();
		for(String s : array) {
			char[] chararray = s.toCharArray();
			boolean flag = true;
			for (char c : chararray) {
				if (c > 'z' || c < 'a')
					flag = false;
			}
			if (flag == false) { 
				txtoutput.clear();
				txtoutput.appendText("LA PAROLA PUO' CONTENERE SOLO LETTERE \n");
				return;
			} else 
			inputTextList.add(s);
		}
	
		try {
		dictionary.loadDictionary(language);
		} catch (IOException e){
				System.out.println("Errore nella lettura del file");
				
		}
	
		
		outputTextList.addAll(dictionary.spellCheckText(inputTextList));
		
		
		int errori =0;
		
		for(RichWord rw : outputTextList) {
			if(!rw.isCorretta()) {
				txtoutput.appendText(rw.getParola()+"\n");
				errori++;
			}
		}
		lblerrori.setText("The text contains "+errori+" errors");
		long stop = System.nanoTime();
		long tempo = (stop - start);
		lbltempo.setText("Spell check completed in "+tempo+" nanoseconds");
		

	}

	@FXML
	void initialize() { // This method is called by the FXMLLoader when initialization is complete
		assert choice != null : "fx:id=\"choice\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtinput != null : "fx:id=\"txtinput\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtoutput != null : "fx:id=\"txtoutput\" was not injected: check your FXML file 'Scene.fxml'.";
		assert lblerrori != null : "fx:id=\"lblerrori\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
		assert lbltempo != null : "fx:id=\"lbltempo\" was not injected: check your FXML file 'Scene.fxml'.";
		list.addAll("English", "Italian");
		choice.setItems(list);
		choice.setValue("Italian");
		dictionary = new Dictionary();

	}

}

    


