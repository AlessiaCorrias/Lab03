package it.polito.tdp.spellchecker.model;

import java.io.Serializable;

public class RichWord implements Serializable {

	public RichWord() {
		
	}
	
	private String parola;
	private boolean isCorretta;
	
	public RichWord(String parola, boolean isCorretta) {
		super();
		this.parola = parola;
		this.isCorretta = isCorretta;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public boolean isCorretta() {
		return isCorretta;
	}

	public void setCorretta(boolean isCorretta) {
		this.isCorretta = isCorretta;
	}



}
