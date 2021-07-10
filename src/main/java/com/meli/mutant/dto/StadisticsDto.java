package com.meli.mutant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StadisticsDto {
	
	@JsonProperty("count_mutant_dna")
	private double countMutantDna;
	@JsonProperty("count_human_dna")
	private double countHumanDna;
	private double ratio;
	
	public double getCountMutantDna() {
		return countMutantDna;
	}
	public void setCountMutantDna(double countMutantDna) {
		this.countMutantDna = countMutantDna;
	}
	public double getCountHumanDna() {
		return countHumanDna;
	}
	public void setCountHumanDna(double countHumanDna) {
		this.countHumanDna = countHumanDna;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
	
}
