package com.meli.mutant.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MatrixUtils {

	public static List<String> getColumns(List<String> dna) {
		List<String> columns = new ArrayList<>();
		for (int c = 0; c < dna.size();c++) {
			StringBuilder builder = new StringBuilder();
			for (int f = 0; f<dna.size();f++) {
				builder.append(dna.get(f).toCharArray()[c]);
			}
			columns.add(builder.toString());
		}
		
		return columns;
	}
	
	public static int countSeq(List<String> dna, int numConcurr) {
		int numSeq = 0;
		for (String string : dna) {
			int ind = 0;
			int cont = 0;
			char[] dnaCharArr = string.toCharArray();
			while(ind < string.length()-1) {
				if(dnaCharArr[ind] == dnaCharArr[ind+1]) {
					cont++;
				}else {
					cont = 0;
				}
				
				if(cont == numConcurr - 1) {
					numSeq++;
					cont=0;
				}
				ind++;
			}
		}
		
		return numSeq;
	}
	
	public static List<String> getLeftToRightDiagonals(List<String> dna, int minDiagonalLenght) {
		int indexDiagonalLenght = minDiagonalLenght - 1;
		List<String> seq = new ArrayList<>();
		int size = dna.size();
		for (int i = 0; i < size-indexDiagonalLenght; i++) {
			StringBuilder builder = new StringBuilder();
			int c = 0;
			for(int f = i; f < size ; f++) {
				builder.append(dna.get(f).toCharArray()[c]);
				c++;
			}
			seq.add(builder.toString());
		}
		
		for (int i = 1; i < size-indexDiagonalLenght; i++) {
			StringBuilder builder = new StringBuilder();
			int c = 0;
			for(int f = i; f < size ; f++) {
				builder.append(dna.get(c).toCharArray()[f]);
				c++;
			}
			seq.add(builder.toString());
		}
		
		return seq;
		
	}
	
	public static List<String> getRigthToLeftDiagonals(List<String> dna,int minDiagonalLenght) {
		int indexDiagonalLenght = minDiagonalLenght - 1;
		List<String> seq = new ArrayList<>();
		int size = dna.size();
		for (int i = size ; i > indexDiagonalLenght ; i--) {
			StringBuilder builder = new StringBuilder();
			int c = i - 1;
			for(int f = 0; f < size ; f ++) {
				builder.append(dna.get(f).toCharArray()[c]);
				c--;
			}
			size--;
			seq.add(builder.toString());
		}
		
		size = dna.size();
		int cont = 0;
		for (int i = size - 1 , j = 1; i > indexDiagonalLenght ; i--, j++) {
			StringBuilder builder = new StringBuilder();
			int c = j;
			size--;
			for(int f = dna.size()-1; f > cont ; f --) {
				builder.append(dna.get(c).toCharArray()[f]);
				c++;
			}
			cont++;
			seq.add(builder.toString());
		}
		return seq;
	}
	
	public static String oneLineMatrix(List<String> matrix) {
		StringBuilder builder = new StringBuilder();
		for (String string : matrix) {
			builder.append(string);
		}
		return builder.toString();
	}

}
