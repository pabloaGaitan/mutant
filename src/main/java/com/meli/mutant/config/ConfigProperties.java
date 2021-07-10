package com.meli.mutant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mutant") 
public class ConfigProperties {
	
	private int mutantNumConcurr;
	
	private int numSeqToBeMutant;
	
	private String permitedLetters;

	public int getMutantNumConcurr() {
		return mutantNumConcurr;
	}

	public void setMutantNumConcurr(int mutantNumConcurr) {
		this.mutantNumConcurr = mutantNumConcurr;
	}

	public String getPermitedLetters() {
		return permitedLetters;
	}

	public void setPermitedLetters(String permitedLetters) {
		this.permitedLetters = permitedLetters;
	}

	public int getNumSeqToBeMutant() {
		return numSeqToBeMutant;
	}

	public void setNumSeqToBeMutant(int numSeqToBeMutant) {
		this.numSeqToBeMutant = numSeqToBeMutant;
	}

}
